package quali.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import quali.Main;
import quali.model.Context;

public class CanvasController extends DecorationController implements Initializable {
	
	@FXML
	private HBox header;

	@FXML
	private Label minimize;

	@FXML
	private Label maximize;

	@FXML
	private Label close;

	@FXML
	private BorderPane windowPlaceholder;

	public static BorderPane container;

	@FXML
	private Pane petcpBtn;

	@FXML
	private Pane difrepBtn;

    /**
     * appelé pour sauvegarder le main en référence dans cette classe
     *
     * @param mainApp Instance de main
     */
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(header,Main.stage,true);
		setButtonsActions();
		container = this.windowPlaceholder;
    }
    
	/**
	 * Définit le comportement des différents bouton
	 */
	private void setButtonsActions() {
		close.setOnMouseClicked(event -> {
			Context.getInstance().save();
			super.close();
		});

		maximize.setOnMouseClicked(event -> {
			super.maximize();
		});

		minimize.setOnMouseClicked(event -> {
			super.minimize();
		});
	}

	/**
	 * @param fxmlLocation, @see Views, la localisation de la vue souhaité
	 * @param resource, le resource bundle associé a l'application
	 * @throws IOException
	 *
	 *  Permet de charger une page dans le canvas
	 */
	public static void loadPage(URL fxmlLocation) throws IOException{
		Parent root = FXMLLoader.load(fxmlLocation);
		container.setCenter(root);
		root.resize(container.getWidth(), container.getHeight());
		AnchorPane.setTopAnchor(root, 0.0);
		AnchorPane.setBottomAnchor(root, 0.0);
		AnchorPane.setLeftAnchor(root, 0.0);
		AnchorPane.setRightAnchor(root, 0.0);
	}
}
