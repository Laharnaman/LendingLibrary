import models.Book;
import models.DVD;
import models.Material;
import models.MaterialCatalog;
import models.MaterialNotFoundException;


public class MaterialCatalogTest {

	public static void main(String[] args)  {
		MaterialCatalog materialCatalog = new MaterialCatalog();
		Book newBook = new Book("3456","Java Interfaces","Bob kelly","232323","Local Branch",300);
		DVD newDVD = new DVD("D123","DVD Java Demos", "Central", "Bob Kelly", "D12345",60);
		
		materialCatalog.addMaterial(newBook);
		materialCatalog.addMaterial(newDVD);
		for(Material material: materialCatalog.getMaterialMap().values()) 
		{
			System.out.println(material.toString());
		}
	
		try {
			System.out.println(materialCatalog.findMaterial("DVD Java Demos"));
		} catch (MaterialNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Material not found");
		}
	}

}
