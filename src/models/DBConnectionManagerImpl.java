package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManagerImpl implements DBConnectionManager

{
	Connection conn;
	PreparedStatement preparedStatement;
	Statement statement;
	public String DATBASE_URL = "jdbc:derby://localhost/library";

	public DBConnectionManagerImpl()
	{

		try
		{
			conn = DriverManager.getConnection(DATBASE_URL);
			System.out.println("connection successful");
		}
		catch (Exception e)
		{
			System.out.println("connection to DB failed");
			throw new RuntimeException();
		}

	}

	@Override
	public Connection getDBConnection()
	{
		return conn;
	}

	@Override
	public PreparedStatement getPreparedStatement(String sql)
	{
		PreparedStatement pstm = null;
		try
		{
			Connection conn = this.getDBConnection();
			pstm = conn.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return pstm;
	}

	@Override
	public ResultSet getQueryResults(String sql) throws Exception
	{
		try
		{
			try
			{
				Statement stm = this.getStatement();
				return stm.executeQuery(sql);
			}
			finally
			{
				if (conn != null)
				{
//					conn.close();
//					System.out
//							.println("Connection was closed in getQueryResults()");
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@Override
	public void setDBConnectionURL(String url)
	{
		this.DATBASE_URL = url;

	}

	@Override
	public Statement getStatement()
	{
		Statement statement = null;
		try
		{
			Connection conn = this.getDBConnection();
			statement = conn.createStatement();

		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return statement;
	}

	@Override
	public void closeConnection()
	{
		if (conn != null )
			try
			{
				conn.close();
				System.out.println("DBConnectionManager closed connection");
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
