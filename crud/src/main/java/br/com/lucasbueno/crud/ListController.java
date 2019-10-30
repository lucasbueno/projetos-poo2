package br.com.lucasbueno.crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListController implements Initializable {

	@FXML
	private SplitPane splitPane;

	@FXML
	private ListView<User> listUser;

	@FXML
	private TextField txtUsername;

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
		UserDAO dao = new UserDAO();
		User user = dao.get(txtUsername.getText());
		if (user == null) {
			showMsg("Usuário não encontrado");
		} else {
			showMsg("O servidor encontrou " + user.getName() + ", com " + user.getAge() + " anos.");
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
