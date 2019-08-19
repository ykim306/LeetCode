////////
// You can add and modify values and implementations if needed.
// Professional 검정에서는 입출력 코드를 '문제의 조건에 맞게' 직접 작성하셔야 합니다.
// 입출력은 표준 입출력을 사용하여야 합니다.
// 단, 여러분의 PC에서 작업하실 때 필요한 System.setIn 구문은 아래 제시된 것을 이용하시면 됩니다.
// 입력은 BufferedReader, StringTokenizer 를 사용하는 것을 권장합니다. 
// 예) 한 줄에 있는 int형 정수 N 개를 입력받는 경우 (공백 구분)
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 최초 1회 설정
//     ...
//     StringTokenizer st = new StringTokenizer(br.readLine());
//     for(int i=0; i<N; i++) myvalue[i] = Integer.parseInt(st.nextToken());
////////
//import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


class Solution {
    public static void main(String args[]) {
		long startTime = System.nanoTime();
		BufferedReader br = null;
		InputStreamReader isr = null;
		InputStream in = null;
		try {
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			int testCase = Integer.parseInt(br.readLine());
			int[] answers = new int[testCase];
			for(int t=0; t<testCase; t++) {
				String[] input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				
				int startX = -1;
				int startY = -1;
				int[][] board = new int[x][y];
				for(int i=0; i<x; i++) {
					 String inputLine = br.readLine();
					 for(int j=0; j<inputLine.length(); j++) {
						 if(inputLine.charAt(j) == '#') {
							 board[i][j] = -1; 
						 } else if(inputLine.charAt(j) == '.') {
							 board[i][j] = 0;
						 } else if(inputLine.charAt(j) == '>') {
							 board[i][j] = 1;
							 startX = i;
							 startY = j;
						 }
					 }
				}
				//RIGHT 
				//System.out.println(""startX : "" + startX + "" startY : "" + startY);
				Robot robot = new Robot(startX, startY, 2);
				RobotFindRoute rfr = new RobotFindRoute();
				rfr.initBoard(board, startX, startY);
				if(rfr.chkCnt <=1) {
					answers[t] = 1;
				} else {
					rfr.findRoute(robot);
					answers[t] =rfr.shortestPath();
				}
			}
			
			//ANSWER PART
			for(int i=0; i<testCase; i++) {
				System.out.println("#" + (i+1) + " " + answers[i]);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { if(br!=null) br.close(); } catch(Exception e) {}
			try { if(isr!=null) isr.close(); } catch(Exception e) {}
			try { if(in!=null) in.close(); } catch(Exception e) {}
		}
	}	
}


class RobotFindRoute  {
	private  int[][] board = null;
	private int rec[] = null;
	private int index = 0;
	private boolean[] isCheked = new boolean[9];
	public int chkCnt = 0;
	
	private final int UP = 1;
	private final int RIGHT = 2;
	private final int DOWN = 4;
	private final int LEFT = 8;
	private int startPointX;
	private int startPointY;
	
	
	public void initBoard(int[][] board, int startPointX, int startPointY) {
		this.board = board;
		rec = new int[board.length * board[0].length * 2];
		this.startPointX = startPointX;
		this.startPointY = startPointY;
		
		if(startPointX-1 >=0)  {
			isCheked[UP] = board[startPointX-1][startPointY] == -1;
			if(!isCheked[UP]) chkCnt++;
		}
		if(startPointX+1 <board.length) {
			isCheked[DOWN] = board[startPointX+1][startPointY] == -1;
			if(!isCheked[DOWN]) chkCnt++;
		}
		if(startPointY-1 >=0) {
			isCheked[LEFT] = board[startPointX][startPointY-1] == -1;
			if(!isCheked[LEFT]) chkCnt++;
		}
		if(startPointY+1 <board[0].length) {
			isCheked[RIGHT] = board[startPointX][startPointY+1] == -1;
			if(!isCheked[RIGHT]) chkCnt++;
		}
		
		
	}
	
	public void findRoute(Robot robot) {
		record(robot.x, robot.y);
		
		forward(robot);
		record(-1, -1);
	}
	
	public boolean isChecked(Robot robot, int curDir) {
		if(robot.x == startPointX && robot.y == startPointY && isCheked[curDir]) {
			return true;
		}
		return false;
	}
	
	public void record(int x, int y) {
		rec[index++] = x;
		rec[index++] = y;
	}
	
	
	private void forward(Robot robot) {
		int curDir = robot.dir;
		boolean isDone = false;
		while(!isDone) {
		curDir>>=1;
		if(curDir ==0) curDir = LEFT;
		
			for(int i=0; i<4; i++) {
				if(wallAhead(robot.x, robot.y, curDir)) {
					if(i == 0) {
						curDir = robot.dir;
					} else {
						curDir<<=1;
					}
					curDir = (curDir > LEFT) ? UP : curDir;
				} else {
					if(isChecked(robot, curDir)) {
						isDone = true;
						break;
					}
					if(robot.x == startPointX && robot.y == startPointY) {
						isCheked[curDir] = true;
					}
					
					robot.y = (curDir == LEFT) ? --robot.y : (curDir == RIGHT) ? ++robot.y : robot.y;
					robot.x = (curDir == UP) ? --robot.x : (curDir == DOWN) ? ++robot.x : robot.x;
					robot.dir = curDir;
					
					record(robot.x, robot.y);
					break;
				}
			}
		}
	}
	
	private boolean wallAhead(int x, int y, int dir) {
		y = (dir == LEFT) ? --y : (dir==RIGHT) ? ++y : y;
		x = (dir == UP) ? --x : (dir==DOWN) ? ++x : x;
		return board[x][y] == -1;
	}
	
	public void print() {
		int i=0; 
		while(rec[i] >= 0) {
			System.out.println(rec[i] +"," +  rec[i+1] );
			i = i+2;
		}
	}
	
	
	public int shortestPath() {
		int result = Integer.MAX_VALUE;
		int i=0;
		int prevX = -1;
		int maxSamePosition = 0;
		boolean needToInitial = false;
		int startCount = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while(rec[i] >= 0) {
			if(needToInitial) {
				maxSamePosition = 0;
				map = new HashMap<String, Integer>();
				needToInitial = false;
			}
			if(rec[i] == startPointX && rec[i+1] == startPointY) {
				startCount++;
				if(prevX != -1) {
					int pathCnt = (i - prevX) / 2;
					if(pathCnt < result) {
						result  = pathCnt;
					}
					needToInitial = true;
				}
				prevX = i;
			} else {
				String key = rec[i] + "_" + rec[i+1];
				if(map.containsKey(key)) {
					int prevIndex = map.get(key);
					if((i - prevIndex) / 2 > maxSamePosition) {
						maxSamePosition = (i - prevIndex) / 2; 
					} 
				} else {
					map.put(key, i);
				}
			}
			i = i+2;
		}
		return result == Integer.MAX_VALUE ? 1 : startCount == 2 ? result - maxSamePosition : result;
	}
	
}

class Robot {
	int x;
	int y;
	int dir;
	
	public Robot (int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
