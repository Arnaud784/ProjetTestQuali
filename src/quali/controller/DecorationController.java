package quali.controller;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Un controller qui encapsule le comportement des d�corations d'un fen�tre standard
 * ce type de controller doit �tre utilis� dans le cas d'utilisation d'un style transparent or undecorated
 */
public class DecorationController{
	/**
	 * Le container qui contient les d�coration et g�re le d�placement de la fen�tre
	 */
	private Parent decorationContainer;
	private double xOffset;
	private double yOffset;
	private Stage stage;

	public void initialize(Parent decorationContainer, Stage stage, boolean maximizable) {
		this.decorationContainer = decorationContainer;
		this.stage = stage;
		makeStageDragable();
		if(maximizable){
			makeStageMaximized();
		}
	}

	/**
	 * D�finit l'action de fermeture de la page
	 */
	protected void close() {
		System.exit(0);
	}

	/**
	 * D�finit l'action de maximisation de la page
	 */
	protected void maximize() {
		this.stage.setMaximized(!stage.isMaximized());
	}

	/**
	 * D�finit l'action de minimisation de la page
	 */
	protected void minimize() {
		this.stage.setIconified(true);
	}

	/**
	 * Maximise la taille de la fen�tre
	 */
	private void makeStageMaximized() {
		decorationContainer.setOnMouseClicked(event -> {
		  if (event.getClickCount() == 2) {
			  this.stage.setMaximized(!stage.isMaximized());
	        }
		});
	}

	/**
	 * D�finit le comportement de la fen�tre lorsque celle-ci est d�plac�
	 */
	private void makeStageDragable() {
		decorationContainer.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});

		decorationContainer.setOnMouseDragged(event -> {
			this.stage.setMaximized(false);
			this.stage.setX(event.getScreenX() - xOffset);
			this.stage.setY(event.getScreenY() - yOffset);
			this.stage.setOpacity(0.5f);
		});

		decorationContainer.setOnDragDone(event -> this.stage.setOpacity(1.0f));
		decorationContainer.setOnMouseReleased(event -> this.stage.setOpacity(1.0f));
	}
}