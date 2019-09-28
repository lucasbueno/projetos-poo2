package br.com.lucasbueno.orm_javafx_modular;

import java.io.IOException;

import javax.persistence.EntityManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(new File("teste"));
		em.getTransaction().commit();
		em.close();
		
		scene = new Scene(loadFXML("main"));
		stage.setTitle("Our Money Factory");
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
}