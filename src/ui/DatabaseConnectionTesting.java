package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTesting {

	public static void main(String[] args) {
		RetrieveResultSet("select * from materials");
		ChangeData("update materials set title = 'Test title' where id=1");
		ChangeData2("update materials set title = ? where id= ?", ">>>>Another new title from prepared statement",3);
		RetrieveResultSet("select * from materials");
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
