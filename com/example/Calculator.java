package com.example;

public class Calculator {
	void square(int x) 
	{
		int result = x*x; //local variable
		System.out.println("Square is "+result);
	}
	
	static void add(int x, int y)
	{
		System.out.println("The addition of "+x+" and "+y+ " is "+(x+y));
	}
	
	int cube(int x)
	{
		return (x*x*x);
	}
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
		c.square(5);
		Calculator.add(22,33);
		c.add(56, 78);
		add(6,4);
		int output = c.cube(5);
		System.out.println(output);
		System.out.println(c.cube(5));
	}
}