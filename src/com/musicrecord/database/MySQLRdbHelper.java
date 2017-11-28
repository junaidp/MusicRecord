package com.musicrecord.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.musicrecord.shared.Records;
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

    public ArrayList<Records> fetchRecords(HashMap<String, String> map) throws Exception {
	Session session = null;
	ArrayList<Records> listRecords = new ArrayList<Records>();
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(Records.class);
	    crit.createAlias("category", "catego");
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Records record = (Records) it.next();
		listRecords.add(record);

	    }
	    return listRecords;

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in getAuthentication", ex.getMessage()), ex);
	    System.out.println("Exception occured in getAuthentication" + ex.getMessage());

	    throw new Exception("Exception occured in getAuthentication");
	} finally {
	    session.close();
	}

    }

    public String editRecord(Records record) throws Exception {
	Session session = null;

	try {
	    session = sessionFactory.openSession();
	    session.update(record);
	    session.flush();
	    return "record updated";

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in getAuthentication", ex.getMessage()), ex);
	    System.out.println("Exception occured in getAuthentication" + ex.getMessage());

	    throw new Exception("Exception occured in getAuthentication");
	} finally {
	    session.close();
	}
    }

    public String deleteRecord(Records record) throws Exception {
	Session session = null;

	try {
	    session = sessionFactory.openSession();
	    session.delete(record);
	    session.flush();
	    return "record deleted";

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in getAuthentication", ex.getMessage()), ex);
	    System.out.println("Exception occured in getAuthentication" + ex.getMessage());

	    throw new Exception("Exception occured in getAuthentication");
	} finally {
	    session.close();
	}
    }

}
