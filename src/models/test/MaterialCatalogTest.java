package models.test;

//start of chapter 21 begun 15-MAR.
//cloned from initial git master which ended with chaper 20 JUnit testing.
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import models.Book;
import models.Material;
import models.MaterialCatalog;
import models.MaterialNotFoundException;

import org.junit.Test;

public class MaterialCatalogTest {
	private MaterialCatalog bc;
	private Book book1;

	public MaterialCatalogTest() {
		book1 = new Book("1", "Learning Java", "", "", "", 300);
		bc = new MaterialCatalog();
		bc.addMaterial(book1);
		// System.out.println("constructor run");
	}

	@Test
	public void testNumberOfBooks() {
		int initialNumber = bc.getNumberOfMaterials();
		Material newBook = new Book("11", "", "", "", "", 200);
		bc.addMaterial(newBook);
		assertTrue("Checking if no of books increments",
				initialNumber == bc.getNumberOfMaterials() - 1);
	}

	@Test
	public void testFindBook() {

		Material findBook = null;
		try {
			findBook = bc.findMaterial("Learning Java");
			assertNotNull("Checking if find book exists in MaterialCatalog",
					findBook);
		} catch (MaterialNotFoundException e1) {
			fail("book not found");
		}
	}

	@Test(expected = MaterialNotFoundException.class)
	public void testFindBookThatDoesntExist() throws MaterialNotFoundException {

		Material findBook = bc.findMaterial("Learning Javaxx");

	}

	@Test
	public void testFindABookIgnoringTheCase() {

		Material findBook = null;
		try {
			findBook = bc.findMaterial("learning java");

		} catch (MaterialNotFoundException e) {
			fail("book not found");
		}
	}

	@Test
	public void testFindABookWithExtraSpaces() {

		Material findBook = null;
		try {
			findBook = bc.findMaterial("learning java  ");

		} catch (MaterialNotFoundException e) {
			fail("book not found");
		}
	}

	@Test
	public void testAddABook() {
		try {
			int currentSize=bc.getMaterialMap().size();
			System.out.println(currentSize);
			Book addedBook = new Book("2","","","","",200);
			bc.addMaterial(addedBook);
			System.out.println(bc.getMaterialMap().size());
			assertTrue(currentSize == bc.getNumberOfMaterials()-1);
		} catch (Exception e) {
			fail("could not add book");
		}

	}
}
