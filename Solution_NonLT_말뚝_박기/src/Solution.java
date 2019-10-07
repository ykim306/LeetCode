import java.io.*;
import java.util.*;

class Solution {
	static long tStart = 0;
	
   static int T; // 테스트 케이스 개수
   static int N; // 말뚝의 개수
   static int Q; // 빠진 말뚝 질문 수 
   static int total; // 질문 중 울타리를 다시 만들어야 하는경우
   static Node[] post= new Node[1000]; //말뚝의 최대 개수 1000이기 때문에 최대치 만큼 배열 미리 생성
   static long maxX;  //말뚝이 박혀 있는 x좌표중 최대 크기
   static long minX;  //말뚝이 박혀 있는 x좌표중 최소 크기
   static boolean dirCheck; //젤 처음 말뚝이 있는 곳 저장(왼쪽인지, 오른쪽인지)
   static boolean makeRound; // 울타리를 만들어야 하는지 판단

   static class Node{ // 말뚝의 위치를 저장할 Node
	   
	   long x; // 말뚝의 x좌표
	   long y; // 말뚝의 y좌표
	   
	   Node(long a , long b)
	   {
		   x = a;
		   y = b;
	   } 
   }

	public static void main(String[] args) throws IOException {
		tStart = System.currentTimeMillis();
		System.setIn(new FileInputStream("sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		
		for(int test = 1 ; test <=T; test++)
		{
			total = 0;   //울타리를 새로 만들어야하는 경우 초기화
			maxX = 0; // 말뚝이 박혀있는 최대 x 좌표 초기화
			minX = Long.MAX_VALUE;; //말뚝이 박혀있는 최소 x좌표 초기화
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N ;i++)
			{
				//말뚝의 좌표를 long 타입으로 하는 이유는 ccw에서 곱셈 연산을 할때 int 타입은 최대치인 10억x10억을 저장할 수 없기 때문
				st = new StringTokenizer(br.readLine());
				long x = Long.parseLong(st.nextToken());
				long y = Long.parseLong(st.nextToken());
				post[i] = new Node(x,y);
				// 최소값과 최대값을 구해놓은 이유는 최소,최대 말뚝범위 밖에 말뚝이 있을경우 무조건 울타리를 다시 만들어줘야하기 때문에
				// 계산하는 시간을 줄이기 위해서 구해놓음
				maxX = Math.max(maxX, x); 
				minX = Math.min(minX, x);
			}
			
			st = new StringTokenizer(br.readLine());
			Q = Integer.parseInt(st.nextToken());
			
			for(int k=0; k<Q;k++) //질문 개수만큼 반복
			{
				
				st = new StringTokenizer(br.readLine());
				long x = Long.parseLong(st.nextToken());
				long y = Long.parseLong(st.nextToken());
				Node Qpost = new Node(x,y); //빠진 말뚝의 좌표
				
				if(x <=minX || x >=maxX){
					total++;
					continue;
				}
				
				// 최악의 경우 (N-1)(N-1) 대충 N^2 이 나오게 되는데 N의 최대값이 1000이라서 제한시간안에 가능
				
				for(int i=0; i<N; i++) // 빠진 말뚝과 기존말뚝 중 1개를 연결하여 직선을 만듬
				{
					boolean checkFirst = true;
					makeRound = false;
					for(int j=0; j<N; j++) // 직선과 나머지 말뚝들을 비교하여 어느쪽에 말뚝이 존재하는지 판단
					{
						if(i==j) // 기존말뚝의 중복을 피하기 위해 똑같을 경우 pass
							continue;
						
						if(checkFirst) //젤 처음 판단한 말뚝이 왼쪽인지 , 오른쪽인지 판단
						{
							dirCheck = ccw(post[i],Qpost,post[j]);
							checkFirst = false;
						}
						boolean currentDir =ccw(post[i],Qpost,post[j]); // 말뚝이 어느쪽에 있는지 저장
						if(currentDir != dirCheck) // 처음에 판단한 말뚝과 같은쪽에 있지 않으면 울타리를 생성할 수 없음
						{
							makeRound = false;
							break;
						}
						else // 모든 말뚝이 같은 쪽에 있으면 울타리를 생성할 수 있음
						{
							makeRound = true;
						}
	
					}
					
					if(makeRound) // 이미 울타리를 생성할 수 있기 때문에 다른 경우 판단하지 않아도됨
					{
						total++; 
						break;
					}	
				}
				
			}
			System.out.printf("#"+test+" "+total);
		}
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	static boolean ccw(Node a, Node b , Node c)
	{
		long temp = a.x*b.y +b.x * c.y + c.x * a.y;
		temp -= a.y * b.x + b.y * c.x + c.y * a.x;
		
		//long temp = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
		
		if( temp > 0)   //0보다 크면 ab직선의 왼쪽에 c말뚝 존재
			return true;
		else           // 0보다 작으면 ab직선의 오른쪽에 c말뚝 존재
			return false;
		// 0일때는 a b c 세점이 같은 직선에 존재하는경우 ( 문제에서 이런경우는 없다고해서 판단안해도 상관없음)
		
	}
    
	
	
}
