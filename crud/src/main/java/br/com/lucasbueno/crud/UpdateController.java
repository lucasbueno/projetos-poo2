package br.com.lucasbueno.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateController {

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtAge;

	@FXML
	private void update(ActionEvent e) {
		User user = new User(txtName.getText(), Integer.valueOf(txtAge.getText()));
		new UserDAO().update(user);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}
	
	public void selectedUser(User user) {
		txtName.setText(user.getName());
		txtAge.setText(String.valueOf(user.getAge()));
	}
}