import java.io.*;
import java.util.*;

class Solution {
	static long tStart = 0;
	
	static int T;
	static int n;
	
	static int[] dp;

	public static void main(String[] args) throws IOException {
    	tStart = System.currentTimeMillis();
		System.setIn(new FileInputStream("sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
	        
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
	        dp = new int[n + 1];

	        int answer = 0;
	        
	        if (n % 2 == 0) {
	            dp[2] = 3;
	            dp[0] = 1;
	            for (int i = 4; i <= n; i += 2) {
	                for (int j = 2; j <= i; j += 2) {
	                    int standard = j == 2 ? 3 : 2;
	                    dp[i] += standard * dp[i - j];
	                    
	                    System.out.println("i=" + i + " j=" + j + " standard= " + standard + " dp[i-j] = " + dp[i-j]);
	                    printArray(dp);
	                }
	            }
	            answer = dp[n];
	        }

	        System.out.println("#" + testCase + " " + (answer % 1000000007));
		}
		
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
}
