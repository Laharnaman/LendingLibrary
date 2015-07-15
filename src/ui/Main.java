package ui;
// continue at 30:54
import models.Book;
import models.Customer;
import models.CustomerAlreadyExistsException;
import models.CustomerRecords;
import models.DVD;
import models.Loan;
import models.LoanAlreadyExistsException;
import models.LoansRegistry;
import models.Material;
import models.MaterialCatalogDatabaseVersion;
import models.MaterialCatalogInterface;
import models.MaterialNotFoundException;
import utilities.GenderType;

public class Main {

	public static void main(String[] args) {

		
		
		MaterialCatalogInterface materialCatalog = new MaterialCatalogDatabaseVersion();
		
		
		Book book1 = new Book("5002","An introduction to Java","Matt Greencroft","12345","Anytown Branch", 400);
		Book book2 = new Book("323x2","Better Java","Joe Le Blanc","23456","Anytown Branch",150);
		Book book6 = new Book("6202","Les Miserables 6","Jule Verne","","ANy town",200);
		Book book3 = new Book("2202","Les Miserables 1","Jule Verne","","ANy town",200);
		Book book4 = new Book("14x2","Les Miserables 2","Jule Verne","","ANy town",200);
		Book book5 = new Book("342","Les Miserables 3","Jule Verne","","ANy town",200);
		DVD dvd1 = new DVD("D122","An Epic Film About Java","Anytown Branch","Stephen Spielberg","99887",120);
		DVD dvd2 = new DVD("D132","An Epic Film About Java","Anytown Branch","Stephen Spielberg","99887",120);
//
//		System.out.println(dvd1.getTitle());
//		book1.relocate("MyCity branch");


//		materialCatalog.addMaterial(book1);
//		materialCatalog.addMaterial(book2);
//		materialCatalog.addMaterial(book6);
//		materialCatalog.addMaterial(book3);
//		materialCatalog.addMaterial(book4);
//		materialCatalog.addMaterial(book5);
//		materialCatalog.addMaterial(dvd1);
//		materialCatalog.addMaterial(dvd2);
		
		
		System.out.println("there are "+ materialCatalog.getNumberOfMaterials() + " items in the library");
		
		// Create customers
		Customer customer = new Customer("Mr", "Michael", "Smith", "1 The High Street","1234","a@b.com",1,GenderType.MALE);
		Customer customer2 = new Customer("Mr", "John", "Kelly", "53 Sultan Street","1234","a@b.com",1,GenderType.MALE);
		Customer customer3 = new Customer("Mrs", "Mary", "Kelly", "53 Sultan Street","1234","a@b.com",1,GenderType.FEMALE);


		UI ui = new UI();
		ui.printHeader();
		System.out.println("\nPrinting material catalog");
		ui.printMaterialCatalog(materialCatalog.getMaterialMap());

		try {
			Material foundMaterial = materialCatalog.findMaterial("Better Java");
			System.out.println("We found " + foundMaterial.getTitle());
		}
		catch (MaterialNotFoundException e) {
			System.out.println("The book wasn't found");
		}

		int myTest = 1;

		try {
			if (myTest != 2) {
				throw new RuntimeException("Something went wrong");
			}
		}
		catch (RuntimeException e) {
			// do nothing here so that we can continue;
		}
		
		
		System.out.println(customer.getExpiryDate());
		System.out.println(customer.getMailingName());

//		System.out.println(customer);
//		System.out.println(dvd1);
//
//		System.out.println(dvd1.equals(dvd2));
//		System.out.println(customer.equals(customer));
		
		Loan firstLoan = new Loan(1,customer,book1);
		System.out.println(firstLoan.getDueDate());
		System.out.println(firstLoan);
		
		System.out.println("\nAdding a new loan------------------");
		LoansRegistry registry = new LoansRegistry();
		try {	
			registry.addLoan(firstLoan);
			System.out.println("addLoan worked");
			}
		catch (LoanAlreadyExistsException e) {
			System.out.println("addLoan failed");
		}
		System.out.println("\nAdding the same loan(should fail)------------------");
		try {	
			registry.addLoan(firstLoan);
			System.out.println("addLoan worked");
			}
		catch (LoanAlreadyExistsException e) {
			System.out.println("addLoan failed");
		}
		
		System.out.println("\nTest if book is on loan:" + registry.isBookOnLoan(book1.getID()));
		System.out.println("\nEnd the loan:");
		firstLoan.endLoan();
		System.out.println("\nTest if book was returned:" + registry.isBookOnLoan(book1.getID()));
		System.out.println("Is book still on loan: "+registry.isBookOnLoan(book1.getID()));
		
		System.out.println("\nAdd customer to customer registry");
		CustomerRecords customerRegistry = new CustomerRecords();
		try {
			customerRegistry.add(customer);
			System.out.println("Info: Customer successfully added to registry");
		} catch (CustomerAlreadyExistsException e) {
			System.out.println("Warning: Customer already exists");
		}
		
		System.out.println("\nAdd SAME customer to customer registry");
		try {
			customerRegistry.add(customer);
			System.out.println("Info: Customer successfully added to registry");
		} catch (CustomerAlreadyExistsException e) {
			System.out.println("Warning: Customer already exists");
		}
		
	}

}
