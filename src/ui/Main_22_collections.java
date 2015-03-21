package ui;
//continue at 41:19
//TODO testcase for CustomerRecords
import models.Book;
import models.BookCatalog;
import models.Customer;
import models.CustomerAlreadyExistsException;
import models.CustomerRecords;
import models.Loan;
import models.LoansRegistry;
import utilities.GenderType;

public class Main_22_collections {

	public static void main(String[] args) {
		Book book1 = new Book("1", "More Java", "", "", "", 200);
		Customer customer = new Customer("Mr", "John", "Kelly", "", "", "",
				1234, GenderType.MALE);
		Customer customer2 = new Customer("Mrs", "Mary", "Midgeley", "", "", "",
				1235, GenderType.FEMALE);
		CustomerRecords customerRecords = new CustomerRecords();
	
		Loan loan = new Loan(9999, customer, book1);
		LoansRegistry loanRegistry = new LoansRegistry();
		BookCatalog bc = new BookCatalog();

		
		try {
			customerRecords.add(customer);
			customerRecords.add(customer2);
			System.out.println("Number of registered customers:"+customerRecords.getCustomerRegistry().size());
			//customerRecords.add(customer);
			//System.out.println("Number of registered customers:"+customerRecords.getCustomerRegistry().size());
		} 
		catch (CustomerAlreadyExistsException e) {
			System.out.println("Customer already exists");
		}
		
		customerRecords.printCustomerList();
//		bc.addBook(book1);
//		try {
//			Book found = bc.findBook(book1.getTitle());
//			// System.out.println("book added: " + found.getID()
//			// + found.getTitle());
//			// found = bc.findBook("ABCddddd");
//			// System.out.println("book added: " + found.getID()
//			// + found.getTitle());
//		} catch (BookNotFoundException e) {
//			System.out.println("book not added...somehow " + e.getMessage());
//		}
//
//		UI ui = new UI();
//		ui.printHeader();
//		ui.printBookCatalog(bc.getBookMap());
//
//		try {
//			loanRegistry.addLoan(loan);
//		//	loanRegistry.addLoan(loan);
//		} catch (LoanAlreadyExistsException e) {
//			System.out.println("Loan already exists with ID:"
//					+ e.getLoanToAdd().getBook().getID());
//		}

	}
}
