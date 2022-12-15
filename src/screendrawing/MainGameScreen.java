package screenDrawing;

import java.io.IOException;


import gui.BombPane;
import gui.PauseController;
import gui.TimeAndScorePane;
import gui.Timer;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.GameLogic;


public class MainGameScreen extends Scene{
	private Canvas canvas;
	private GraphicsContext gc;
	private Pane root;
	private Parent endGameScene;
	private	Parent pauseScene;
	private double mouseX;
	private BombPane bombPane ;
	private TimeAndScorePane timeAndScorePane;
	private static MainGameScreen instance;
	
	private MainGameScreen() {
		// TODO Auto-generated method stub
		super(new Pane(),GameLogic.WIDTH,GameLogic.HEIGHT);
		setCanvas(new Canvas(GameLogic.WIDTH,GameLogic.HEIGHT));
		setGc(getCanvas().getGraphicsContext2D());
		getCanvas().setCursor(Cursor.MOVE);
		getCanvas().setOnMouseMoved(e-> setMouseX(e.getX()));
		getCanvas().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				
				if(GameLogic.getAllShots().size() < GameLogic.getMaxshot()) {
					GameLogic.getAllShots().add(GameLogic.getPlayer().shoot("Player Shot"));
					sharedObject.RenderableHolder.laserGunSound.play();
				}
				
			}
		});
		logic.GameLogic.InitializeGame();
		root = new Pane(); 
		root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE) {
            	if(logic.GameLogic.getAmountBomb()> 0) {
            		logic.GameLogic.setAmountBomb(logic.GameLogic.getAmountBomb()-1);
            		GameLogic.getAllShots().add(GameLogic.getPlayer().shoot("Bomb Shot"));
            	}
			}
		});
		
		timeAndScorePane = new TimeAndScorePane();
		timeAndScorePane.setTranslateX(680);
		bombPane = new BombPane();
		bombPane.setAlignment(Pos.BOTTOM_RIGHT);
		bombPane.setTranslateX(700);
		bombPane.setTranslateY(520);
		PauseController pauseBtn = new PauseController();
		pauseBtn.setTranslateX(10);
		pauseBtn.setTranslateY(10);
		initializeEndGameScene();
		initializePauseScene();
		root.getChildren().addAll(getCanvas(),timeAndScorePane,bombPane,pauseBtn,getEndGameScene(),getPauseScene());
		this.setRoot(root);
	}

	public void run() {
		bombPane.drawCurrentAmount(BombPane.getGc());
		timeAndScorePane.updateScore(timeAndScorePane.getGc());
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				getGc().drawImage(sharedObject.RenderableHolder.mainGameBg,0,0, GameLogic.WIDTH,GameLogic.HEIGHT);
				GameLogic.runGameScence(getGc());
			}
		});
		
	}
	
	public void initializeEndGameScene() {
		try {
			setEndGameScene(FXMLLoader.load(getClass().getClassLoader().getResource("gui/EndGameScene.fxml")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getEndGameScene().setVisible(false);
	}
	
	public void initializePauseScene() {
		try {
			setPauseScene(FXMLLoader.load(getClass().getClassLoader().getResource("gui/PauseScene.fxml")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getPauseScene().setVisible(false);
	}
	
	public static void restart(){
		GameLogic.InitializeGame();
		MainGameScreen.instance = null;
	}
	
	public static MainGameScreen getInstance() {
		if (instance == null) instance = new MainGameScreen();
		return instance;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	
	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public double getMouseX() {
		return mouseX;
	}
	
	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public Parent getEndGameScene() {
		return endGameScene;
	}

	public void setEndGameScene(Parent endGameScene) {
		this.endGameScene = endGameScene;
	}

	public Parent getPauseScene() {
		return pauseScene;
	}

	public void setPauseScene(Parent pauseScene) {
		this.pauseScene = pauseScene;
	}
	
}

	