import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

	Grid cells are connected horizontally/vertically (not diagonally).
	The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
	
	The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
	One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
	Determine the perimeter of the island.
	
	Example:
	
	Input:
	[[0,1,0,0],
	 [1,1,1,0],
	 [0,1,0,0],
	 [1,1,0,0]]
	
	Output: 16
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
					
					int totRows = sc.nextInt();
					int totCols = sc.nextInt();
					int[][] numArray = new int[totRows][totCols];
					
					for (int j = 0; j < totRows; j++) {
						for (int k = 0; k < totCols; k++) {
							numArray[j][k] = sc.nextInt();
						} 
					}
					
					for (int[] a : numArray) {
						System.out.println(Arrays.toString(a));
					}
					
					// answer = islandPerimeter(numArray);
					answer = islandPerimeterDFS(numArray);
					
					System.out.println(answer);
				}
			}
		}
		
		static private int islandPerimeter(int[][] grid) {
			int count = 0;
			
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j]==1) {
						count += ( j-1>=0 ? 1-grid[i][j-1] : 1 );
						count += ( j+1<=grid[i].length-1 ? 1-grid[i][j+1] : 1 );
						count += ( i-1>=0 ? 1-grid[i-1][j] : 1 );
						count += ( i+1<=grid.length-1 ? 1-grid[i+1][j] : 1 );
					}
				}
			}
			
			return count;
		}
		
		static private int islandPerimeterDFS(int[][] grid) {
			if (grid == null) return 0;
			for (int i = 0 ; i < grid.length ; i++){
				for (int j = 0 ; j < grid[0].length ; j++){
					if (grid[i][j] == 1) {
						return getPerimeter(grid,i,j);
					}
				}
			}
			return 0;
		}

		static private int getPerimeter(int[][] grid, int i, int j){
			if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
				return 1;
			}
			
			if (grid[i][j] == 0) {
				return 1;
			}
			
			if (grid[i][j] == -1) return 0;
			
			int count = 0;
			grid[i][j] = -1;
			
			count += getPerimeter(grid, i-1, j);
			count += getPerimeter(grid, i, j-1);
			count += getPerimeter(grid, i, j+1);
			count += getPerimeter(grid, i+1, j);
			
			return count;
		}
		
	}



