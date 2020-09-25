package com.revature.generic.arraylist;

/*
 * For this activity, simply implement
 * all of the below methods!
 */
public class ArrayList<T> {
	T[] arr;
	int count;

	public ArrayList() {
		arr = (T[]) new Object[10];
		count = 0;
	}
	public void add(T t) {
		if (count < arr.length) {
			arr[count] = t;
			count++;
		}else {
			T[] newArr = (T[]) new Object[arr.length * 2];
			
			for (int i = 0; i < arr.length; i++) {
				newArr[i] = arr[i];
			}
			
			arr = newArr;
			
			add(t);
		}
	}
	
	public T get(int i) {
		return arr[i];
	}
	
	public int size() {
		return arr.length;
	}
	
	public int count() {
		return count;
	}
	
	/*
	 * Split should take arr and split into a number of subarrays.
	 * The number is given by numberOfNewArrays.
	 * 
	 * eg if [1, 5, 6, 5, 7] and input 3
	 * so the output would be: 
	 * [
	 *   [1, 5],
	 *   [6, 5],
	 *   [7, null]
	 * ]
	 */
	public T[][] split(int numberOfNewArrays) {
		int newSize;
		if (count % numberOfNewArrays == 0) {
			newSize = count/numberOfNewArrays;
		}else {
			newSize = (count/numberOfNewArrays) + 1;
		}
		T[][] returnVal= (T[][]) new Object[numberOfNewArrays][newSize];
		int currentIndex = 0;
		for (int i = 0; i < returnVal.length; i++) {
			for (int j = 0; j < returnVal[i].length; j++) {
				if (currentIndex >= count) {
					break;
				}
				returnVal[i][j] = arr[currentIndex];
				currentIndex++;
			}
		}
		return returnVal;
	}
	
	public String toString() {
		StringBuilder ab = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			ab.append(arr[i]);
			
			if (i + 1 < arr.length) {
				ab.append(", ");
			}
		}
		
		return ab.toString();
		
	}
}