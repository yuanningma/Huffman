package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import huffman.DataWrapper;
import huffman.Huffman;

public class Main {
	public static void main(String[] args) throws IOException {
		
		new Main(args).run();
	}
	
	private Main(String[] args) {

	}
	 
	public void run() throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String line;
		 Huffman h = new Huffman();
		 
//		 h.testPrint();
		 while ((line = br.readLine()) != null) {
			 DataWrapper encoded = h.encodeString(line);
			 h.decodeMap(encoded.getN(), encoded.getP());
		 }
	}
}
