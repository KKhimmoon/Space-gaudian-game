package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
	
	@FXML
	private Button startButton;
	@FXML
	private Button howToPlayButton;
	
	@FXML
	private ImageView mySpaceShip;
	@FXML
	private ImageView meteorite;
	@FXML
	private ImageView startImage;
	@FXML
	private ImageView howToPlayImage;
	@FXML
	private ImageView titleImage;
	@FXML
	private ImageView spaceBg;
	@FXML
	private Pane homeBg;
	
	public static AnimationTimer homeSound;
	private Stage stage;
	private Scene scene;
	private Pane howToPlay;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//translate
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(mySpaceShip);
		translate.setDuration(Duration.millis(1500));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		translate.setAutoReverse(true);
		translate.setByX(520);
		translate.play();
		
		spaceBg.setImage(sharedObject.RenderableHolder.homeBg);
		startImage.setImage(sharedObject.RenderableHolder.startTxt);
		howToPlayImage.setImage(sharedObject.RenderableHolder.howtoplayTxt);
		mySpaceShip.setImage(sharedObject.RenderableHolder.player2);
		mySpaceShip.setScaleX(1.5);
		mySpaceShip.setScaleY(1.5);
		titleImage.setImage(sharedObject.RenderableHolder.titleTxt);
		
		startButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				startImage.setScaleX(1.2);
				startImage.setScaleY(1.2);
			}
		});

		startButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				startImage.setScaleX(1);
				startImage.setScaleY(1);
			}
		});
		howToPlayButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				howToPlayImage.setScaleX(1.2);
				howToPlayImage.setScaleY(1.2);
			}
		});
		howToPlayButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				howToPlayImage.setScaleX(1);
				howToPlayImage.setScaleY(1);
			}
		});
		startButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					switchToSelectedScene(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		howToPlayButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					switchToHowToPlayScene(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		playHomeSound();
		howToPlay = new Pane();
		howToPlay.setPrefSize(800, 600);
		howToPlay.setStyle("-fx-background-color: #00000055;");
		ImageView howToPlayImg = new ImageView(sharedObject.RenderableHolder.howToPlay);
		howToPlayImg.setTranslateX(65);
		howToPlayImg.setTranslateY(100);
		howToPlay.getChildren().addAll(howToPlayImg);
		howToPlay.setVisible(false);
		howToPlay.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				howToPlay.setVisible(false);
			}
		});
		homeBg.getChildren().add(howToPlay);
	}
	public void playHomeSound() {
		homeSound = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if (!sharedObject.RenderableHolder.homeSound.isPlaying()) {
					sharedObject.RenderableHolder.homeSound.play();
				}
			}
		};
		homeSound.start();
	}
	public void switchToHowToPlayScene(Event event) throws IOException {
		howToPlay.setVisible(true);
	}
	public void switchToSelectedScene(Event event) throws IOException {
		sharedObject.RenderableHolder.onClickSound.play();
		Parent root = FXMLLoader.load(getClass().getResource("SelectedScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	

}