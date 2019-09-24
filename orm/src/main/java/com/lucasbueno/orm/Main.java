package com.lucasbueno.orm;

import javax.persistence.EntityManager;

import com.lucasbueno.orm.database.Conn;
import com.lucasbueno.orm.model.File;
import com.lucasbueno.orm.model.Folder;
import com.lucasbueno.orm.model.User;

public class Main {

	public static void main(String[] args) {

		// crio as entidades
		User user = new User("Lucas");
		File file = new File("nao-abra.txt");
		Folder folder = new Folder("pasta-oculta");
		file.shareWith(user);
		folder.addFile(file);

		// as salvo no banco
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.persist(file);
		em.persist(folder);
		em.getTransaction().commit();
		em.close();
		
		// as recupero do banco
		
		

		Conn.closeConn();
	}
}