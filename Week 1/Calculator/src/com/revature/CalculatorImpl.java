package com.revature;

public class CalculatorImpl implements Calculator{

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public int subtract(int a, int b) {
		return a - b;
	}

	@Override
	public int multiply(int a, int b) {
		return a*b;
	}

	@Override
	public int divide(int a, int b) {
		if (b == 0) {
			System.out.println("Can't divide by 0");
			return 0;
		}
		
		return a/b;
	}

	@Override
	public int exponent(int x, int e) {
		return (int) Math.pow(x,  e);
	}

	@Override
	public int fibonacci(int i) {
				
		// TODO Auto-generated method stub
		if (i < 2) {
			return i;
		}
		return fibonacci(i-1) + fibonacci(i-2);
	}

	@Override
	public int parse(String s)  {
		String[] operation = s.split(" ");
		String operator = operation[1];
		
		if(Character.isDigit(operation[0].charAt(0))) {
			try {
				int number1 = Integer.parseInt(operation[0]);
				int number2 = Integer.parseInt(operation[2]);
				
				switch(operator) {
					case "+":
						return add(number1, number2);
					case "-":
						return subtract(number1, number2);
					case "*":
						return multiply(number1, number2);
					case "/":
						return divide(number1, number2);
					default:
						System.out.println("Invalid Command");
						return 0;
						
				}
			}catch ( NumberFormatException e) {
				e.printStackTrace();	
				return 0;
			}
		}else {
			
			try {
				int number1 = Integer.parseInt(operation[1]);
				switch(operation[0]) {
					case "fib":
						return fibonacci(number1);
					case "exp":
						int number2 = Integer.parseInt(operation[2]);			
						return exponent(number1, number2);
					default:
						return 0;
				}
			}catch ( NumberFormatException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
}
