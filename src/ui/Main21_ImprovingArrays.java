package ui;
//ArrayList, HashSet, HashMap
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import models.Book;

public class Main21_ImprovingArrays {

	public static void main(String[] args) {
		
		ArrayList<String> myArrayList = new ArrayList<String>();
		HashSet<String> hset = new HashSet<String>();
		hset.add("first");
		hset.add("second");
		hset.add("third");
		hset.add("fourth");
		hset.add("fourth");  //won't appear in the list
		
		myArrayList.add("first item");
		boolean result = myArrayList.add("second item");
		
		System.out.println(result);
		myArrayList.add("third item");
		myArrayList.add("fourth item");
		
		System.out.println(myArrayList.size());
		
		
		myArrayList.remove(1);
		myArrayList.add("fifth item");
		
//		for(int i=0;i<myArrayList.size();i++) {
//			System.out.println(myArrayList.get(i));
//		}
//		System.out.println("------------");
//	
//		myArrayList.add(1, "inserted at pos 1");
//		
//		for(int i=0;i<myArrayList.size();i++) {
//			System.out.println(myArrayList.get(i));
//		}
		Iterator<String> iter = hset.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		HashMap<String, Book> hMap = new HashMap<String,Book>();
		
		Book book1 = new Book("1","first book","","","",200);
		Book book2 = new Book("2","second book","","","",200);
		Book book3 = new Book("3","third book","","","",200);
		
		hMap.put(book1.getTitle(), book1);
		hMap.put(book2.getTitle(), book2);
		hMap.put(book3.getTitle(), book3);
		
		System.out.println("sizeof hashmap "+hMap.size());
		
		hMap.remove(book2.getTitle());
		System.out.println("sizeof hashmap "+hMap.size());
		
		Iterator<Book> myValues = hMap.values().iterator();
		
		//Iterator<String> iter = hset.iterator();
		while (myValues.hasNext()) {
			System.out.println(myValues.next().getTitle());
		}
		
	}

}
