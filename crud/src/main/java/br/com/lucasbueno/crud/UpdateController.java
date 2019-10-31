package br.com.lucasbueno.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtAge;
	
	private ListController listController;

	@FXML
	private void update(ActionEvent e) {
		User user = new User(txtName.getText(), Integer.valueOf(txtAge.getText()), "");
		new UserDAO().update(user);
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		listController.updateList();
		stage.close();
	}

	public void selectedUser(User user, ListController listController) {
		txtName.setText(user.getName());
		txtAge.setText(String.valueOf(user.getAge()));
		this.listController = listController;
	}
}