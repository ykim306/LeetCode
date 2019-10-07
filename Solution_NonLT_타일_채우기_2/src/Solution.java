import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

////////
// You can add and modify values and implementations if needed.
// Professional 검정에서는 입출력 코드를 '문제의 조건에 맞게' 직접 작성하셔야 합니다.
// 입출력은 표준 입출력을 사용하여야 합니다.
// 단, 여러분의 PC에서 작업하실 때 필요한 System.setIn 구문은 아래 제시된 것을 이용하시면 됩니다.
// 입력은 BufferedReader, StringTokenizer 를 사용하는 것을 권장합니다. 
// 예) 한 줄에 있는 int형 정수 N 개를 입력받는 경우 (공백 구분)
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 최초 1회 설정
//     ...
//     StringTokenizer st = new StringTokenizer(br.readLine());
//     for(int i=0; i<N; i++) myvalue[i] = Integer.parseInt(st.nextToken());
////////
//import java.io.*;

class Solution {
	
	static long tStart = 0;
	
	static int T;
	static long ANSWER;
	
	static int N;
	static int X;
	static int Y;
	static int[] dp;
	
	static int MOD = 1000000007;
	
    public static void main(String args[]) throws Exception {
		/*
		 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 sample_input.txt 파일로부터
		 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를 테스트 할 때, 편의를 위해서
		 sample_input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 
		 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석처리 하셔야 합니다.
		*/
    	tStart = System.currentTimeMillis();
		//System.setIn(new FileInputStream("sample_input.txt"));
		System.setIn(new FileInputStream("sample_input_small_2.txt"));

	    //////// Your algorithm is implemented here. ////////
    	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			dp = new int[N + 1];
	
	        int ANSWER = 0;
	        
	        if ((X-1+Y-1)%2 == 0) {
				firstHalf(X - 1);
				firstHalf(N - X);
			}
            
			System.out.println("#" + testCase + " " + (ANSWER % 1000000007));
        
		}
		
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
    }

	private static void firstHalf(int X) {

		    dp[2] = 3;
		    dp[0] = 1;
		    for (int i = 5; i <= X; i += 2) {
		        for (int j = 2; j <= i; j += 2) {
		        	
		            int blank = 0;
		            
		            if (Y == 2) {
		            	blank = 0;
		            } else {
		            	blank = 4;
		            }
		            
		            dp[i] += blank * dp[i - j];
		            
			        System.out.println("i=" + i + " j=" + j + " blank= " + blank + " dp[i-j] = " + dp[i-j]);
		            printArray(dp);
		        }
		    }
		    ANSWER += dp[X];

	}
	
	private static void secondHalf(int X) {

		    dp[4] = 4;
		    dp[0] = 1;
		    for (int i = 5; i <= X; i += 2) {
		        for (int j = 2; j <= i; j += 2) {
		        	
		            int blank = 0;
		            
		            if (Y == 2) {
		            	blank = 1;
		            } else {
		            	blank = 2;
		            }
		            
		            dp[i] += blank * dp[i - j];
		        }
		    }
		    ANSWER += dp[N];

	}
	
	static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
}
