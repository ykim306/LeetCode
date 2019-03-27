	import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
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
					
					answer = validParenthesis(testInput);
					
					System.out.println(answer);
				}
			}
		}
		
		static private boolean validParenthesis(String s) {
			boolean result = false;
			
			// (40ms / 37 MB)
			char[] charArray = s.toCharArray();
			
			Stack<Character> mStack = new Stack<>();
			
			if (charArray.length % 2 == 0) {
				for (int i = 0; i < charArray.length; i++) {
					char mChar = charArray[i];

					if (mChar == '[' || mChar == '(' || mChar == '{') {
						mStack.push(mChar);
					} else if (mChar == ']' || mChar == ')' || mChar == '}') {
						if (!mStack.isEmpty()) {
							if (mChar == ']' && mStack.peek() == '[') {
								mStack.pop();
							} else if (mChar == ')' && mStack.peek() == '(') {
								mStack.pop();
							} else if (mChar == '}' && mStack.peek() == '{') {
								mStack.pop();
							}
						}
					}
				}
				result = mStack.isEmpty();
			}
			
			return result;
		}
	
	}

	/*
	Example 1:
	Input: "()"			Output: true
	
	Example 2:
	Input: "()[]{}"		Output: true

	Example 3:
	Input: "(]"			Output: false

	Example 4:
	Input: "([)]"		Output: false

	Example 5:
	Input: "{[]}"		Output: true
	*/