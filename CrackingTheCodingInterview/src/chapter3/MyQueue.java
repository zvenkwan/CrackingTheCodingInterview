package chapter3;

import java.util.Stack;

public class MyQueue<T> {

    Stack<T> stk , stk2 ;
    
    public MyQueue(){
    	stk = new Stack<T>();
    	stk2 = new Stack<T>();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyQueue<Integer> q = new MyQueue<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		q.add(5);
		System.out.println(q.element());
		q.remove();
		System.out.println(q.element());
		q.remove();
		System.out.println(q.element());
		q.remove();
		System.out.println(q.element());
		q.remove();
		q.add(6);
		q.add(7);
		System.out.println(q.element());
		q.remove();
		System.out.println(q.element());
		q.remove();
		q.remove();
		System.out.println(q.element());
	}

	public void add(T i) {
		stk.push(i);
	}
	
	public T element() {
		if(stk2.empty())
			shiftStack();
		return stk2.peek();
	}
	
	public T remove() {
		if(stk2.empty())
			shiftStack();
		return stk2.pop();
	}
	
	public void shiftStack() {
		while(!stk.isEmpty()) {
			stk2.push(stk.pop());
		}
	}
}
