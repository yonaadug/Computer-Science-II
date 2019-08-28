package assignment1;

import java.util.ArrayList;

/**
 * This class has static functions that work with a password to check its validity 
 * or to see if it's weak.
 * @author Yonathan Kebede
 *
 */
public class PasswordCheckerUtility {
	
	
	/**
	 * Return true if the password is a valid password
	 * @param passwordString - Password string
	 * @return It returns true if it is valid password and false otherwise
	 * @throws LengthException - If length is less than 6 characters in the password
	 * @throws NoDigitException - If there is no digit in the password
	 * @throws NoUpperAlphaException - If there is no upper case letter in the password
	 * @throws NoLowerAlphaException - If there is no lower case letter in the password
	 * @throws InvalidSequenceException - If there is a 3 consecutively repeating characters in the password
	 */
	public static boolean isValidPassword (String passwordString) throws LengthException, 
					NoDigitException, NoUpperAlphaException, NoLowerAlphaException, 
				InvalidSequenceException {
		
		//Check first for length then for other password requirements
		if ( passwordString.length() < 6 ) {
			throw new LengthException("The password must be at least 6 characters long.");
		}else {
			
			
			boolean hasDigit = false, hasAlphaCase = false, hasLowerCase = false, invalidSequence = true;
			
			char currentChar;
			
			for(int i = 0; i<passwordString.length(); i++) {
				
				currentChar = passwordString.charAt(i);
				
				//Check for digit
				if(Character.isDigit(currentChar))
					hasDigit = true;
				
				//Check for uppercase
				if(Character.isUpperCase(currentChar))
					hasAlphaCase = true;
				
				//Check for lowercase
				if(Character.isLowerCase(currentChar))
					hasLowerCase = true;			
				
				// Look for repeated characters in three times in a sequence
				if(passwordString.contains(""+ currentChar + currentChar + currentChar)&&invalidSequence)
					invalidSequence = false;
								
			}
			
			//Throws exceptions based on requirement that is not filled.
			if(!hasDigit) {
				throw new NoDigitException("The password must contain at least one digit.");
			} else if(!hasAlphaCase) {
				throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character.");
			} else if(!hasLowerCase) {
				throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character.");
			} else if(!invalidSequence) {
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
			}
			
		}
		
		return true;	
	}
	
	
	/**
	 * It determines if password is a weak password or not
	 * @param passwordString - Password string
	 * @return Returns false if password is not a password
	 */
	public static boolean isWeakPassword(String passwordString) {
		
		if(passwordString.length()>5 && passwordString.length()<10)
			return true;
		
		return false;
	}
	
	
	/**
	 * Determines if passwords in a list is valid or not
	 * @param passwords - An array of passwords
	 * @return - Returns the an array of invalid passwords with their respective errors
	 */
	public static ArrayList<String> validPasswords(ArrayList<String> passwords){
		
		//ArrayList for invalid passwords and their exceptions
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		
		for(String password: passwords) {
			
			//Get passwords that are invalid and their respective exceptions
			try {
				isValidPassword(password);
			}catch(Exception e) {
				//Add invalid passwords and their respective exception message to new arraylist
				invalidPasswords.add(password+" "+e.getMessage());
			}
		}
		
		
		return invalidPasswords;

	}
	
	
	

}
