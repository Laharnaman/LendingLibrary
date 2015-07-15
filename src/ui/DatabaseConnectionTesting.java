package ui;
//continue at ch 27 18:00
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import models.Material;
import models.MaterialCatalogDatabaseVersion;
import models.MaterialCatalogInterface;
import models.MaterialNotFoundException;

public class DatabaseConnectionTesting {

	public static void main(String[] args) {
		RetrieveResultSet("select title from materials");
		//Test number of materials in DB
//		System.out.println("Test number of materials in DB");
		MaterialCatalogInterface mci = new MaterialCatalogDatabaseVersion();
		System.out.println(mci.getNumberOfMaterials());
//		MaterialCatalogDatabaseVersion mci2 =  new MaterialCatalogDatabaseVersion();
		
		
		try
		{
			System.out.println("Test find materials in DB");
			Material materialFound = mci.findMaterial("Bob on ");
			System.out.println("Title of material found is "+ materialFound.getTitle());
//			
			
			System.out.println("=========== List of all materials ===============");
//			mci2.printAllMaterials();
			
			
//			System.out.println("\n\n\n=========== Material map getMaterialMap===============");
//			TreeMap<String, Material> materialsList = mci.getMaterialMap();
//			
//			for(Material m : materialsList.values()) {
//				System.out.println( m.toString());
//			}
//			System.out.println("Books/DVDs found = " +materialsList.size());
			
			
		} catch (MaterialNotFoundException e)
		{
			System.out.println("ERROR: Could not find that title");
			e.printStackTrace();
		}
//		System.out.println("Testing mci---------------");
//		MaterialCatalogInterface mci = new MaterialCatalogDatabaseVersion();
//		Book book1 = new Book("ID014", "BAR0014", "Mysticism revisited","Evelyn Underhill","12345",999,"Anytown Branch");
//		mci.addMaterial(book1);
//		
//		DVD dvd1 = new DVD("ID003","BAR000", "Bob on not yet on Holiday","CAT002", 120, true, "Central Lending");
//		mci.addMaterial(dvd1);
	}
	
	public static void ChangeData2(String sql, String title, int id) {
		try {

			Class.forName("org.apache.derby.jdbc.ClientDriver");

			try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost/library")) {
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					stm.setString(1, title);
					stm.setInt(2, id);
					int results = stm.executeUpdate();
					System.out.println("records amended : " + results);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	public static void ChangeData(String sql) {
		try {

			Class.forName("org.apache.derby.jdbc.ClientDriver");

			try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost/library")) {
				try (Statement stm = conn.createStatement()) {
					int results = stm.executeUpdate(sql);
					System.out.println("records amended : " + results);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	public static void RetrieveResultSet(String sql) {

		try {

			Class.forName("org.apache.derby.jdbc.ClientDriver");

			try (Connection conn = DriverManager
					.getConnection("jdbc:derby://localhost/library")) {
				try (Statement stm = conn.createStatement()) {
					try (ResultSet rs = stm
							.executeQuery(sql)) {
						while (rs.next()) {
							System.out.println(rs.getString("TITLE"));
						}
					}
				}
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	
}
