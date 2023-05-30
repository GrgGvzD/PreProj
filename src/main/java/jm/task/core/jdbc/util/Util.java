package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
	private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Rooot";
	private Connection connection = null;
	//Statement statement = null;
	private static Util util = null;

	public Util(){
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println(connection.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return connection;
	}
	public static Util getUtil(){
		if(util == null) {
			util = new Util();
		}
		return  util;
	}
}
