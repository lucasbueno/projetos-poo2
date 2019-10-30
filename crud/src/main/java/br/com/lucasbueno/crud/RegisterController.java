package br.com.lucasbueno.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterController {

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtAge;

	@FXML
	private void register(ActionEvent e) {
//		User user = new User(txtName.getText(), Integer.valueOf(txtAge.getText()));
//		new UserDAO().add(user);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}
}