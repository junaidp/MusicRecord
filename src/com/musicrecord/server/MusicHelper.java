package com.musicrecord.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicrecord.database.MySQLRdbHelper;
import com.musicrecord.shared.User;

public class MusicHelper {

    public User signIn(String userid, String password) throws Exception {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
	User user = (User) rdbHelper.getAuthentication(userid, password);
	if (user != null) {
	    // session = getThreadLocalRequest().getSession(true);
	    //
	    // session.setAttribute("user", user);
	}
	return user;
    }

}
