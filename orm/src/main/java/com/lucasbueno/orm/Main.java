package com.lucasbueno.orm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.lucasbueno.orm.model.Folder;

public class Main {
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {

		// adiciono uma pasta
		entityManagerFactory = Persistence.createEntityManagerFactory("com.lucasbueno.orm");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Folder("Teste"));
		entityManager.getTransaction().commit();
		entityManager.close();

		// recupero as pastas
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Folder> result = entityManager.createQuery("from Folder", Folder.class).getResultList();
		for (Folder folder : result)
			System.out.println("Folder (" + folder.getName() + ")");
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}

}
