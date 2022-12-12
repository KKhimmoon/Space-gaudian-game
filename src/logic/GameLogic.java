package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	public static int countBomb2;
	public static int countterBomb;
	public static int countBullet;
	public static int countterBullet;
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
	public static Parent endgame;
	public static Parent pausescene;
	
	private static TimeAndScorePane timerAndScorePane;
	private static BombPane bombpane ;
	
	public void InitializeGame() {
		shots = new ConcurrentLinkedQueue<>();
		enemys = new ConcurrentLinkedQueue<>();
		enemysshots = new ConcurrentLinkedQueue<>();
		bulletitems = new ConcurrentLinkedQueue<>();
		bombitems = new ConcurrentLinkedQueue<>();
		player = new Rocket(WIDTH/2,HEIGHT-PLAYER_SIZE-30,PLAYER_SIZE);
		countBomb2 = 1000;
		countterBomb = 0;
		countBullet = 1200;
		countterBullet = 0;
		setCountBomb(0);
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
		return new Enemy(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
		
	}
	public static BombItem newBomb() {
		return new BombItem(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
	}
	public static BulletItem newBullet() {
		return new BulletItem(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
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
			public void handle(Event e) {
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
		this.setOnKeyPressed((KeyEvent keyevent) -> {
			if(keyevent.getCode().equals(KeyCode.SPACE)) {
				shots.add(player.shoot());
				System.out.println("pppppppppppp");
			}
		});
		InitializeGame();
		root = new Pane();
		timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setTranslateX(680);
		bombpane = new BombPane();
		bombpane.setAlignment(Pos.BOTTOM_RIGHT);
		
		try {
			endgame = FXMLLoader.load(getClass().getClassLoader().getResource("application/EndgameScene.fxml"));
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
		endgame.setVisible(false);
		bombpane.setTranslateX(700);
		bombpane.setTranslateY(520);
		root.getChildren().addAll(canvas,timerAndScorePane,bombpane,pause,endgame,pausescene);
		this.setRoot(root);
	}
	
	public static void run(GraphicsContext gc) {
		bombpane.drawCurrentAmount(BombPane.getGc());
		timerAndScorePane.updateScore(timerAndScorePane.getGc());
		gc.setFill(Color.grayRgb(20));
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		
//		if(gameOver) {
//			gc.setFont(Font.font(35));
//			gc.setFill(Color.YELLOW);
//			gc.fillText("Game Over" + score,WIDTH/2,HEIGHT/2.5);
//		}
	//	----------------------------------------------
		if(enemys.size() < 2) {
		if(RAND.nextInt(500) < 10) {
			enemys.add(newEnemy());
		}}
		for(Enemy x:enemys) {
			if(x.isExploding()) {enemys.remove(x); continue;}
			x.draw(gc);
			if(RAND.nextInt(500) < 20) {
				enemysshots.add(x.shoot());
			}
		}
		for(Shot shot: enemysshots) {
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
	//------------------------------------------------
		if(bulletitems.size() < 1) {
			if(RAND.nextInt(500) < 10) {
				bulletitems.add(newBullet());
		}}
		
		for(BulletItem x:bulletitems) {
			countterBullet += 1;
			if(countterBullet >= countBullet) {
				x.draw(gc);
				if(x.isDestroyed() || x.isExploding()) {
					countterBullet = 0;
					bulletitems.remove(x);
				}
			}
		}
		for(BulletItem x:bulletitems) {
			if(player.colide(x) && !player.isExploding()) {
				x.explode();
				BulletState += 1;
			}
		}
	//------------------------------------------------	
		
	   if(bombitems.size() < 1) {
			if(RAND.nextInt(500) < 10) {
				bombitems.add(newBomb());
	   }}
		
	for(BombItem x: bombitems) {
			countterBomb += 1;
			if(countterBomb >= countBomb2) {
				x.draw(gc);
				if(x.isDestroyed() || x.isExploding()) {
					countterBomb = 0;
					bombitems.remove(x);
			   }
			}
	}
	for(BombItem x: bombitems) {
		if(player.colide(x) && !player.isExploding()) {
			x.explode();
			setCountBomb(getCountBomb()+1);
		}
	  }
	//------------------------------------------------
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
					shot.setRemove(true);
					x.attack(player);
					if(x.getBlood() == 0) {
						x.explode();
						score += 10;
					}
				}
			}
		}
		gameOver = player.isDestroyed();
	}

	public static TimeAndScorePane getTimerAndScorePane() {
		return timerAndScorePane;
	}

	public static void setTimerAndScorePane(TimeAndScorePane timerAndScorePane) {
		GameLogic.timerAndScorePane = timerAndScorePane;
	}


}
