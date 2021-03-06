package models;

public class DVD extends Material {

	private String director;
	public String getCatalogNo()
	{
		return catalogNo;
	}

	public int getRunningTime()
	{
		return runningTime;
	}

	public boolean getLicenced()
	{
		return licenced;
	}

	private String catalogNo;
	private int runningTime;
	private boolean licenced;
	
	public DVD(String id, String title, String branch, String director,
			String catalogNo, int runningTime) {
		super(id,  title, branch);
		this.director = director;
		this.catalogNo = catalogNo;
		this.runningTime = runningTime;
		licenced = false;
	}
	
	public DVD(String id, String barcode, String title, String catalogNo, int runningTime, boolean licenced, String branch) {
		super(id, barcode,title, branch);
				
		this.director = director;
		this.catalogNo = catalogNo;
		this.runningTime = runningTime;
		licenced = false;
	}
	
	public void licence() {
		licenced = true;
	}
	
	public boolean lend(Customer customer) {
		if(licenced) {
			return super.lend(customer);
		}
		else {
			return false;
		}
	}
	
	public int getLoanPeriod() {
		return 7;
	}
	
	@Override
	public String toString() {
		return "DVD: " + getID() + " " + getTitle() + "/ " + getDirector();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
}
