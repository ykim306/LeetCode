import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/*
 * 
sample_input_small.txt output 
#1 0.0
#2 4.0
#3 1.414213562373
#4 2.828427124
#5 535017.927853824340

*/
public class Solution_3D
{
	static long tStart = 0;
	
	static int T;
	static int inputN;
	
	static final int MAX_N = 1500;
	//static final int MAX_N = 2;
	static final int MOD = 1000000007;
	// 3차원 dp를 정의합니다.(n개의 수, 오름차순으로 m번째의 수, 기울기(오름세와 내림세))
	// dp[2][1][1] = 1 : 2개의 수 중 1번째의 수부터 오름세1로 시작하는 경우. 곧 [1,2] 1가지 경우의 수가 존재합니다.
	// dp[2][2][0] = 1 : 2개의 수 중 2번째의 수부터 내림세0로 시작하는 경우. 곧 [2,1] 1가지 경우의 수가 존재합니다.
	static int[][][] dp = new int[MAX_N + 1][MAX_N + 1][2];
	
	static int ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("sample_input.txt"));
		System.setIn(new FileInputStream("sample_input_small.txt"));
		//System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));

		tStart = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// dp의 초항을 정의합니다.
		dp[2][1][1] = 1;
		dp[2][2][0] = 1;

		// 모든 dp 값을 채우는 부분입니다.
		for (int i = 3; i <= MAX_N; i++) {
			for (int j = 1; j <= i; j++) {

				if (j == 1) {// 첫 번째 수로 시작하여야 하기 때문에 더 작은 수가 없으므로 반드시 오름세여야만 하며
					// 그 다음 수는 내림세가 되어야 하므로 남아있는 수들 중 두 번째 수들부터 선택할 수 있습니다.
					// 그리하여 k 값은 2부터 시작합니다.
					for (int k = 2; k <= i - 1; k++) {
						dp[i][j][1] += dp[i - 1][k][0];
						dp[i][j][1] %= MOD;
					}
					continue;
				}
				if (j == i) {// 마지막 번째 수로 시작하여야 하기 때문에 그 다음에 더 큰 수가 없으므로 반드시 내림세여야만 하며
					// 그 다음 수는 오름세가 되어야 하므로 남아있는 수들 중 가장 큰 수를 제외하고 선택할 수 있습니다.
					// 그리하여 k 값는 i - 1개 중 i - 2까지 갈 수 있습니다. 
					for (int k = 1; k <= i - 2; k++) {
						dp[i][j][0] += dp[i - 1][k][1];
						dp[i][j][0] %= MOD;
					}
					continue;
				}

				// 오름세인 경우입니다.
				// i개의 수들 중 j 번째의 수가 오름세1였다면,
				// 남아있는 i-1개의 수들 중 j 번째 이상의 수부터 내림세0일 수 있습니다.
				// j번째 수인 이유는 남이있는 i - 1 개의 수들 중 j번? 수가
				// 원래 i 개의 수들 중 j 번째 수보다는 반드시 크기 때문입니다.
				for (int k = j; k <= i - 1; k++) {
					dp[i][j][1] += dp[i - 1][k][0];
					dp[i][j][1] %= MOD;
				}

				// 내림세인 경우입니다.
				// i개의 수들 중 j 번째의 수가 내림세0였다면,
				// 남아있는 i - 1개의 수들 중 j - 1번째까지의 수까지 오름세일 수 있습니다.
				// j - 1번째 수인 이유는 남아있는 i - 1개의 수들 중 j번째 수가
				// 원래 i 개의 수들 중 j번째 수보다는 반드시 크고,
				// 남아있는 i - 1번째의 수들 중 j - 1 번째의 수가 
				// 원래 i 개의 수들 중 j번째 수보다는 반드시 작기 때문입니다. 
				for (int k = 1; k <= j - 1; k++) {
					dp[i][j][0] += dp[i - 1][k][1];
					dp[i][j][0] %= MOD;
				}

			}
		}
		
		T = Integer.parseInt(br.readLine());
		
		for (int test = 1; test <= T; test++) {
			long answer = 0;
			inputN = Integer.parseInt(br.readLine());
			// N개의 수 중 i번째 수로부터 오름세, 내림세로 시작하는 모든 경우의 수를 다 더하면 답이 나옵니다!
			for (int i = 1; i <= inputN; i++) {
				for (int j = 0; j <= 1; j++) {
					answer += dp[inputN][i][j];
					answer %= MOD;
				}
			}
			System.out.println("#" + test + " " + answer);
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}

}