package quali.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import quali.Main;
import quali.model.Context;
import quali.model.User;

public class UserController extends DecorationController implements Initializable {

	private static final String COMMONS_VIEWS_HOME_FXML = "../view/Home.fxml";

	@FXML
	private AnchorPane root;

	@FXML
	private Button deconnect;

	@FXML
	private Label firstname;

	@FXML
	private Label lastname;

	@FXML
	private Label email;

	@FXML
	private Label address;

	@FXML
	private Label phone;

	@FXML
	private Label birthday;

	@FXML
	private ImageView picture;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(root, Main.getStage(),false);

		User user = Context.getInstance().getLoggedUser();
		firstname.setText(user.getFirstName());
		lastname.setText(user.getLastName());
		email.setText(user.getEmail());
		address.setText(user.getAddress());
		phone.setText(user.getPhone());
		birthday.setText(user.getBirthDay());

		InputStream stream = null;
		try {
			stream = new FileInputStream("src/quali/resources/photo.jpg");

			Image image = new Image(stream);
			picture.setImage(image);
		} catch (FileNotFoundException e1) {
			Main.LOGGER.severe("Impossible de charger la photo de l'utilisateur");
		}

		deconnect.setOnMouseClicked(event -> {
			try {
				CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de charger la page d'accueil");
			}
		});
	}

}
