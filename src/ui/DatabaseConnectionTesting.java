package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTesting {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {

			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver");

				conn = DriverManager
						.getConnection("jdbc:derby://localhost/library");
				stm = conn.createStatement();

				rs = stm.executeQuery("SELECT * FROM MATERIALS");

				while (rs.next()) {
					System.out.println(rs.getString("TITLE"));
				}
			} finally {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
				if (conn != null) {
					conn.close();
					System.out.println("conn was closed");

				}
				System.out.println("finally was run");
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
