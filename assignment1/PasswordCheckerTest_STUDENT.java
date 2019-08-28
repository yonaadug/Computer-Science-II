package assignment1;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Yonathan Kebede
 * 
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> passwords;
	
	@Before
	public void setUp() throws Exception {
		String[] pass = { "abcde", "Hello Worlddd", "12333 abec", "People 22",
				"abcde !!!ABC123", "HELLO WORLD 123"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(pass));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidPassword(passwords.get(0));
			assertTrue("It must a LengthException",false);
		}catch(LengthException e) {
			assertTrue("Threw a LengthException",true);
		}catch(Exception e) {
			assertTrue("Threw other exception. Must throw a LengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			PasswordCheckerUtility.isValidPassword(passwords.get(2));
			assertTrue("It must a NoUpperAlphaException",false);
		}catch(NoUpperAlphaException e) {
			assertTrue("Threw a NoUpperAlphaException",true);
		}catch(Exception e) {
			assertTrue("Threw other exception. Must throw a NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			PasswordCheckerUtility.isValidPassword(passwords.get(5));
			assertTrue("It must a NoUpperAlphaException",false);
		}catch(NoLowerAlphaException e) {
			assertTrue("Threw a NoLowerAlphaException",true);
		}catch(Exception e) {
			assertTrue("Threw other exception. Must throw a NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertTrue(PasswordCheckerUtility.isWeakPassword(passwords.get(3)));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			PasswordCheckerUtility.isValidPassword(passwords.get(4));
			assertTrue("It must a InvalidSequenceException",false);
		}catch(InvalidSequenceException e) {
			assertTrue("Threw a InvalidSequenceException",true);
		}catch(Exception e) {
			assertTrue("Threw other exception. Must throw a InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			PasswordCheckerUtility.isValidPassword(passwords.get(1));
			assertTrue("It must a NoDigitException",false);
		}catch(NoDigitException e) {
			assertTrue("Threw a NoDigitException",true);
		}catch(Exception e) {
			assertTrue("Threw other exception. Must throw a NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(PasswordCheckerUtility.isValidPassword("123456aB"));
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testValidPasswords() {
		ArrayList<String> results;
		String resultNext;
		Scanner scan;
		
		results = PasswordCheckerUtility.validPasswords(passwords);
		
		scan = new Scanner(results.get(0));
		resultNext = scan.nextLine();
		assertTrue(resultNext.contains("abcde") && resultNext.contains("at least 6"));
		scan.close();
		
		
		scan = new Scanner(results.get(1));
		resultNext = scan.nextLine();
		assertTrue(resultNext.contains("Hello Worlddd") && resultNext.contains("digit"));
		scan.close();
		
		scan = new Scanner(results.get(2));
		resultNext = scan.nextLine();
		assertTrue(resultNext.contains("12333 abec") && resultNext.contains("upper"));
		scan.close();
		
		scan = new Scanner(results.get(3));
		resultNext = scan.nextLine();
		assertTrue(resultNext.contains("abcde !!!ABC123") && resultNext.contains("more than two"));
		scan.close();
		
		scan = new Scanner(results.get(4));
		resultNext = scan.nextLine();
		assertTrue(resultNext.contains("HELLO WORLD 123") && resultNext.contains("lower"));
		scan.close();
		
	}
	
}
