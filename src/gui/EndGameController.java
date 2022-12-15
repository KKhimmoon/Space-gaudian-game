package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import screendrawing.MainGameScreen;

public class EndGameController implements Initializable{
	private Stage stage;
	private Scene scene;
	@FXML 
	private Pane popUp;
	@FXML 
	private Button yesBtn;
	@FXML
	private Button noBtn;
	@FXML
	private ImageView yesImg;
	@FXML
	private ImageView noImg;
	@FXML
	private ImageView timeImg;
	@FXML
	private ImageView upImg;
	@FXML
	private ImageView playAgainImg;
	
	private Canvas canvas;
	private static GraphicsContext gc;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		yesImg.setImage(sharedObject.RenderableHolder.yesTxt);
		noImg.setImage(sharedObject.RenderableHolder.noTxt);
		timeImg.setImage(sharedObject.RenderableHolder.timeTxt);
		upImg.setImage(sharedObject.RenderableHolder.upTxt);
		playAgainImg.setImage(sharedObject.RenderableHolder.playagainTxt);
		
		canvas = new Canvas(500,100);
		gc = canvas.getGraphicsContext2D();
		canvas.setTranslateX(110);
		canvas.setTranslateY(80);
		popUp.getChildren().add(canvas);
		
		yesBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					yesButtonHandler(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		noBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					noButtonHandler(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static void updateYourScore(GraphicsContext gc) {
		gc.setFont(sharedObject.RenderableHolder.endgameFont);
		gc.setFill(Color.WHITE);
		gc.fillText("Your Score : " + logic.GameLogic.getScore(),0,50);
	}
	
	public void yesButtonHandler(Event event) throws IOException{
		sharedObject.RenderableHolder.onClickSound.play();
		sharedObject.RenderableHolder.mainGameSound.stop();
		Timer.getMainGameSound().stop();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		MainGameScreen.restart();
		scene = MainGameScreen.getInstance();
		stage.setScene(scene);
		stage.show();
		
	}
	public void noButtonHandler(Event event) throws IOException {
		sharedObject.RenderableHolder.onClickSound.play();
		sharedObject.RenderableHolder.mainGameSound.stop();
		Timer.getMainGameSound().stop();
		MainGameScreen.restart();
		Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static GraphicsContext getGc() {
		return gc;
	}


	public static void setGc(GraphicsContext gc) {
		EndGameController.gc = gc;
	}
}
