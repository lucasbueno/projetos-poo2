package br.com.lucasbueno.crud.controllers;

import br.com.lucasbueno.crud.dao.UserDAO;
import br.com.lucasbueno.crud.entities.User;
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
	
	private ListController listController;

	@FXML
	private void register(ActionEvent e) {
		User user = new User(txtName.getText(), Integer.valueOf(txtAge.getText()), "");
		new UserDAO().add(user);
		listController.updateList();
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}
	
	public void setListController(ListController listController) {
		this.listController = listController;
	}
}