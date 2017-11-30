package com.musicrecord.server;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicrecord.database.MySQLRdbHelper;
import com.musicrecord.shared.Category;
import com.musicrecord.shared.Records;
import com.musicrecord.shared.User;

public class MusicHelper {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");

    public User signIn(String userid, String password) throws Exception {

	User user = (User) rdbHelper.getAuthentication(userid, password);
	if (user != null) {
	    // session = getThreadLocalRequest().getSession(true);
	    //
	    // session.setAttribute("user", user);
	}
	return user;
    }

    public ArrayList<Records> fetchRecords(HashMap<String, String> map) throws Exception {

	return rdbHelper.fetchRecords(map);
    }

    public String saveRecord(Records record) throws Exception {
	return rdbHelper.saveRecord(record);
    }

    public String deleteRecord(Records record) throws Exception {
	return rdbHelper.deleteRecord(record);
    }

    public ArrayList<Category> fetchCategories() throws Exception {

	return rdbHelper.fetchCategories();
    }
}
