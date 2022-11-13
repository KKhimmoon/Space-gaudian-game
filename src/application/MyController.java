package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyController implements Initializable {
	@FXML
	private MediaView BackgroundMedia;
	
	private File file;
	private Media media;
	private MediaPlayer mediaplayer;
	@FXML
	private Button StartButton;
	
	@FXML
	private Button HowtoplayButton;

	@FXML
	private TextField outputField;

	@FXML
	private ImageView MySpaceShip;
	
	@FXML
	private ImageView Meteorite;
	
	@FXML
	private ImageView StartImage;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//translate
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(MySpaceShip);
		translate.setDuration(Duration.millis(1500));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		translate.setAutoReverse(true);
		translate.setByX(520);
		translate.play();
		
		//rotate
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(Meteorite);
		rotate.setDuration(Duration.millis(1200));
		rotate.setCycleCount(TranslateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByAngle(360);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.play();
		
		TranslateTransition translateMeteorite = new TranslateTransition();
		translateMeteorite.setNode(Meteorite);
		translateMeteorite.setDuration(Duration.millis(5000));
		translateMeteorite.setCycleCount(TranslateTransition.INDEFINITE);
		translateMeteorite.setByY(800);
		translateMeteorite.play();
		
		//fade
		//scale
		
		String media_path = ClassLoader.getSystemResource("SpaceBg2.gif").toString();
		media = new Media(media_path);
		mediaplayer = new MediaPlayer(media);
		mediaplayer.play();
		BackgroundMedia.setMediaPlayer(mediaplayer);
	}

	public void switchToHome(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	public void switchToSelectedScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SelectedScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
//	@FXML
//	public void pop(ActionEvent event) throws IOException {
//		System.out.println("Mouse");
//		StartImage.resize(2, 2);
//	}

}