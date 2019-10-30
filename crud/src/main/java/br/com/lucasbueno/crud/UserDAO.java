package br.com.lucasbueno.crud;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javafx.collections.ObservableList;

public class UserDAO implements InterfaceDAO<User> {

	private static ObservableList<User> users;

	@Override
	public User get(String id) {
		User user = null;
		try {
			Socket server = new Socket("localhost", 1024);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("user;get;"+id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();
			
			if(!msg.contains("404")){
				String[] splitResult = msg.split(";");
				user = new User(splitResult[0], Integer.valueOf(splitResult[1]), splitResult[2]);
			}		

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getAll() {
//		if (users == null) {
//			EntityManager entityMng = Conn.getEntityManager();
//			users = entityMng.createQuery("select user from User as user", User.class).getResultList();
//			entityMng.close();
//		}
		return users;
	}

	@Override
	public void add(User user) {
//		EntityManager entityMng = Conn.getEntityManager();
//		entityMng.getTransaction().begin();
//		entityMng.persist(user);
//		entityMng.getTransaction().commit(); // sempre que iniciamos uma transação, precisamos dar o commit
//		entityMng.close();
//
//		// adiciono na lista de usuários que está na memória, se todos os usuários já
//		// foram carregados do banco
//		if (users != null)
//			users.add(user);
	}

	@Override
	public void delete(User obj) {
//		EntityManager entityMng = Conn.getEntityManager();
//		entityMng.getTransaction().begin();
//		User userDB = entityMng.find(User.class, obj.getName());
//		entityMng.remove(userDB);
//		entityMng.getTransaction().commit();
//		entityMng.close();
//
//		User found = null;
//		if (users != null)
//			for (User user : users)
//				if (user.getName().contentEquals(obj.getName()))
//					found = user;
//		if (found != null)
//			users.remove(found);
	}

	@Override
	public void update(User obj) {
//		EntityManager entityMng = Conn.getEntityManager();
//		entityMng.getTransaction().begin();
//		User userDB = entityMng.find(User.class, obj.getName());
//		userDB.setAge(obj.getAge());
//		userDB.setRegisterDate(obj.getRegisterDate());
//		entityMng.getTransaction().commit();
//		entityMng.close();
//
//		if (users != null) {
//			for (User user : users) {
//				if (user.getName().contentEquals(obj.getName())) {
//					user.setAge(obj.getAge());
//					user.setRegisterDate(obj.getRegisterDate());
//				}
//			}
//		}

	}
}
