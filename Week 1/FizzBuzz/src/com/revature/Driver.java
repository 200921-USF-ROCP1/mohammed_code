package com.revature;

public class Driver {
	
	public static void main(String[] args) {
		Driver.FizzBuzz(15);
		
	}
	
	public static void FizzBuzz(int a) {
		for(int i = 1; i <= a; i++) {
			// divisible by 3 and 5?
			if(i % 15 == 0) System.out.println("FizzBuzz"); 
			
			// divisible by 3?
			else if(i % 3 == 0) System.out.println("Fizz");
			
			// divisible by 5?
			else if(i % 5 == 0) System.out.println("Buzz"); 
			
			else System.out.println(i);
		}
	}

}
