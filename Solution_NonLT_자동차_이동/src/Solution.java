import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
[Input]
2
2 3 2100
1 2 1000
2 1 1000
1 2 2000
3 4 8100
1 2 1000
2 3 1000
3 2 1000
1 3 2000

[Output]
#1 2
#2 8

*/
public class Solution
{
	static long tStart = 0;
	
	static final double MOD = 1000000007;
	
	static int T;
	static int inputN;
	
	static double ANSWER;
	
	static int N, M, B;
	static int [][] P, I; //P: 두 노드 간 통행료 | I: 특정 노드에서 통행 횟수별 최소 통행료
	static ArrayList<Integer>[] R; //R: 간선 정보
	
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("sample_input.txt"));
		System.setIn(new FileInputStream("sample_input_small.txt"));
		//System.setIn(new FileInputStream("sample_input_small_2.txt"));
		//System.setIn(new FileInputStream("sample_input_big.txt"));

		tStart = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			ANSWER = 0.0;

			String[] inputNMB = br.readLine().split(" ");
			
			N = Integer.parseInt(inputNMB[0]);
			M = Integer.parseInt(inputNMB[1]);
			B = Integer.parseInt(inputNMB[2]);
			
			clear(); //P, I, R 초기화 해주는 메서드
			
			for(int j=1; j<=M; j++){
				String[] inputSEP = br.readLine().split(" ");
				int s = Integer.parseInt(inputSEP[0]);
				int e = Integer.parseInt(inputSEP[1]);
				int p = Integer.parseInt(inputSEP[2]);
				
				if(P[s][e]==Integer.MAX_VALUE) R[s].add(e); // 같은 간선 정보가 중복되어 들어가지 않도록 체크하며 add
				if(P[s][e]>p) P[s][e] = p; // 가장 최소 통행료가 저장될 수 있도록 체크하며 갱신
			}
			
			drive(); // 정답 구하는 메서드
			
			System.out.println("#" + testCase + " " + (ANSWER));
		}
			
		System.out.println("\n" + ( (System.currentTimeMillis() - tStart) / 1000.0 ) + " seconds");
	}
	
	static void clear(){
		for(int i=0; i<=5000; i++){
			for(int j=0; j<=5000; j++){
				P[i][j] = Integer.MAX_VALUE; // 최소값을 갱신해줘야하기 때문에 Max Value로 초기화
				I[i][j] = Integer.MAX_VALUE;
			}
			R[i] = new ArrayList<Integer>();
		}
		
		I[1][0] = 0; // 제일 처음 시작하는 값(1번 노드에서 0번 통행)은 통행료가 없으므로 0으로 초기화 → 나중에 queue에서 사용됨
	}
	
	static void drive(){
		Queue<Info> q = new LinkedList<Info>();
		q.add(new Info(1,0)); // 1번 도시에서 출발하는 queue의 시작점
		while(!q.isEmpty()){
			Info i = q.poll();
			int n = i.n; // queue에서 꺼낸 현재 도시 번호 정보
			int a = i.a; // queue에서 꺼낸 현재 통행 횟수 정보
			if(ANSWER < a) ANSWER = a; // 현재 통행 횟수 정보와 현재 정답 비교하여 max 저장
			
			if(I[n][a]==B) continue; // 현재 정보 상으로 기준 통행료(B)와 동일할 시, 다음 도시 이동하지 않도록 continue
			for(int j=0; j<R[n].size(); j++){ // 현재 도시와 연결된 간선 정보로 이동할 수 있는 다음 도시 확인
				int e = R[n].get(j); // 연결되어 있는 다음 도시 번호
				int np = I[n][a] + P[n][e]; // queue에서 꺼낸 정보에 다음 도시(e)와의 통행료를 더한 새로운 통행료
				if(np<=B&& np < I[e][a+1]){ // 새로운 통행료가 기준 통행료(B)와 같거나 작고, 이동할 다음 도시의 최소 통행료보다 작을 때만 이동
					I[e][a+1] = np; // 최소 통행료 갱신
					q.add(new Info(e, a+1)); // queue에 새로운 이동 정보 넣어줌
				}
			}
		}
		
	}
	
	//queue 돌리는데 사용하는 class
	static class Info{ 
		int n; // 현재 도시 번호
		int a; // 현재 통행 횟수 
		
		public Info(int ni, int ai){
			n = ni;
			a = ai;
		}
	}

}