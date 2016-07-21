package chapter2;

import java.util.HashSet;

public class LinkedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedLists lls = new LinkedLists();
		
		ListNode ln1 = ListNode.buildList(new int[]{1,2,3,4,5,4,3,2,3,4,5});
		ListNode.printList(ln1);
		lls.removeDups(ln1);
		ListNode.printList(lls.removeDups(ln1));
		
		ListNode ln2 = ListNode.buildList(new int[]{1,2,3,4,5,6,7,8,9,10});
		System.out.println(lls.returnKthToLast(ln2, 3).val);
		
		ListNode ln3 = ListNode.buildList(new int[]{1,2,3,4,5,6,7,8,9,10});
		ListNode.printList(ln3);
		lls.deleteMiddleNode(ln3.next.next.next.next);
		ListNode.printList(ln3);
		
		ListNode ln4 = ListNode.buildList(new int[]{3,5,8,5,10,2,1});
		ListNode.printList(ln4);
		ListNode.printList(lls.partition(ln4, 5));
		
		ListNode ln51 = ListNode.buildList(new int[]{7,1,6});
		ListNode ln52 = ListNode.buildList(new int[]{5,9,3,9});
		ListNode.printList(ln51);
		ListNode.printList(ln52);
		ListNode.printList(lls.sumLists(ln51,ln52));
		
		ListNode ln71 = ListNode.buildList(new int[]{3,1,5,9});
		ListNode ln72 = ListNode.buildList(new int[]{4,6});
		ListNode ln73 = ListNode.buildList(new int[]{7,2,1});
		ListNode p = ln71;
		while(p.next!=null) {
			p=p.next;
		}
		p.next = ln73;
		p = ln72;
		while(p.next!=null) {
			p=p.next;
		}
		p.next = ln73;
		System.out.println(ln73);
		System.out.println(lls.getIntersection(ln71,ln72));
	}
	
	/*
	 * 2.1
	 * Remove Dups: Write code to remove duplicates form an unsorted linked list.
	 * FOLLOW UP
	 * How would you solve this problem if a temporary buffer is not allowed?
	 */
	/*
	 * without extra space, this can not be done in O(n) but O(n^2)
	 */
	public ListNode removeDups(ListNode head) {		
		if(head == null) return head;
		HashSet<Integer> set = new HashSet<Integer>();
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		while(head!=null) {
			if(set.contains(head.val)) {
				System.out.println("true  "+head.val);
				prev.next = head.next;
			} else {
				set.add(head.val);
				prev = head;
			}
			head = head.next;
		}
		return dummy.next;
	}
	
	/*
	 * 2.2
	 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
	 */
	/*
	 * use two pointers, fast one goes k-1 step ahead of slow pointer
	 */
	public ListNode returnKthToLast(ListNode head, int k) {
		ListNode fast = head;
		ListNode slow = head;
		while(fast!=null) {
			fast = fast.next;
			if(k--<=0) slow = slow.next;
		}
		return slow;
	}
	
	/*
	 * 2.3
	 * Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e. , any node but the first and last node,
	 * not necessarily the exact middle) of a singly linked list, given only access to that node.
	 * example,
	 * input: the node c from the linked list a->b->c->d->e->f
	 * result: nothing is returned , but the new linked list looks like a->b->d->e->f
	 */
	public void deleteMiddleNode(ListNode nodeToDelete) {
		if(nodeToDelete == null || nodeToDelete.next == null) return;
//		copy value of next node to this node and delete next node
		nodeToDelete.val = nodeToDelete.next.val;
		nodeToDelete.next = nodeToDelete.next.next;
	}
	
	/*
	 * 2.4
	 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.
	 * If x is contained within the list, the values of x only need to after the elements less than x (see below). 
	 * The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions;
	 * example,
	 * input: 3->5->8->5->10->2->1[partition = 5]
	 * output:3->1->2->10->5->5->8
	 */
	public ListNode partition(ListNode head, int pivot) {
		ListNode smaller = new ListNode(-1);
		ListNode prev = smaller;
		prev.next = head;
		ListNode greater = new ListNode(-1);
		ListNode p = greater;
		while(head!=null) {
			if(head.val >= pivot) {
				p.next = head;
				p = head;
			} else {
				prev.next = head;
				prev = head;
			}
			head = head.next;
		}
		p.next = null;
		prev.next = greater.next;
		return smaller.next;
	}
	
	/*
	 * 2.5
	 * Sum List: You have two numbers represented by a linked list, where each node contains a single digit.
	 * The digits are stored in reverse order, such that the 1's digit is at the head of the list.
	 * Write a function that adds the two numbers and returns the sum as a linked list.
	 * example,
	 * Input: (7->1->6) + (5->9->2). That is 617+295.
	 * Output:2->1->9. That is, 912.
	 * FOLLOW UP
	 * Suppose the digits are stored in forward order. Repeat the above problem.
	 * Input: (6->1->7) + (2->9->5). That is 617+295.
	 * Output:9->1->2. That is, 912.
	 */
	public ListNode sumLists(ListNode h1, ListNode h2) {
		int carry = 0;
		return sumLists(h1, h2, carry);
	}

	private ListNode sumLists(ListNode h1, ListNode h2, int carry) {
		if(h1 == null && h2 == null) {
			return carry==1?new ListNode(1):null;
		}
		if(h1 != null && h2 != null) {
			h1.val = h1.val+h2.val+carry;
			if(h1.val>9) {
				h1.val-=10;
				carry = 1;
			} else carry = 0;
			h1.next = sumLists(h1.next, h2.next, carry);
			return h1;
		}
		ListNode nn = h2 ==null? h1: h2;
		return sumLists(nn, carry);
	}

	private ListNode sumLists(ListNode nn, int carry) {
		if(nn == null) {
			return carry==1?new ListNode(1):null;
		}
		nn.val += carry;
		if(nn.val>9) {
			nn.val-=10;
			carry = 1;
		} else carry = 0;
		nn.next = sumLists(nn.next, carry);
		return nn;
	}
	
