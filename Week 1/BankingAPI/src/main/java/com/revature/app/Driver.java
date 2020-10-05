package com.revature.app;

import java.util.Scanner;

import com.revature.models.Role;
import com.revature.models.User;
// @#._$%&*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
public class Driver {

	static User newUser;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String nameString = "nonsense"; //onse
		char[] mychars = nameString.toCharArray();
		StringBuilder sb = new StringBuilder();
//		System.out.println(mychars);
		for (int i = 0; i < mychars.length; i++) {
			boolean dup = false;
			for (int j = i; j < mychars.length - 1; j++) {
//				System.out.println(mychars[i] + " and " + mychars[j] + "= " + (mychars[i] == mychars[j]));
//				dup = mychars[i] == mychars[j];
				if (mychars[i] == mychars[j+1]) {
//					System.out.println(mychars[i] + " and " + mychars[j + 1] + "= " + (mychars[i] == mychars[j + 1]));
					dup = true;
					break;
				}
			}
//			System.out.println(dup);
			if (!dup) {
				sb.append(mychars[i]);
			}
			
		}
		
		System.out.println(sb.toString());
//		// Display menu
//		displayMenu();
//		int userChoice = sc.nextInt();
//		
//		while (userChoice > 0 || userChoice < 6) {
//
//			switch (userChoice) {
//				case 1:
//					newUser = createAccount();
//					System.out.println("Account created successfully!");
//					displayMenu();
//					userChoice = sc.nextInt();
//					break;
//				case 2:
//					logIn();
//					displayMenu();
//					userChoice = sc.nextInt();
//					break;
//					
//				case 3:
//					editMyInfo();
//					break;
//					
//				case 4:
//					logOut();
//					break;
//					
//				case 5:
//					transferFunds();
//					break;
//					
//				case 6:
//					System.out.println("Good bye!");
//					break;
//					
//				default:
//					System.out.println("Please choose the correct number from the given choice: ");
//					displayMenu();
//					userChoice = sc.nextInt();
//					break;
//				}
//		}
	
	}

	static void displayMenu() {
		System.out.println("****************************");
		System.out.println("* Welcome to Z's Bank! *");
		System.out.println("****************************");

		System.out.println("What would you like to do? (Enter a number from the following choices: ");
		System.out.println("\t1. Create an Account \n" + "\t2. Log In\n" + "\t3. Edit My Info\n" + "\t4. Log Out\n"
				+ "\t5. Transfer Funds\n" + "\t6. Exit");
	}

	static User createAccount() {

		Scanner scanner = new Scanner(System.in);

		
		System.out.println("Enter your username: ");
		String userName= scanner.nextLine();
		
		System.out.println("Enter your password: ");
		String password= scanner.nextLine();
		
		System.out.println("Enter your first name: ");
		String firstName= scanner.nextLine();
		
		System.out.println("Enter your last name: ");
		String lastName= scanner.nextLine();
		
		System.out.println("Enter your email: ");
		String userEmail= scanner.nextLine();
		
		return new User(userName, password, firstName, lastName, userEmail, new Role("customer"));
	}

	static void logIn() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Username: ");
		String userName = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		if (newUser.validateCredentials(userName, password)) {
			System.out.println(newUser.toString());
		}else {
			System.out.println("Incorrect username or password, pleaser try again");
		}
		
	}

	static void editMyInfo() {

	}

	static void logOut() {
		newUser = null;
		System.out.println("You've successfully logged out!");
		System.out.println("Good bye!");
	}

	static void transferFunds() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount: ");
		int amount = sc.nextInt();
		
	}
	
}
