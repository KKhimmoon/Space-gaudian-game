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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectedController implements Initializable {
	
	@FXML
	private Button PlayButton;
	@FXML
	private Button Space1Btn;
	@FXML
	private Button Space2Btn;
	@FXML
	private Button Space3Btn;
	@FXML
	private Button BackHomeBtn;
	
	@FXML
	private ImageView PlayImage;
	@FXML
	private ImageView selectedbg;
	@FXML
	private ImageView spaceship1;
	@FXML
	private ImageView spaceship2;
	@FXML
	private ImageView spaceship3;
	@FXML
	private ImageView BackHomeimg;
	
	private Button Selected;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String spaceship1_path = ClassLoader.getSystemResource("295ef468535024b2.png").toString();
		Image spaceship1img = new Image(spaceship1_path);
		spaceship1.setImage(spaceship1img);
		
		String spaceship2_path = ClassLoader.getSystemResource("4ced2fc5b95a6026.png").toString();
		Image spaceship2img = new Image(spaceship2_path);
		spaceship2.setImage(spaceship2img);
		
		String spaceship3_path = ClassLoader.getSystemResource("f5a14a449cd65af7.png").toString();
		Image spaceship3img = new Image(spaceship3_path);
		spaceship3.setImage(spaceship3img);
		
		String playbtn_path = ClassLoader.getSystemResource("text-1668336560572.png").toString();
		Image playbtn = new Image(playbtn_path);
		PlayImage.setImage(playbtn);
		
		String selectedbg_path = ClassLoader.getSystemResource("space.png").toString();
		Image selectedbgimg = new Image(selectedbg_path);
		selectedbg.setImage(selectedbgimg);
		
		String backhome_path = ClassLoader.getSystemResource("35cc4b0f4226a0f1.png").toString();
		Image backhomeimg = new Image(backhome_path);
		BackHomeimg.setImage(backhomeimg);
		
		PlayButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				PlayImage.setScaleX(1.1);
				PlayImage.setScaleY(1.1);
			}
		});
		
		PlayButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				PlayImage.setScaleX(1);
				PlayImage.setScaleY(1);
			}
		});
		
		Space1Btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Space1Btn.setStyle("-fx-background-color: none; -fx-border-color: #FC441F; -fx-border-width: 5;");
			}
		});
		
		Space1Btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Space1Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
			}
		});
		Space2Btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Space2Btn.setStyle("-fx-background-color: none; -fx-border-color: #FC441F; -fx-border-width: 5;");
			}
		});
		
		Space2Btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Space2Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
			}
		});
		Space3Btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Space3Btn.setStyle("-fx-background-color: none; -fx-border-color: #FC441F; -fx-border-width: 5;");
			}
		});
		
		Space3Btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Space3Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
			}
		});
	
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
	public void onClickHandler(ActionEvent event,Button b,int size) throws IOException {
		this.Selected = b;
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		 
	}
	

}