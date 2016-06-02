package chapter3;

import java.util.LinkedList;

public class AnimalShelter {
	
	LinkedList<Cat> catList;
	LinkedList<Dog> dogList;
	private int index = 0;
	public AnimalShelter() {
		catList = new LinkedList<Cat>();
		dogList = new LinkedList<Dog>();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void enqueue(Animal a){
		a.setIndex(index++);
		if(a instanceof Cat)
			catList.add((Cat) a);
		else if(a instanceof Dog)
			dogList.add((Dog) a);
	}
	
	public Cat dequeueCat(){
		return catList.poll();
	}
	
	public Dog dequeueDog(){
		return dogList.poll();
	}
	
	public Animal dequeueAny(){
		if(catList.size()==0)
			return dequeueDog();
		else if(dogList.size()==0)
			return dequeueCat();
		Dog d = dogList.peek();
		Cat c = catList.peek();
		if(d.getIndex() < c.getIndex())
			return dequeueDog();
		else
			return dequeueCat();
	}
	
}

class Animal {
	private int index;
	protected String name;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Animal(String name){
		this.name = name;
	}
}

class Cat extends Animal{

	public Cat(String name) {
		super(name);
	}
}

class Dog extends Animal{

	public Dog(String name) {
		super(name);
	}
	
}
