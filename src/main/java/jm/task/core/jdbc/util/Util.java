package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
//import org.hibernate.mapping.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Util {
	private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
	private static final String USERNAME = "root";
//	private static final String PASSWORD = "Rooot";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

//	private static Util util = null;
	//Configuration cfg;
	private static SessionFactory sessionFactory = null;
	//public Util() {}

	public static SessionFactory getSessionFactory () {
		if (sessionFactory == null) {
			try {

				Properties properties = new Properties();
				properties.put(Environment.DRIVER, DRIVER);
				properties.put(Environment.URL, URL);
				properties.put(Environment.USER, USERNAME);
				properties.put(Environment.PASS, PASSWORD);
				properties.put(Environment.FORMAT_SQL, "true");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				properties.put(Environment.SHOW_SQL, "true");
				properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				properties.put(Environment.HBM2DDL_AUTO, "create");
				properties.put(Environment.POOL_SIZE, "5");

				sessionFactory = new Configuration()
						.setProperties(properties)
						.addAnnotatedClass(User.class)
						.buildSessionFactory();

			} catch (Throwable ex) {
				System.err.println("build SessionFactory failed :" + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}

//	public static Util getUtil(){
//		if(util == null) {
//			util = new Util();
//		}
//		return  util;
//	}

}