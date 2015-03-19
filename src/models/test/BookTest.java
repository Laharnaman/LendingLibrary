package models.test;

import static org.junit.Assert.*;

import models.Book;

import org.junit.Test;

public class BookTest {

	@Test
	public void test2EqualBooks() {
		Book book1 = new Book("3", "An introduction to Java", "Matt Greencroft",
				"12345", "Anytown Branch", 400);
		Book book2 = new Book("3", "Better Java", "Joe Le Blanc", "23456",
				"Anytown Branch", 150);

		assertEquals("testing book equality", book1, book2);
		assertTrue(book1.equals(book2));

	}

	@Test
	public void test2NonEqualBooks() {
		Book book1 = new Book("4", "An introduction to Java", "Matt Greencroft",
				"12345", "Anytown Branch", 400);
		Book book2 = new Book("5", "Better Java", "Joe Le Blanc", "23456",
				"Anytown Branch", 150);

		assertNotSame("testing book INequality", book1, book2);
		assertFalse(book1.equals(book2));
	}
}
