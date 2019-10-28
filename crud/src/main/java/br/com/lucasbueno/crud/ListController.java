package br.com.lucasbueno.crud;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class ListController implements Initializable {

	@FXML
	private SplitPane splitPane;

	@FXML
	private ListView<User> listUser;

	private boolean barBottom = true;

	public void updateList() {
		UserDAO dao = new UserDAO();
		listUser.setItems(null);
		listUser.setItems((ObservableList<User>) dao.getAll());
	}

	// método que será executado sempre que a interface gráfica terminar de ser
	// carregada
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}

	@FXML
	private void register() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void changeBar() {

		// pegar a referência pro segundo item do split pane
		Node bottom = splitPane.getItems().get(1);
		// remover segundo item
		splitPane.getItems().remove(1);
		// colocar o segundo item na posição do primeiro
		splitPane.getItems().add(0, bottom);
		barBottom = !barBottom;
		if (barBottom)
			splitPane.setDividerPositions(0.8);
		else
			splitPane.setDividerPositions(0.2);
	}

	@FXML
	private void update() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

		// passo para o controlador da tela de update a referência do usuário
		// selecionado e deste controlador, para que depois ele possa pedir para
		// atualizar a listView
		UpdateController controller = (UpdateController) fxmlLoader.getController();
		controller.selectedUser(listUser.getSelectionModel().getSelectedItem(), this);
	}

	@FXML
	private void delete() {
		new UserDAO().delete(listUser.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void sendMessage() {
		try {
			Socket server = new Socket("localhost", 1024);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("oi");
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();
			showMsg(msg);

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private void showMsg(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mensagem recebido");
		alert.setHeaderText("Olha só o que o servidor enviou...");
		alert.setContentText(msg);
		alert.showAndWait();
	}

	@FXML
	private void exit() {
		Platform.exit();
	}
}
