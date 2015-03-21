package models;

import java.util.HashSet;

public class CustomerRecords {
	private HashSet<Customer> customerRegistry = new HashSet<Customer>();
	
	public boolean add(Customer customer) throws CustomerAlreadyExistsException {
		try {
			if (this.findByName(customer.getMailingName()) != null) {
				throw new CustomerAlreadyExistsException();
			}
		} catch (CustomerNotFoundException e) {
				this.customerRegistry.add(customer);
		}
		return false;
	}
	
	public Customer findByName(String customerName) throws CustomerNotFoundException {
		for(Customer customer : this.customerRegistry) {
			if(customer.getMailingName().equalsIgnoreCase(customerName)) {
				return customer;
			}
		}
		throw new CustomerNotFoundException();
	}

	public void printCustomerList() {
		for(Customer customer:this.customerRegistry) {
			System.out.println(customer.toString());
		}
		
	}

	public HashSet<Customer> getCustomerRegistry() {
		return customerRegistry;
	}
}
