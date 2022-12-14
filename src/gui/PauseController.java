package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import screenDrawing.MainGameScreen;
public class PauseController extends Pane implements Initializable{
	
	@FXML
	private ImageView pausedImg;
	@FXML
	private ImageView resumeImg;
	@FXML
	private ImageView exitImg;
	@FXML
	private Button resumeBtn;
	@FXML
	private Button exitBtn;
	
	private Button pauseBtn;
	private Stage stage;
	private Scene scene;
	
	public PauseController() {
		this.pauseBtn = new Button();
		ImageView pauseBtnImg = new ImageView(sharedObject.RenderableHolder.pausedBtn);
		pauseBtnImg.setFitHeight(30);
		pauseBtnImg.setFitWidth(30);
		this.pauseBtn.setGraphic(pauseBtnImg);
		this.pauseBtn.setStyle("-fx-background-color: #FFFFFF00;");
		this.pauseBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
			@Override
            public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				sharedObject.RenderableHolder.stopSound.play();
				MainGameScreen.getInstance().getPauseScene().setVisible(true);
				Timer.getAnimationTimer().stop();
			}
		});
		this.setCursor(Cursor.HAND);
		this.setStyle("-fx-background-color: #FFFFFF00;");
		this.getChildren().add(pauseBtn);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		pausedImg.setImage(sharedObject.RenderableHolder.pausedTxt);
		resumeImg.setImage(sharedObject.RenderableHolder.resumeTxt);
		exitImg.setImage(sharedObject.RenderableHolder.exitTxt);
		
		resumeBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				resumeBtn.setStyle("-fx-background-color: #F12808; -fx-background-radius: 10;");
			}
		});
		
		resumeBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				resumeBtn.setStyle("-fx-background-color: #FAE82E; -fx-background-radius: 10;");
			}
		});
		
		resumeBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					onClickResume(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				exitBtn.setStyle("-fx-background-color: #F12808; -fx-background-radius: 10;");
			}
		});
		
		exitBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				exitBtn.setStyle("-fx-background-color: #FAE82E; -fx-background-radius: 10;");
			}
		});
		
		exitBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				try {
					onClickExit(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void onClickResume(Event event) throws IOException {
		sharedObject.RenderableHolder.onClickSound.play();
		Timer.getAnimationTimer().start();
		screenDrawing.MainGameScreen.getInstance().getPauseScene().setVisible(false);
	}
	
	public void onClickExit(Event event) throws IOException {
		sharedObject.RenderableHolder.onClickSound.play();
		sharedObject.RenderableHolder.mainGameSound.stop();
		screenDrawing.MainGameScreen.restart();
		Timer.getMainGameSound().stop();
		Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
}
