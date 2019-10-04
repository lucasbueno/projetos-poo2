package br.com.lucasbueno.crud;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		Conn.getEntityManager().close();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("list.fxml"));
		Parent parent = fxmlLoader.load();
		scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		Conn.closeConn();
		super.stop();
	}
}