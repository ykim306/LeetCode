import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	Input: [1,2,3]
	Output: [1,2,4]
	Explanation: The array represents the integer 123.
	Example 2:

	Input: [4,3,2,1]
	Output: [4,3,2,2]
	Explanation: The array represents the integer 4321.
	*/
	class Solution
	{
		static int totSampleSet;
		static int target;
		static int[] answer;
		
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
					int arraySize = sc.nextInt();
					int[] numArray = new int[arraySize];
					
					for (int j = 0; j < numArray.length; j++) {
						numArray[j] = sc.nextInt();
					}
					
					//answer = plusOne(numArray);
					answer = plusOneRecursively(numArray);
					
					System.out.println(Arrays.toString(answer));
				}
			}
		}
		
		static private int[] plusOne(int[] digits) {
			
			for (int i = digits.length - 1; i >=0; i--) {
				if (digits[i] != 9) {
					digits[i]++;
					break;
				} else {
					digits[i] = 0;
				}
			}
			if (digits[0] == 0) {
				int[] res = new int[digits.length+1];
				res[0] = 1;
				return res;
			}
			return digits;
			
		}
		
		static private int[] plusOneRecursively(int[] digits) {
			
			return recursion(digits, digits.length-1);
			
		}
		
		static private int[] recursion(int[] tmpArray, int maxIndex) {
			
			if (maxIndex < 0) { return tmpArray; }
			if (tmpArray[maxIndex] < 9) {
				tmpArray[maxIndex] += 1;
				return tmpArray;
			}
			
			tmpArray[maxIndex] = 0;
			tmpArray = recursion(tmpArray, maxIndex-1);
			
			if (tmpArray[0] == 0) {
				int[] res = new int[tmpArray.length+1];
				res[0] = 1;
				return res;
			}
			
			return tmpArray;
		}
		
	}



