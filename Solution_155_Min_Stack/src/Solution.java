	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	*/
	class Solution
	{
		
		public static void main(String args[]) throws Exception
		{
			Solution solution = new Solution();
			MinStack mStack = solution.new MinStack();
			
			mStack.push(-3);
			System.out.println(mStack.getMin());
		}
		
		// 53ms / 45.5MB
		class MinStack {
			static final int MAX_SIZE = 10000;
			private int[] tmpArray;
			private int currIndex;
			private int minValue;
			
		    /** initialize your data structure here. */
		    public MinStack() {
		    	currIndex = -1;
		    	tmpArray = new int[MAX_SIZE];
		    }
		    
		    public void push(int x) {
		    	if (currIndex < MAX_SIZE) {
		    		if (currIndex == -1 || currIndex > -1 && x < minValue) {
	    				minValue = x;
	    			}
	    			currIndex++;
	    			tmpArray[currIndex] = x;
		    	}
		    }
		    
		    public void pop() {
		        if (tmpArray[currIndex] == minValue) {
		        	
		        	minValue = tmpArray[0];
		        	
		        	for (int i=1; i<currIndex; i++) {
		        		if (tmpArray[i] < minValue) {
		        			minValue = tmpArray[i];
		        		}
		        	}
		        }
		        currIndex--;
		    }
		    
		    public int top() {
		        return tmpArray[currIndex];
		    }
		    
		    public int getMin() {
		        return minValue;
		    }
		}

		
		/**
		 * Your MinStack object will be instantiated and called as such:
		 * MinStack obj = new MinStack();
		 * obj.push(x);
		 * obj.pop();
		 * int param_3 = obj.top();
		 * int param_4 = obj.getMin();
		 */
		
	}



