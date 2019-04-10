import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	Example 1:
	Input: 1
	Output: true 
	Explanation: 20 = 1
	
	Example 2:
	Input: 16
	Output: true
	Explanation: 24 = 16
	
	Example 3:
	Input: 218
	Output: false
	*/
	class Solution
	{
		static int totSampleSet;
		static boolean answer;
		
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
				
				for (int i = 0; i < totSampleSet; i++) {
					
					//answer = isPowerOfTwo(sc.nextInt());
					answer = isPowerOfTwoBinary(sc.nextInt());
					
					System.out.println(answer);
				}
			}
		}
		
		static private boolean isPowerOfTwo(int n) {
			
			if (n == 1) {
				return true;
			}
			
			while (n%2==0 && n>=2) {
				n /= 2;
				if (n==1) {
					return true;
				}
			}
			
			return false;
		}
		
		static private boolean isPowerOfTwoBinary(int n) {
			/*
			a = 0011 1100
			b = 0000 1101
			a&b = 0000 1100
			8 = 0000 1000
			8-7 = 0000 0111
			*&7 = 0000 0000
			*/
			
			return (n>0 && ((n-1)&n)==0);
			
			// bit count of binary is alway 1 (for power of two e.g. 01, 10)
			// return n>0 && Integer.bitCount(n) == 1;
		}
		
	}



