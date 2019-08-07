package br.edu.ifsc.canoinhas.projeto2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
	
	@FXML
	Button btnPrimeiro;
	@FXML
	Button btnSegundo;
	@FXML
	Label lblConteudo;
	
	public void tratarPrimeiro() {
		lblConteudo.setText("Primeiro");
	}

	public void tratarSegundo() {
		lblConteudo.setText("Segundo");
	}
}
