package Connection;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modal.User;

public class DBConnection {
	
	Session session = null;
	SessionFactory sf = null;
	Configuration cfg = null;
	Properties prop = null;
	
	public Session getsession() {
		prop = new Properties();
		prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/cla");
		prop.setProperty("hibernate.connection.username", "root");
		prop.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernte.show_sql", "true");
		cfg = new Configuration();
		cfg.addProperties(prop).addAnnotatedClass(User.class);
		sf = cfg.buildSessionFactory();
		session = sf.openSession();
		return session;
		
	}
}
