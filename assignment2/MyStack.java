package assignment2;

import java.util.ArrayList;

import com.sun.glass.ui.View.Capability;

public class MyStack<T> implements StackInterface<T> {

	private T[] stack;
	private int size;
	private int capacity;
	
	/**
	 * No-Arg Constructor creates a stack of size 10 by
	 * default.
	 */
	public MyStack() {
		stack = (T[]) new Object[10];
		capacity = 10;
		size = 0;
	}
	
	/**
	 * Constructor that makes a stack with a capacity
	 * specified by the user
	 * @param i
	 */
	public MyStack(int i) {
		// TODO Auto-generated constructor stub
		stack = (T[]) new Object[i];
		capacity = i;
		size = 0;
	}

	/**
	 * Checks if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size==0);
	}

	/**
	 * Checks if stack is full
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (size == capacity);
	}

	/**
	 * Pop method for stack
	 */
	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		
		if(size == 0) {
			throw new StackUnderflowException();
		} 
	
		T obj = stack[size-1];
		stack[size-1] = null;
		size--;
		return obj;
	}

	/**
	 * Peek method for stack
	 */
	@Override
	public T peek() throws StackUnderflowException {
		// TODO Auto-generated method stub
		
		if(size == 0)
			throw new StackUnderflowException();
		
		return stack[size-1];
	}

	/**
	 * Returns size of stack
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Push method for stack
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		// TODO Auto-generated method stub
		
		if(size == capacity) {
			throw new StackOverflowException();
		}
		
		stack[size] = e;
		size ++;
		
		return true;
	}

	@Override
	public String toString(String delimiter) {
		// TODO Auto-generated method stub
		
		if (size==0)
			return "";
		
		String string = stack[0].toString();
		
		for(int i= 1; i< size; i++) {
			
			string+=delimiter;
			string+=stack[i];
		}
		
		return string;
	}

	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		// TODO Auto-generated method stub
		if(list.size() == capacity)
			throw new StackOverflowException();
		
		
		for(T obj: list) {
			
			if(size == capacity) {
				throw new StackOverflowException();
			
			}else {			
				stack[size++] =  obj;
			}
		}
	}
	
	@Override
	public String toString() {
		String string = "";
		
		for(int i=0; i< size; i++) {
			string += stack[i].toString();
		}
		
		return string;
	}
	

}
