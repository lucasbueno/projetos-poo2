package br.edu.ifsc.canoinhas.projeto2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController {

	private int parametro;

	@FXML
	Button botaoDoMeio;

	public void metodoA() {
		Stage janela = (Stage) botaoDoMeio.getScene().getWindow();
		System.out.println(parametro);
		janela.close();
	}

	public int getParametro() {
		return parametro;
	}

	public void setParametro(int parametro) {
		this.parametro = parametro;
	}

}
