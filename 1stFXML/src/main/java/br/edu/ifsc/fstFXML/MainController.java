package br.edu.ifsc.fstFXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

	@FXML
	private Button btnGame1;

	@FXML
	private Button btnGame2;

	@FXML
	private Label lblGame;

	@FXML
	private void updateGame1() {
		lblGame.setText(btnGame1.getText());
	}

	@FXML
	private void updateGame2() {
		lblGame.setText(btnGame2.getText());
	}
}
