package quali.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import quali.Main;
import quali.model.Context;
import quali.model.User;

public class HomeController extends DecorationController implements Initializable {

	private static final String COMMONS_VIEWS_FORGOT_FXML = "../view/Forgot.fxml";

	private static final String COMMONS_VIEWS_REGISTER_FXML = "../view/Register.fxml";

	private static final String COMMONS_VIEWS_ADMIN_FXML = "../view/Admin.fxml";

	private static final String COMMONS_VIEWS_USER_FXML = "../view/User.fxml";

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane snackBar;

	@FXML
	private Button loginBtn;

	@FXML
	private TextField emailLogin;

	@FXML
	private PasswordField passwordLogin;

	@FXML
	private Button createAccountBtn;

	@FXML
	private Button forgotPasswordBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(root, Main.getStage(),false);

		if(!Context.getInstance().getPasswordIsForgot().isEmpty()) {
			SnackAlertService.displayInformation("Le mot de passe est : "+Context.getInstance().getPasswordIsForgot(), snackBar, Duration.seconds(10), SnackAlertService.AlertSnackType.INFORMATION);
			Context.getInstance().setPasswordIsForgot("");
		}

		forgotPasswordBtn.setOnMouseClicked(event -> {
			try {
				CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_FORGOT_FXML));
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de charger la page mot de passe oublié");
			}
		});

		loginBtn.setOnMouseClicked(event -> login());
		createAccountBtn.setOnMouseClicked(event -> {
			try {
				CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_REGISTER_FXML));
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de charger la page inscription");
			}
		});
	}

	/**
	 * Lance la procédure de login
	 */
	public void login() {
		if(!emailLogin.getText().isEmpty() && !passwordLogin.getText().isEmpty()){
			try {
				User user = Context.getInstance().findUser(emailLogin.getText(), passwordLogin.getText());
				if(user == null) {
					SnackAlertService.displayInformation("L'email ou le mot de passe est incorrect !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
				} else {
					Context.getInstance().setLoggedUser(user);
					if(user.isAdmin()) {
						CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_ADMIN_FXML));
					} else {
						CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_USER_FXML));
					}
				}
			} catch (Exception e) {
				Main.LOGGER.severe("Erreur lors de la connexion");
				SnackAlertService.displayInformation("Une erreur est survenue !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
			}
		} else {
			SnackAlertService.displayInformation("L'email et le mot de passe ne doivent pas être vide !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
		}
	}
}
