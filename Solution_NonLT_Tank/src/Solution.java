import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

	/*
	sample_input_small.txt output
	#1 20 
	#2 77 
	
	sample_input.txt output
	#1 167436 
	#2 166417 
	#3 182761 
	#4 669232868 
	#5 1425634765276 
	#6 443318195116 
	#7 1787841655257 
	#8 47548858537543 
	#9 25678408034773 
	#10 34362071934741

	*/
	class Solution
	{
		static long tStart = 0;
		static int T;
		static long ANSWER;
		
		public static void main(String args[]) throws Exception
		{
			//System.setIn(new FileInputStream("sample_input.txt"));
			//System.setIn(new FileInputStream("sample_input_small.txt"));
			System.setIn(new FileInputStream("sample_input_big.txt"));
			
			tStart = System.currentTimeMillis();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			T = Integer.parseInt(br.readLine().trim());
			for(int testCase=1; testCase<=T; testCase++){
				int N = Integer.parseInt(br.readLine().trim());
				String[] sInput;
				//List<Node> nodeListByX = new ArrayList<>();
				//Node[] nodeArrayByX = new Node[N];
				TreeSet<Node> xNodeTree = new TreeSet<Node>(new SortByNodeXDesc());
				
				for(int i=0; i<N; i++){
					sInput = br.readLine().split(" ");
					//nodeListByX.add(new Node(Integer.parseInt(sInput[0]), Integer.parseInt(sInput[1]), Integer.parseInt(sInput[2])));
					//nodeArrayByX[i] = new Node(Integer.parseInt(sInput[0]), Integer.parseInt(sInput[1]), Integer.parseInt(sInput[2]));
					xNodeTree.add(new Node(Integer.parseInt(sInput[0]), Integer.parseInt(sInput[1]), Integer.parseInt(sInput[2])) );
				}
				
//				Collections.sort(nodeListByX, new Comparator<Node>(){
//					@Override
//					public int compare(Node o1, Node o2) {
//						return o1.x - o2.x;
//					}
//					
//				});
				
				//Arrays.sort(nodeArrayByX, new SortByNodeXDesc());
				
//				for(Node n : nodeListByX) {
//					System.out.println(n.x + " " + n.y + " " + n.data);
//				}
				
//				for(Node n : nodeArrayByX) {
//					System.out.println(n.x + " " + n.y + " " + n.data);
//				}
				
				Iterator<Node> xItr = xNodeTree.iterator();
				
				// Instantiate TreeSet
				TreeSet<Node> yNodeTree = new TreeSet<Node>(new SortByNodeYDesc());
				
				ANSWER = 0;
				
				// 1st element will alway not have higher y, so just add
				//nodeTree.add(nodeArrayByX[0]);
				yNodeTree.add(xItr.next());
				
				while (xItr.hasNext()) {
					//Node currNode = nodeArrayByX[i];
					Node currNode = xItr.next();
					
					Node tmpNode = yNodeTree.lower(currNode);
					
					if (tmpNode != null) {
						// find subset with higher y's
						ANSWER += getSubSetSum((TreeSet<Node>) yNodeTree.subSet(yNodeTree.first(), true, tmpNode, true));
					} 
					
					yNodeTree.add(currNode);
					
				}
				
				System.out.println("#" + testCase + " " + ANSWER);
			}
				
			System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
		}

		static class Node {
			int x;
			int y;
			long data;
			
			public Node (int x, int y, int data) {
				this.x = x;
				this.y = y;
				this.data = data;
			}
		}
		
		static class SortByNodeXDesc implements Comparator<Node> {
			public int compare(Node n1, Node n2) {
				return n2.x - n1.x;
//				if (n1.x < n2.x) return -1;
//				else if (n1.x == n2.x) return 0;
//				else return 1;
			}
		}
		
		static class SortByNodeYDesc implements Comparator<Node> {
			public int compare(Node n1, Node n2) {
				return n2.y - n1.y;
			}
		}

		private static long getSubSetSum(TreeSet<Node> nodeTree) {
			
			long runningSum = 0;
			Iterator<Node> itr = nodeTree.iterator();
			
			while (itr.hasNext()) {
				runningSum += itr.next().data;
			}
			
			return runningSum;
		}
		
		// Helper function to print TreeSet
		static void printTree(TreeSet<Node> tree) {
			for (Node n : tree) {
				System.out.println(n.x + " " + n.y + " " + n.data + " ");
			}
		}

	}



