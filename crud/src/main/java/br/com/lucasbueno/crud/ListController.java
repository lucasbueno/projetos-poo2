package br.com.lucasbueno.crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class ListController implements Initializable {

	@FXML
	private SplitPane splitPane;

	@FXML
	private ListView<User> listUser;

	private boolean barBottom = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listUser.setItems(UserDAO.getUsers());
	}

	@FXML
	private void register() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void changeBar() {
		Node bottom = splitPane.getItems().get(1);
		splitPane.getItems().remove(1);
		splitPane.getItems().add(0, bottom);
		barBottom = !barBottom;
		if (barBottom)
			splitPane.setDividerPositions(0.8);
		else
			splitPane.setDividerPositions(0.2);
	}

	@FXML
	private void update() {

	}

	@FXML
	private void delete() {
		UserDAO.delete(listUser.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void exit() {
		Platform.exit();
	}
}
