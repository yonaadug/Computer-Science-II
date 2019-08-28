package assignment2;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStackTest {
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill;
	
	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	// STUDENT: add variables as needed for your student tests
	
	@Before
	public void setUp() throws Exception {
		fill = new ArrayList<String>();
		stringS = new MyStack<String>(5);
		doubleS = new MyStack<Double>(4);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
	}

	@After
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
		fill = null;
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		stringS.pop();
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertEquals(false, stringS.isFull());
		stringS.push(d);
		stringS.push(e);
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}

	@Test
	public void testPopStudent() {
		//Use the doubleQ for student tests
		try {
			doubleS.pop();
		}catch(StackUnderflowException e) {
			assertTrue("This should have caused an StackUnderflowException", true);
			
			try {
				doubleS.push(50.0);
				assertTrue(doubleS.size() == 1);
				assertTrue(doubleS.pop().equals(50.0));
				assertTrue(doubleS.size() == 0);
				doubleS.pop();
			}catch (StackUnderflowException e1) {
				assertTrue("This should have caused an StackUnderflowException", true);
			}catch (Exception e1) {
				assertTrue("This should have caused an StackUnderflowException", false);
			}		
			
		}catch(Exception e) {
			assertTrue("This should have caused an StackUnderflowException", false);
		}

	}
	
	@Test
	public void testPeek() {
		try{
			assertEquals(c, stringS.peek());
			stringS.push(d);
			assertEquals(d, stringS.peek());
			stringS.pop();
			stringS.pop();
			assertEquals(b, stringS.peek());	
		}
		catch (StackUnderflowException e){
			assertTrue("This should not have caused an StackUnderflowException", false);
		}
		catch (Exception e){
			assertTrue("This should not have caused an Exception", false);
		}
	}

	@Test
	public void testSize() {
		assertEquals(3, stringS.size());
		stringS.push(d);
		assertEquals(4, stringS.size());
		stringS.pop();
		stringS.pop();
		assertEquals(2, stringS.size());
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}

	@Test
	public void testPushStudent() {
		//Use the doubleQ for student tests
		try {
			assertTrue(doubleS.push(3.141));
			assertTrue(doubleS.push(2.718));
			assertTrue(doubleS.push(0.0));
			assertTrue(doubleS.push(1.0));
			doubleS.push(100.5);
			assertTrue("This should have caused an StackOverflowException", false);
		
		}catch(StackOverflowException e) {
			assertTrue("This should have caused an StackOverflowException", true);
			
		}catch(Exception e) {
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}
	
	@Test
	public void testToString() {
		assertEquals("abc", stringS.toString());
		stringS.push(d);
		assertEquals("abcd", stringS.toString());
		stringS.push(e);
		assertEquals("abcde", stringS.toString());
	}

	@Test
	public void testToStringStudent() {
		//Use the doubleQ for student tests
		doubleS.push(1.0);
		doubleS.push(2.718);
		doubleS.push(3.142);
		doubleS.push(0.0);
		
		assertEquals("1.02.7183.1420.0",doubleS.toString());
		assertEquals("1.0/2.718/3.142/0.0",doubleS.toString("/"));
		
	}
	
	@Test
	public void testToStringDelimiter() {
		assertEquals("a%b%c", stringS.toString("%"));
		stringS.push(d);
		assertEquals("a&b&c&d", stringS.toString("&"));
		stringS.push(e);
		assertEquals("a/b/c/d/e", stringS.toString("/"));
	}

	@Test
	public void testFill() {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new MyStack<String>(5);
		//fill with an ArrayList
		stringS.fill(fill);
		assertEquals("carrot", stringS.pop());
		assertEquals("banana", stringS.pop());
		assertEquals("apple", stringS.pop());		
	}
	@Test
	public void testFillFull() {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		fill.add("delish");
		fill.add("eggplant");
		fill.add("fresh");
		//start with an empty queue
		
		//fill with an ArrayList
		//the Arraylist is larger than the capacity of the Stack, throws exception
		try{
			stringS.fill(fill);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}
}
