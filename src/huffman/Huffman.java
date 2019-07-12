package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Huffman class! This is an implementation of Huffman compression, a way to compress
 * large strings or text documents into a tree.
 * @author yuanningma
 *
 */
public class Huffman {
	
	private Map<Character, Integer> count;
	private Map<Character, String> codeMap;
	public Huffman() {
		count = new HashMap<Character, Integer>();
		codeMap = new HashMap<Character, String>();
	}
	
	// Just a test to see if it worked
	public void testPrint() {
		System.out.println("TESTING");
		Node n = new Node('a');
		Node n1 = new Node('b');
		Node n2 = new Node('c');
		 n.setLeft(n1);
		 n.setRight(n2);
		 System.out.println("n: " + n.getC());
		 System.out.println("n1: " + n1.getC());
		 System.out.println("n2: " + n2.getC());
		 TreePrinter.printNode(n);
		 System.out.println("DONE TESTING");
	}
	
	public String decodeMap(Node r, String toLook) {
		String chain = "";
		Node root = r;
		char[] arr = toLook.toCharArray();
		for (int i = 0; i < arr.length; i++){
			if (arr[i] == '0') {
				root = root.getLeft();
				if (root.getC() != '\0') {
					chain += root.getC();
					root = r;
				}
			} else if (arr[i] == '1') {
				root = root.getRight();
				if (root.getC() != '\0') {
					chain += root.getC();
					root = r;
				}
			}
		}
		System.out.println("ORIGINAL WAS: " + chain);
		return chain;
	}
	
	public DataWrapper encodeString(String s) {
		String str = s;
		char[] arr = str.toCharArray();
		for (char c: arr) {
			if (count.containsKey(c)) {
				count.put(c, count.get(c) + 1);
			} else {
				count.put(c,  1);
			}
		}
		
		// Construction of priority queue
		List<Node> nList = new ArrayList<Node>();
		PriorityQueue<Node> huffmanQueue = new PriorityQueue<Node>(new NodeComparator());
//		Queue<Node> llist = new LinkedList<Node>();
		for (Entry<Character, Integer> e: count.entrySet()) {
			Node n = new Node(e.getKey(), e.getValue());
			huffmanQueue.add(n);
			nList.add(n);
//			llist.add(n);
		}
		
		
		while (huffmanQueue.size() > 1) {
			System.out.println(huffmanQueue.size());
			Node n1 = huffmanQueue.poll();
			Node n2 = huffmanQueue.poll();
			Node newNode = new Node();

			newNode.setLeft(n1);
			n1.setParent(newNode);

			newNode.setRight(n2);
			n2.setParent(newNode);
			newNode.setWeight(n1.getWeight() + n2.getWeight());
			huffmanQueue.add(newNode);
			nList.add(newNode);
//			llist.add(newNode);
			
			
		}
		
		int max = -1;
		Node best = null;
		for (Node n: nList) {
			if (maxLevel(n) > max) {
				best = n;
			}
			System.out.println("Char: " + n.getC() + ", Count: "+n.getWeight());
		}
		TreePrinter.printNode(best);
		
		String so = "";
		Node n = best;
		codeMap = new HashMap<Character, String>();
		solvePaths(n, so);
//		System.out.println(so);
		for (Map.Entry<Character, String> entry: codeMap.entrySet()) {
			System.out.println("Char: " + entry.getKey() + ", Path: " + entry.getValue());
		}
		
		for (int i = 0; i < arr.length; i++) {
			so += codeMap.get(arr[i]);
		}
		return new DataWrapper(best, so);
	}
	
