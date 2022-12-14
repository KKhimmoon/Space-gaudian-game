package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import logic.GameLogic;

public class SelectedController implements Initializable {
	
	@FXML
	private Button playButton;
	@FXML
	private Button player1Btn;
	@FXML
	private Button player2Btn;
	@FXML
	private Button player3Btn;
	@FXML
	private Button backHomeBtn;
	
	@FXML
	private ImageView playImage;
	@FXML
	private ImageView selectedBg;
	@FXML
	private ImageView spaceShip1;
	@FXML
	private ImageView spaceShip2;
	@FXML
	private ImageView spaceShip3;
	@FXML
	private ImageView backHomeImg;
	
	private static Image selectedSpaceShip;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private boolean isSelected1;
	private boolean isSelected2;
	private boolean isSelected3;
	
	public SelectedController() {
		super();
		setSelectedSpaceShip(null);
		setSelected1(false);
		setSelected2(false);
		setSelected3(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		spaceShip1.setImage(sharedObject.RenderableHolder.player1);
		spaceShip2.setImage(sharedObject.RenderableHolder.player2);
		spaceShip3.setImage(sharedObject.RenderableHolder.player3);
		playImage.setImage(sharedObject.RenderableHolder.playTxt);
		selectedBg.setImage(sharedObject.RenderableHolder.selectedBg);
		backHomeImg.setImage(sharedObject.RenderableHolder.arrow);
		backHomeImg.setRotate(180);
		
		
		player1Btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isSelected1()) player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				else{
					sharedObject.RenderableHolder.mouseEnteredSound.play();
					player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #FC441F; -fx-border-width: 5;");}
			}
		});
		
		player1Btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isSelected1()) player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				else{player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");}
			}
		});
		player1Btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				sharedObject.RenderableHolder.onClickSound.play();
				setSelected1(true);
				setSelected2(false);
				setSelected3(false);
				setSelectedSpaceShip(sharedObject.RenderableHolder.player1);
				player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
				player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
			}
		});
		player2Btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isSelected2()) player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				else{
					sharedObject.RenderableHolder.mouseEnteredSound.play();
					player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #FC441F; -fx-border-width: 5;");}
			}
		});
		
		player2Btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isSelected2()) player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				else{player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");}
			}
		});
		player2Btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				sharedObject.RenderableHolder.onClickSound.play();
				setSelected1(false);
				setSelected2(true);
				setSelected3(false);
				setSelectedSpaceShip(sharedObject.RenderableHolder.player2);
				player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
				player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
			}
		}); 
		player3Btn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isSelected3()) player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				else {
					sharedObject.RenderableHolder.mouseEnteredSound.play();
					player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #FC441F; -fx-border-width: 5;");}
			}
		});
		
		player3Btn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(isSelected3()) player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
				else {player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");}
			}
		});
		player3Btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				sharedObject.RenderableHolder.onClickSound.play();
				setSelected1(false);
				setSelected2(false);
				setSelected3(true);
				setSelectedSpaceShip(sharedObject.RenderableHolder.player3);
				player1Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
				player2Btn.setStyle("-fx-background-color: none; -fx-border-color: #F1C40F; -fx-border-width: 5;");
				player3Btn.setStyle("-fx-background-color: none; -fx-border-color: #7FFF00; -fx-border-width: 5;");
			}
		}); 
		
		playButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				playImage.setScaleX(1.1);
				playImage.setScaleY(1.1);
			}
		});
		
		playButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				playImage.setScaleX(1);
				playImage.setScaleY(1);
			}
		});
		
		playButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				try {
					switchToGame(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		backHomeBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				try {
					switchToHome(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		backHomeBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				backHomeImg.setScaleX(1.1);
				backHomeImg.setScaleY(1.1);
			}
		});
		
		backHomeBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				backHomeImg.setScaleX(1);
				backHomeImg.setScaleY(1);
			}
		});
	}

	public boolean isSelected1() {
		return isSelected1;
	}

	public void setSelected1(boolean isSelected1) {
		this.isSelected1 = isSelected1;
	}
	public boolean isSelected2() {
		return isSelected2;
	}

	public void setSelected2(boolean isSelected2) {
		this.isSelected2 = isSelected2;
	}
	public boolean isSelected3() {
		return isSelected3;
	}

	public void setSelected3(boolean isSelected3) {
		this.isSelected3 = isSelected3;
	}

	public static Image getSelectedSpaceShip() {
		return selectedSpaceShip;
	}

	public void setSelectedSpaceShip(Image selectedSpaceShip) {
		this.selectedSpaceShip = selectedSpaceShip;
	}

	public void switchToHome(Event event) throws IOException {
		sharedObject.RenderableHolder.onClickSound.play();
		sharedObject.RenderableHolder.homeSound.stop();
		HomeController.homeSound.stop();
		 Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	public void switchToGame(Event event) throws IOException {
		if(getSelectedSpaceShip()==null) {
			sharedObject.RenderableHolder.errorSound.play();
			String dialogueString = "You have to choose a Spaceship before PLAY";
			Alert alert = new Alert(AlertType.CONFIRMATION, dialogueString, ButtonType.OK);
	        alert.setHeaderText("Warning");
	        alert.setTitle("Selected Warning");
	        Optional<ButtonType> result = alert.showAndWait();
	        
	        if (result.get() == ButtonType.OK) {
	            return ;
	        }
		}
		sharedObject.RenderableHolder.onClickSound.play();
		if(sharedObject.RenderableHolder.homeSound.isPlaying()||sharedObject.RenderableHolder.mainGameSound.isPlaying()) {
			sharedObject.RenderableHolder.homeSound.stop();
		}
		HomeController.homeSound.stop();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = screendrawing.MainGameScreen.getInstance();
		stage.setScene(scene);
		stage.show();
		 
	}
	

}