//	public ListNode sumLists2(ListNode h1, ListNode h2) {
//		
//	}
	
	/*
	 * 2.6
	 * Palindrome: Implement a function to check if a linked list is a palindrome.
	 */
	public boolean isPalindrome(ListNode head) {
/*
 * 		approach 1, reverse and compare
 */
//		ListNode rev = reverse(head);
//		return isEqual(head, rev);
/*
 * 		approach 2, store first half in stack, and compare
 */
//		ListNode slow = head;
//		ListNode fast = head;
//		Stack<Integer> s = new Stack<Integer>();
////		fast moves twice fast as slow, slow stays at the middle when odd and end of first half when even
//		while(fast!=null && fast.next!=null) {
//			s.add(slow.val);
//			slow = slow.next;
//			fast = fast.next.next;
//		}
////		skip the middle if odd, then compare each element after middle with elements in the stack
//		if(fast!=null) {
//			slow = slow.next;
//		}
//		while(slow!=null) {
//			if( s.pop().intValue() != slow.val)
//				return false;
//			slow = slow.next;
//		}
//		return true;
/*
 * 		approach 3: recursive
 */
		return isPalindromeRecurse(head, getLength(head)).result;
	}
	private Result isPalindromeRecurse(ListNode head, int length) {
		if(head == null || length == 0)
			return new Result(head, true);
		if(length == 1)
			return new Result(head.next, true);
		Result res = isPalindromeRecurse(head.next, length - 2);
//		if false then return or the pointer comes to an end
		if(!res.result || res.node == null)
			return res;
//		if true then see if the current value is same with the match position
		res.result = (head.val == res.node.val);
//		set node to next node
		res.node = res.node.next;
		return res;
	}

	private int getLength(ListNode head) {
		int size = 0;
		while(head!=null) {
			size++;
			head = head.next;
		}
		return size;
	}

	private boolean isEqual(ListNode head, ListNode rev) {
		while(head!=null && rev!=null) {
			if(head.val != rev.val) return false;
			head = head.next;
			rev = rev.next;
		}
		return head==null && rev==null;
	}

	private ListNode reverse(ListNode head){
		ListNode reversedHead = null;
		while(head!=null) {
			ListNode n = new ListNode(head.val);
			n.next = reversedHead;
			reversedHead = n;
			head = head.next;
		}
		return reversedHead;
	}
	
	/*
	 * 2.7
	 * Intersection: Given two (singly) linked lists, determine if the two lists intersect.
	 * Return the intersecting node. Note that the intersection is defined based on reference, not value.
	 * That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node
	 * of the second linked list, then they are intersecting.
	 */
	public ListNode getIntersection(ListNode l1, ListNode l2) {
		/*
		 * approach 1: get length, and chop off the longer one by difference
		 */
//		int len1 = getLength(l1);
//		int len2 = getLength(l2);
//		ListNode ln = len1>len2?l1:l2;
//		ListNode sn = len1>len2?l2:l1;
//		while(len2>0) {
//			if(len1>len2) {
//				ln = ln.next;
//				len1--;
//				continue;
//			}
//			if(ln != sn) {
//				ln=ln.next;
//				sn=sn.next;
//				len1--;
//				len2--;
//				continue; 
//			}
//			return ln;
//		}
//		return ln;
		/*
		 * approach 2: scan both list together, when 1st comes to end, start from beginning of second. Do this similiarly to 2nd.
		 * the two pointers will meet if there is a intersection.
		 */
		boolean doneOnce1 = false;
		boolean doneOnce2 = false;
		ListNode first = l1;
		ListNode second = l2;
		while(true) {
			if(doneOnce1 && doneOnce2 && first == null && second == null) 
				return null;
			if(first == null) {
				doneOnce1 = true;
				first = l2;
			}
			if(second == null) {
				doneOnce2 = true;
				second = l1;
			}
			if(first == second) 
				return first;
			first = first.next;
			second = second.next;
		}
	}
	
	/*
	 * 2.8
	 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
	 * DEFINITION
	 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
	 * as to make a loop in the linked list.
	 * EXAMPLE
	 * Input: A->B->C->D->E->C (the same C as earlier)
	 * Output: C
	 */
	public ListNode loop(ListNode head) {
//		meet position would be loopsize - k which k represents distance from beginning to the beginning of loop
//		consider two cars racing, the fast car runs twice speed as the slow one
//		fast car has run m circles and slow one has run n circles before they meet
//		assume x is the distance between start of loop and position they meet
//		slow car has gone k + loopsize*n + x
//		fast car has gone k + loopsize*m + x
//		fast car should have gone twice distance as the slow one, so
//		2*(k + loopsize*n + x) = k + loopsize*m + x, then x = loopsize*(m - 2n) - k
//		loopsize*(m - 2n)  is start of loop, loopsize*(m - 2n) - k would be k distance away to the start
		ListNode slow = head;
		ListNode fast = head;
		while(fast!=null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) break;//found circle
		}
//		no circle
		if(fast == null || fast.next == null)
			return null;
//		since the distance from meeting point to start of loop is same as the distance from beginning to the start of loop,
//		both of them are equal to k
//		put slow to beginning and fast to meeting point, they will meet at the start of loop if they go at the same speed
		slow = head;
		while(slow!=fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
}

class Result {
	ListNode node;
	boolean result;
	public Result(ListNode n, boolean result) {
		this.node = n;
		this.result = result;
	}
}