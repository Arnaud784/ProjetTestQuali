package quali.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import quali.Main;
import quali.model.Context;

public class ForgotController extends DecorationController implements Initializable {

	private static final String COMMONS_VIEWS_HOME_FXML = "../view/Home.fxml";

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane snackBar;

	@FXML
	private Button recoveryBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private TextField emailLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(root, Main.stage,false);

		recoveryBtn.setOnMouseClicked(event -> recovery());

		cancelBtn.setOnMouseClicked(event -> {
			try {
				CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Lance la procédure de récupération de mot de passe
	 */
	public void recovery() {
		if(!emailLogin.getText().isEmpty()) {
			try {
				String password = Context.getInstance().recovery(emailLogin.getText());
				if(password == null) {
					SnackAlertService.displayInformation("L'email est incorrect !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
				} else {
					Context.getInstance().setPasswordIsForgot(password);
					CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
				}
			} catch (Exception e) {
				e.printStackTrace();
				SnackAlertService.displayInformation("Une erreur est survenue !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
			}
		} else {
			SnackAlertService.displayInformation("L'email ne doit pas être vide !", snackBar, Duration.seconds(3), SnackAlertService.AlertSnackType.ERROR);
		}
	}

}
