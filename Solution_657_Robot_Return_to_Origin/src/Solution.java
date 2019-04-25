	import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	Example 1:
	Input: "UD"
	Output: true 
	
	Example 2:
	Input: "LL"
	Output: false
	*/
	class Solution
	{
		static int totSampleSet;
		static boolean answer;
		static String testInput;
		
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
				totSampleSet = sc.nextInt();
				// call nextLine() to skip first line break;
				sc.nextLine();
				
				for (int i = 0; i < totSampleSet; i++) {
					testInput = sc.nextLine();
					
					//System.out.println(Arrays.toString(charArray));
					
					answer = judgeCircle(testInput);
					
					System.out.println(answer);
				}
			}
		}
		
		static private boolean judgeCircle(String moves) {
			int x=0, y=0;
			
			for (char c : moves.toCharArray()) {
				if (c == 'R') { x++;}
				if (c == 'L') { x--;}
				if (c == 'U') { y++;}
				if (c == 'D') { y--;}
			}
			
			return (x==0 && y==0);
		}
	
	}