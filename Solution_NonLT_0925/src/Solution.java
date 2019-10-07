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
public class Solution
{
	static long tStart = 0;
	
	static final double MOD = 1000000007;
	static final double MIL = 1000000;
	
	static int T;
	static int inputN;
	static String[] inputPoints;
	static String[] inputVector;
	static String[] inputSegment;
	
	static Point[] pointArray;
	
	static Point vector;
	static Point segStart;
	static Point segEnd;
	
	static double maxPointX;
	static double maxPointY;
	static double minPointX;
	static double minPointY;
	
	static double ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("sample_input.txt"));
		//System.setIn(new FileInputStream("sample_input_small.txt"));
		System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));

		tStart = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			ANSWER = 0.0;
			maxPointX = Double.MIN_VALUE;
			maxPointY = Double.MIN_VALUE;
			minPointX = Double.MAX_VALUE;
			minPointY = Double.MAX_VALUE;

			inputN = Integer.parseInt(br.readLine());
			
			pointArray = new Point[inputN];
			
			for (int i = 0; i < inputN; i++) {
				inputPoints = br.readLine().split(" ");
				pointArray[i] = new Point(Double.parseDouble(inputPoints[0]), Double.parseDouble(inputPoints[1]));
			}
			
//			for (int i = 0; i < inputN; i++) {
//				System.out.println(pointArray[i].x + ", " + pointArray[i].y);
//			}
			
			inputVector = br.readLine().split(" ");
			inputSegment = br.readLine().split(" ");
			
			vector = new Point(Double.parseDouble(inputVector[0]), Double.parseDouble(inputVector[1]));
			
			segStart = new Point(Double.parseDouble(inputSegment[0]), Double.parseDouble(inputSegment[1]));
			segEnd = new Point(Double.parseDouble(inputSegment[2]), Double.parseDouble(inputSegment[3]));
			
