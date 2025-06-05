package main;

import java.util.ArrayList;

abstract class Employee {
	private String name;
	private int id;
	
	public Employee(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public abstract double calculateSalary();
	
	@Override
	public String toString() {
		return "Employee [name = "+name+ " id = " +id+ " salary = " + calculateSalary()+ "]";
	}
}

class FullTimeEmployee extends Employee {
	private double monthlySalary;
	
	public FullTimeEmployee(String name, int id, double monthlySalary) {
		super(name, id);
		this.monthlySalary = monthlySalary;
	}
	
	@Override
	public double calculateSalary() {
		return monthlySalary;
	}
}

class PartTimeEmployee extends Employee {
	private int hoursWorked;
	private double hourlyRate;
	

	public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
		super(name, id);
		this.hoursWorked = hoursWorked;
		this.hourlyRate = hourlyRate;
	}
	
	@Override 
	public double calculateSalary() {
		return hoursWorked * hourlyRate;
	}
	
}

class PayRollSystem{
	private ArrayList<Employee> employeeList;
	
	public PayRollSystem() {
		employeeList = new ArrayList<>();
	}
	
	public void addemployee(Employee employee) {
		employeeList.add(employee);
	}
	
	public void removeEmployee(int id) {
		Employee employeeToRemove = null;
		for(Employee employee : employeeList) {
			if(employee.getId() == id) {
				employeeToRemove = employee;
				break;
			}
		}
		
		if(employeeToRemove!=null) {
			employeeList.remove(employeeToRemove);
		}else {
			System.out.println("Employee with id "+id+ " doesn't exist.");
		}
	}
	
	public void displayEmployees() {
		for(Employee employee : employeeList) {
			System.out.println(employee);
		}
	}
}

public class Main {

	public static void main(String[] args) {
		PayRollSystem payRollSystem = new PayRollSystem();
		FullTimeEmployee emp1 = new FullTimeEmployee("Rohit", 1, 45000.50);
		PartTimeEmployee emp2 = new PartTimeEmployee("Sagar", 2, 40, 100);
		
		payRollSystem.addemployee(emp1);
		payRollSystem.addemployee(emp2);
		
		System.out.println("Initial Employee Details : ");
		payRollSystem.displayEmployees();
		
		System.out.println("Removing Employees ");
		payRollSystem.removeEmployee(1);
		
		System.out.println("Remaining Employee Details : ");
		payRollSystem.displayEmployees();
	}
}