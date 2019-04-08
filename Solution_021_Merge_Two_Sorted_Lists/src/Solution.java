import java.io.FileInputStream;
import java.util.Scanner;

	/*
	As the name of the class should be Solution, using Solution.java as the filename is recommended.
	In any case, you can execute your program by running 'java Solution' command.
	
	Input: 1->2->4, 1->3->4
	Output: 1->1->2->3->4->4
	*/
	class Solution
	{
		static int totSampleSet;
		ListNode head;
		
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
				
				int list01size = sc.nextInt();
				
				ListNode head01 = new Solution().head;
				ListNode head02 = new Solution().head;
				
				
				for (int i=0; i<list01size; i++) {
					head01 = insertNode(head01, sc.nextInt());
					//head01 = insertRecursively(head01, sc.nextInt());
				}
				
				int list02size = sc.nextInt();
				
				for (int j=0; j<list02size; j++) {
					head02 = insertNode(head02, sc.nextInt());
					//head02 = insertRecursively(head02, sc.nextInt());
				}
				
				ListNode head = new Solution().mergeTwoLists(head01, head02);
				//ListNode head = new Solution().mergeTwoListsRecursively(head01, head02);
				
				printEachNode(head);
				
			}
		}
		
		static class ListNode {
			int val;
			ListNode next;
			ListNode(int x) { val = x; }
		}
		
		public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
			
			ListNode preHead = new ListNode(0);
			ListNode answerHead = preHead;
			
			while (l1 != null && l2 != null) {
				if (l1.val > l2.val) {
					answerHead.next = l2;
					l2 = l2.next;
				} else {
					answerHead.next = l1;
					l1 = l1.next;
				}
				answerHead = answerHead.next;
			}
			
			if(l1 == null) {
				answerHead.next = l2;
			} else {
				answerHead.next = l1;
			}
			
			return preHead.next;
		}
		
		public ListNode mergeTwoListsRecursively(ListNode l1, ListNode l2) {
			
			if (l1 == null) {return l2;}
			if (l2 == null) {return l1;}
			
			if (l1.val < l2.val) {
				l1.next = mergeTwoListsRecursively(l1.next, l2);
				return l1;
			} else {
				l2.next = mergeTwoListsRecursively(l1, l2.next);
				return l2;
			}
			
		}
		
		static private ListNode insertNode(ListNode head, int data) {
			ListNode newNode = new ListNode(data);
			
			if (head == null) {
				head = newNode;
			} else {
				ListNode tmp = head;
				while (tmp.next != null) {
					tmp = tmp.next;
				}
				tmp.next = newNode;
			}
			
			return head;
		}
		
		static private ListNode insertRecursively(ListNode head, int data) {
			
			if (head == null) {
				head = new ListNode(data);
				return head;
			} else if (head.next == null) {
				head.next = new ListNode(data);
				return head;
			}
			
			insertRecursively(head.next, data);
			
			return head;
		}
		
		private static void printEachNode(ListNode head) {
			ListNode tmp = head;
			while (tmp != null) {
				System.out.println(tmp.val);
				tmp = tmp.next;
			}
		}
		
		private static void printRecursively(ListNode head) {
			if (head.next == null) {
				System.out.println(head.val);
				return;
			}
			
			System.out.println(head.val);
			
			printRecursively(head.next);
			
			//System.out.println(head.val);
		}
		
	}



