import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	#1 20 
	#2 77 
	
	(sample_input.txt 에 대한 출력) 
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

	*/
	class Solution
	{
		static long _start = 0;
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
			
			//System.setIn(new FileInputStream("sample_input.txt"));
			System.setIn(new FileInputStream("sample_input_small.txt"));
			
			/*
			   Make new scanner from standard input System.in, and read data.
			 */
			
			_start = System.currentTimeMillis();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int T = Integer.parseInt(br.readLine().trim());
			for(int test_case=1; test_case<=T; test_case++){
				int N = Integer.parseInt(br.readLine().trim());
				String[] sInput = new String[N];
				
				for(int n=1; n<=N; n++){
					sInput = br.readLine().split(" ");
					System.out.println(Arrays.toString(sInput));
				}
				System.out.println();
			}
				
			System.out.println(">> " + (System.currentTimeMillis() - _start) + "ms");
		}
		
		static private void twoSums(int[] nums, int target) {
			
			return;
		}
		
	}



