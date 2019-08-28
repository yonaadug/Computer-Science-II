package assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree morseCode = new MorseCodeTree();

	public static String convertToEnglish(String code) {
		// TODO Auto-generated method stub
		String[] words = code.split(" / ");
		String[] letters;
		String decryptedStr = "";
		
		for(String word: words) {
			
			//Make array of letters
			letters = word.split(" ");
			//Decrypt each letter and add to string
			for(String codeLetter: letters) {
				decryptedStr +=morseCode.fetch(codeLetter);
			}
			
			decryptedStr += " ";

		}

		return decryptedStr.trim();
	}

	public static String printTree() {
		// TODO Auto-generated method stub
		
		ArrayList<String> tree = morseCode.toArrayList();
		String str = "";
		
		for(String letter: tree) {
			str += letter + " ";
		}
		
		
		return str.trim();
	}

	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(codeFile);
		String fileString = "";
		
		while(sc.hasNextLine())
			fileString += sc.nextLine() + "\n"; 
		
		sc.close();
		
		return convertToEnglish(fileString.trim());
		
	}

}
