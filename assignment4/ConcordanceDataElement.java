package assignment4;

import java.util.Iterator;
import java.util.LinkedList;

public class ConcordanceDataElement implements Comparable<ConcordanceDataElement> {

	private String word;
	private LinkedList<Integer> lineNumbers;
	
	
	public ConcordanceDataElement(String word) {
		this.word = word;
		lineNumbers = new LinkedList<Integer>()	;
		
	}
	
	public String toString() {
		Iterator<Integer> itr = lineNumbers.iterator();
		String str = ""+word+": " + (itr.hasNext()?itr.next():"");
		
			
		while(itr.hasNext()) {
			str += ", "+itr.next();
		}
		
		return str;
		
	}
	
	public String getWord() {
		return word;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Integer> getList(){
		return (LinkedList<Integer>) lineNumbers.clone();
	}
	
	
	public void addPage(int lineNum) {
		
		if(!lineNumbers.contains(lineNum)) {
			lineNumbers.add(lineNum);
		}
	}
	
	public int compareTo(ConcordanceDataElement arg0) {
		
		return this.word.compareToIgnoreCase(arg0.getWord());
	}
	
	public int hashCode() {
		return word.hashCode();
	}
	
	
	
	
	
}
