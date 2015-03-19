package models.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import models.Book;
import models.Material;

import org.junit.Test;

public class MaterialTest {

	@Test
	public void testEquals() {
		Material mat1 = new Book("88","","","","",214);
		Material mat2 = new Book("88","","","","",214);
	//	assertEquals("[Checking equals()]",mat1,mat2);
		assertTrue("Checking equals with assertTrue", mat1.equals(mat2));
		assertFalse("Checking inequality>>",!mat1.equals(mat2));
	}

}
