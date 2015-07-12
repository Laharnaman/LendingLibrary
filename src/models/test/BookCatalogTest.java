package models.test;

//start of chapter 21 begun 15-MAR.
//cloned from initial git master which ended with chaper 20 JUnit testing.
import static org.junit.Assert.*;
import models.Book;
import models.BookCatalog;
import models.BookNotFoundException;

import org.junit.Test;

public class BookCatalogTest {
	private BookCatalog bc;
	private Book book1;

	public BookCatalogTest() {
		book1 = new Book("1", "Learning Java", "", "", "", 300);
		bc = new BookCatalog();
		bc.addBook(book1);
		// System.out.println("constructor run");
	}

	@Test
	public void testNumberOfBooks() {
		int initialNumber = bc.getNumberOfBooks();
		Book newBook = new Book("11", "", "", "", "", 200);
		bc.addBook(newBook);
		assertTrue("Checking if no of books increments",
				initialNumber == bc.getNumberOfBooks() - 1);
	}

	@Test
	public void testFindBook() {

		Book findBook = null;
		try {
			findBook = bc.findBook("Learning Java");
			assertNotNull("Checking if find book exists in BookCatalog",
					findBook);
		} catch (BookNotFoundException e1) {
			fail("book not found");
		}
	}

	@Test(expected = BookNotFoundException.class)
	public void testFindBookThatDoesntExist() throws BookNotFoundException {

		Book findBook = bc.findBook("Learning Javaxx");

	}

	@Test
	public void testFindABookIgnoringTheCase() {

		Book findBook = null;
		try {
			findBook = bc.findBook("learning java");

		} catch (BookNotFoundException e) {
			fail("book not found");
		}
	}

	@Test
	public void testFindABookWithExtraSpaces() {

		Book findBook = null;
		try {
			findBook = bc.findBook("learning java  ");

		} catch (BookNotFoundException e) {
			fail("book not found");
		}
	}

	@Test
	public void testAddABook() {
		try {
			int currentSize=bc.getBookMap().size();
			System.out.println(currentSize);
			Book addedBook = new Book("2","","","","",200);
			bc.addBook(addedBook);
			System.out.println(bc.getBookMap().size());
			assertTrue(currentSize == bc.getNumberOfBooks()-1);
		} catch (Exception e) {
			fail("could not add book");
		}

	}
}
