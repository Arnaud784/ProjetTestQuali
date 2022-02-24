package quali.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import quali.Main;

public class SnackAlertService {

	public enum AlertSnackType {
		ERROR("error-snack"),
		WARNING("warning-snack"),
		INFORMATION("information-snack");

		private String style;

		AlertSnackType(String style){
			this.style = style;
		}
	}

	public static void  displayInformation(String message, Pane snackbarContainer, Duration duration, AlertSnackType type){
		try {
			JFXSnackbar snackBar = new JFXSnackbar(snackbarContainer);
			Label label = FXMLLoader.load(SnackAlertService.class.getResource("../view/AlertSnack.fxml"));
			label.setText(message);
			label.getStyleClass().add(type.style);
			snackBar.setLayoutX(0.0);
			snackBar.setLayoutY(0.0);
			snackBar.enqueue(new SnackbarEvent(label,duration));
		} catch (IOException e) {
			Main.LOGGER.severe("Impossible d'afficher un message popup");
		}
	}
}
