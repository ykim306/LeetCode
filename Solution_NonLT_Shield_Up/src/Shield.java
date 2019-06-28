import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Shield {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		
		int T = Integer.parseInt(br.readLine());
		int N, M ;
		String temp;
		int count ; 
		int shield[][] ;
		int time [][] ; 
		int result ; 
		int x,y,s;
		space sp;
		PriorityQueue<space> PQ = new PriorityQueue<space>();
		
		for(int t= 1 ; t<=T ; t++){
			
			result = 0 ; 
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			shield = new int [N+2][M+2];
			time = new int [N+2][M+2];
			
			
			for(int i = 1 ; i <= N  ; i++){
				temp =br.readLine();
				count = 0 ;
				for(int j = 1; j <=M ; j++){
					shield[i][j] = Integer.parseInt(temp.substring(count, count+1));
					count++;
				}
			}
			

			
			for(int i =1 ; i <= N ; i++){
				for(int j=1 ; j <= M ; j++){
					if(shield[i][j]!=0){
						if(shield[i-1][j]==0 ||shield[i+1][j]==0 ||shield[i][j-1]==0 ||shield[i][j+1]==0 ){
							time[i][j] = shield[i][j];
							PQ.offer(new space(i,j,time[i][j]));
							result += time[i][j];
						}else{
							time[i][j] = -1 ; 
						}
					}
				}
			}

			
			while(!PQ.isEmpty()){
				sp = PQ.poll();
				x = sp.x;
				y= sp.y;
				s= sp.s;
				
				//Top
				if(time[x-1][y] == -1){
					time[x-1][y]  = s + shield[x-1][y];
					PQ.offer(new space(x-1,y,time[x-1][y] ));
					result += time[x-1][y];
				}
				//Bottom
				if(time[x+1][y]== -1){
					time[x+1][y]  = s + shield[x+1][y];
					PQ.offer(new space(x+1,y,time[x+1][y] ));
					result += time[x+1][y];
				}
				//Left
				if(time[x][y-1]== -1){
					time[x][y-1]  = s + shield[x][y-1];
					PQ.offer(new space(x,y-1,time[x][y-1] ));
					result += time[x][y-1];
				}
				//Right
				if(time[x][y+1]== -1){
					time[x][y+1]  = s + shield[x][y+1];
					PQ.offer(new space(x,y+1,time[x][y+1] ));
					result += time[x][y+1];
				}
			}
			
			for(int i = 1 ; i <= N  ; i++){
				for(int j = 1; j <=M ; j++){
					System.out.print(time[i][j]+" ");
				}

				System.out.println(" ");
			}
			
			
			System.out.println("#"+t +" "+ result);
			
			
			
		}
		
		

	}
	


}

class space implements Comparable<space>{
	
	int x ;
	int y;
	int s;
	
	space(int x, int y, int s){
		this.x = x;
		this.y = y;
		this.s = s ;
	}

	@Override
	public int compareTo(space o) {
		return this.s - o.s;
	}
	
	
}


