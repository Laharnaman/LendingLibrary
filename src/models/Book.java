package models;

public class Book extends Material {

	private String author;
	private String isbn;
	private int noOfPages;
	private String barcode;
	
	//checking git
	//checking git again

	// new Book("50001", "I2324343", "An introduction to Java","Matt Greencroft","12345",999,"Anytown Branch");
	
	 public Book(String id, String barcode, String title, String author, String isbn, int noOfPages, String branch)
		{
			super(id, title, branch);
			this.barcode = barcode;
			this.author = author;
			this.isbn = isbn;		
			this.noOfPages = noOfPages;
		}
	 
	public String getBarcode()
	{
		return barcode;
	}

	public Book(String id, String title, String author, String isbn, String branch, int noOfpages) 
	{
		super(id,title,branch);
		this.author = author;
		this.isbn = isbn;
		this.noOfPages= noOfpages;
	}
	

	public int getNoOfPages()
	{
		return noOfPages;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void sendForRepair() {
		System.out.println("Book has been sent for repair");
	}
	
	public int getLoanPeriod() {
		return 21;
	}
	@Override
	public String toString() {
		return "Book: " + getID() + " " + getTitle() + "/ " + getAuthor();
	}
	
}
