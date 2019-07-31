import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

	/*
	sample_input_small.txt output
	#1 20 
	#2 77 
	
	sample_input.txt output
	#1 167436 
	#2 166417 
	#3 182761 
	#4 669232868 
	#5 1425634765276 
	#6 443318195116 
	#7 1787841655257 
	#8 47548858537543 
	#9 25678408034773 
	#10 34362071934741
	
	// Area = Math.abs( ( x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2) ) /2.0 )
	// Area = A1 + A2 + A3

	*/
	class Solution
	{
		static long tStart = 0;
		static int T;
		static Point[] pointArray;
		static Triangle[] triangleArray;
		static long ANSWER;
		
		public static void main(String args[]) throws Exception
		{
			//System.setIn(new FileInputStream("sample_input.txt"));
			//System.setIn(new FileInputStream("sample_input_small.txt"));
			System.setIn(new FileInputStream("sample_input_big.txt"));
			
			tStart = System.currentTimeMillis();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			T = Integer.parseInt(br.readLine().trim());
			for(int testCase=1; testCase<=T; testCase++){
				ANSWER = 0;
				
				String[] inputNQ = br.readLine().split(" ");
				int N = Integer.parseInt(inputNQ[0]);
				int Q = Integer.parseInt(inputNQ[1]);
				
				pointArray = new Point[N];
				triangleArray = new Triangle[Q];
				
				for(int i=0; i<N; i++) {
					String[] inputPoint = br.readLine().split(" ");
					int x = Integer.parseInt(inputPoint[0]);
					int y = Integer.parseInt(inputPoint[1]);
					pointArray[i] = new Point(x,y);
				}
				
//				for(Point p : pointArray) {
//					System.out.println(p.x +" " + p.y);
//				}
				
				for(int i=0; i<Q; i++) {
					String[] inputTriangle = br.readLine().split(" ");
					int a = Integer.parseInt(inputTriangle[0]);
					int b = Integer.parseInt(inputTriangle[1]);
					int c = Integer.parseInt(inputTriangle[2]);
					triangleArray[i] = new Triangle(a,b,c);
				}
				
//				for(Triangle t : triangleArray) {
//					System.out.println(t.a +" " + t.b + " " + t.c);
//				}
				
				for(int i=0; i<Q; i++) {
					Triangle currTriangle = triangleArray[i];
					for(int j=0; j<N; j++) {
						int a = currTriangle.a-1;
						int b = currTriangle.b-1;
						int c = currTriangle.c-1;
						
						if (a != j && b != j && c != j) {
							boolean isInside = findIfInside(pointArray, currTriangle.a-1, currTriangle.b-1, currTriangle.c-1, j);
							if (isInside) {
								ANSWER++;
							}
						}
					}
				}
				
				System.out.println("#" + testCase + " " + ANSWER);
			}
				
			System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
		}
		
		static boolean findIfInside(Point[] pointArray, int a, int b, int c, int j) {
			// Area = Math.abs( ( x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2) ) /2.0 )
			// Area = Math.abs( ( xa*(yb-yc) + xb*(yc-ya) + xc*(ya-yb) ) /2.0 )
			// Area = A1 + A2 + A3
			
			// a b c
			double A = Math.abs(
						(
							pointArray[a].x*(pointArray[b].y - pointArray[c].y) +
							pointArray[b].x*(pointArray[c].y - pointArray[a].y) +
							pointArray[c].x*(pointArray[a].y - pointArray[b].y)
						) / 2.0
					);
			
			// a j c
			double A1 = Math.abs(
						(
							pointArray[a].x*(pointArray[j].y - pointArray[c].y) +
							pointArray[j].x*(pointArray[c].y - pointArray[a].y) +
							pointArray[c].x*(pointArray[a].y - pointArray[j].y)
						) / 2.0
					);
						
			// a b j
			double A2 = Math.abs(
						(
							pointArray[a].x*(pointArray[b].y - pointArray[j].y) +
							pointArray[b].x*(pointArray[j].y - pointArray[a].y) +
							pointArray[j].x*(pointArray[a].y - pointArray[b].y)
						) / 2.0
					);
			
			// j b c
			double A3 = Math.abs(
						(
							pointArray[j].x*(pointArray[b].y - pointArray[c].y) +
							pointArray[b].x*(pointArray[c].y - pointArray[j].y) +
							pointArray[c].x*(pointArray[j].y - pointArray[b].y)
						) / 2.0
					);
			
			return (A == A1 + A2 + A3);
		}

		static class Point {
			int x,y;
			public Point(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
		
		static class Triangle {
			int a, b, c;
			public Triangle(int a, int b, int c) {
				this.a = a;
				this.b = b;
				this.c = c;
			}
		}

	}



