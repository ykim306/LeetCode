import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
sample_input_small.txt output 
#1 1
#2 57

*/
public class Solution
{
	static long tStart = 0;
	static int T;
	static int N;
	static int M;
	static int startI;
	static int startJ;
	static long[][] adjMatrix;
	
	static int[] moveJ = new int[] {0, -1, 0, 1};
	static int[] moveI = new int[] {-1, 0, 1, 0};
	
	static boolean stopCleaning;
	static int runningSum;
	static int ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("sample_input.txt"));
		System.setIn(new FileInputStream("sample_input_small.txt"));
		//System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));
		
		tStart = System.currentTimeMillis();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++){
			ANSWER = 0;
			int[] distance = null;
			
			PriorityQueue<int[]> pQue = new PriorityQueue<int[]>(10, new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					return a[0] - b[0];
				}
			});
			
			String[] inputNM = br.readLine().split(" ");
			
			N = Integer.parseInt(inputNM[0]);
			M = Integer.parseInt(inputNM[1]);
			
//			adjMatrix = new long[N+1][N+1];
//			
//			for (int i =0; i < N+1; i++) {
//				for (int j = 0; j < N+1; j++) {
//					adjMatrix[i][j] = Long.MAX_VALUE;
//					distance[i] = Integer.MAX_VALUE;
//				}
//			}
//			
//			for (int i = 0; i < M; i++) {
//				String[] inputGrph = br.readLine().split(" ");
//				int input0 = Integer.parseInt(inputGrph[0]);
//				int input1 = Integer.parseInt(inputGrph[1]);
//				long input2 = Long.parseLong(inputGrph[2]);
//				
//				adjMatrix[input0][input1] = input2;
//				adjMatrix[input1][input0] = input2;
//			}
			
			//printMatrix(adjMatrix);
			//System.out.println();
			
			distance = new int[N+1];
			for (int i = 0; i < N+1; i++) {
				distance[i] = Integer.MAX_VALUE;
			}
			
			ArrayList<Integer>[] con = new ArrayList[N+1];
			ArrayList<Integer>[] conv = new ArrayList[N+1];

			for (int i=1;i<N+1;i++){
				con[i] = new ArrayList<>();
				conv[i] = new ArrayList<>();
			}

			for (int i=1;i<=M;i++){
				String[] inputGrph = br.readLine().split(" ");
				int input0 = Integer.parseInt(inputGrph[0]);
				int input1 = Integer.parseInt(inputGrph[1]);
				int input2 = Integer.parseInt(inputGrph[2]);
				
			    con[input0].add(input1);
			    conv[input0].add(input2);
			    
			    con[input1].add(input0);
			    conv[input1].add(input2);
			}
			
			for (ArrayList<Integer> arrayList : con) {
				System.out.println(arrayList);
			}
			
			for (ArrayList<Integer> arrayList : conv) {
				System.out.println(arrayList);
			}
			
			distance[1] = 0;
			pQue.add(new int[]{0, 1});
			
			while (!pQue.isEmpty()){

			    int[] tmp = pQue.poll();
			    int d = tmp[0];
			    int q = tmp[1];
			    
			    if (distance[q] != d) continue;
			    
			    for (int i=0;i<con[q].size();i++){
			        int t = con[q].get(i);
			        int v = conv[q].get(i);

					if (distance[t] > distance[q] + v){
						distance[t] = distance[q] + v;
						pQue.add(new int[]{distance[t], t});
					}
				}
			}
			
			for (int i = 0; i < distance.length; i++) {
				System.out.println(i + " : " + distance[i]);
			}
			
			//System.out.println("#" + testCase + " " + ANSWER);
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	static void printMatrix(long[][] matrix) {
		for (long[] i : matrix) {
			for(long i2 : i) {
				System.out.print(i2+" ");
			}
			System.out.println();
		}
	}

}