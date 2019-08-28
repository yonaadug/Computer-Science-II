package assignment2;

import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

	private T[] queue;
	private int size;
	private int capacity;
	
	public MyQueue() {
		// TODO Auto-generated constructor stub
		queue = (T[]) new Object[10];
		capacity = 10;
		size = 0;
		
	}
	
	public MyQueue(int i) {
		// TODO Auto-generated constructor stub
		queue = (T[]) new Object[i];
		capacity = i;
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size==0);
	}


	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (size == capacity);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		
		if(size==0)
			throw new QueueUnderflowException();
		
		
		T dequeueObj = queue[0];
		/*queue = Arrays.copyOfRange(queue, 1, size-1);
		queue = Arrays.copyOf(queue,capacity);*/
		
		for(int i=1; i<size; i++) {
			queue[i-1] = queue[i];
		}
		
		size--;
		return dequeueObj;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		// TODO Auto-generated method stub
		
		if(size == capacity)
			throw new QueueOverflowException();
		
		queue[size] = e;
		size++;
		return true;
	}

	@Override
	public String toString(String delimiter) {
		// TODO Auto-generated method stub
		
		if (size==0)
			return "";
		
		String string = queue[0].toString();
		
		for(int i= 1; i< size; i++) {
			
			string+=delimiter;
			string+=queue[i];
		}
		
		return string;
	}

	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		// TODO Auto-generated method stub
		if(list.size() == capacity)
			throw new QueueOverflowException();
		
		
		for(T obj: list) {
			
			if(size == capacity) {
				throw new QueueOverflowException();
			
			}else {			
				queue[size++] =  obj;
			}
		}
	}
	
	@Override
	public String toString() {
		String string = "";
		
		for(int i=0; i< size; i++) {
			string += queue[i].toString();
		}
		
		return string;
	}

}
