package application;

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
//	private final String mainBackgroungURL = "space.png";
	private Pane root;
	
	public GameScene() {
		super(new StackPane(), 800, 600);
		// TODO Auto-generated constructor stub
//		String background_path = ClassLoader.getSystemResource(mainBackgroungURL).toString();
//		Image background = new Image(background_path);
//		BackgroundImage backgroundimage = new BackgroundImage(background, null, null, null, null);
		root = new StackPane();
//		root.setBackground(new Background(backgroundimage));
		TimeAndScorePane timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setAlignment(Pos.TOP_RIGHT);
		BombPane bombpane = new BombPane();
		bombpane.setAlignment(Pos.BOTTOM_RIGHT);
		root.getChildren().addAll(timerAndScorePane,bombpane);
		this.setRoot(root);
		
		
	}

}
