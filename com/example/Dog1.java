package com.example;

public class Dog1 {
	static int legs = 4;
	static void eat()
	{
		System.out.println("Eat Bones");
	}
	
	public static void main(String[] args) {
		Dog1.eat();
		System.out.println(Dog1.legs);
		System.out.println(legs);
		
		Dog1 d1 = new Dog1();
		d1.eat();
		System.out.println(d1.legs);
	}
}
