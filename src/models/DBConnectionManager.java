package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DBConnectionManager
{
	
	public String sql=null;
	public Connection getDBConnection() ;
	public void closeConnection();
	public PreparedStatement getPreparedStatement(String sql) throws Exception;
	public Statement getStatement() throws Exception;
	public void setDBConnectionURL(String url);
	ResultSet getQueryResults(String sql) throws Exception;
}