package br.edu.ifsc.canoinhas.projeto2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController {

	@FXML
	Button botaoDoMeio;

	public void metodoA(ActionEvent e) {
		Stage janela = (Stage) botaoDoMeio.getScene().getWindow();
		janela.close();
	}
}
