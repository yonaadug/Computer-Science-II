package assignment1;


/**
 * Exception for no lower case alphabet character in password
 * @author Yonathan Kebede
 *
 */
public class NoLowerAlphaException extends RuntimeException{
	
	public NoLowerAlphaException() {
		
	}
	
	public NoLowerAlphaException(String message) {
		super(message);
	}

}