//			System.out.println("-----------------------");
//			System.out.println("vector : " + vector.x + ", " + vector.y);
//			System.out.println("segStart : " + segStart.x + ", " + segStart.y);
//			System.out.println("segEnd : " + segEnd.x + ", " + segEnd.y);
//			System.out.println("=======================");
			
			ANSWER = findMaxDistance(pointArray, vector, segStart, segEnd);
			
			System.out.println("#" + testCase + " " + (ANSWER));
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	private static double findMaxDistance(Point[] pointArray, Point vector, Point segStart, Point segEnd) {
		
		for (int i = 0; i < pointArray.length; i++) {
			Point imgPoint = getImaginaryPoint(pointArray[i], vector);
			
			findSegmentMaxMin(segStart, segEnd, pointArray[i], imgPoint);
		}
		
//		Point imgPointTest1 = getImaginaryPoint(new Point(10,10), new Point(1,1));
//		Point imgPointTest2 = getImaginaryPoint(new Point(10,10), new Point(-1,0));
//		Point imgPointTest3 = getImaginaryPoint(new Point(10,10), new Point(-1,1));
//		Point imgPointTest4 = getImaginaryPoint(new Point(10,10), new Point(0,-1));
//		Point imgPointTest5 = getImaginaryPoint(new Point(10,10), new Point(-1,1));
//		Point imgPointTest6 = getImaginaryPoint(new Point(10,10), new Point(1,0));
//		Point imgPointTest7 = getImaginaryPoint(new Point(10,10), new Point(0,1));
//		Point imgPointTest8 = getImaginaryPoint(new Point(10,10), new Point(1,1));
//		Point imgPointTest9 = getImaginaryPoint(new Point(10,10), new Point(0,0));
//		
//		System.out.println(imgPointTest1.x + ", " + imgPointTest1.y);
//		System.out.println(imgPointTest2.x + ", " + imgPointTest2.y);
//		System.out.println(imgPointTest3.x + ", " + imgPointTest3.y);
//		System.out.println(imgPointTest4.x + ", " + imgPointTest4.y);
//		System.out.println(imgPointTest5.x + ", " + imgPointTest5.y);
//		System.out.println(imgPointTest6.x + ", " + imgPointTest6.y);
//		System.out.println(imgPointTest7.x + ", " + imgPointTest7.y);
//		System.out.println(imgPointTest8.x + ", " + imgPointTest8.y);
//		System.out.println(imgPointTest9.x + ", " + imgPointTest9.y);
		
		return Math.sqrt((maxPointX-minPointX)*(maxPointX-minPointX)+(maxPointY-minPointY)*(maxPointY-minPointY));
	}

	private static void findSegmentMaxMin(Point segStart, Point segEnd, Point point, Point imgPoint) {
		// segment line
		double yDelta1 = segEnd.y - segStart.y; 
		double xDelta1 = segStart.x - segEnd.x; 
		double c1 = yDelta1*(segStart.x) + xDelta1*(segStart.y); 
	
		// imaginary line 
		double yDelta2 = imgPoint.y - point.y; 
		double xDelta2 = point.x - imgPoint.x; 
		double c2 = yDelta2*(point.x)+ xDelta2*(point.y); 
	
		double divider = yDelta1*xDelta2 - yDelta2*xDelta1; 
	
		if (divider != 0) 
		{ 
			double x = (xDelta2*c1 - xDelta1*c2)/divider; 
			double y = (yDelta1*c2 - yDelta2*c1)/divider; 
			
			if (isInSegment(x,y)) {
//				System.out.println(x + ", " + y);
				maxPointX = Math.max(maxPointX, x);
				maxPointY = Math.max(maxPointY, y);
				minPointX = Math.min(minPointX, x);
				minPointY = Math.min(minPointY, y);
			}
			
		} 
	}

	private static boolean isInSegment(double x, double y) {
		boolean isX;
		boolean isY;
		
		if (segStart.x > segEnd.x) {
			isX = (x >= segEnd.x && x <= segStart.x);
		} else if (segStart.x < segEnd.x) {
			isX = (x <= segEnd.x && x >= segStart.x);
		} else {
			isX = (x == segStart.x && x == segEnd.x);
		}
		
		if (segStart.y > segEnd.y) {
			isY = (y >= segEnd.y && y <= segStart.y);
		} else if (segStart.y < segEnd.y) {
			isY = (y <= segEnd.y && y >= segStart.y);
		} else {
			isY = (y == segStart.y && y == segEnd.y);
		}
		
		return (isX && isY);
	}

	private static Point getImaginaryPoint(Point point, Point vector) {
		if (vector.x < 0 && vector.y < 0) {
			return new Point((point.x + Math.abs(vector.x) + MIL) * -1, (point.y + Math.abs(vector.y) + MIL) * -1);
		} else if (vector.x < 0 && vector.y > 0) {
			return new Point((point.x + Math.abs(vector.x) + MIL) * -1, point.y + vector.y + MIL);
		} else if (vector.x > 0 && vector.y < 0) {
			return new Point(point.x + vector.x + MIL, (point.y + Math.abs(vector.y) + MIL) * -1);
		} else if (vector.x < 0 && vector.y == 0) {
			return new Point((point.x + Math.abs(vector.x) + MIL) * -1, point.y+vector.y);
		} else if (vector.x == 0 && vector.y < 0) {
			return new Point(point.x+vector.x, (point.y + Math.abs(vector.y) + MIL) * -1);
		} else if (vector.x == 0 && vector.y > 0) {
			return new Point(point.x+vector.x, point.y + vector.y + MIL);
		} else if (vector.x > 0 && vector.y == 0) {
					return new Point(point.x + vector.x + MIL, point.y + vector.y);
		} else if (vector.x > 0 && vector.y > 0) {
			return new Point(point.x + vector.x + MIL, point.y + vector.y + MIL);
		} else {
			return new Point(point.x + vector.x, point.y + vector.y);
		}
	}

	static class Point {
		double x, y; 
		
		Point (double x, double y) {
			this.x = x;
			this.y = y;
		} 
	}
	
	

}