package models.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import models.Customer;
import models.CustomerAlreadyExistsException;
import models.CustomerNotFoundException;
import models.CustomerRecords;

import org.junit.Test;

import utilities.GenderType;

public class CustomerRegistryTest {
	CustomerRecords customerRegistry;
	// Create customers
	Customer customer;
	Customer customer2;
	Customer customer3;
	int noOfCustomers;

	public CustomerRegistryTest() {
		customerRegistry = new CustomerRecords();
		// Create customers
		customer = new Customer("Mr", "Michael", "Smith", "1 The High Street",
				"1234", "a@b.com", 1, GenderType.MALE);
		customer2 = new Customer("Mr", "John", "Kelly", "53 Sultan Street",
				"1235", "a@b.com", 1, GenderType.MALE);
		customer3 = new Customer("Mrs", "Mary", "Kelly", "53 Sultan Street",
				"1236", "a@b.com", 1, GenderType.FEMALE);
		noOfCustomers = this.customerRegistry.getCustomerRegistrySize();

		try {
			customerRegistry.add(customer);
			customerRegistry.add(customer2);
			customerRegistry.add(customer3);

		} catch (CustomerAlreadyExistsException e) {
			System.out
					.println("WARNING: Junit constructor customer already exists");
		}
	}

	@Test
	public void testAdd() {
		int customerRegistrySize = customerRegistry.getCustomerRegistrySize();
		Customer customer4 = new Customer("Mrs", "Robin", "Kelly",
				"12 Walnut Park", "1234", "a@b.com", 1, GenderType.FEMALE);
		try {
			customerRegistry.add(customer4);
			assertTrue(customerRegistrySize == customerRegistry
					.getCustomerRegistrySize() - 1);
		} catch (CustomerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			fail("FAIL: Customer registry did not increase by 1 after adding customer");

		}
	}

	@Test
	public void testFindCustomer() {
		try {
			System.out.println(customer2.getMailingName());
			Customer foundCustomer = this.customerRegistry
					.findByName("Mr J Kelly");
			assertNotNull(
					"Checking if valid customer was actually found and returned in registry",
					foundCustomer);
		} catch (CustomerNotFoundException e) {
			fail("JUNIT: customer not found");
		}
	}

	@Test
	public void testForDuplicates() {
		try {

			this.customerRegistry.add(customer3);
			assertTrue(noOfCustomers == this.customerRegistry
					.getCustomerRegistrySize());
		} catch (CustomerAlreadyExistsException e) {
			System.out.println("customer already exists");
		}
	}
}// end of class