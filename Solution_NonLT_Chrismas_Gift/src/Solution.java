import java.util.Arrays;

	/*
	(Input)
	2
	3 2
	5 1
	3 2
	2 3
	3 2
	10 1
	3 2
	1 1	
		
	sample_input_small.txt output
	#1 2.667
	#2 5.500
	
	sample_input.txt output
	#1 2.122 
	#2 0.985 
	#3 2.881 
	#4 1.748 
	#5 2.614 
	#6 1.309 
	#7 2.571 
	#8 2.859 
	#9 1.670 
	#10 2.133
	*/

	class Solution
	{
		public static void main(String[] args) throws Exception {
			int[]p=null, w=null;
			int K;
			float answer;
			int zeroN = 0; //give py code added 0 in front of array input. ex) p, w = [0] + p, [0] + w
			
			p = new int[]{zeroN, 5,3,2}; // py input ex) p = [5,3,2]
			w = new int[]{zeroN, 1,2,3};
			K = 2;
			answer = optimalGiftSelection(p,w,K);
			System.out.println(answer);
			//# 2.67
					
					
			p = new int[]{zeroN, 10,3,1};
			w = new int[]{zeroN, 1,2,1};
			K = 2;

			answer = optimalGiftSelection(p,w,K);
			System.out.println(answer);
			//# 5.5
		}
		
		public static float optimalGiftSelection(int[] p, int[] w, int K) {
			int N = p.length;

			float[][] V = new float[N+1][K+1];
			for(int i=0; i<K+1; i++) {
				Arrays.fill(V[i], -Float.MAX_VALUE);
			}
		
			float[][] SW = new float[N+1][K+1+1]; // sum of weights
			
	 		for(int i=0; i<N; i++) {
	 			V[i][0] = 0;
	 		}
	 		
	 		for(int i=1; i<N; i++) {
	 			for(int k=1; k < (Math.min(i, K) + 1) ; k++) {
	 				if(((V[i-1][k-1] * SW[i-1][k-1] + p[i]) * 1.0 / (SW[i-1][k-1] + w[i])) > V[i-1][k]) {
	                    V[i][k] = (float) ((V[i-1][k-1] * SW[i-1][k-1] + p[i]) * 1.0 / (SW[i-1][k-1] + w[i]));
	                    SW[i][k] = SW[i-1][k-1] + w[i];
	 				} else {
	                    V[i][k] = V[i-1][k];
	                    SW[i][k] = SW[i-1][k];
	 				}
	 			}
	 		}
			
			return V[N-1][K];
		}

	}
	
	/*
	http://www.pythontutor.com/visualize.html#mode=display

	p = [5,3,2]
	w = [1,2,3]
	K = 2
	N = len(p)
	p, w = [0] + p, [0] + w
	V = [[float('-inf')]*(K+1) for _ in range(N+1)]
	SW = [[0]*(K+1) for _ in range(N+1)] # sum of weights
	# Base cases
	for i in range(0, N+1):
	    V[i][0] = 0
	for i in range(1, N+1):
	    for k in range(1, min(i, K) + 1):
	        if (V[i-1][k-1] * SW[i-1][k-1] + p[i]) * 1.0 / (SW[i-1][k-1] + w[i]) > V[i-1][k]:
	            V[i][k] = (V[i-1][k-1] * SW[i-1][k-1] + p[i]) * 1.0 / (SW[i-1][k-1] + w[i])
	            SW[i][k] = SW[i-1][k-1] + w[i]
	        else:
	            V[i][k] = V[i-1][k]
	            SW[i][k] = SW[i-1][k]
	print(V[N][K])

	# 2.67

	*/



