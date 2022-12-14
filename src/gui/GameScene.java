package gui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class GameScene extends Scene{
	private Pane root;
	
	public GameScene() {
		super(new StackPane(), 800, 600);
		// TODO Auto-generated constructor stub
		root = new StackPane();
		TimeAndScorePane timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setAlignment(Pos.TOP_RIGHT);
		BombPane bombpane = new BombPane();
		bombpane.setAlignment(Pos.BOTTOM_RIGHT);
		root.getChildren().addAll(timerAndScorePane,bombpane);
		this.setRoot(root);
		
		
	}

}
