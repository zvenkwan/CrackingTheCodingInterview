package chapter3;

import java.util.Stack;
public class SetOfStacks {
	/*
	 * 3.3
	 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely
	 * to start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
	 * composed of several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should behave
	 * identically to a single stack( that is, pop() should return the same values as it would if there were just a single stack).
	 * FOLLOW UP
	 * Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
	 */
	private Stack<Stack<Integer>> sos;
	private int size;
	private static final Integer LIMIT = 10;
	
	public SetOfStacks() {
		sos = new Stack<Stack<Integer>>();
	}
	
	public void push(int i) {
		Stack<Integer> sub ;
		if(sos.isEmpty() || size == LIMIT) {
			sub = new Stack<Integer>();
			size = 0;
			sos.push(sub);
		}
		sub = sos.peek();
		sub.push(i);
		size++;
		System.out.println("current size is "+size);
	}
	
	public void pop() {
		if(sos == null || sos.isEmpty()) {
			throw new NullPointerException();
		}
		sos.peek().pop();
		if(--size == 0) {
			sos.pop();
			size = 10;
		}
		System.out.println("current size is "+size);
	}
}
