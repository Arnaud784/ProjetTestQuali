package quali;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import quali.controller.CanvasController;
import quali.controller.HomeController;
import quali.model.Context;

public class Main extends Application {

	private static final String COMMONS_VIEWS_CANVAS_FXML = "view/Canvas.fxml";

	private static final String COMMONS_VIEWS_HOME_FXML = "view/Home.fxml";

	private static final String ICONS_QUALI_PNG = "resources/quali_ico.png";

	public static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());

	private static Stage stage;

	private static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Context.getInstance().load();
		Context.getInstance().setLoggedUser(null);
		Handler fh = new FileHandler("Logs.log");
	    LOGGER.addHandler(fh);
		try {
			setStage(primaryStage);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream(ICONS_QUALI_PNG)));
			BorderPane rootContainer = FXMLLoader.load(getClass().getResource(COMMONS_VIEWS_CANVAS_FXML));
			CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
			setScene(new Scene(rootContainer,1200,500));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			Main.LOGGER.severe("Impossible de charger la page d'accueil");
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stg) {
		stage = stg;
	}

	public static Scene getScene() {
		return scene;
	}

	public static void setScene(Scene scn) {
		scene = scn;
	}

	public static void setSceneRoot(Parent root,int width, int height){
		scene.setRoot(root);
		stage.setWidth(width);
		stage.setHeight(height);
	}

}
