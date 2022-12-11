package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

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

public class SpaceInvaders extends Scene {
	static final Random RAND = new Random(); // private
	
	
	public static Pane root;
	public static Parent modal;
	
	
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static final int PLAYER_SIZE = 100;
	
	static String spaceship1_path = ClassLoader.getSystemResource("295ef468535024b2.png").toString();
	static final Image spaceship1img = new Image(spaceship1_path);
	static String explosion_path = ClassLoader.getSystemResource("shield.png").toString();
	static final Image EXPLOSION_IMG = new Image(explosion_path);
	static final int EXPLOSION_W = 128;
	static final int EXPLOSION_ROWS = 3;
	static final int EXPLOSION_COL =3;
	static final int EXPLOSION_H = 128;
	static final int EXPLOSION_STEPS = 15;
	static String enemy_path = ClassLoader.getSystemResource("35cc4b0f4226a0f1.png").toString();
	static final Image enemyimg = new Image(enemy_path);
	
	final int Maxbom = 10; 
	final int MaxShot = Maxbom*2;
	boolean gameOver = false;
	private GraphicsContext gc;
	
	Rocket player;
	ArrayList<Shot> shots;
	ArrayList<Universe> univ;
	ConcurrentLinkedQueue<Enemy> enemys;
	private int score;
	private double mouseX;
	
	Enemy newEnemy() {
		return new Enemy(50 + RAND.nextInt(WIDTH-100),0,PLAYER_SIZE);
		
	}
	
	public static int distance(int x1,int y1,int x2,int y2) {
		return (int)Math.sqrt(Math.pow(x1-x2,2) + Math.pow((y1-y2), 2));
	}
	
	
	public SpaceInvaders() {
		super(new StackPane(),WIDTH, HEIGHT);
		// TODO Auto-generated method stub
		root = new Pane();
		TimeAndScorePane timerAndScorePane = new TimeAndScorePane();
		timerAndScorePane.setAlignment(Pos.TOP_RIGHT);
		BombPane bombpane = new BombPane();
		bombpane.setAlignment(Pos.BOTTOM_RIGHT);
		
		Canvas canvas = new Canvas(WIDTH,HEIGHT);
		gc = canvas.getGraphicsContext2D();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e-> run(gc)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
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
					setup();
				}
				
			}
		});
		setup();

		try {
			modal = FXMLLoader.load(getClass().getResource("EndgameScene.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		modal.setVisible(false);
		bombpane.setTranslateX(700);
		bombpane.setTranslateY(520);
		modal.setTranslateX(170);
		modal.setTranslateY(150);
		root.getChildren().addAll(canvas,timerAndScorePane,bombpane,modal);
		this.setRoot(root);
	}
	
	public void setup() {
		univ = new ArrayList<>();
		shots = new ArrayList<>();
		enemys = new ConcurrentLinkedQueue<>();
		player = new Rocket(WIDTH/2,HEIGHT -PLAYER_SIZE,PLAYER_SIZE);
		score = 0;
		IntStream.range(0, Maxbom).mapToObj(i -> this.newEnemy());
	}
	
	private void run(GraphicsContext gc) {
		gc.setFill(Color.grayRgb(20));
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(Font.font(20));
		gc.setFill(Color.WHITE);
		gc.fillText("Score" + score,750,25);
		
		
		if(gameOver) {
			gc.setFont(Font.font(35));
			gc.setFill(Color.YELLOW);
			gc.fillText("Game Over" + score,WIDTH/2,HEIGHT/2.5);
		}
		
		if(RAND.nextInt(50) > 5) {
			enemys.add(newEnemy());
		}
		for(Enemy x:enemys) {
			x.draw(gc);
		}
		
		player.update();
		player.draw(gc);
		player.setPosX((int) mouseX);
		

//		enemys.stream().peek(Rocket::update).peek(Rocket::draw(gc)).forEach(e -> {
//			if(player.colide(e) && !player.isExploding()) {
//				player.explode();
//			}
//		});
		for(int i = shots.size()-1;i>= 0;i--) {
			Shot shot = shots.get(i);
			if(shot.getPosY()< 0 || shot.isRemove) {
				shots.remove(i);
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
		for(Enemy x:enemys) {
			if(x.isDestroyed()) {
				enemys.remove(x);
			}
		}
//		for(int i = enemys.size()-1;i>= 0;i--) {
//			if(enemys.get(i).isDestroyed()) {
//				enemys.set(i, newEnemy());
//			}
//		}
		gameOver = player.isDestroyed();
		if(RAND.nextInt(10)>2) {
			univ.add(new Universe());
		}
		for(int i = 0;i< univ.size();i++) {
			if(univ.get(i).posY > HEIGHT) {
				univ.remove(i);
			}
		}
		
	}

	
	

}
