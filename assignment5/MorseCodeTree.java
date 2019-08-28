package assignment5;

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	
	private TreeNode<String> root;

	public MorseCodeTree() {
		buildTree();
	}
	
	public void buildTree() {
		root = new TreeNode<String>("");
		
		//Level 1
		insert(".", "e");
		insert("-", "t");
		
		//Level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		//Level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		//Level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
		
	public TreeNode<String> getRoot(){
		return root;
	}
	
	/**
	 * 
	 */
	public MorseCodeTree insert(String code, String letter) {
		
		addNode(root,code,letter);
		
		return this;
	}
	/**
	 * 
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		
		String letter;
		
		if(code.length()<=1) {
			if(code.equals("."))
				return root.lc.data;
			else
				return root.rc.data;
		}else {
			if(code.charAt(0) == '.')
				letter = fetchNode(root.lc, code.substring(1));
			else //if(code.charAt(0) == '-')
				letter = fetchNode(root.rc, code.substring(1));
		}
		return letter;

	}
	
	/**
	 * 
	 */
	public String fetch(String code) {
		return fetchNode(root, code);
		
	}
	
	
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		if(code.length() == 1) {
			if(code.charAt(0) == '.') {
				root.lc = new TreeNode<String>(letter);
			}else if(code.charAt(0) == '-'){
				root.rc = new TreeNode<String>(letter);
			}
			return;
		} else {
			
			//Recursive section that replaces root with root.lc/rc and
			//first character of code removed in the recursion
			if(code.charAt(0) == '.')
				addNode(root.lc, code.substring(1),letter);
			else if(code.charAt(0) == '-')
				addNode(root.rc, code.substring(1), letter);
			
		}
		
	}
	
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root.lc != null)
			LNRoutputTraversal(root.lc,list);  // traverse the entire left subtree
		
		//System.out.println(root.data);  // output the root node
		list.add(root.data);
		
		if(root.rc != null)
		   LNRoutputTraversal(root.rc,list);  // traverse the entire right subtree

	}
	
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;			
	}
	
	
	public ArrayList<String> toArrayList() {
		
		ArrayList<String> arrList = new ArrayList<String>();
		LNRoutputTraversal(root, arrList);	
		
		return arrList;
	}
	
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
		
	}
	
}
