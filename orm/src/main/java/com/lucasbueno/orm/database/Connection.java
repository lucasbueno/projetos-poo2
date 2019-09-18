package com.lucasbueno.orm.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

	private static EntityManagerFactory entityManagerFactory;

	public static EntityManagerFactory getConn() {
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("com.lucasbueno.orm");
		return entityManagerFactory;
	}

	public static void closeConn() {
		if (entityManagerFactory != null)
			entityManagerFactory.close();
	}

}
