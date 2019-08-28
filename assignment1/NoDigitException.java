package assignment1;

/**
 * Exception for no digit in password
 * @author Yonathan Kebede
 *
 */
public class NoDigitException extends RuntimeException{
	
	
	public NoDigitException() {
		
	}
	
	public NoDigitException(String message) {
		super(message);
	}
}
