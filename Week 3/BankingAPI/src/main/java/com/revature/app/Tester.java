package com.revature.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tester {
	Map<Integer, ArrayList<Integer>> arrays = new HashMap<>();
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	
    public static ArrayList<Integer> qualified(int N, int A[]) {
    	
        int result=-404;
        int[] previosMarks = new int[A.length];
        ArrayList<Integer> qualifiedRolls = new ArrayList<Integer>();
//        int[] qualifiedRolls = new int[A.length];
        qualifiedRolls.add(0);
        previosMarks[0] = A[0];
        //write your Logic here:
        for (int i = 1; i < A.length; i++) {
            if(A[i] > max(previosMarks)) {
            	qualifiedRolls.add(i);
            }
            previosMarks[i] = A[i];
        }
        
        return qualifiedRolls;
    }
    
    public static int max(int marks[]) {
        int max = marks[0];
        for (int i =1; i < marks.length; i++ ) {
            if (max < marks[i]) {
                max = marks[i];
            }
        }
        
        return max;
    }
//    public static void main(String[] args) {
//        // INPUT [uncomment & modify if required]
////        Scanner sc = new Scanner(System.in);
////        int N = sc.nextInt();
////        
////        int A [] = new int[N];
////        for(int i=0; i<N; i++) {
////            A[i] = sc.nextInt();
////        }
////    	int A [] = {10, 23, 50, 5, 32, 15, 3};
////
////        // OUTPUT [uncomment & modify if required]
////        System.out.print(qualified(A.length,A).toString());
//    	
//    	
//
//    }
    
    
    
    
//	public static void main(String[] args) {
//		int[][] A = {{1, 3}, {2, 7}, {6, 9}};
//		
//		System.out.println(maximumPeople(A.length, A));
//	}
//	
//	public static int maximumPeople(int N,int[][] A)
//    {
//        int result=-404;
//  
//        //write your Logic here:
//
//        int counter = 0;
//        
//        for (int i = 0; i < A.length - 1; i++ ) {
//            if (A[i][1] > A[i + 1][1]) {
//                counter++;
//            }
//        } 
//
//        return counter;
//    }
	
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n=sc.nextInt();
        sc.nextLine();
        
        Map<Integer, ArrayList<Integer>> arrays = new HashMap();
        ArrayList<Integer[]> queries = new ArrayList();

        for(int i=0;i<n;i++)
        {
            ArrayList<Integer> al = new ArrayList();
            int d = sc.nextInt();
            for (int j=0;j<d;j++){
                al.add(sc.nextInt());
            }
            arrays.put(i+1, al);
            sc.nextLine();
        }
        
        
        int q = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<q;i++)
        {
        	
        	int x = sc.nextInt();
            int y = sc.nextInt();
            Integer[] query = {x, y};
            queries.add(query);
            sc.nextLine();
        }
        
        
        for (Integer[] query : queries) {
        	int x = query[0];
        	int y = query[1];

        	 if(arrays.containsKey(x)) {
                 ArrayList<Integer> al = arrays.get(x);
                 if (y > al.size()) {
                     System.out.println("Error");
                 }else {
                     System.out.println(al.get(y-1));
                 }
             }
        }
    }
	
}