import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	*/
	class Solution
	{
		static int totSampleSet;
		static int target;
		static String answer;
		
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
					
					//answer = reverseVowels(sc.nextLine());
					answer = reverseVowels02(sc.nextLine());
					
					System.out.println(answer);
				}
			}
		}
		
		static private String reverseVowels(String s) {
			Deque<Character> tmpStack = new ArrayDeque<>();
			char[] tmpArray = s.toCharArray();
			
			for (char c : tmpArray) {
				if (Character.toLowerCase(c)=='a' || Character.toLowerCase(c)=='e'
						|| Character.toLowerCase(c)=='i' || Character.toLowerCase(c)=='o'
						|| Character.toLowerCase(c)=='u') {
					tmpStack.push(c);
				}
			}
			
			for (int i=0; i<tmpArray.length; i++) {
				if (Character.toLowerCase(tmpArray[i])=='a' || Character.toLowerCase(tmpArray[i])=='e' 
						|| Character.toLowerCase(tmpArray[i])=='i' || Character.toLowerCase(tmpArray[i])=='o'
						|| Character.toLowerCase(tmpArray[i])=='u') {
					tmpArray[i] = tmpStack.pop();
				}
			}
			
			return String.valueOf(tmpArray);
		}

		static private String reverseVowels02(String s) {
			
			String lookUpChar = "aeiouAEIOU";
			char[] tmpArray = s.toCharArray();
			int start = 0;
			int end = tmpArray.length-1;
			
			while (start<end) {
				
				while (start<end &&
						!lookUpChar.contains( String.valueOf(tmpArray[start])) ) {
					start++;
				}
				
				while (start<end &&
						!lookUpChar.contains( String.valueOf(tmpArray[end])) ) {
					end--;
				}
				
				char tmpChar = tmpArray[start];
				tmpArray[start] = tmpArray[end];
				tmpArray[end] = tmpChar;
				
				start++;
				end--;
			}
			
			return String.valueOf(tmpArray);
		}
	}



