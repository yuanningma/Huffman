package huffman;

public class DataWrapper {
	private Node n;
	private String p;
	public DataWrapper(Node n, String p) {
		this.n = n;
		this.p = p;
	}
	public Node getN() {
		return n;
	}
	public void setN(Node n) {
		this.n = n;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
}