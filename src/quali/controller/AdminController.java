package quali.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import quali.Main;
import quali.model.Context;
import quali.model.User;

public class AdminController extends DecorationController implements Initializable {

	private static final String COMMONS_VIEWS_HOME_FXML = "../view/Home.fxml";

	@FXML
	private AnchorPane root;

	@FXML
	private Button deconnect;

	@FXML
	private TableView<User> usersTable;

	@FXML
	private TableColumn<User, String> firstname;

	@FXML
	private TableColumn<User, String> lastname;

	@FXML
	private TableColumn<User, String> email;

	@FXML
	private TableColumn<User, String> address;

	@FXML
	private TableColumn<User, String> phone;

	@FXML
	private TableColumn<User, String> birthday;

	@FXML
	private TableColumn<User, String> admin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(root, Main.getStage(),false);

		usersTable.setItems(Context.getInstance().getUsersListProperty());
		firstname.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		lastname.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
		email.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
		address.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
		phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
		birthday.setCellValueFactory(cellData -> cellData.getValue().getBirthdayProperty());
		admin.setCellValueFactory(cellData -> cellData.getValue().getAdminProperty());

		deconnect.setOnMouseClicked(event -> {
			try {
				Context.getInstance().setLoggedUser(null);
				CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de charger la page d'accueil");
			}
		});
	}

}
