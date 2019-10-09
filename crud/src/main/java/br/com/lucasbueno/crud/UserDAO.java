package br.com.lucasbueno.crud;

import java.util.List;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// uma implementação da interface com tipo genérico, para User
public class UserDAO implements InterfaceDAO<User> {

	// observable list para auxiliar na atualização dos elementos no list view
	private static ObservableList<User> users;

	// na implementação de cada um dos métodos eu tomei o cuidado de manter a lista
	// da memória atualizada, se ela já tiver sido carregada, já que eu sempre
	// estou mantendo todos os usuários na tela

	@Override
	public User get(String id) {
		if (users != null) // se já carreguei a lista de todos os usuários na memória
			for (User user : users) // procuro o usuário específico
				if (user.getName().contentEquals(id))
					return user;

		EntityManager entityMng = Conn.getEntityManager();
		User user = entityMng.find(User.class, id);
		entityMng.close();
		return user;
	}

	@Override
	public List<User> getAll() {
		if (users == null) {
			EntityManager entityMng = Conn.getEntityManager();
			users = FXCollections.observableArrayList(
					entityMng.createQuery("select user from User as user", User.class).getResultList());
			entityMng.close();
		}
		return users;
	}

	@Override
	public void add(User user) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(user);
		entityMng.getTransaction().commit(); // sempre que iniciamos uma transação, precisamos dar o commit
		entityMng.close();

		// adiciono na lista de usuários que está na memória, se todos os usuários já
		// foram carregados do banco
		if (users != null)
			users.add(user);
	}

	@Override
	public void delete(User obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		User userDB = entityMng.find(User.class, obj.getName());
		entityMng.remove(userDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		User found = null;
		if (users != null)
			for (User user : users)
				if (user.getName().contentEquals(obj.getName()))
					found = user;
		if (found != null)
			users.remove(found);
	}

	@Override
	public void update(User obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		User userDB = entityMng.find(User.class, obj.getName());
		userDB.setAge(obj.getAge());
		userDB.setRegisterDate(obj.getRegisterDate());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (users != null) {
			for (User user : users) {
				if (user.getName().contentEquals(obj.getName())) {
					user.setAge(obj.getAge());
					user.setRegisterDate(obj.getRegisterDate());
				}
			}
		}

	}
}
