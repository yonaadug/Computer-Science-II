package assignment1;
/**
 * An exception when the "password" entry and "retype password" entry
 * are not the same
 * @author Yonathan Kebede
 *
 */
public class UnmatchedException extends RuntimeException {

	public UnmatchedException() {
		
	}

	public UnmatchedException(String arg0) {
		super(arg0);
		
	}

}
