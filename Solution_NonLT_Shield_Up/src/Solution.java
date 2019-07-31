import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

	/*
	sample_input_small.txt output
	#1 4 
	#2 7 
	#3 8 
	
	sample_input.txt output
	#1 0 
	#2 1046 
	#3 252 
	#4 85 
	#5 75167 
	#6 39366 
	#7 15326 
	#8 79846238 
	#9 7676968 
	#10 3213263 

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
				String[] inputNM = br.readLine().split(" ");
				int N = Integer.parseInt(inputNM[0]);
				int M = Integer.parseInt(inputNM[1]);
				
				Node[][] NM = new Node[N][M];
				
				for(int i=0; i<N; i++){
					String inputRow = br.readLine();
					for(int j=0; j<M; j++) {
						NM[i][j] = new Node(i,j,Character.getNumericValue(inputRow.charAt(j)));
					}
				}
				
				printMatrix(NM);
				
				findNearestEnemyShip(NM, null);
				
				ANSWER = 0;
				
				//System.out.println("#" + testCase + " " + ANSWER);
			}
				
			System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
		}
		
		static void findNearestEnemyShip(Node[][] NM, Node currentNode) {
			traverseNode(NM);
		}
		
		public static int traverseNode(Node[][] matrix) {
			List<Node> queue = new ArrayList<Node>();
			queue.add(matrix[1][1]);
			
			while(!queue.isEmpty()) {
				Node current = queue.remove(0);
				
				System.out.println("(" + current.x + "," + current.y + ")");
				
				matrix[current.x][current.y].isVisted = true; // mark as visited
				
				//printMatrix(matrix);
				
				List<Node> neighbors = getNeighbors(matrix, current);
				queue.addAll(neighbors);
			}
			
			return 0;
		}
		
		public static List<Node> getNeighbors(Node[][] matrix, Node node) {
			List<Node> neighbors = new ArrayList<Node>();
			
			if(isValidPoint(matrix, node.x-1,node.y)) {
				neighbors.add(matrix[node.x-1][node.y]);
			}
			
			if(isValidPoint(matrix, node.x+1,node.y)) {
				neighbors.add(matrix[node.x+1][node.y]);
			}
			
			if(isValidPoint(matrix, node.x, node.y-1)) {
				neighbors.add(matrix[node.x][node.y-1]);
			}
			
			if(isValidPoint(matrix, node.x,node.y+1)) {
				neighbors.add(matrix[node.x][node.y+1]);
			}
			
			return neighbors;
		}
		
		public static boolean isValidPoint(Node[][] matrix, int x, int y) {
			return (x >= 0 && x < matrix[0].length && y >= 0 && y < matrix.length) 
					&& (!matrix[x][y].isVisted)
					&& (matrix[x][y].data != 0);
		}

		static class Node {
			int x;
			int y;
			int data;
			boolean isVisted;
			
			public Node (int x, int y, int data) {
				this.x = x;
				this.y = y;
				this.data = data;
				this.isVisted = false;
			}
		}

		// Helper function to print Matrix
		static void printMatrix(Node[][] matrix) {
			for (Node[] mArray : matrix) {
				for(Node n : mArray) {
					System.out.print(n.data);
				}
				System.out.println();
			}
		}

	}



