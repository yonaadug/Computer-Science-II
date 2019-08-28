package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ConcordanceDataManager implements ConcordanceDataManagerInterface {
	
	private ConcordanceDataStructure data;
	
	

	@Override
	public ArrayList<String> createConcordanceArray(String input) {
		// TODO Auto-generated method stub
		
		int lineNumber = 0;
		int size;
		String line;
		Scanner wordScan;
		Scanner lineScan;
		String word;
		String newInput = input.replaceAll("[,.!?\"_]", "").toLowerCase();
		lineScan = new Scanner(newInput);

		size =newInput.split(" ").length;
		
		data = new ConcordanceDataStructure(size);
		
		
		while(lineScan.hasNext()) {
			
			lineNumber++;
			line = lineScan.nextLine();
			wordScan = new Scanner(line);
						
			while(wordScan.hasNext()) {
				word = wordScan.next();
				if(!(word.equals("and")||word.equals("the")||word.length()<3))
					data.add(word,lineNumber);
			}
			wordScan.close();
		}
		
		lineScan.close();
		
		
		return data.showAll();
	}

	@Override
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		// TODO Auto-generated method stub
	
		
		StringBuilder fileString = new StringBuilder("");
		Scanner lineScan = new Scanner(input);
		ArrayList<String> dataArr;
				
		while(lineScan.hasNext()) {
			fileString.append(lineScan.nextLine().toLowerCase()+"\n");
		}
		
		dataArr = createConcordanceArray(fileString.toString());
		lineScan.close();
		//System.out.println(dataArr);
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(output);
			PrintWriter printWriter = new PrintWriter(fileWriter);
		    
		    for(String str: dataArr) {
		    	printWriter.print(str+"\n");
		    }
		    
		    printWriter.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException thrown");
		}
	    
	    
		
		
		return true;
	}
}
