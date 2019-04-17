import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	Example:
	Input:
	[4,3,2,7,8,2,3,1]
	
	Output:
	[5,6]
	*/
	class Solution
	{
		static int totSampleSet;
		static int target;
		static List<Integer> answer;
		
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
					
					answer = findDisappearedNumbers(numArray);
					
					System.out.println(answer);
				}
			}
		}
		
		static private List<Integer> findDisappearedNumbers(int[] nums) {
	        List<Integer> ret = new ArrayList<Integer>();
	        
	        for(int i = 0; i < nums.length; i++) {
	            int val = Math.abs(nums[i]) - 1;
	            if(nums[val] > 0) {
	                nums[val] = -nums[val];
	            }
	        }
	        
	        for(int i = 0; i < nums.length; i++) {
	            if(nums[i] > 0) {
	                ret.add(i+1);
	            }
	        }
	        return ret;
	    }
		
	}



