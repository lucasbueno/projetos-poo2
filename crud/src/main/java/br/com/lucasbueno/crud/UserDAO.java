package br.com.lucasbueno.crud;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {
	private static ObservableList<User> users;

	public static ObservableList<User> getUsers() {
		if (users == null) {
			EntityManager entityMng = Conn.getEntityManager();
			users = FXCollections.observableArrayList(
					entityMng.createQuery("select user from User as user", User.class).getResultList());
			entityMng.close();
		}
		return users;
	}

	public static void addUser(User user) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(user);
		entityMng.getTransaction().commit();
		entityMng.close();
		getUsers().add(user);
	}
}
