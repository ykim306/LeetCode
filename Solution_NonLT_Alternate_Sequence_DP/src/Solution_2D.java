import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/*
 * 
sample_input_small.txt output 
6
1
2
3
4
5
1500

#1 0
#2 2
#3 4
#4 10
#5 32
#6 264767722

*/
public class Solution_2D
{
	static long tStart = 0;
	
	static int T;
	static int inputN;
	
	//static final int MAX_N = 1500;
	static final int MAX_N = 3;
	static final int MOD = 1000000007;

	static long upDown[][] = new long[MAX_N+1][MAX_N+1];
	static long downUp[][] = new long[MAX_N+1][MAX_N+1];
	
	static long ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("sample_input.txt"));
		//System.setIn(new FileInputStream("sample_input_small.txt"));
		System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));row

		tStart = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/*
		(a<b, b>c, c<d) 이런 경우는 DP1
		(a>b, b<c, c>d) 이런 경우는 DP2
		처음오는 숫자 1~N : A라고 하면
		DP1[N][A] = DP2[N-1][A]~DP2[N-1][N-1]의 합 
		DP2[N][A] = DP1[N-1][1]~DP1[N-1][A-1]의 합
		*/
		
		upDown[2][1] = 1;
		downUp[2][2] = 1;
		
		for (int row = 1; row <= MAX_N; row++) {
			for (int col = 1; col <= row; col++) {
				for (int subCol = col; subCol <= row - 1; subCol++) {
					//DP1[a][b] += DP2[a - 1][c];
					//DP1[a][b] = DP1[a][b] % MOD;
					
					upDown[row][col] = ( upDown[row][col] + downUp[row - 1][subCol] ) % MOD;
					
					System.out.println("upDown : " + row + ", " + col);
					for (long[] rows : upDown) {
						for (long c : rows) {
							System.out.print(c + " ");
						}
						System.out.println();
					}
					
					System.out.println("downUp : " + row + ", " + col);
					for (long[] rows : downUp) {
						for (long c : rows) {
							System.out.print(c + " ");
						}
						System.out.println();
					}
					System.out.println();
				}
				for (int subCol = 1; subCol <= col - 1; subCol++) {
					//DP2[a][b] += DP1[a - 1][c];
					//DP2[a][b] = DP2[a][b] % MOD;
					
					downUp[row][col] = ( downUp[row][col] + upDown[row - 1][subCol] ) % MOD;
					
					System.out.println("upDown2 : " + row + ", " + col);
					for (long[] rows : upDown) {
						for (long c : rows) {
							System.out.print(c + " ");
						}
						System.out.println();
					}
					
					System.out.println("downUp2 : " + row + ", " + col);
					for (long[] rows : downUp) {
						for (long c : rows) {
							System.out.print(c + " ");
						}
						System.out.println();
					}
					System.out.println();
				}
			}
		}
		
//		System.out.println("upDown : a<b>c<d>e");
//		for (long[] rows : upDown) {
//			for (long col : rows) {
//				System.out.print(col + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("downUp : a>b<c>d<e");
//		for (long[] rows : downUp) {
//			for (long col : rows) {
//				System.out.print(col + " ");
//			}
//			System.out.println();
//		}
		
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			ANSWER = 0;
			
			inputN = Integer.parseInt(br.readLine());
			
			for (int a = 1; a <= inputN; a++) {
				ANSWER += (upDown[inputN][a] + downUp[inputN][a]) % MOD;
				ANSWER = ANSWER % MOD;
			}
			System.out.println("#" + testCase + " " + ANSWER);

		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}

}