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
public class Solution_2D_Alternative
{
	static long tStart = 0;
	
	static int T;
	static int inputN;
	
	static final int MAX_N = 1500;
	//static final int MAX_N = 5;
	static final int MOD = 1000000007;

	static long upDown[][] = new long[MAX_N+1][MAX_N+1];
	static long downUp[][] = new long[MAX_N+1][MAX_N+1];
	
	static long ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("sample_input.txt"));
		System.setIn(new FileInputStream("sample_input_small.txt"));
		//System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));

		tStart = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		upDown[2][1] = 1;
		downUp[2][2] = 1;
		
		for (int row = 2; row <= MAX_N; row++) {
			for (int col = 1; col <= row; col++) {
				
//				for (int subCol = 1; subCol <= row-1; subCol++) {
//					
//					if (subCol <= (col-1)) {
//						
//						downUp[row][col] = (downUp[row][col] % MOD) + (upDown[row - 1][subCol] % MOD);
//				
////						System.out.println("upDown : " + row + ", " + col);
////						for (long[] rows : upDown) {
////							for (long c : rows) {
////								System.out.print(c + " ");
////							}
////							System.out.println();
////						}
////						
////						System.out.println("downUp : " + row + ", " + col);
////						for (long[] rows : downUp) {
////							for (long c : rows) {
////								System.out.print(c + " ");
////							}
////							System.out.println();
////						}
////						System.out.println();
//					} else {
//						
//						upDown[row][col] = (upDown[row][col] % MOD) + (downUp[row - 1][subCol] % MOD);
//				
////						System.out.println("upDown : " + row + ", " + col);
////						for (long[] rows : upDown) {
////							for (long c : rows) {
////								System.out.print(c + " ");
////							}
////							System.out.println();
////						}
////						
////						System.out.println("downUp : " + row + ", " + col);
////						for (long[] rows : downUp) {
////							for (long c : rows) {
////								System.out.print(c + " ");
////							}
////							System.out.println();
////						}
////						System.out.println();
//					}
//					
//				}	
				
				for (int subCol = 1; subCol <= col-1; subCol++) {
					//downUp[row][col] = ( downUp[row][col] + upDown[row - 1][subCol] ) % MOD;
					downUp[row][col] = (downUp[row][col] % MOD) + (upDown[row - 1][subCol] % MOD);
					
//					System.out.println("upDown : " + row + ", " + col);
//					for (long[] rows : upDown) {
//						for (long c : rows) {
//							System.out.print(c + " ");
//						}
//						System.out.println();
//					}
//					
//					System.out.println("downUp : " + row + ", " + col);
//					for (long[] rows : downUp) {
//						for (long c : rows) {
//							System.out.print(c + " ");
//						}
//						System.out.println();
//					}
//					System.out.println();
				}
				
				for (int subCol = col; subCol <= row-1; subCol++) {
					//upDown[row][col] = ( upDown[row][col] + downUp[row - 1][subCol] ) % MOD;
					upDown[row][col] = (upDown[row][col] % MOD) + (downUp[row - 1][subCol] % MOD);
					
//					System.out.println("upDown2 : " + row + ", " + col);
//					for (long[] rows : upDown) {
//						for (long c : rows) {
//							System.out.print(c + " ");
//						}
//						System.out.println();
//					}
//					
//					System.out.println("downUp2 : " + row + ", " + col);
//					for (long[] rows : downUp) {
//						for (long c : rows) {
//							System.out.print(c + " ");
//						}
//						System.out.println();
//					}
//					System.out.println();
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
			
			for (int col = 1; col <= inputN; col++) {
//				ANSWER += (upDown[inputN][col] % MOD) + (downUp[inputN][col] % MOD);
//				ANSWER = ANSWER % MOD;
				ANSWER = (ANSWER % MOD) + (upDown[inputN][col] % MOD) + (downUp[inputN][col] % MOD);
				ANSWER = ANSWER % MOD;
			}
			
			System.out.println("#" + testCase + " " + ANSWER);

		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}

}