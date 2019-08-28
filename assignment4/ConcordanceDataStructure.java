package assignment4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ConcordanceDataStructure implements ConcordanceDataStructureInterface {

	private LinkedList<ConcordanceDataElement>[] table;
	private int size;
	
	
	@SuppressWarnings("unchecked")
	public ConcordanceDataStructure(int num) {
		// TODO Auto-generated constructor stub
		size = nextPrime((int)(num/1.5));
		table = new LinkedList[size];
	}

	@SuppressWarnings("unchecked")
	public ConcordanceDataStructure(String test, int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		table = new LinkedList[size];
		
		
	}

	@Override
	public int getTableSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public ArrayList<String> getWords(int index) {
		// TODO Auto-generated method stub
		
		//ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		LinkedList<ConcordanceDataElement> list = table[index];
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i).getWord());
		}
		return temp;
		
	}

	@Override
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		// TODO Auto-generated method stub
		ArrayList<LinkedList<Integer>> temp = new ArrayList<LinkedList<Integer>>();
		LinkedList<ConcordanceDataElement> list = table[index];
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i).getList());
		}
		return temp;
		
	}
	
	/*	Use the hashcode of the ConcordanceDataElement to see if it is in the hashtable.
		If the word does not exist in the hashtable - Add the ConcordanceDataElement to 
		the hashtable. Put the line number in the linked list If the word already exists
		in the hashtable 1. add the line number to the end of the linked list in the 
		ConcordanceDataElement (if the line number is not currently there).
	*/
	@Override
	public void add(String word, int lineNum) {
		// TODO Auto-generated method stub
		
		
		
		int index;
		boolean added = false;
		ConcordanceDataElement element = new ConcordanceDataElement(word.toLowerCase());
		index = Math.abs(element.hashCode() % size);
		if (table[index] != null) {
			for (int i = 0; i < table[index].size(); i++) {
				if (table[index].get(i).compareTo(element) == 0) {
					table[index].get(i).addPage(lineNum);
					added = true;
				}
			}
		} else {
			LinkedList<ConcordanceDataElement> listAdd = new LinkedList<ConcordanceDataElement>();
			listAdd.add(element);
			table[index] = listAdd;
			table[index].getFirst().addPage(lineNum);
			added = true;
		}
		if (added == false) {
			table[index].add(element);
			table[index].getLast().addPage(lineNum);
		}

	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> orderedWords = new ArrayList<String>();
		
		for(LinkedList<ConcordanceDataElement> wordList: table) {
			
			if(wordList != null) {
				
				for(ConcordanceDataElement concordance: wordList) {
					
					if(concordance.getWord()!=null)
						orderedWords.add(concordance.toString());
					
				}
			}
		}
		
		Collections.sort(orderedWords);//.sort(String::compareToIgnoreCase);
		
		return orderedWords;
	}
	
	
	private int nextPrime(int n) 
    { 
		@SuppressWarnings("unused")
		double k;
        BigInteger b = new BigInteger(String.valueOf(n)); 
        int prime;
        prime = Integer.parseInt(b.nextProbablePrime().toString()); 
    	k = (prime-3.0)%4.0;
        
    	//(prime-3.0)%4.0!=0
    	//(k-((int)k))!=0
    	while((prime-3.0)%4.0!=0) {
        	b = new BigInteger(String.valueOf(prime+1)); 
        	prime = Integer.parseInt(b.nextProbablePrime().toString()); 
        	k = (prime-3.0)/4.0;
        	
        }
    	return prime;
    } 
	
	
	
	

}
