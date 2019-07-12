package huffman;

public class Node {
	
	private char c;
	private int weight;
	private String code;
	private Node left;
	private Node right;
	private Node parent;
	
	public Node() {
		this.c = '\0';
		this.weight = 0;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.code = "";
	}
	
	public Node(char c, int weight) {
		this.c = c;
		this.weight = weight;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.code = "";
	}
	
	public Node(char c) {
		this.c = c;
		this.weight = 0;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.code = "";
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLeft(Node l) {
		this.left = l;
	}
	
	public void setRight(Node r) {
		this.right = r;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public void setParent(Node p) {
		this.parent = p;
	}
	
	public Node getParent() {
		return this.parent;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
