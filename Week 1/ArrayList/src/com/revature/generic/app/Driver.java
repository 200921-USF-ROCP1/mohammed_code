package com.revature.generic.app;

import com.revature.generic.arraylist.ArrayList;

public class Driver {

	public static void main(String[] args) {
		ArrayList<String>  myArray = new ArrayList<>();
		
		myArray.add("Zulkifl");
		myArray.add("Mohammed");
		
		for (int i = 0; i < myArray.count(); i++) {
			System.out.println(myArray.get(i));
		}

		ArrayList<Integer> myNumbers = new ArrayList<>();

		myNumbers.add(1);
		myNumbers.add(5);
		myNumbers.add(6);
		myNumbers.add(5);
		myNumbers.add(7);

		Object[][] split = myNumbers.split(3);
		System.out.println(split);
		for (int i = 0; i < split.length; i++) {
			for (int j = 0; j < split[i].length; j++) {
				System.out.print(split[i][j]);

				if (j + 1 < split[i].length) {
					System.out.print(", ");
				}
			}
			System.out.print("\n");
		}
	}
}
