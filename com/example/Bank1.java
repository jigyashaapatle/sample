package com.example;

public class Bank1 {
	static int amount = 2000;
	
	public static void main(String[] args) {
		Bank1 b1 = new Bank1();
		Bank1 b2 = new Bank1();
		
		b1.amount += 5000;
		
		System.out.println("First Object "+b1.amount);
		System.out.println("Second Object "+b2.amount);
		System.out.println("Class Level "+Bank1.amount);
	}
}
