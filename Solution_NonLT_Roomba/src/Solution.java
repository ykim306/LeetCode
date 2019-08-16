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
	static int[][] map;
	
//	static int[] moveX = new int[] {0, -1, 0, 1};
//	static int[] moveY = new int[] {-1, 0, 1, 0};
	static int[] moveX = new int[] {0, 1, 0, -1};
	static int[] moveY = new int[] {-1, 0, 1, 0};
	
	static boolean stopCleaning;
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
			stopCleaning = false;
			
			String[] inputNM = br.readLine().split(" ");
			String[] inputXYD = br.readLine().split(" ");
			
			N = Integer.parseInt(inputNM[0]);
			M = Integer.parseInt(inputNM[1]);
			
			int startY = Integer.parseInt(inputXYD[0]);
			int startX = Integer.parseInt(inputXYD[1]);
			int startD = Integer.parseInt(inputXYD[2]);
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				String[] inputRow = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(inputRow[j]);
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]) + "\n");
//			}
			
			// Flip left and right to simplify direction
			// top = 0 | left = 1 | bottom = 2 | right = 3
//			if (startD==3) startD=1;
//			else if (startD==1) startD=3;
			
			cleanMap(startX, startY, startD);
							
			System.out.println("#" + testCase + " " + ANSWER);
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}

	private static void cleanMap(int x, int y, int d) {
				
		if (!stopCleaning) {
			if (map[y][x] == 0) {
				// visited
				map[y][x] = 2;
				ANSWER++;
			}
			
			System.out.println("At (" + x + ", " + y + ") d= " + d % 4);
			for (int i = 0; i < N; i++) {
				System.out.print(Arrays.toString(map[i]) + "\n");
			}
			System.out.println();
			
			d = (d + 3) % 4;
			
//			if (checkIfCanClean(x, y, d+1)) {
//				cleanMap(x + moveX[(d+1) % 4], y + moveY[(d+1) % 4], d+1);
//			} else if (checkIfCanClean(x, y, d+2)) {
//				cleanMap(x + moveX[(d+2) % 4], y + moveY[(d+2) % 4], d+2);
//			} else if (checkIfCanClean(x, y, d+3)) {
//				cleanMap(x + moveX[(d+3) % 4], y + moveY[(d+3) % 4], d+3);
//			} else if (checkIfCanClean(x, y, d)) {
//				cleanMap(x + moveX[(d) % 4], y + moveY[(d) % 4], d);
//			}
			if (checkIfCanClean(x, y, d)) {
				cleanMap(x + moveX[(d) % 4], y + moveY[(d) % 4], d);
			} else if (checkIfCanClean(x, y, d+3)) {
				cleanMap(x + moveX[(d+3) % 4], y + moveY[(d+3) % 4], d+3);
			} else if (checkIfCanClean(x, y, d+2)) {
				cleanMap(x + moveX[(d+2) % 4], y + moveY[(d+2) % 4], d+2);
			} else if (checkIfCanClean(x, y, d+1)) {
				cleanMap(x + moveX[(d+1) % 4], y + moveY[(d+1) % 4], d+1);
			}
			
			// All surrounding area is 2 or 1 or map boundary
			if (checkIfCanCleanReverse(x, y, d)) {
				cleanMap(x - moveX[d%4], y - moveY[d%4], d);
			} 
			
			stopCleaning = true;
		}
		
	}

	private static boolean checkIfCanClean(int x, int y, int d) {
		int nextX = x + moveX[d%4];
		int nextY = y + moveY[d%4];
		
		if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N) {
			if (map[nextY][nextX]==0) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkIfCanCleanReverse(int x, int y, int d) {
		int nextX = x - moveX[d%4];
		int nextY = y - moveY[d%4];
		
		if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N) {
			if (map[nextY][nextX]==2) {
				return true;
			}
		}
		return false;
	}

}