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
	static int N;
	static int M;
	static int startI;
	static int startJ;
	static int[][] map;
	
	static int[] moveJ = new int[] {0, -1, 0, 1};
	static int[] moveI = new int[] {-1, 0, 1, 0};
	
	static boolean stopCleaning;
	static int runningSum;
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
			ANSWER = 1;
			stopCleaning = false;
			// top is always blocked and start is looking east,
			// starting by blocking east and looking at west to start south
			// add 4 to handle d = -1
			int startD = 3+4;
			startJ = 0;
			startI = 0;
			
			runningSum = 1; 
			
			String[] inputNM = br.readLine().split(" ");
			
			N = Integer.parseInt(inputNM[0]);
			M = Integer.parseInt(inputNM[1]);
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				String inputRow = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = convertMapToInt(inputRow, i, j);
				}
			}
			
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]) + "\n");
			}
			
			if (map[startI][startJ+1] == 0) {
				map[startI][startJ+1] = 2;
				cleanMap(startI, startJ, startD, 1);
				ANSWER = runningSum;
				runningSum = 1;
				map[startI][startJ+1] = 0;
			}
			
			if (map[startI+1][startJ] == 0) {
				map[startI+1][startJ] = 2;
				cleanMap(startI, startJ, startD+1, 1);
				ANSWER = Math.min(runningSum, ANSWER);
				runningSum = 1;
				map[startI+1][startJ] = 0;
			}
			
			if (map[startI][startJ-1] == 0) {
				map[startI][startJ-1] = 2;
				cleanMap(startI, startJ, startD, 1);
				runningSum = 1;
				ANSWER = Math.min(runningSum, ANSWER);
				map[startI][startJ-1] = 0;
			}
							
			System.out.println("#" + testCase + " " + ANSWER);
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	private static int convertMapToInt(String inputRow, int i, int j) {
		if (inputRow.charAt(j)=='#') {
			return 2;
		} else if (inputRow.charAt(j)=='>') {
			startJ = j;
			startI = i;
			return 3;
		} else {
			return 0;
		}
	}

	private static void cleanMap(int i, int j, int d, int level) {
		
		if (d < 0) {
			d = d + 4;
		}
		
		if (!stopCleaning) {
			
			if (map[i][j] == 0 && level == 1) {
				// visited once
				map[i][j] = 1;
				runningSum++;
			}
			if (map[i][j] == 1 && level == 2) {
				// visited twice
				//map[i][j] = 2;
				runningSum++;
			}
			
			System.out.println("At (i=" + i + ", j=" + j + ") d= " + d % 4 + " level= " + level + " runningSum= " + runningSum);
			for (int row = 0; row < N; row++) {
				System.out.print(Arrays.toString(map[row]) + "\n");
			}
			System.out.println();
			
			if (level == 1) {
				if (checkIfCanClean(i, j, d - 1)) {
					cleanMap(i + moveI[(d - 1) % 4], j + moveJ[(d - 1) % 4], d - 1, 1);
				} else if (checkIfCanClean(i, j, d)) {
					cleanMap(i + moveI[d % 4], j + moveJ[d % 4], d, 1);
				} else if (checkIfCanClean(i, j, d + 1)) {
					cleanMap(i + moveI[(d + 1) % 4], j + moveJ[(d + 1) % 4], d + 1, 1);
				} else if (checkIfCanClean(i, j, d + 2)) {
					cleanMap(i + moveI[(d + 2) % 4], j + moveJ[(d + 2) % 4], d + 2, 1);
				}
				
				level = 2;
			}
			
			if (level == 2) {
				// All surrounding area is 2 or 1, then return
				if (checkIfCanCleanReturn(i, j, d - 1)) {
					cleanMap(i + moveI[(d - 1) % 4], j + moveJ[(d - 1) % 4], d - 1, 2);
				} else if (checkIfCanCleanReturn(i, j, d)) {
					cleanMap(i + moveI[d % 4], j + moveJ[d % 4], d, 2);
				} else if (checkIfCanCleanReturn(i, j, d + 1)) {
					cleanMap(i + moveI[(d + 1) % 4], j + moveJ[(d + 1) % 4], d + 1, 2);
				} else if (checkIfCanCleanReturn(i, j, d + 2)) {
					cleanMap(i + moveI[(d + 2) % 4], j + moveJ[(d + 2) % 4], d + 2, 2);
				} 
			}
			
			stopCleaning = true;
		}
		
	}

	private static boolean checkIfCanClean(int i, int j, int d) {
		int nextI = i + moveI[d % 4];
		int nextJ = j + moveJ[d % 4];
		
		if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < M) {
			
			if (map[nextI][nextJ]==0) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkIfCanCleanReturn(int i, int j, int d) {
		int nextI = i + moveI[d % 4];
		int nextJ = j + moveJ[d % 4];
		
//		System.out.println("nextI= " + nextI + " nextJ= " + nextJ);
		
		if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < M) {
			
			if (map[nextI][nextJ]==3) {
				stopCleaning = true;
				return false;
			} else if (map[nextI][nextJ]==1) {
				return true;
			}
		}
		return false;
	}

}