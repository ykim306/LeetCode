import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

	/*
	sample_input_small.txt output 
	#1 -1
	#2 1

	*/
	class Solution
	{
		static long tStart = 0;
		static int T;
		static int V;
		static int K;
		static LinkedList<Integer>[] adj;
		static boolean[] visited;
		static boolean[] cycle;
		static int[] kAnswer;
		static int kCounter;
		static boolean isCyclic;
		static int ANSWER;
		
		public static void main(String args[]) throws Exception
		{
			//System.setIn(new FileInputStream("sample_input.txt"));
			System.setIn(new FileInputStream("sample_input_small.txt"));
			//System.setIn(new FileInputStream("sample_input_big.txt"));
			
			tStart = System.currentTimeMillis();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			T = Integer.parseInt(br.readLine().trim());
			for(int testCase=1; testCase<=T; testCase++){
				ANSWER = 0;
				
				String[] inputNQ = br.readLine().split(" ");
				int N = Integer.parseInt(inputNQ[0]);
				int M = Integer.parseInt(inputNQ[1]);
				K = Integer.parseInt(inputNQ[2]);
				
				Graph g = new Graph(N);
				
				for (int i = 0; i < M; i++) {
					String[] inputAB = br.readLine().split(" ");
					int a = Integer.parseInt(inputAB[0]);
					int b = Integer.parseInt(inputAB[1]);
					
					addEdge(a, b);
				}
				
//				for (int i=0; i<N+1; i++) {
//					System.out.print(i);
//					for (Integer j : adj[i]) {
//						System.out.print(" " + j);
//					}
//					System.out.println();
//				}
				
				tSort();
				
				System.out.println("#" + testCase + " " + kAnswer[kCounter]);
			}
				
			System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
		}
		
		static class Graph {
			public Graph(int v) {
				adj = new LinkedList[v+1];
				visited = new boolean[v+1];
				cycle = new boolean[v+1];
				kAnswer = new int[v+1];
				
				kCounter = 0;
				isCyclic = false;
				
				for (int i = 0; i < adj.length; i++) {
					adj[i] = new LinkedList<Integer>();
					visited[i] = false;
					cycle[i] = false;
					kAnswer[i] = -1;
				}
			}
		}
		
		static void addEdge(int a, int b) {
			adj[a].add(b);
		}
		
		static void tSort() {
			for (int i = V; i > 0; i--) {
				if (visited[i]==false && cycle[i]==false) { //&& cycle[i]==false) {
					tSortRecursive(i, visited, cycle, kAnswer);
				}
			}
		}

		static void tSortRecursive(int i, boolean[] visited, boolean[] cycle, int[] kAnswer) {
			int nextInt;
			visited[i] = true;
			cycle[i] = true;
			
			Iterator<Integer> itr = adj[i].iterator();
			
			while (itr.hasNext()) {
				nextInt = itr.next();
				
				if (visited[nextInt]==false && cycle[nextInt]==false) {
					tSortRecursive(nextInt, visited, cycle, kAnswer);
				} else if (cycle[nextInt]==true) {
					isCyclic = true;
				}
				
				if (isCyclic) {
					kAnswer[K] = -1;
					return;
				}
				
				kCounter++;
				kAnswer[kCounter] = i;
			}
		}

	}



