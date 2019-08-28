package assignment1;

/**
 * Exception for short length password
 * @author Yonathan Kebede
 *
 */
public class LengthException extends RuntimeException {

	public LengthException() {
		
	}
	
	public LengthException(String message) {
		super(message);
	}
	
	
}
