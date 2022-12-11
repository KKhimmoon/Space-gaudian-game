package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Endgame implements Initializable{
	private Stage stage;
	private Scene scene;
	@FXML 
	private static Pane popUp;
	@FXML 
	private Button yesBtn;
	@FXML
	private Button noBtn;
	@FXML
	private ImageView yesIMG;
	@FXML
	private ImageView noIMG;
	@FXML
	private ImageView timeIMG;
	@FXML
	private ImageView upIMG;
	@FXML
	private ImageView playAgainIMG;
	@FXML
	private Text yourScoreText;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String yes_path = ClassLoader.getSystemResource("text-1670682864624.png").toString();
		Image yes = new Image(yes_path);
		yesIMG.setImage(yes);
		
		String no_path = ClassLoader.getSystemResource("text-1670682874098.png").toString();
		Image no = new Image(no_path);
		noIMG.setImage(no);
		
		String time_path = ClassLoader.getSystemResource("text-1670683535366.png").toString();
		Image time = new Image(time_path);
		timeIMG.setImage(time);
		
		String up_path = ClassLoader.getSystemResource("text-1670683506454.png").toString();
		Image up = new Image(up_path);
		upIMG.setImage(up);
		
		String playagain_path = ClassLoader.getSystemResource("text-1670683737700.png").toString();
		Image playagain = new Image(playagain_path);
		playAgainIMG.setImage(playagain);
		
		this.yourScoreText.setText("Your Score : "+SpaceInvaders.getScore());

	}


	public void yesButtonHangler(ActionEvent event) throws IOException{
//		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
//		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
	}
	public void noButtonHangler(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
