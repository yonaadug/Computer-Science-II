package assignment5;


/**
 * This generic class is used in the MorseCodeTree classes. 
 * The class consists of a reference to the data and a reference
 * to the left and right child.  Follow the Javadoc that is
 * provided.   You may add any 
 * private methods you need for your design.
 * @author Yonathan Kebede
 *
 * @param <T>
 */
public class TreeNode<T> {
	
	protected T data;	
	protected TreeNode<T> lc;	
	protected TreeNode<T> rc;
	
	public TreeNode (T dataNode) {	
		data = dataNode;
		lc = rc = null;
	}

	public T getData() {
		return data;
	}
	
	
	/**/
	public TreeNode (TreeNode<T> node) {
		this.data = node.data;
		this.lc = node.lc;
		this.rc = node.rc;
		
	}

}
