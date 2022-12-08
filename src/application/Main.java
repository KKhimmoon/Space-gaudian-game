package application;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	private final String mainBackgroungURL = "space.png";
	public Main() {
		super();
//		this.prefHeight(600);
//		this.prefWidth(800);
//		String background_path = ClassLoader.getSystemResource(mainBackgroungURL).toString();
//		Image background = new Image(background_path);
//		BackgroundImage backgroundimage = new BackgroundImage(background, null, null, null, null);
//		this.setBackground(new Background(backgroundimage));
		
	}
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane root = new Pane();
    	root.setPrefHeight(600);
    	root.setPrefWidth(800);
    	String background_path = ClassLoader.getSystemResource(mainBackgroungURL).toString();
		Image background = new Image(background_path);
		BackgroundImage backgroundimage = new BackgroundImage(background, null, null, null, null);
    	root.setBackground(new Background(backgroundimage));
		Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
