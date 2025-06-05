package com.example;

public class Bank {
	int amount = 2000;
	public static void main(String[] args) {
		Bank b1 = new Bank();
		Bank b2 = new Bank();
		b1.amount += 5000;
		System.out.println("First Object "+b1.amount);
		System.out.println("Second Object "+b2.amount);
	}
}