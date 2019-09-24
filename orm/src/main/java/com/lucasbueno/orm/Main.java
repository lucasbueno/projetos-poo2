package com.lucasbueno.orm;

import javax.persistence.EntityManager;

import com.lucasbueno.orm.database.Connection;
import com.lucasbueno.orm.model.File;
import com.lucasbueno.orm.model.Folder;
import com.lucasbueno.orm.model.User;

public class Main {

	public static void main(String[] args) {

		Connection.getEntityManagerFactory();

		User user = new User("Lucas");
		File file = new File("nao-abra.txt");
		Folder folder = new Folder("pasta-oculta");
		file.shareWith(user);
		folder.addFile(file);

		// adiciono um arquivo

		EntityManager entityManager = Connection.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(file);
		entityManager.persist(folder);
		entityManager.getTransaction().commit();
		entityManager.close();

		Connection.closeConn();

	}
}