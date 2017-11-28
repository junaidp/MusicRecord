package com.musicrecord.database;

import org.apache.log4j.Logger;
import org.hibernate.MappingNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySQLRdbStorage {
    protected final static Logger log = Logger.getLogger(MySQLRdbStorage.class.getName());
    private static ApplicationContext ctx = null;

    public static ApplicationContext getSpringApplicationContext() throws Exception {
	try {
	    if (ctx == null) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	    }
	} catch (MappingNotFoundException mnfe) {
	    System.out.println("Exception in getStorageFacility() Hibernate mappings getMessage = " + mnfe.getMessage()
		    + "\n getPath = " + mnfe.getPath());
	    throw new Exception("fail", mnfe);
	}
	return ctx;
    }

}
