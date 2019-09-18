package com.lucasbueno.orm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.lucasbueno.orm.model.File;
import com.lucasbueno.orm.model.User;

public class Main {

	public static void main(String[] args) {
		
		User user = new User("Lucas");
		File file = new File("nao-abra.txt");
		file.shareWith(user);

		// adiciono um arquivo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.lucasbueno.orm");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(file);
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}
}