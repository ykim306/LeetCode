import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	


	Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
	
	To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
	
	To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
	
	Example 1:
	
	Input: [
	[1,1,0],
	[1,0,1],
	[0,0,0]]
	Output: [
	[1,0,0],
	[0,1,0],
	[1,1,1]]
	Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
	Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
	Example 2:
	
	Input: [
	[1,1,0,0],
	[1,0,0,1],
	[0,1,1,1],
	[1,0,1,0]]
	Output: [
	[1,1,0,0],
	[0,1,1,0],
	[0,0,0,1],
	[1,0,1,0]]
	Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
	Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
	Notes:
	
	1 <= A.length = A[0].length <= 20
	0 <= A[i][j] <= 1
	*/
	class Solution
	{
		static int totSampleSet;
		static int[][] answer;
		
		public static void main(String args[]) throws Exception
		{
			/*
			   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
			   To test your program, you may save input data in input.txt file,
			   and call below method to read from the file when using nextInt() method.
			   You may remove the comment symbols(//) in the below statement and use it.
			   But before submission, you must remove the freopen function or rewrite comment symbols(//).
			 */
			
			System.setIn(new FileInputStream("sample_input.txt"));
			
			/*
			   Make new scanner from standard input System.in, and read data.
			 */
			
			try (Scanner sc = new Scanner(System.in)){
				
				totSampleSet = Integer.parseInt(sc.nextLine());
				
				for (int i = 0; i < totSampleSet; i++) {
					
					int totRows = sc.nextInt();
					int totCols = sc.nextInt();
					int[][] numArray = new int[totRows][totCols];
					
					for (int j = 0; j < totRows; j++) {
						for (int k = 0; k < totCols; k++) {
							numArray[j][k] = sc.nextInt();
						} 
					}
					
//					for (int[] a : numArray) {
//						System.out.println(Arrays.toString(a));
//					}
//					System.out.println();
					
					answer = flipAndInvertImage(numArray);
					
					for (int[] a : answer) {
						System.out.println(Arrays.toString(a));
					}
					System.out.println();
				}
			}
		}
		
		private static int[][] flipAndInvertImage(int[][] A) {
						
			for (int i = 0; i < A.length; i++) {
				
				int startIndex = 0;
				int endIndex = A[i].length-1;
				
				while (startIndex <= endIndex) {
					
					if (startIndex == endIndex) {
						A[i][startIndex] = A[i][startIndex]^1;
					} else if (A[i][startIndex] == A[i][endIndex]) {
						A[i][startIndex] = A[i][startIndex]^1;
						A[i][endIndex] = A[i][endIndex]^1;
					}
					
					startIndex++;
					endIndex--;
				}
			}
			
			return A;
		}
		
	}



