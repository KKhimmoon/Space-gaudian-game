package application;

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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.GameLogic;
public class Pause extends Pane implements Initializable{
	
	@FXML
	private ImageView pausedIMG;
	@FXML
	private ImageView resumeIMG;
	@FXML
	private ImageView exitIMG;
	
	@FXML
	private Button resumeBtn;
	@FXML
	private Button exitBtn;
	
	private Button pauseBtn;
	private Stage stage;
	private Scene scene;
	
	public Pause() {
		this.pauseBtn = new Button();
		ImageView pauseBtnIMG = new ImageView(new Image(ClassLoader.getSystemResource("pause.png").toString()));
		pauseBtnIMG.setFitHeight(30);
		pauseBtnIMG.setFitWidth(30);
		this.pauseBtn.setGraphic(pauseBtnIMG);
		this.pauseBtn.setStyle("-fx-background-color: #FFFFFF00;");
		this.pauseBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
			@Override
            public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				logic.GameLogic.pausescene.setVisible(true);
				Timer.getAnimationTimer().stop();
			}
		});
//		this.pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				logic.GameLogic.pausescene.setVisible(true);
//				Timer.getAnimationTimer().stop();
//			}
//		});
		
//		this.resumeBtn.setCursor(Cursor.HAND);
//		this.resumeBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				SpaceInvaders.pausescene.setVisible(false);
//			}
//		});
//		this.exitBtn.setCursor(Cursor.HAND);
//		this.exitBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		this.setCursor(Cursor.HAND);
		this.setStyle("-fx-background-color: #FFFFFF00;");
		this.getChildren().add(pauseBtn);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String paused_path = ClassLoader.getSystemResource("text-1670742224985.png").toString();
		Image paused = new Image(paused_path);
		pausedIMG.setImage(paused);
		
		String resume_path = ClassLoader.getSystemResource("text-1670742468482.png").toString();
		Image resume = new Image(resume_path);
		resumeIMG.setImage(resume);

		String exit_path = ClassLoader.getSystemResource("text-1670742457889.png").toString();
		Image exit = new Image(exit_path);
		exitIMG.setImage(exit);
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
		Timer.getAnimationTimer().start();
		logic.GameLogic.pausescene.setVisible(false);
	}
	public void onClickExit(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}

}
