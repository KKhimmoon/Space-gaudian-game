package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

import com.sun.media.jfxmediaimpl.platform.Platform;

import application.BombPane;
import application.Pause;
import application.TimeAndScorePane;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import sharedObject.RenderableHolder;

public class GameLogic extends Scene {

	private static Rocket player;
	private static ConcurrentLinkedQueue<Shot> shots;
	private static ConcurrentLinkedQueue<Shot> enemysshots;
	private static ConcurrentLinkedQueue<Enemy> enemys;
	private static ConcurrentLinkedQueue<BombItem> bombitems;
	private static ConcurrentLinkedQueue<BulletItem> bulletitems;
	public static int countBomb;

	public static int BulletState;
	
	public static GraphicsContext getGc() {
		return gc;
	}

	private static int score;
	private static final Random RAND = new Random(); // private
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static final int PLAYER_SIZE = 100;
	final int MaxShot = 20;
	private static boolean gameOver = false;
	private static GraphicsContext gc;
	private static double mouseX;
	
	public static Pane root;
	public static Parent modal;
	public static Parent pausescene;
	
	private static TimeAndScorePane timerAndScorePane;
	
	public void InitializeGame() {
		shots = new ConcurrentLinkedQueue<>();
		enemys = new ConcurrentLinkedQueue<>();
		enemysshots = new ConcurrentLinkedQueue<>();
		bombitems = new ConcurrentLinkedQueue<>();
		player = new Rocket(WIDTH/2,HEIGHT-PLAYER_SIZE-30,PLAYER_SIZE);
		setBulletState(0);
		setScore(0);
	}
	
	public static int getCountBomb() {
		return countBomb;
	}


	public static void setCountBomb(int countBomb) {
		GameLogic.countBomb = countBomb;
	}
	public static int getBulletState() {
		return BulletState;
	}


	public static void setBulletState(int bulletState) {
		BulletState = bulletState;
	}


	public static int getScore() {
		return score;
	}
	public static int distance(int x1,int y1,int x2,int y2) {
		return (int)Math.sqrt(Math.pow(x1-x2,2) + Math.pow((y1-y2), 2));
	}


	public void setScore(int score) {
		GameLogic.score = score;
	}
	public static Enemy newEnemy() {
		return new Enemy(50 + RAND.nextInt(WIDTH-100),0,PLAYER_SIZE);
		
	}
	public static BombItem newBomb() {
		return new BombItem(50 + RAND.nextInt(WIDTH-100),0,PLAYER_SIZE);
		
	}

	public GameLogic() {
		// TODO Auto-generated method stub
		super(new Pane(),WIDTH,HEIGHT);
		Canvas canvas = new Canvas(WIDTH,HEIGHT);
		gc = canvas.getGraphicsContext2D();
//		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e-> run(gc)));
//		timeline.setCycleCount(Timeline.INDEFINITE);
//		timeline.play();
//		AnimationTimer animationTimer = new AnimationTimer() {
//			public void handle(long arg0) {
//				// ===========================================
//				
//				// ===========================================
//		
//				
//				}
//		};
//
//		animationTimer.start();
		canvas.setCursor(Cursor.MOVE);
		canvas.setOnMouseMoved(e-> mouseX = e.getX());
		canvas.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if(shots.size() < MaxShot) {
					shots.add(player.shoot());
				}
				if(gameOver) {
					gameOver = false;
					InitializeGame();
				}
				
			}
		});
		InitializeGame();
		
		root = new Pane();
		timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setTranslateX(680);
		BombPane bombpane = new BombPane();
		bombpane.setAlignment(Pos.BOTTOM_RIGHT);
		
		try {
			modal = FXMLLoader.load(getClass().getClassLoader().getResource("application/EndgameScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pausescene = FXMLLoader.load(getClass().getClassLoader().getResource("application/PauseScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Pause pause = new Pause();
		pause.setTranslateX(10);
		pause.setTranslateY(10);
		pausescene.setVisible(false);
		modal.setVisible(false);
		bombpane.setTranslateX(700);
		bombpane.setTranslateY(520);
		root.getChildren().addAll(canvas,timerAndScorePane,bombpane,pause,modal,pausescene);
		this.setRoot(root);
	}
	
	public static void run(GraphicsContext gc) {
		timerAndScorePane.updateScore(timerAndScorePane.getGc());
		gc.setFill(Color.grayRgb(20));
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
//		gc.setFont(Font.font(20));
//		gc.setFill(Color.WHITE);
//		gc.fillText("Score" + score,60,20);
		
		if(gameOver) {
			gc.setFont(Font.font(35));
			gc.setFill(Color.YELLOW);
			gc.fillText("Game Over" + score,WIDTH/2,HEIGHT/2.5);
		}
		if(RAND.nextInt(500) < 10) {
			enemys.add(newEnemy());
		}
		for(Enemy x:enemys) {
			if(x.isExploding()) {enemys.remove(x); continue;}
			x.draw(gc);
			if(RAND.nextInt(500) < 20) {
				enemysshots.add(x.shoot());
			}
//			enemysshots.add(x.shoot());
		}

		for(Shot shot: enemysshots) {
//				System.out.println("haha");
				if(shot.getPosY()< 0 || shot.isRemove) {
					enemysshots.remove(shot);
					continue;
				}
				shot.update();
				shot.draw(gc);
				if(shot.colide(player)) {
						score--;
						shot.setRemove(true);
					}
		}
		if(RAND.nextInt(1000) < 10) {
			bombitems.add(newBomb());
		}
		player.update();
		player.draw(gc);
		player.setPosX((int) mouseX);
		for(Shot shot: shots) {
			
			if(shot.getPosY()< 0 || shot.isRemove) {
				shots.remove(shot);
				continue;
			}
			shot.update();
			shot.draw(gc);
			for(Enemy x:enemys) {
				if(shot.colide(x) && !x.isExploding()) {
					score++;
					x.explode();
					shot.setRemove(true);
				}
			}
		}
		for(BombItem x:bombitems) {
			x.draw(gc);
			if(player.colide(x) && !player.isExploding()) {
				x.explode();
				countBomb +=1;
				BulletState += 1;
			}
		}	
//		for(int i = enemys.size()-1;i>= 0;i--) {
//			if(enemys.get(i).isDestroyed()) {
//				enemys.set(i, newEnemy());
//			}
//		}
		gameOver = player.isDestroyed();
	}

	public static TimeAndScorePane getTimerAndScorePane() {
		return timerAndScorePane;
	}

	public static void setTimerAndScorePane(TimeAndScorePane timerAndScorePane) {
		GameLogic.timerAndScorePane = timerAndScorePane;
	}


}
