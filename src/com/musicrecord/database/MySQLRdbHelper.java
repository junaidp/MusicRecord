package com.musicrecord.database;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.musicrecord.shared.User;

public class MySQLRdbHelper {

    private SessionFactory sessionFactory;
    Logger logger;

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public User getAuthentication(String userid, String password) throws Exception {

	User users = null;
	Session session = null;
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(User.class);
	    crit.add(Restrictions.eq("name", userid));
	    crit.add(Restrictions.eq("password", password));
	    // crit.createAlias("roleId", "role");

	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		users = (User) it.next();
		System.out.println(users.getPassword());
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in getAuthentication", ex.getMessage()), ex);
	    System.out.println("Exception occured in getAuthentication" + ex.getMessage());

	    throw new Exception("Exception occured in getAuthentication");
	} finally {
	    session.close();
	}

	return users;
    }

    // public UserTest getAuthenticationtest(String userid, String password)
    // throws Exception {
    //
    // UserTest users = null;
    // Session session = null;
    // try {
    // session = sessionFactory.openSession();
    //
    // Criteria crit = session.createCriteria(User.class);
    // crit.add(Restrictions.eq("name", userid));
    // crit.add(Restrictions.eq("password", password));
    // List rsList = crit.list();
    // for (Iterator it = rsList.iterator(); it.hasNext();) {
    // users = (UserTest) it.next();
    // System.out.println(users.getPassword());
    // }
    //
    // } catch (Exception ex) {
    // logger.warn(String.format("Exception occured in getAuthentication",
    // ex.getMessage()), ex);
    // System.out.println("Exception occured in getAuthentication" +
    // ex.getMessage());
    //
    // throw new Exception("Exception occured in getAuthentication");
    // } finally {
    // session.close();
    // }
    //
    // return users;
    // }

}
