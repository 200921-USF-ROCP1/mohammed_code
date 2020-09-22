package com.revature;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Driver {
	/*
	 * Using your Calculator implementation,
	 * add exception handling to it and complete
	 * the method calculate() below.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Calculator!");
		
		
		// The calculate method is run in an infinite loop, 
		// i.e. until the program ends
		while (true) {
			calculate();
		}
	}
	
	/*
	 * Calculate should:
	 * 1. Take input via the Scanner class
	 * 2. Parse the input (this is a part of the Calculator interface)
	 * 3. If it is valid input, output the result
	 * 4. If it is invalid, output an error
	 * 
	 * Because it is in an infinite loop, you only need 
	 * to do those four steps.
	 */
	public static void calculate() {
		Scanner sc = new Scanner(System.in);
		CalculatorImpl calculator = new CalculatorImpl();
		
		System.out.println("Enter your command below:");
		String operation = sc.nextLine();
		try {
			System.out.println(calculator.parse(operation));
		}catch (Exception e) {
			System.out.println("Incorrect Command");
		}
		
	}
}