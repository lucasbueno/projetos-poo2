package br.edu.ifsc.canoinhas.projeto2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

	public void tratarClique(ActionEvent e) throws IOException {
		Button botaoClicado = (Button) e.getSource();
		tratarClique(botaoClicado);
		Stage stage = new Stage();
		stage.setScene(new Scene(loadFXML("settings")));
		stage.show();
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
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
