package assignment1;
/**
 * Exception for more than 2 repeating consecutive characters
 * @author Yonathan Kebede
 *
 */
public class InvalidSequenceException extends RuntimeException{

	public InvalidSequenceException() {
		
	}
	
	public InvalidSequenceException(String message) {
		super(message);
	}
	
	
	
}
