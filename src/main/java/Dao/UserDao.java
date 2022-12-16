package Dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Connection.DBConnection;
import Modal.User;

public class UserDao {
	SessionFactory sf = null;
	Session session = null;
	Transaction tx = null;
	List<User> list = null;

	public void insertUser(User u) {
		try {
			session = new DBConnection().getsession();
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User logi(User u ) {
		User u1 = null;
		try {
			session = new DBConnection().getsession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from User u where u.email=:email and u.password=:password");
			q.setParameter("email",u.getEmail());
			q.setParameter("password", u.getPassword());
			list = q.list();
			u1 = list.get(0);
			System.out.println(u1);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u1;
		
	}
	public User getdatabyid(int id) {
		User u = null;
		try {
			session = new DBConnection().getsession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from User u where u.id=:id");
			list = q.list();
			u = list.get(0);
			System.out.println(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public User updateUser(User u) {
		try {
			session = new DBConnection().getsession();
			tx = session.beginTransaction();
			Query q = session.createQuery("update User u set u.name=?1,u.contact=?2,u.address=?3,u.email=?4,u.password=?5 where u.id=?6");
			q.setParameter(1, u.getName());
			q.setParameter(2, u.getContact());
			q.setParameter(3, u.getAddress());
			q.setParameter(4, u.getEmail());
			q.setParameter(5, u.getPassword());
			q.setParameter(6, u.getId());
			q.executeUpdate();
			System.out.println(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public User deleteUser(int id) {
		User u = null;
		session = new DBConnection().getsession();
		tx = session.beginTransaction();
		Query q = session.createQuery("delete from User u where u.id=?1");
		q.setParameter(1, id);
		q.executeUpdate();
		tx.commit();
		session.close();
		return u;
		
		
	}
	
}