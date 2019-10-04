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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListController implements Initializable {

	@FXML
	private SplitPane splitPane;

	@FXML
	private TableView<User> table;

	private boolean barBottom = true;

	@FXML
	private TableColumn<User, String> userName;

	@FXML
	private TableColumn<User, Integer> userAge;

	@FXML
	private TableColumn<User, String> userRegisterDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userName.setCellValueFactory(new PropertyValueFactory<>("name"));
		userAge.setCellValueFactory(new PropertyValueFactory<>("age"));
		userRegisterDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
		table.setItems(UserDAO.getUsers());
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
	private void exit() {
		Platform.exit();
	}
}
