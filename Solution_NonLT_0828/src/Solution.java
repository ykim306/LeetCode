import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/*
sample_input_small.txt output 
#1 1
#2 57

*/
public class Solution
{
	static long tStart = 0;
	
	static int T;
	static int inputN;
	static String[] inputPoints;
	static Line[] lineArray;
	static String[] inputSource;
	static String[] inputTarget;
	
	static Point sourcePoint;
	static Point targetPoint;
	
	static long maxX;
	static long maxY;
	static long minX;
	static long minY;
	
	static int ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("sample_input.txt"));
		//System.setIn(new FileInputStream("sample_input_small.txt"));
		// System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));

		tStart = System.currentTimeMillis();
		
		Solution solution = new Solution();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			ANSWER = 0;

			inputN = Integer.parseInt(br.readLine());
			
			lineArray = new Line[inputN];
			
			for (int i = 0; i < inputN; i++) {
				inputPoints = br.readLine().split(" ");
				lineArray[i] = solution.new Line(Long.parseLong(inputPoints[0]), Long.parseLong(inputPoints[1])
						, Long.parseLong(inputPoints[2]), Long.parseLong(inputPoints[3]));
			}
			
//			for (int i = 0; i < inputN; i++) {
//				System.out.println(lineArray[i].x1 + ", " + lineArray[i].y1 + ", " + lineArray[i].x2 + ", " + lineArray[i].y2);
//			}
			
			inputSource = br.readLine().split(" ");
			inputTarget = br.readLine().split(" ");
			
			sourcePoint = solution.new Point(Long.parseLong(inputSource[0]), Long.parseLong(inputSource[1]));
			targetPoint = solution.new Point(Long.parseLong(inputTarget[0]), Long.parseLong(inputTarget[1]));
			
			maxX = Math.max(sourcePoint.x, targetPoint.x);
			maxY = Math.max(sourcePoint.y, targetPoint.y);
			
			minX = Math.min(sourcePoint.x, targetPoint.x);
			minY = Math.min(sourcePoint.y, targetPoint.y);
			
//			System.out.println("-----------------------");
//			System.out.println(sourcePoint.x + ", " + sourcePoint.y + ", " + targetPoint.x + ", " + targetPoint.y);
//			System.out.println("Max: " + maxX + ", " + maxY);
//			System.out.println("Min: " + minX + ", " + minY);
//			System.out.println("=======================");
			
			for (int i = 0; i < inputN; i++) {
				if ( isIntersection(lineArray[i].x1, lineArray[i].y1, lineArray[i].x2, lineArray[i].y2
						, sourcePoint.x, sourcePoint.y, targetPoint.x, targetPoint.y) ) {
					ANSWER++;
				}
			}
			
			System.out.println("#" + testCase + " " + (ANSWER));
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	static int ccw(long ax, long ay, long bx, long by, long cx, long cy) {
		long result = (long)(bx-ax)*(cy-ay) - (long)(cx-ax)*(by-ay);
		if (result > 0) return 1;
		if (result < 0) return -1;
		return 0;
	}
	
	static boolean isIntersection(long ax, long ay, long bx, long by, long cx, long cy, long dx, long dy) {
		return ccw(ax, ay, bx, by, cx, cy) * ccw(ax, ay, bx, by, dx, dy) < 0 &&
				ccw(cx, cy, dx, dy, ax, ay) * ccw(cx, cy, dx, dy, bx, by) < 0;
	}
	
	class Line {
		public long x1, x2;
		public long y1, y2;
		
		public Line (long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	class Point {
		public long x, y;
		
		public Point (long x, long y) {
			this.x = x;
			this.y = y;
		} 
	}

}