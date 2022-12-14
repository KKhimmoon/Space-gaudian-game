package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HowToPlayController implements Initializable{

	@FXML
	private ImageView howToPlayBg;
	@FXML
	private ImageView bigMeteoriteImg;
	@FXML
	private ImageView smallMeteoriteImg;
	@FXML
	private ImageView mouseImg;
	@FXML
	private ImageView alienImg;
	@FXML
	private ImageView bombItemImg;
	@FXML
	private ImageView bulletItemImg;
	@FXML
	private ImageView spaceBarImg;
	@FXML
	private Pane howToPlayPane;

	private Canvas canvas;
	private GraphicsContext gc;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		howToPlayBg.setImage(sharedObject.RenderableHolder.howToPlayBg);
//		bigMeteoriteImg.setImage(sharedObject.RenderableHolder.meteorite);
//		smallMeteoriteImg.setImage(sharedObject.RenderableHolder.meteorite);
//		mouseImg.setImage(sharedObject.RenderableHolder.mouseClick);
//		alienImg.setImage(sharedObject.RenderableHolder.enemy);
//		bombItemImg.setImage(sharedObject.RenderableHolder.bombItem);
//		bulletItemImg.setImage(sharedObject.RenderableHolder.bulletItem);
//		spaceBarImg.setImage(sharedObject.RenderableHolder.spaceBar);
		
	}
}
