package quali;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import quali.controller.CanvasController;
import quali.model.Context;

public class Main extends Application {
	
	private static final String COMMONS_VIEWS_CANVAS_FXML = "view/Canvas.fxml";
	
	private static final String COMMONS_VIEWS_HOME_FXML = "view/Home.fxml";
	
	private static final String ICONS_QUALI_PNG = "resources/quali_ico.png";

	public static Stage stage;
	private static Scene scene;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Context.getInstance().load();
		Context.getInstance().setLoggedUser(null);
		try {
			stage = primaryStage;
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream(ICONS_QUALI_PNG)));
			/*AnchorPane root = FXMLLoader.load(getClass().getResource(COMMONS_VIEWS_LOGIN_FXML),resourceBundle);
			scene = new Scene(root,800,400);*/
			BorderPane rootContainer = FXMLLoader.load(getClass().getResource(COMMONS_VIEWS_CANVAS_FXML));
			CanvasController.loadPage(getClass().getResource(COMMONS_VIEWS_HOME_FXML));
			scene = new Scene(rootContainer,900,450);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
			/*ContextSerializationService contextSerializationService = ContextSerializationService.createEmptyInstance();
			Context.setInstance(contextSerializationService.load());*/

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Stage getStage() {
		return stage;
	}

	public static void setSceneRoot(Parent root,int width, int height){
		scene.setRoot(root);
		stage.setWidth(width);
		stage.setHeight(height);
	}

}
