package assignment1;

/**
 * Exception for no upper case alphabet character in password
 * @author Yonathan Kebede
 *
 */
public class NoUpperAlphaException extends RuntimeException {
	
	public NoUpperAlphaException() {
		
	}
	
	public NoUpperAlphaException(String message) {
		super(message);
	}
	

}
