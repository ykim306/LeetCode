import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	s = "leetcode"
	return 0.
	
	s = "loveleetcode",
	return 2.
	*/
	class Solution
	{
		static int totSampleSet;
		static int answer;
		
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
					
					answer = firstUniqChar(sc.nextLine());
					
					System.out.println(answer);
				}
			}
		}
		
		static private int firstUniqChar(String s) {
			int index = -1;
			
			Map<Character, Integer> map = new HashMap<>();
			
			for (int i=0; i<s.length(); i++) {
				map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1 );
			}
			
			for (int i=0; i<s.length(); i++) {
				int uniqueCount = map.get(s.charAt(i));
				if (uniqueCount==1) {
					index = i;
					return index;
				}
			}
			
			return index;
		}
		
	}



