package br.com.lucasbueno.crud.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.lucasbueno.crud.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class LoadingController implements Initializable {

	@FXML
	private ProgressBar progressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Thread loadingThread = new Thread(new LoadingRunnable(progressBar, this));
		loadingThread.start();
	}

	public void closeWindow() throws IOException {		
		Scene scene = progressBar.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();			

		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("list.fxml"));
		Parent parent = fxmlLoader.load();
		Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.setScene(newScene);
		newStage.show();
	}

}
