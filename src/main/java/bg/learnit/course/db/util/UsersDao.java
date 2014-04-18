package bg.learnit.course.db.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import bg.learnit.course.db.model.Users;


public class UsersDao {

	public Users getUser(int empId) {
		Session sess = null;
		try {
			sess = HibernateUtil.getSession();
			return (Users) sess.get(Users.class, empId);
		} catch (HibernateException e) {
			e.printStackTrace();// Later remove this by appropriate logger
								// statement or throw custom exception
		}
		return null;
	}

	public static void main(String[] args) {
		UsersDao dao = new UsersDao();
		Users user = dao.getUser(1);
		if (user != null) {
			System.out.println(user.getId() + " " + user.getEmail());
		}
	}
}