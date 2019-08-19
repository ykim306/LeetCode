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
	        int val[] = { 60, 100, 120 }; 
	        int wt[] = { 10, 20, 30 }; 
	        int W = 50; 
	        int n = val.length; 
	  
	        printknapSack(W, wt, val, n); 

			//System.out.println("#" + testCase + " " + ANSWER);
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
    // A utility function that returns  
    // maximum of two integers 
    static int max(int a, int b)  
    { 
        return (a > b) ? a : b; 
    } 
  
    // Prints the items which are put  
    // in a knapsack of capacity W 
    static void printknapSack(int W, int wt[], int val[], int n) 
    { 
        int i, w; 
        int K[][] = new int[n + 1][W + 1]; 
  
        // Build table K[][] in bottom up manner 
        for (i = 0; i <= n; i++) { 
            for (w = 0; w <= W; w++) { 
                if (i == 0 || w == 0) 
                    K[i][w] = 0; 
                else if (wt[i - 1] <= w) 
                    K[i][w] = Math.max(val[i - 1] +  
                              K[i - 1][w - wt[i - 1]], K[i - 1][w]); 
                else
                    K[i][w] = K[i - 1][w]; 
            } 
        } 
  
        // stores the result of Knapsack 
        int res = K[n][W]; 
        System.out.println(res); 
  
        w = W; 
        for (i = n; i > 0 && res > 0; i--) { 
  
            // either the result comes from the top 
            // (K[i-1][w]) or from (val[i-1] + K[i-1] 
            // [w-wt[i-1]]) as in Knapsack table. If 
            // it comes from the latter one/ it means 
            // the item is included. 
            if (res == K[i - 1][w]) 
                continue; 
            else { 
  
                // This item is included. 
                System.out.print(wt[i - 1] + " "); 
  
                // Since this weight is included its 
                // value is deducted 
                res = res - val[i - 1]; 
                w = w - wt[i - 1]; 
            } 
        } 
    } 

}