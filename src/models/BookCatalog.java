package models;
//chapter 22 carry on xxx
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class BookCatalog {
	//private LinkedHashMap<String, Book> bookCatalog ;
	private TreeMap<String, Book> bookCatalog;
	
	public BookCatalog() {
		//bookCatalog = new LinkedHashMap<String, Book>();
		bookCatalog = new TreeMap<String, Book>();
	}
	public TreeMap<String,Book> getBookMap() {
		return bookCatalog;
	}

	public void addBook(Book newBook) {
		bookCatalog.put(newBook.getID(), newBook);
	}

	public Book findBook(int id) throws BookNotFoundException {
		//TODO implement findBook with String arg.
		Book found = bookCatalog.get(id);
		if (found == null) {
			throw new BookNotFoundException();
		} else {
			return found;
		}
	}

	public Book findBook(String title) throws BookNotFoundException {
		for (Book next: bookCatalog.values()) {
			if(next.getTitle().equalsIgnoreCase(title.trim())) {
				return next;
			}
		}
		throw new BookNotFoundException();
	}
	
	public int getNumberOfBooks() {
		return bookCatalog.size();
	}

}
