package application;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	private final String mainBackgroungURL = "space.png";
	private int posX;
	private int posY;
	private int size;
	private ImageView player;
	private static Scene mainScene;
	private Stage primaryStage;
 	
		
//	public Main(int posX, int posY, int size) {
//			this.posX = posX;
//			this.posY = posY;
//			this.size = size;
//			this.player = SelectedController.getSelectedSpaceShip();
//			
//		}
	public Main() {
		
	}
//
//	public void initializePlayer() {
//		this.player.setImage(SelectedController.getSelectedSpaceShip());
//		this.player.prefHeight(30);
//		this.player.prefWidth(30);
//	}
//		this.prefHeight(600);
//		this.prefWidth(800);
//		String background_path = ClassLoader.getSystemResource(mainBackgroungURL).toString();
//		Image background = new Image(background_path);
//		BackgroundImage backgroundimage = new BackgroundImage(background, null, null, null, null);
//		this.setBackground(new Background(backgroundimage));
		
	
//	public Main(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		this.primaryStage = primaryStage;
//		Pane root = new Pane();
//    	root.setPrefHeight(600);
//    	root.setPrefWidth(800);
//    	String background_path = ClassLoader.getSystemResource(mainBackgroungURL).toString();
//		Image background = new Image(background_path);
//		BackgroundImage backgroundimage = new BackgroundImage(background, null, null, null, null);
//    	root.setBackground(new Background(backgroundimage));
//    	Scene scene = new Scene(root);
//		this.primaryStage.setScene(scene);
//    	this.primaryStage.show();
//    	
//	}


@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	this.primaryStage = primaryStage;
	Pane root = new Pane();
	root.setPrefHeight(600);
	root.setPrefWidth(800);
	String background_path = ClassLoader.getSystemResource(mainBackgroungURL).toString();
	Image background = new Image(background_path);
	BackgroundImage backgroundimage = new BackgroundImage(background, null, null, null, null);
	root.setBackground(new Background(backgroundimage));
	Scene scene = new Scene(root);
	this.primaryStage.setScene(scene);
	this.primaryStage.show();
}



	
	
}
