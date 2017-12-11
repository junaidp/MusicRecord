package com.musicrecord.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.musicrecord.shared.Category;
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
	    crit.createAlias("roleId", "role");

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
	int count = 0;
	String searchBy = map.get("searchBy");
	String keyWord = map.get("keyWord");
	ArrayList<Records> listRecords = new ArrayList<Records>();
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(Records.class);
	    crit.createAlias("category", "cat");
	    if (keyWord.trim().length() > 0) {
		if (searchBy.equalsIgnoreCase("title")) {
		    crit.add(Restrictions.ilike("title", keyWord, MatchMode.START));
		} else if (searchBy.equalsIgnoreCase("artist")) {
		    crit.add(Restrictions.like("artist", keyWord, MatchMode.START));
		} else if (searchBy.equals("All")) {
		    Disjunction disc = Restrictions.disjunction();
		    disc.add(Restrictions.like("artist", keyWord, MatchMode.START));
		    disc.add(Restrictions.like("title", keyWord, MatchMode.START));
		    crit.add(disc);
		}
	    }
	    count = crit.list().size();
	    crit.setMaxResults(Integer.parseInt(map.get("length")));
	    String sortorder = map.get("sortOrder");
	    crit.setFirstResult(Integer.parseInt(map.get("start")));
	    if (sortorder.equalsIgnoreCase("asc")) {
		crit.addOrder(Order.asc(map.get("sortField")));
	    } else {
		crit.addOrder(Order.desc(map.get("sortField")));
	    }
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Records record = (Records) it.next();
		record.setCount(count);
		listRecords.add(record);

	    }
	    return listRecords;

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in Fetch Records", ex.getMessage()), ex);
	    throw new Exception("Exception occured in Fetch Records");
	} finally {
	    session.close();
	}

    }

    public String saveRecord(Records record) throws Exception {
	Session session = null;

	try {
	    session = sessionFactory.openSession();
	    session.saveOrUpdate(record);
	    session.flush();
	    return "Record Saved";

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in save record", ex.getMessage()), ex);
	    throw new Exception("Exception occured in save record");
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
	    logger.warn(String.format("Exception occured in Delete Record", ex.getMessage()), ex);
	    throw new Exception("Exception occured in Delete Record");
	} finally {
	    session.close();
	}
    }

    public ArrayList<Category> fetchCategories() throws Exception {
	Session session = null;

	ArrayList<Category> listCategories = new ArrayList<Category>();
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(Category.class);

	    List csList = crit.list();

	    for (Iterator it = csList.iterator(); it.hasNext();) {

		Category category = (Category) it.next();
		listCategories.add(category);

	    }
	    return listCategories;

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in Fetch Categories", ex.getMessage()), ex);
	    throw new Exception("Exception occured in fetchCategories");
	} finally {
	    session.close();
	}

    }
}
