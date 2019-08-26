package br.edu.ifsc.canoinhas.projeto2;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

	@FXML
	TextField nossaTxt;

	@FXML
	ListView<String> nossaListView;

	private ObservableList<String> nossaLista;

	public void tratarClique(ActionEvent e) throws IOException {
		Button botaoClicado = (Button) e.getSource();
		tratarClique(botaoClicado);
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("settings.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		SettingsController controller = fxmlLoader.getController();
		controller.setParametro(2);
		stage.show();
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

	public void nossoMetodo() {
		getNossaLista().add(nossaTxt.getText());
	}

	private ObservableList<String> getNossaLista() {
		if (this.nossaLista == null) {
			this.nossaLista = FXCollections.observableArrayList();
			nossaListView.setItems(nossaLista);
		}
		return this.nossaLista;
	}
}
