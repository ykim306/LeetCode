import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
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
				
				target = sc.nextInt();
				
				for (int i = 0; i < totSampleSet; i++) {
					int arraySize = sc.nextInt();
					int[] numArray = new int[arraySize];
					
					for (int j = 0; j < numArray.length; j++) {
						numArray[j] = sc.nextInt();
					}
					
					answer = twoSums(numArray, target);
					
					System.out.println(Arrays.toString(answer));
				}
			}
		}
		
		static private int[] twoSums(int[] nums, int target) {
			int[] indices = new int[2];
			
			// Hash one-pass method
			Map<Integer, Integer> hmap = new HashMap<>();
			
			for (int i = 0; i < nums.length; i++) {
				int diff = target - nums[i];
				
				if (hmap.containsKey(diff)) {
					indices = new int[] { hmap.get(diff), i };
					break;
				}
				
				hmap.put(nums[i], i);
			}
			
			/* Hash method (4ms / 38.1MB)
			Map<Integer,Integer> hmap = new HashMap<>();
			
			for (int i=0; i<nums.length; i++) {
				hmap.put(nums[i], i);
			}
			
			for (int j=0; j<nums.length; j++) {
				int diff = target - nums[j];
				
				if (hmap.containsKey(diff) && hmap.get(diff) != j) {
					indices = new int[] {hmap.get(diff), j};
				}
			}
			*/
			
			/* Brute force (34ms / 38.5MB)
			for (int i=0; i<nums.length; i++) {
		    	for(int j=i+1; j<nums.length; j++) {
		    		if (nums[j] == (target - nums[i]) ) {
		    			indices = new int[] {i, j};
		    		}
		    	}
		    }
		    */
			
			return indices;
		}
		
	}



