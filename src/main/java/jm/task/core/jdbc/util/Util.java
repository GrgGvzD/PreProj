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

/*public class Util {
	private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Rooot";
	//private Connection connection = null;
	//Statement statement = null;
	private SessionFactory sessionFactory = null;
	private static Util util = null;


	public Util(){
		Configuration cfg = new Configuration()
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.connection.username",USERNAME)
				.setProperty("hibernate.connection.password", PASSWORD)
				.setProperty("hibernate.connection.url",URL)
				.configure();
		sessionFactory = cfg.addAnnotatedClass(User.class).buildSessionFactory();
//		sessionFactory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
		//connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		//System.out.println(connection.isClosed());

	}
	public SessionFactory getSessionFactory() { return sessionFactory;}

	/*public Connection getConnection() {
		return connection;
	}*/
	/*public static Util getUtil(){
		if(util == null) {
			util = new Util();
		}
		return  util;
	}
}*/

public class Util {
	private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Rooot";
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

	private static Util util = null;
	//Configuration cfg;
	private static SessionFactory sessionFactory = null;
	public Util() {
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
				System.err.println("build SeesionFactory failed :" + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
	}

	public static SessionFactory getSessionFactory () {
		return sessionFactory;
	}

	public static Util getUtil(){
		if(util == null) {
			util = new Util();
		}
		return  util;
	}

}