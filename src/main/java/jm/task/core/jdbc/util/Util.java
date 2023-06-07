package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
	//private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
	//private final static String USERNAME = "root";
	//private final static String PASSWORD = "Rooot";
	//private Connection connection = null;
	//Statement statement = null;
	private SessionFactory sessionFactory = null;
	private static Util util = null;

	public Util(){
		sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		//connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		//System.out.println(connection.isClosed());

	}
	public SessionFactory getSessionFactory() { return sessionFactory;}

	/*public Connection getConnection() {
		return connection;
	}*/
	public static Util getUtil(){
		if(util == null) {
			util = new Util();
		}
		return  util;
	}
}
