package screendrawing;

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
	private  GraphicsContext gc;
	public  Pane root;
	public  Parent endGame;
	public	Parent pauseScene;
	private static TimeAndScorePane timerAndScorePane;
	private double mouseX;
	private BombPane bombPane ;
	private static MainGameScreen instance;
	
	public static MainGameScreen getInstance() {
		if (instance == null) instance = new MainGameScreen();
		return instance;
	}

	private MainGameScreen() {
		// TODO Auto-generated method stub
		super(new Pane(),GameLogic.WIDTH,GameLogic.HEIGHT);
		Canvas canvas = new Canvas(GameLogic.WIDTH,GameLogic.HEIGHT);
		gc = canvas.getGraphicsContext2D();
		canvas.setCursor(Cursor.MOVE);
		canvas.setOnMouseMoved(e-> mouseX = e.getX());
		canvas.setOnMouseClicked(new EventHandler<Event>() {

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
		timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setTranslateX(680);
		bombPane = new BombPane();
		bombPane.setAlignment(Pos.BOTTOM_RIGHT);
		
		try {
			endGame = FXMLLoader.load(getClass().getClassLoader().getResource("gui/EndGameScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pauseScene = FXMLLoader.load(getClass().getClassLoader().getResource("gui/PauseScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PauseController pause = new PauseController();
		pause.setTranslateX(10);
		pause.setTranslateY(10);
		pauseScene.setVisible(false);
		endGame.setVisible(false);
		bombPane.setTranslateX(700);
		bombPane.setTranslateY(520);
		root.getChildren().addAll(canvas,timerAndScorePane,bombPane,pause,endGame,pauseScene);
		this.setRoot(root);
	}

	public void run() {
		bombPane.drawCurrentAmount(BombPane.getGc());
		timerAndScorePane.updateScore(timerAndScorePane.getGc());
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gc.drawImage(sharedObject.RenderableHolder.mainGameBg,0,0, GameLogic.WIDTH,GameLogic.HEIGHT);
				GameLogic.runGameScence(gc);
			}
		});
		
	}
	
	public GraphicsContext getGc() {
		return gc;
	}

	public double getMouseX() {
		return mouseX;
	}
	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}
	public static void restart(){
		GameLogic.InitializeGame();
		MainGameScreen.instance = null;
	}
	
}

	