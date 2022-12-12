package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

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

public class MyController implements Initializable {
	
	@FXML
	private Button StartButton;
	@FXML
	private Button HowtoplayButton;
	
	@FXML
	private ImageView MySpaceShip;
	@FXML
	private ImageView Meteorite;
	@FXML
	private ImageView StartImage;
	@FXML
	private ImageView HowtoplayImage;
	@FXML
	private ImageView TitleImage;
	@FXML
	private ImageView spaceBg;
	@FXML
	private Pane HomeBg;
	
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
		
		spaceBg.setImage(sharedObject.RenderableHolder.homeBg);
		StartImage.setImage(sharedObject.RenderableHolder.startTxt);
		HowtoplayImage.setImage(sharedObject.RenderableHolder.howtoplayTxt);
		MySpaceShip.setImage(sharedObject.RenderableHolder.player1);
		TitleImage.setImage(sharedObject.RenderableHolder.titleTxt);
		
		StartButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				StartImage.setScaleX(1.2);
				StartImage.setScaleY(1.2);
			}
		});

		StartButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				StartImage.setScaleX(1);
				StartImage.setScaleY(1);
			}
		});
		HowtoplayButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				HowtoplayImage.setScaleX(1.2);
				HowtoplayImage.setScaleY(1.2);
			}
		});
		HowtoplayButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				HowtoplayImage.setScaleX(1);
				HowtoplayImage.setScaleY(1);
			}
		});
		StartButton.setOnMouseClicked(new EventHandler<Event>() {

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
		HowtoplayButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					switchToHome(e); ///editttttttttttt
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void switchToHome(Event event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	public void switchToSelectedScene(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SelectedScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	

}