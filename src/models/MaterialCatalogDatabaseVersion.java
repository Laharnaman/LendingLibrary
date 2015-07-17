package models;
// chp 27 36 mins on 15 July

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

public class MaterialCatalogDatabaseVersion implements MaterialCatalogInterface
{
	private int noOfMaterials;

	private TreeMap<String, Material> materialMap;

	private DBConnectionManager dbConnectionManager;
	 
	public MaterialCatalogDatabaseVersion()
	{
		materialMap = new TreeMap<String, Material>();
		try
		{
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		dbConnectionManager = new DBConnectionManagerImpl();
	}

	public int getNoOfMaterials()
	{
		return noOfMaterials;
	}

	public void printAllMaterials() {
		try
		{
			String sql = "select id, barcode, title, author, catalognumber,runningtime,licenced,branch,type from materials order by id ";

			try (Connection conn = DriverManager
					.getConnection("jdbc:derby://localhost/library"))
					{
				try (PreparedStatement stm = conn.prepareStatement(sql))
				{

					ResultSet rs = stm.executeQuery();

					while (rs.next()) {
						System.out.println(
								rs.getString("id") + " " + rs.getString("barcode") + " " + rs.getString("title") + " " + 
										rs.getString("type") + " "+  rs.getString("author") + " "  );
					}

				}
					}
		}  catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	@Override
	public void addMaterial(Material material)
	{
		if (material instanceof Book)
		{
			Book newBook = (Book)material;

			String sql = "insert into materials "
					+ "(barcode, title, author, isbn, numberOfPages, branch, type) values(?,?,?,?,?,?,?) ";

			this.addBookToDB(sql, newBook);

			materialMap.put(material.getID(), (Book) material);
		} else if (material instanceof DVD)
		{
			DVD newDVD = (DVD)material;

			String sql = "insert into materials "
					+ "(barcode, title, catalogNumber, runningTime, licenced, branch, type) values(?,?,?,?,?,?,?) ";

			this.addDVDToDB(sql, newDVD);

			materialMap.put(material.getID(), (DVD) material);
		}

	}

	@Override
	public TreeMap<String, Material> getMaterialMap()
	{
		
		String sql = "select * from materials";
		// get all materials
		try {

			ResultSet rs = dbConnectionManager.getQueryResults(sql);
			
			while (rs.next())
			{
				Material nextMaterial = createMaterial(rs);
				this.materialMap.put(nextMaterial.getID(), nextMaterial);
			}
			dbConnectionManager.closeConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materialMap;

	}

	private Material createMaterial(ResultSet rs) 
			throws SQLException,
			MaterialNotFoundException
	{
		Material materialToCreate=null;
		if (rs.getString("type").equals("BOOK")) {
			materialToCreate = new Book(rs.getString("id"),rs.getString("barcode"), 
					rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getInt("numberOfPages"), rs.getString("branch"));
		} else if (rs.getString("type").equals("DVD")) {
			materialToCreate = new DVD(rs.getString("id"), rs.getString("barcode"), rs.getString("title"), rs.getString("catalognumber"), 
					rs.getInt("runningtime"), rs.getBoolean("licenced"), rs.getString("branch"));

		} else
		{
			throw new MaterialNotFoundException();
		}
		return materialToCreate;
	}

	@Override
	public Material findMaterial(String title) throws MaterialNotFoundException
	{
		String sql = "select * from materials where title like " + "'%" + title + "%'";
		Material materialFound=null;

		try {

			try (Connection conn = DriverManager
					.getConnection("jdbc:derby://localhost/library")) {
				try (Statement stm = conn.createStatement()) {
					try (ResultSet rs = stm
							.executeQuery(sql)) {
						if(rs.next()) {
							materialFound = this.createMaterial(rs);
						} else {
							throw new MaterialNotFoundException();
						}

					}
				}
				return materialFound;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getNumberOfMaterials()
	{
		String sql = "select count(id) from materials ";

		updateNumberOfMaterials(sql);

		return this.getNoOfMaterials();
	}

	private void updateNumberOfMaterials(String sql)
	{

		try {

			try (Connection conn = DriverManager
					.getConnection("jdbc:derby://localhost/library")) {
				try (Statement stm = conn.createStatement()) {
					try (ResultSet rs = stm
							.executeQuery(sql)) {
						rs.next();//moves to first rec.
						this.noOfMaterials= rs.getInt(1);	//0 should contain number of hits

					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void addBookToDB(String sql, Book newBook) {
		try
		{

			try (Connection conn = DriverManager
					.getConnection("jdbc:derby://localhost/library"))
					{
				try (PreparedStatement stm = conn.prepareStatement(sql))
				{
					stm.setString(1, newBook.getBarcode());
					stm.setString(2, newBook.getTitle());
					stm.setString(3, newBook.getAuthor());
					stm.setString(4, newBook.getIsbn());
					stm.setInt(5, newBook.getNoOfPages());
					stm.setString(6, newBook.getBranch());
					stm.setString(7, "BOOK");


					int results = stm.executeUpdate();
					System.out.println("records amended : " + results);
				}
					}
		} catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public void addDVDToDB(String sql, DVD newDVD) {
		try
		{

			try (Connection conn = DriverManager
					.getConnection("jdbc:derby://localhost/library"))
					{
				try (PreparedStatement stm = conn.prepareStatement(sql))
				{
					stm.setString(1, newDVD.getBarcode());
					stm.setString(2, newDVD.getTitle());
					stm.setString(3, newDVD.getCatalogNo());
					stm.setInt(4, newDVD.getRunningTime());
					stm.setBoolean(5,newDVD.getLicenced());
					stm.setString(6, newDVD.getBranch());
					stm.setString(7, "DVD");


					int results = stm.executeUpdate();
					System.out.println("records added : " + results);
				}
					}
		} catch (SQLException e)
		{
			System.out.println(e);
		}
	}


}
