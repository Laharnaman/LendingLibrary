package models;

public abstract class Material {

	private String id;
	private String title;
	private String branch;
	private Customer borrower;
	private String barcode;

	public String getBarcode()
	{
		return barcode;
	}

	public Material(String id, String title, String branch) {
		this.id = id;
		this.title = title;
		this.branch = branch;
	}

	public Material(String id, String barcode, String title, String branch) {
		this.id = id;
		this.title = title;
		this.branch = branch;
		this.barcode=barcode;
	}
	
	public String getTitle() {
		return title;
	}

	public String getID() {
		return id;
	}


	public String getBranch()
	{
		return branch;
	}

	public void relocate(String newBranch) {
		this.branch = newBranch;
	}

	public boolean lend(Customer customer) {
		if (borrower == null) {
			borrower = customer;
			return true;
		} else {
			return false;
		}
	}

	// must be overridden
	public abstract int getLoanPeriod();

	@Override
	public String toString() {
		return "ID="+id + "TITLE:"+title+"BARCODE:"+ barcode;
	}

	@Override
	public boolean equals(Object obj) {
		/**
		 * only checks the book ID for equality.
		 */
		if (obj == null) {
			return false;
		}

		if (getClass() == obj.getClass()) {
			Material otherClass = (Material) obj;
			if (id == otherClass.id) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
