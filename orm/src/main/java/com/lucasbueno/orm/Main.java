package com.lucasbueno.orm;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import com.lucasbueno.orm.database.Conn;
import com.lucasbueno.orm.model.File;
import com.lucasbueno.orm.model.Folder;
import com.lucasbueno.orm.model.User;

public class Main {

	public static void main(String[] args) {

		// crio as entidades
		User user = new User("Lucas");
		user.setDataCadastro(new GregorianCalendar(2019, Calendar.AUGUST, 24).getTime());
		User user3 = new User("Teste");
		User user4 = new User("Oi");
		File file = new File("nao-abra.txt");
		Folder folder = new Folder("pasta-oculta");
		file.shareWith(user);
		folder.addFile(file);

		// as salvo no banco
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.persist(user3);
		em.persist(user4);
		em.persist(folder);
		em.persist(file);
		em.getTransaction().commit();
		em.close();

		// as recupero do banco
		em = Conn.getEntityManager();
		User user2 = em.find(User.class, "Lucas"); // recuperando um usuario especifico
		System.out.println(user2.getDataCadastro());

		List<User> users = em.createQuery("select user from User as user", User.class).getResultList();

		System.out.println("Usuários:");
		for (User u : users)
			System.out.println(u.getName());

		String valor  = "Oi";
		users = em.createQuery("select user from User as user where user.name = ?1", User.class).setParameter(1, valor)
				.getResultList();
		
		System.out.println("Usuários filtrados:");
		for (User u : users)
			System.out.println(u.getName());	
		
		em.close();
		Conn.closeConn();
	}
}