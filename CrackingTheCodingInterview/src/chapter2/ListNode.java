package chapter2;

import java.util.HashSet;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x;
	next = null;}
	public static void printList(ListNode head) {
		while(head!=null) {
			System.out.print(head.val);
			if(head.next==null) {
				System.out.println();
			} else {
				System.out.print("->");
			}
			head=head.next;
		}
	}
	public static ListNode buildList(int[] values) {
		return buildList(values, 0);
	}
	public static ListNode buildList(int[] values, int start) {
		if(start>=values.length) 
			return null;
		ListNode nextNode= buildList(values, start+1);
		ListNode head=new ListNode(values[start]);
		head.next=nextNode;
		return head;
	}
	public static void main(String args[]) {
		int n[]={1,3,5};
		ListNode ln = buildList(n);
		printList(ln);
	}
}
