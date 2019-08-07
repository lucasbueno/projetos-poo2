package br.edu.ifsc.canoinhas.projeto2;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

	@FXML
	Button btnPrimeiro;

	@FXML
	Button btnSegundo;

	@FXML
	Button btnTerceiro;

	@FXML
	Button btnQuarto;

	@FXML
	Button btnQuinto;

	@FXML
	Label lblConteudo;

	public void tratarPrimeiro() {
		tratarClique(btnPrimeiro);
	}

	public void tratarSegundo() {
		tratarClique(btnSegundo);
	}

	public void tratarTerceiro() {
		tratarClique(btnTerceiro);
	}

	public void tratarQuarto() {
		tratarClique(btnQuarto);
	}

	public void tratarQuinto() {
		tratarClique(btnQuinto);
	}

	private void tratarClique(Button botao) {
		lblConteudo.setText(botao.getText());
		resetarBotoes();
		botao.setPadding(new Insets(20, 0, 20, 0));
	}

	public void resetarBotoes() {
		Insets padding = new Insets(10, 0, 10, 0);
		btnPrimeiro.setPadding(padding);
		btnSegundo.setPadding(padding);
		btnTerceiro.setPadding(padding);
		btnQuarto.setPadding(padding);
		btnQuinto.setPadding(padding);
	}
}
