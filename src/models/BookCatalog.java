package models;

import java.util.TreeMap;

//chapter 22 carry on xxx

public class BookCatalog {
	// private LinkedHashMap<String, Book> bookCatalog ;
	// private TreeMap<String, Book> bookCatalog;

	private TreeMap<String, Book> bookMap;

	public BookCatalog() {
		super();
		bookMap = new TreeMap<String, Book>();
	}

	public BookCatalog(Book book) {
		bookMap.put(book.getID(), book);
	}

	public TreeMap<String, Book> getBookMap() {
		return bookMap;
	}

	public void addBook(Book newBook) {
		bookMap.put(newBook.getID(), newBook);
	}

	public Book findBook(int id) throws BookNotFoundException {
		// TODO implement findBook with String arg.
		Book found = bookMap.get(new Integer(id));
		if (found == null) {
			throw new BookNotFoundException();
		} else {
			return found;
		}
	}

	public Book findBook(String title) throws BookNotFoundException {
		for (Book next : bookMap.values()) {
			if (next.getTitle().equalsIgnoreCase(title.trim())) {
				return next;
			}
		}
		throw new BookNotFoundException();
	}

	public int getNumberOfBooks() {
		return bookMap.size();
	}

}
