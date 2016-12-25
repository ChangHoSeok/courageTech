package kr.pe.courage.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteVersionCheck {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:src/test/resources/sample.db");
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(30);
			
			stmt.executeUpdate("drop table if exists person");
			stmt.executeUpdate("create table person (id integer, name string)");
			stmt.executeUpdate("insert into person values(1, 'leo')");
			stmt.executeUpdate("insert into person values(2, 'yui')");
			ResultSet rs = stmt.executeQuery("select * from person");
			
			while (rs.next()) {
				System.out.println("name = " + rs.getString(2));
				System.out.println("id = " + rs.getInt(1));
			}
			
			rs.close();
			
			rs = stmt.executeQuery("select sqlite_version()");
			
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
