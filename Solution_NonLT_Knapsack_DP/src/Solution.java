import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
sample_input_small.txt output 
#1 1
#2 57

*/
public class Solution
{
	static long tStart = 0;
	static int T;
	static int ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("sample_input.txt"));
		//System.setIn(new FileInputStream("sample_input_small.txt"));
		//System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));
		
		tStart = System.currentTimeMillis();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++){
			ANSWER = 0;
			
//			String[] inputNM = br.readLine().split(" ");
//			
//			N = Integer.parseInt(inputNM[0]);
//			M = Integer.parseInt(inputNM[1]);
//			
//			map = new int[N][M];
//		
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]) + "\n");
//			}
			
			// Driver program to test above function 
		    int val[] = new int[]{60, 100, 120}; 
		    int wt[] = new int[]{1, 2, 3}; 
		    int  W = 3; 
		    int n = val.length; 
		    System.out.println(knapSack(W, wt, val, n)); 

			//System.out.println("#" + testCase + " " + ANSWER);
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	// A utility function that returns maximum of two integers 
    static int max(int a, int b) { return (a > b)? a : b; } 
	       
    // Returns the maximum value that can be put in a knapsack of capacity W 
	static int knapSack(int W, int wt[], int val[], int n) { 
		int i, w; 
		int K[][] = new int[n+1][W+1]; 
	   
		// Build table K[][] in bottom up manner 
		for (i = 0; i <= n; i++) { 
			for (w = 0; w <= W; w++) { 
				if (i==0 || w==0) { 
					K[i][w] = 0;
					
					System.out.println("i==0 or w==0 | " + i + " " + w);
					for (int[] ai : K) {
						System.out.println(Arrays.toString(ai));
					}
					System.out.println();
				}
				else if (wt[i-1] <= w) { 
					K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
					
					System.out.println("wt[i-1] <= w | " + i + " " + w);
					for (int[] ai : K) {
						System.out.println(Arrays.toString(ai));
					}
					System.out.println();
				}
				else {
					K[i][w] = K[i-1][w];
					
					System.out.println("else");
					for (int[] ai : K) {
						System.out.println(Arrays.toString(ai));
					}
					System.out.println();
				}
			} 
		} 
		
		for (int[] ai : K) {
			System.out.println(Arrays.toString(ai));
		}
		
		return K[n][W]; 
	} 

}