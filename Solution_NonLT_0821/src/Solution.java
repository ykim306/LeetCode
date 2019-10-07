import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
sample_input_small.txt output 
#1 1
#2 57

*/
public class Solution
{
	static long tStart = 0;
	
	static int T;
	static int inputN, subMenuLen;
	static String inputMenu;
	
	static long ANSWER;
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("sample_input.txt"));
		//System.setIn(new FileInputStream("sample_input_small.txt"));
		// System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));

		tStart = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			ANSWER = 0;

			inputN = Integer.parseInt(br.readLine());
			inputMenu = br.readLine();

			// System.out.println(inputN + "\n" + inputMenu);

			for (int subL = 1; subL < inputN; subL++) {
				
				for (int s = 0; s < inputN-subL; s++) {
			
					int subMenuStartIndex = s;
					int subMenuEndIndex = s + subL;
					
					//int subMenuStartIndex = 0;
					//int subMenuEndIndex = 0 + 2;
					
					String subMenu = "";
					
					subMenu = inputMenu.substring(subMenuStartIndex, subMenuEndIndex);
					subMenuLen = subMenu.length();
					
					int[] trackArray = new int[subMenuLen + 1];
					
					int subPosition = 0;
					iterateSubString(subMenuStartIndex, subMenu, trackArray, subPosition);
					
					int position = 0;
					iterateString(subMenuStartIndex, subMenu, trackArray, position); 
				} 
			}
			System.out.println("#" + testCase + " " + (ANSWER % 1000000007));
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}

	private static void iterateString(int subMenuStartIndex, String subMenu, int[] trackArray, int position) {
		for (int i = subMenuStartIndex+1+1; i <= inputN; i++) {
			// prior char matches
			if (position > 0 && subMenu.charAt(position) != inputMenu.charAt(i - 1))
				position = trackArray[position];

			if (subMenu.charAt(position) == inputMenu.charAt(i - 1))
				position++;

			if (position == subMenuLen) {
				ANSWER++;
				position = trackArray[position];
			}
		}
	}

	private static void iterateSubString(int subMenuStartIndex, String subMenu, int[] trackArray, int subPosition) {
		for (int i = subMenuStartIndex+2; i <= subMenuLen; i++) {
			// prior char matches
			if (subPosition > 0 && subMenu.charAt(subPosition) != subMenu.charAt(i - 1))
				subPosition = trackArray[subPosition];

			if (subMenu.charAt(subPosition) == subMenu.charAt(i - 1))
				subPosition++;

			trackArray[i] = subPosition;
		}
	}
	

}