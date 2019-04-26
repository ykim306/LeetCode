	import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
	Output: 2
	Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
	*/
	class Solution
	{
		static int totSampleSet;
		static int answer;
		static String[] testInput;
		
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
					testInput = new String[sc.nextInt()];
					
					for (int j = 0; j < testInput.length; j++) {
						testInput[j] = sc.next();
					}
					
					//System.out.println(Arrays.toString(testInput));
					//answer = numUniqueEmails(testInput);
					answer = numUniqueEmails02(testInput);
					System.out.println(answer);
				}
			}
		}
		
		static private int numUniqueEmails(String[] emails) {
			Set<String> emailSet = new HashSet<>();
			
			for (String email : emails) {
				String[] tmpArray = new String[2];
				
				tmpArray = email.split("@",2);
				tmpArray[0] = tmpArray[0].split("\\+", 2)[0];
				//tmpArray[0] = String.join("", tmpArray[0].split("\\."));
				tmpArray[0] = tmpArray[0].replaceAll("\\.", "");
				
				emailSet.add(tmpArray[0] + "@" + tmpArray[1]);
			}
			
			return emailSet.size();
		}
		
		static private int numUniqueEmails02(String[] emails) {
			Map<String, Boolean> emailMap = new HashMap<>();
			
			for (String email : emails) {
				boolean enabled = true;
				boolean finished = false;
				StringBuilder sb = new StringBuilder();
			
				for (int i=0; i<email.length(); i++) {
					char tmpChar = email.charAt(i);
					
					if (!finished) {
						if (tmpChar == '@') {
							sb.append(tmpChar);
							finished = true;
						}
						else if (tmpChar == '+') { enabled = false; }
						else if (tmpChar != '.' && enabled) { sb.append(tmpChar); }
					} else {
						sb.append(tmpChar);
					}
					
				}
				
				//System.out.println(sb.toString());
				emailMap.put(sb.toString(), null);
			}
			
			return emailMap.size();
		}
	
	}