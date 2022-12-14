package screen;

import java.io.IOException;

import gui.BombPane;
import gui.PauseController;
import gui.TimeAndScorePane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import logic.GameLogic;

public class MainGameScreen  extends Scene{
	private static GraphicsContext gc;
	public static Pane root;
	public static Parent endgame;
	public static Parent pausescene;
	private static TimeAndScorePane timerAndScorePane;
	private static BombPane bombpane ;
	public MainGameScreen() {
		// TODO Auto-generated method stub
		super(new Pane(),GameLogic.WIDTH,GameLogic.HEIGHT);
		Canvas canvas = new Canvas(GameLogic.WIDTH,GameLogic.HEIGHT);
		gc = canvas.getGraphicsContext2D();
		canvas.setCursor(Cursor.MOVE);
		canvas.setOnMouseMoved(e-> GameLogic.mouseX = e.getX());
		canvas.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				
				if(allShots.size() < MaxShot) {
					allShots.add(player.shoot("Player Shot"));
					sharedObject.RenderableHolder.laserGunSound.play();
				}
				
			}
		});
		InitializeGame();
		root = new Pane(); 
		root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE) {
            	if(getAmountBomb()> 0) {
            		setAmountBomb(getAmountBomb()-1);
            		allShots.add(GameLplayer.shoot("Bomb Shot"));
            	}
			}
		});
		timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setTranslateX(680);
		bombpane = new BombPane();
		//BombPane bombpane = new BombPane();
		bombpane.setAlignment(Pos.BOTTOM_RIGHT);
		
		try {
			endgame = FXMLLoader.load(getClass().getClassLoader().getResource("gui/EndGameScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pausescene = FXMLLoader.load(getClass().getClassLoader().getResource("gui/PauseScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PauseController pause = new PauseController();
		pause.setTranslateX(10);
		pause.setTranslateY(10);
		pausescene.setVisible(false);
		endgame.setVisible(false);
		bombpane.setTranslateX(700);
		bombpane.setTranslateY(520);
		root.getChildren().addAll(canvas,timerAndScorePane,bombpane,pause,endgame,pausescene);
		this.setRoot(root);
	}
	public static void run(GraphicsContext gc) {
		bombpane.drawCurrentAmount(BombPane.getGc());
		timerAndScorePane.updateScore(timerAndScorePane.getGc());
		gc.drawImage(sharedObject.RenderableHolder.mainGameBg,0,0, GameLogic.WIDTH,GameLogic.HEIGHT);
		GameLogic.GamerunGameScence(gc);
	}
	
	
}

	