	public String solvePaths(Node n, String path) {
		if (n == null) {
			return path;
		} else if (n.getC() != '\0') {
			codeMap.put(n.getC(), path);
			return path;
		}
		
		String oldPath = path;
		path = solvePaths(n.getLeft(), oldPath + '0');
		path = solvePaths(n.getRight(), oldPath + '1');
		return path;
		
	}
	public int encode(String path) {
		
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str;
			// count map of all characters
			while ((str = br.readLine()) != null) {
				char[] arr = str.toCharArray();
				for (char c: arr) {
					if (count.containsKey(c)) {
						count.put(c, count.get(c) + 1);
					} else {
						count.put(c,  1);
					}
				}
				
				// Construction of priority queue
				PriorityQueue<Node> huffmanQueue = new PriorityQueue<Node>(new NodeComparator());
				for (Entry<Character, Integer> e: count.entrySet()) {
					Node n = new Node(e.getKey(), e.getValue());
					huffmanQueue.add(n);
				}
				
				while (huffmanQueue.size() > 1) {
					System.out.println(huffmanQueue.size());
					Node n1 = huffmanQueue.poll();
					Node n2 = huffmanQueue.poll();
					Node newNode = new Node();

					newNode.setLeft(n1);
					n1.setParent(newNode);

					newNode.setRight(n2);
					n2.setParent(newNode);
					newNode.setWeight(n1.getWeight() + n2.getWeight());
					huffmanQueue.add(newNode);
					
					
				}
				
			}
			
			br.close();
			return 1;
		} catch (FileNotFoundException e) {
			return -1;
		} catch (IOException e) {
			return -1;
		}
	}
	
	public int decode(String path) {
		
		return 1;
	}
	
	private class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			if (o1.getWeight() > o2.getWeight()) {
				return 1;
			} else if (o2.getWeight() > o1.getWeight()) {
				return -1;
			} else {
				return 0;
			}
		}
		
	}
	
	private static class TreePrinter {
		
		public static void printNode(Node root) {
			int maxLev = maxLevel(root);
			System.out.println("MAX LEV: " + maxLevel(root));
			
			printNodeInternal(Collections.singletonList(root), 1, maxLev);
		}
		
		private static void printNodeInternal(List<Node> nodes, int l, int lev) {
			if (nodes.isEmpty() || isAllNulls(nodes)) {
				return;
			}
			int floor = lev - l;
			int edgeLines = (int) Math.pow(2, Math.max(floor -1, 0));
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
	        printWhiteSpaces(firstSpaces);
	        
	        List<Node> newNodes = new ArrayList<Node>();
	        for (Node node: nodes) {
//	        	System.out.println("oogabooga");
	        	if (node != null) {
//	        		System.out.println("Not null!");
	        		System.out.print(node.getC());
//	                System.out.print(node.getC() + ":"+node.getWeight());
	                newNodes.add(node.getLeft());
	                newNodes.add(node.getRight());
	            } else {
	                newNodes.add(null);
	                newNodes.add(null);
	                System.out.print(" ");
	            }
	        	printWhiteSpaces(betweenSpaces);
	        }
	        System.out.println("");

	        for (int i = 1; i <= edgeLines; i++) {
	            for (int j = 0; j < nodes.size(); j++) {
	                printWhiteSpaces(firstSpaces - i);
	                if (nodes.get(j) == null) {
	                    printWhiteSpaces(edgeLines + edgeLines + i + 1);
	                    continue;
	                }

	                if (nodes.get(j).getLeft() != null)
	                    System.out.print("/");
	                else
	                    printWhiteSpaces(1);

	                printWhiteSpaces(i + i - 1);

	                if (nodes.get(j).getRight() != null)
	                    System.out.print("\\");
	                else
	                    printWhiteSpaces(1);

	                printWhiteSpaces(edgeLines + edgeLines - i);
	            }

	            System.out.println("");
	        }
//	        System.out.println("HI");

	        printNodeInternal(newNodes, l + 1, lev);
		}
		}
		
		public static int maxLevel(Node root) {
			if (root == null)
	            return 0;
			
//			System.out.println("hi");
	        return Math.max(maxLevel(root.getLeft()), maxLevel(root.getRight())) + 1;
		}
		
		public static void printWhiteSpaces(int num) {
			 for (int i = 0; i < num; i++)
		            System.out.print(" ");
		}
		
		public static boolean isAllNulls(List<Node> nodes) {
			for (Node n: nodes) {
				if (n != null) {
					return false;
				}
			}
			return true;
		}
	}
