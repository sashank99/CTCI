package stacks_queues;

import java.util.LinkedList;

/**
 * An animal shelter, which holds only dogs and cats, operates on a strictly
 * "first in, first out" basis. People must adopt either the "oldest" (based on
 * arrival time) of all animals at the shelter, or they can select whether they
 * would prefer a dog or a cat ( and will receive the oldest animal of that
 * type). They cannot select which specific animal they would like. Create the
 * data structures to maintain this system and implement operations such as
 * enqueue, dequeueAny, dequeueDog and dequeueCat. You may use the built in
 * LinkedList data structure.
 */
public class AnimalShelter {
	private LinkedList<Dog> dogList;
	private LinkedList<Cat> catList;
	private int orderCounter;

	public AnimalShelter() {
		dogList = new LinkedList<>();
		catList = new LinkedList<>();
	}

	public void enqueue(Animal animal) {
		animal.setOrder(++orderCounter);
		if (animal instanceof Dog) {
			dogList.add((Dog)animal);
		} else {
			catList.add((Cat)animal);
		}
	}

	@Override
	public String toString() {
		return "DogList:::"+dogList.toString()+"\n"+"CatList:::"+catList.toString();
	} 
	public Animal dequeue() {

		if (dogList.isEmpty()) {
			return dequeueCat();
		} else if (catList.isEmpty()) {
			return dequeueDog();
		}

		Dog dog = dogList.peek();
		Cat cat = catList.peek();

		int val = dog.compareTo(cat);
		if (val < 0) {
			return dequeueDog();
		} else {
			return dequeueCat();
		}

	}

	public Dog dequeueDog() {
		return dogList.remove();
	}

	public Cat dequeueCat() {
		return catList.remove();
	}
	
	public static void main(String[] args) {
		AnimalShelter animalShelter=new AnimalShelter();
		Dog dog1=new Dog("a");
		Dog dog2=new Dog("b");
		Dog dog3=new Dog("c");
		
		Cat cat1=new Cat("a");
		Cat cat2=new Cat("b");
		Cat cat3=new Cat("c");
		animalShelter.enqueue(dog1);
		animalShelter.enqueue(cat1);
		
		animalShelter.enqueue(dog2);
		animalShelter.enqueue(cat2);
		
		animalShelter.enqueue(dog3);
		animalShelter.enqueue(cat3);
		System.out.println(animalShelter);
		animalShelter.dequeueDog();
		
		System.out.println(animalShelter);
		
	}
}

class Animal {
	private int order;
	private String name;

	public Animal(String name) {
		this.name = name;
	}

	public void setOrder(int order){
		this.order=order;
	}

	public int getOrder() {
		return order;
	}

	
	public int compareTo(Animal b) {
		return this.order - b.order;
	}
	
	@Override
	public String toString() {
		return order+":"+name;
	}
}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
	}
}

class Cat extends Animal {
	public Cat(String name) {
		super(name);
	}
}
