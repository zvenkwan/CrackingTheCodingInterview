package chapter3;

import java.util.Stack;
public class StackandQueue {
	
	public static void main(String args[]) {
		StackandQueue saq = new StackandQueue();
//		SetOfStacks s = new SetOfStacks();
//		s.push(1);
//		s.push(2);
//		s.push(4);
//		s.push(6);
//		s.push(3);
//		s.push(1);
//		s.push(11);
//		s.push(23);
//		s.push(45);
//		s.push(56);
//		s.push(87);
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.pop();
//		s.push(45);
//		s.push(56);
//		s.push(87);
		Stack<Integer> s = new Stack<Integer>();
		s.push(5);
		s.push(3);
		s.push(9);
		s.push(1);
		s.push(2);
		s.push(7);
		Stack<Integer> sorted = saq.sort(s);
		System.out.println(sorted);
	}
	/*
	 * 3.1
	 * Three in One: Describe how you could use a single array to implement three stacks.
	 */
	
	
	/*
	 * 3.2
	 * Stack Min: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
	 * Push, pop and min should all operate in O(1) time.
	 */
	private Stack<Integer> myStack;
	private Stack<Integer> minStack;
	public void push(int i) {
		if(myStack == null) {
			myStack = new Stack<Integer>();
			minStack = new Stack<Integer>();
		}
		minStack.push(myStack.peek()<i?myStack.peek():i);
		myStack.push(i);
	}
	
	public void pop() {
		myStack.pop();
		minStack.pop();
	}
	
	public int min() {
		return minStack.peek();
	}
	
	/*
	 * 3.3
	 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely
	 * to start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
	 * composed of several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should behave
	 * identically to a single stack( that is, pop() should return the same values as it would if there were just a single stack).
	 * FOLLOW UP
	 * Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
	 */
//	please see SetOfStacks.java
	
	/*
	 * 3.4
	 * Implement a MyQueue class which implements a queue using two stacks
	 */
//	please see MyQueue.java
	
	/*
	 * 3.5
	 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, 
	 * but you may not copy the elements into any other data structure (such as an array). The stack supports the following operations: push, pop, peek,
	 * and isEmpty.
	 */
	public Stack<Integer> sort(Stack<Integer> unsorted) {
		Stack<Integer> sorted = new Stack<Integer>();
		if(unsorted == null || unsorted.isEmpty())
			return sorted;
		Integer temp = unsorted.pop();
		while(!unsorted.isEmpty()) {
			if(sorted.isEmpty()) {
				sorted.push(temp);
				temp = null;
			}
			temp = temp==null? unsorted.pop(): temp;
			if(temp <= sorted.peek()) {
				sorted.push(temp);
				temp = null;
			}
			else {
				unsorted.push(sorted.pop());
			}
		}
		return sorted;
	}
	
	/*
	 * 3.6
	 * Animal Shelter: An animall shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis. People must adopt either the "oldest"
	 * (based on arrival time) of all animalls at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
	 * that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as enqueue
	 * ,dequeueAny, dequeueDog, and dequeueCat. You may use the built-in LinkedList data structure.
	 */
//	see AnimalShelter.java
}
