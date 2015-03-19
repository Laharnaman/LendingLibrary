package models.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import models.Book;
import models.Customer;
import models.Loan;

import org.junit.Test;

import utilities.GenderType;

public class LoanTest {

	@Test
	public void testDueDate() {
		//Book book = new Book(1,"ddddd","Bob Kelly","333-3333-333","larne",200);
		Book book = new Book("1","","","","",0);
		Customer customer = new Customer("","","","","","",0,GenderType.MALE);
		Loan loan = new Loan(0,customer,book);
		
		
		GregorianCalendar gcExpected = new GregorianCalendar();
		gcExpected.add(GregorianCalendar.DAY_OF_MONTH, 14);
		
		GregorianCalendar gcActual = new GregorianCalendar();
		gcActual.setTime(loan.getDueDate());
		
		assertEquals("Checking year",gcExpected.get(GregorianCalendar.YEAR), gcActual.get(GregorianCalendar.YEAR));
		assertEquals("Checking month",gcExpected.get(GregorianCalendar.MONTH), gcActual.get(GregorianCalendar.MONTH));
		assertEquals("Checking day of month",gcExpected.get(GregorianCalendar.DAY_OF_MONTH), gcActual.get(GregorianCalendar.DAY_OF_MONTH));
		
		
	}

}
