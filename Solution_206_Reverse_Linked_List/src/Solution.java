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
				int totSize = sc.nextInt();
				
				ListNode head = new Solution().head;
				
				for (int i=0; i<totSize; i++) {
					//head = insertNode(head, sc.nextInt());
					head = insertRecursively(head, sc.nextInt());
				}
				
				//printEachNode(head);
				//printRecursively(head);
				
				head = reverseList(head);
				
				//printEachNode(head);
				printRecursively(head);
				
			}
		}
		
		static class ListNode {
			int val;
			ListNode next;
			ListNode(int x) { val = x; }
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
		
		static private ListNode reverseList(ListNode head) {
			if (head == null || head.next == null) return head;
			ListNode node = reverseList(head.next);
			head.next.next = head;
			head.next = null;
			return node;
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



