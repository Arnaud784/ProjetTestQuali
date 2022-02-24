package quali.controller;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import quali.Main;
import quali.model.Context;
import quali.model.User;

public class RegisterController extends DecorationController implements Initializable {

	private static final String COMMONS_VIEWS_HOME_FXML = "../view/Home.fxml";

	private static final String COMMONS_VIEWS_ADMIN_FXML = "../view/Admin.fxml";

	private static final String COMMONS_VIEWS_USER_FXML = "../view/User.fxml";

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane snackBar;

	@FXML
	private Button cancel;

	@FXML
	private Button valid;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField email;

	@FXML
	private PasswordField password;

	@FXML
	private TextField address;

	@FXML
	private TextField phone;

	@FXML
	private TextField picture;

	@FXML
	private DatePicker birthday;

	@FXML
	private CheckBox admin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(root, Main.getStage(),false);

		cancel.setOnMouseClicked(event -> {
			try {
				CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de charger la page d'accueil");
			}
		});

		valid.setOnMouseClicked(event -> register());
	}

	/**
	 * Lance la procédure d'inscription
	 */
	public void register() {
		if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !email.getText().isEmpty() && !password.getText().isEmpty() && !address.getText().isEmpty() && !phone.getText().isEmpty() && !picture.getText().isEmpty() && !birthday.toString().isEmpty()){
			if(Context.getInstance().findUser(email.getText()) == null) {
				User user = new User(firstName.getText(), lastName.getText(), email.getText(), password.getText(), address.getText(), phone.getText(), picture.getText());
				user.setBirthDay(birthday.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				user.setAdmin(admin.isSelected());
				Context.getInstance().getUsersList().add(user);
				Context.getInstance().setLoggedUser(user);
				try {
					if(admin.isSelected()) {
						CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_ADMIN_FXML));
					} else {
						CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_USER_FXML));
					}
				} catch (IOException e) {
					Main.LOGGER.severe("Impossible de charger la page de l'utilisateur");
				}
			} else {
				SnackAlertService.displayInformation("Cet email est déjà utilisé !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
			}
		} else {
			SnackAlertService.displayInformation("Tous les champs doivent être remplis !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
		}
	}

}
