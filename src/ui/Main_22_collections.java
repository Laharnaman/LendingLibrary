package ui;

import models.Book;
import models.BookCatalog;
import models.BookNotFoundException;

public class Main_22_collections {

	public static void main(String[] args) {
		Book book1 = new Book("1", "ABCddddd", "", "", "", 200);
		BookCatalog bc = new BookCatalog();
		bc.addBook(book1);
		try {
			Book found = bc.findBook(book1.getTitle());
			System.out.println("book added: " + found.getID()
					+ found.getTitle());
			found = bc.findBook("ABCddddd");
			System.out.println("book added: " + found.getID()
					+ found.getTitle());
		} catch (BookNotFoundException e) {
			System.out.println("book not added...somehow " + e.getMessage());
		}
		
		UI ui = new UI();
		ui.printHeader();
		ui.printBookCatalog(bc.getBookMap());
	}
}
