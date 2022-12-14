package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import com.sun.media.jfxmediaimpl.platform.Platform;

import gui.BombPane;
import gui.PauseController;
import gui.TimeAndScorePane;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;

public class GameLogic extends Scene {

	private static Rocket player;
	private static ConcurrentLinkedQueue<Shot> allShots;
	private static ConcurrentLinkedQueue<Shot> allEnemysShots;
	private static ConcurrentLinkedQueue<BombItem> allBombitems;
	private static ConcurrentLinkedQueue<BulletItem> allBulletitems;
	private static ConcurrentLinkedQueue<Enemy> allEnemys;
	private static ConcurrentLinkedQueue<Particle> allParticles;
	public static final Random RAND = new Random(); 
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final int PLAYER_SIZE = 100;
	private static final int MaxShot = 20;
	private static int score;
	private static double mouseX;
	private static boolean hasAlien;



	public static int countBomb;
	public static int countBomb2;
	public static int countterBomb;
	public static boolean isEnemy;
	public static int countBullet;
	public static int countterBullet;
	public static int BulletState;

	
	public static GraphicsContext getGc() {
		return gc;
	}
	private static GraphicsContext gc;
	public static Pane root;
	public static Parent endgame;
	public static Parent pausescene;
	private static TimeAndScorePane timerAndScorePane;
	private static BombPane bombpane ;
	
	public void InitializeGame() {
		allShots = new ConcurrentLinkedQueue<>();
		allEnemysShots = new ConcurrentLinkedQueue<>();
		allBulletitems = new ConcurrentLinkedQueue<>();
		allBombitems = new ConcurrentLinkedQueue<>();
		allEnemys = new ConcurrentLinkedQueue<>();
		allParticles = new ConcurrentLinkedQueue<>();
		player = new Rocket(WIDTH/2,HEIGHT-PLAYER_SIZE-30,PLAYER_SIZE);
		countBomb2 = 1000;
		countterBomb = 0;
		countBullet = 1200;
		countterBullet = 0;
		setCountBomb(0);
		setBulletState(0);
		setScore(0);
		setHasAlien(false);
	}
	
	
	public static boolean isHasAlien() {
		return hasAlien;
	}
	public static void setHasAlien(boolean hasAlein) {
		GameLogic.hasAlien = hasAlien;
	}
	public static int distance(int x1,int y1,int x2,int y2) {
		return (int)Math.sqrt(Math.pow(x1-x2,2) + Math.pow((y1-y2), 2));
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
	public void setScore(int score) {
		GameLogic.score = score;
	}
	public static Alien newAlien() {
		return new Alien(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
	}
	public static BombItem newBomb() {
		return new BombItem(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
	}
	public static BulletItem newBullet() {
		return new BulletItem(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
	}
	public static SmallMeteorite newSmallMeteo() {
		return new SmallMeteorite(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
	}
	public static BigMeteorite newBigMeteo() {
		return new BigMeteorite(10 + RAND.nextInt(WIDTH-120),0,PLAYER_SIZE);
	}
	
	public GameLogic() {
		// TODO Auto-generated method stub
		super(new Pane(),WIDTH,HEIGHT);
		Canvas canvas = new Canvas(WIDTH,HEIGHT);
		gc = canvas.getGraphicsContext2D();
		canvas.setCursor(Cursor.MOVE);
		canvas.setOnMouseMoved(e-> mouseX = e.getX());
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
            	if(getCountBomb()> 0) {
            		setCountBomb(getCountBomb()-1);
            		allShots.add(player.shoot("Bomb Shot"));

            	}
			}
		});
		
		
//		InitializeGame();
//		
//		root = new Pane();
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
		gc.drawImage(sharedObject.RenderableHolder.mainGameBg,0,0, WIDTH,HEIGHT);
		
		player.update();
		player.draw(gc);
		player.setPosX((int) mouseX);
	}
	     //  ----------------------------------------------	
//			if(RAND.nextInt(500) < 10) {
//					allEnemys.add(newBigMeteo());
//			 }
//     //  ----------------------------------------------	
//			if(RAND.nextInt(400) < 10) {
//				allEnemys.add(newSmallMeteo());
//		      }
//	//	----------------------------------------------
//			if(!hasAlien) {
//				allEnemys.add(newAlien());
//				setHasAlien(true);
//			}

//		for(Enemy x: allEnemys) {
//			if(x instanceof Alien) {
////				x.update();
//				if(x.isExploding()|| x.isDestroyed()) {allEnemys.remove(x); setHasEnemy(false);continue;}
//				x.draw(gc);
//				if(RAND.nextInt(500) < 20) {
//					alleEnemysShots.add(((Alien) x).shoot());
//				}
//			}
//	
//		}
//		for(Shot shot: allEnemysShots) {
//			if(shot.getPosY()< 0 || shot.isRemove) {
//				allEnemysShots.remove(shot);
//				continue;
//			}
//			shot.update();
//			shot.draw(gc);
//			if(shot.colide(player)) {
//					score--;
//					shot.setRemove(true);
//			}
//	}
	//------------------------------------------------
//		if(allBulletitems.size() < 1) {
//			if(RAND.nextInt(500) < 10) {
//				allBulletitems.add(newBullet());
//		}}
		
//		for(BulletItem x: allBulletitems) {
//			countterBullet += 1;
//			if(countterBullet >= countBullet) {
//				x.draw(gc);
//				x.update();
//				if(x.isDestroyed() || x.isExploding()) {
//					countterBullet = 0;
//					allBulletitems.remove(x);
//				}
//			}
//		}
//		for(BulletItem x: allBulletitems) {
//			if(player.colide(x) && !player.isExploding()) {
//				sharedObject.RenderableHolder.collectedSound.play();
//				x.explode();
//				BulletState += 1;
//				player.setPower(player.getPower() + 3);
//			}
//		}
	//------------------------------------------------	
		
//	   if(allBombitems.size() < 1) {
//			if(RAND.nextInt(500) < 10) {
//				allBombitems.add(newBomb());
//	   }}
//		
//	for(BombItem x: allBombitems) {
//			countterBomb += 1;
//			if(countterBomb >= countBomb2) {
//				x.draw(gc);
//				x.update();
//				if(x.isDestroyed() || x.isExploding()) {
//					countterBomb = 0;
//					allBombitems.remove(x);
//			   }
//		}
//	}
//	for(BombItem x: allBombitems) {
//		if(player.colide(x) && !player.isExploding()) {
//			sharedObject.RenderableHolder.collectedSound.play();
//			x.explode();
//			setCountBomb(getCountBomb()+1);
//		}
//	  }
	//------------------------------------------------
//		player.update();
//		player.draw(gc);
//		player.setPosX((int) mouseX);
//	for(Enemy x: allEnemys) {
//		if(x.isExploding()) {allEnemys.remove(x); continue;}
//		x.draw(gc);
//		x.update();
//	}
//	for(Shot shot: allShots) {
//		if(shot.getPosY()< 0 || shot.isRemove) {
//				allShots.remove(shot);
//				continue;
//		}
//		shot.update();
//		shot.draw(gc);
//		for(Enemy x: allEnemys) {
//			if(shot.colide(x) && !x.isExploding()) {
//				shot.setRemove(true);
//				x.attack(player);
//				if(shot.getName() == "Bomb Shot") {
//						sharedObject.RenderableHolder.destroySound.play();
//						allParticles.add(new Particle(shot.getPosX(),shot.getPosY()));
//						x.explode();
//				}
//				if(x.getBlood() == 0) {
//						x.explode();
//						score += x.getOwnscore();
//					}
//				}if(!(x instanceof BigMeteorite ||x instanceof SmallMeteorite) && x.isExploding()) {
//					isEnemy = true;
//			    }
//			}
//		}
//		for(Particle x : allParticles) {
//			x.countDelay();
//			if(x.isBombDone()) {allParticles.remove(x); continue;}
//			x.draw(gc);
//		}
//	}
	public static void collideEnemyShot(ConcurrentLinkedQueue<Enemy> allEnemys) {
		for(Shot shot: allEnemysShots) {
			if(shot.getPosY()< 0 || shot.isRemove) {
				allEnemysShots.remove(shot);
				continue;
			}
			shot.update();
			shot.draw(gc);
			if(shot.colide(player)) {
					score--;
					shot.setRemove(true);
			}
		}
	}
	public static void releaseBulletItem(ConcurrentLinkedQueue<BulletItem> allBulletitems,int countterBullet,int countBullet){
		for(BulletItem x: allBulletitems) {
			countterBullet += 1;
			if(countterBullet >= countBullet) {
				x.draw(gc);
				x.update();
				if(x.isDestroyed() || x.isExploding()) {
					countterBullet = 0;
					allBulletitems.remove(x);
				}
			}
		}	
	}
	public static void releaseBombItem(ConcurrentLinkedQueue<BombItem> allBombitems,int countterBomb,int countBomb2) {
		for(BombItem x: allBombitems) {
			countterBomb += 1;
			if(countterBomb >= countBomb2) {
				x.draw(gc);
				x.update();
				if(x.isDestroyed() || x.isExploding()) {
					countterBomb = 0;
					allBombitems.remove(x);
			}
		 }
	  }
	}
	public static void collideBombItem(ConcurrentLinkedQueue<BombItem> allBombitems,Rocket player) {
			for(BulletItem x: allBulletitems) {
				if(player.colide(x) && !player.isExploding()) {
					sharedObject.RenderableHolder.collectedSound.play();
					x.explode();
					BulletState += 1;
					player.setPower(player.getPower() + 3);
				}
			}
	}	
	public static void collideBulletItem(ConcurrentLinkedQueue<BulletItem> allBulletitems,Rocket player) {
		for(BulletItem x: allBulletitems) {
			if(player.colide(x) && !player.isExploding()) {
				sharedObject.RenderableHolder.collectedSound.play();
				x.explode();
				BulletState += 1;
				player.setPower(player.getPower() + 3);
			}
		}
	}
	public static void addEnemy(ConcurrentLinkedQueue<Enemy> allEnemys,boolean hasAlien) {
		if(RAND.nextInt(500) < 10) {
			allEnemys.add(newBigMeteo());
		}if(RAND.nextInt(400) < 10) {
			allEnemys.add(newSmallMeteo());
		}if(!hasAlien) {
		   allEnemys.add(newAlien());
		   setHasAlien(true);
		}
	}
	public static void removeDeadEnemy(ConcurrentLinkedQueue<Enemy> allEnemys) {
		for(Enemy x: allEnemys) {
			if(x.isExploding()) {
				allEnemys.remove(x); 
				continue;
			}
			x.draw(gc);
			x.update();
		}
	}
	public static void addShotsEnemy(ConcurrentLinkedQueue<Enemy> allEnemys,ConcurrentLinkedQueue<Shot> alleEnemysShots) {
		for(Enemy x: allEnemys) {
			if(x instanceof Alien) {
//				x.update();
				if(x.isExploding()|| x.isDestroyed()) {
					allEnemys.remove(x); 
					setHasAlien(false);
					continue;
				}
				x.draw(gc);
				if(RAND.nextInt(500) < 20) {
					alleEnemysShots.add(((Alien) x).shoot());
				  }
			   }
		  }
	}
	public static void addBulletItems(ConcurrentLinkedQueue<BulletItem> allBulletitems) {
		if(allBulletitems.size() < 1) {
			if(RAND.nextInt(500) < 10) {
				allBulletitems.add(newBullet());
			}
		}
	}
	public static void addBombItems(ConcurrentLinkedQueue<BombItem> allBombitems) {
		if(allBombitems.size() < 1) {
			if(RAND.nextInt(500) < 10) {
				allBombitems.add(newBomb());
		   }
		}
	}
	public static void attackAllEnemys(ConcurrentLinkedQueue<Shot> allShots,ConcurrentLinkedQueue<Enemy> allEnemys) {
		for(Shot shot: allShots) {
			if(shot.getPosY()< 0 || shot.isRemove) {
					allShots.remove(shot);
					continue;
			}
			shot.update();
			shot.draw(gc);
			for(Enemy x: allEnemys) {
				if(shot.colide(x) && !x.isExploding()) {
					shot.setRemove(true);
					x.attack(player);
					if(shot.getName() == "Bomb Shot") {
							sharedObject.RenderableHolder.destroySound.play();
							allParticles.add(new Particle(shot.getPosX(),shot.getPosY()));
							x.explode();
					}
					if(x.getBlood() == 0) {
							x.explode();
							score += x.getOwnscore();
						}
					}
//				if(!(x instanceof BigMeteorite ||x instanceof SmallMeteorite) && x.isExploding()) {
//						isEnemy = true;
//				    }
				}
			}
	}
	public static void checkAllParticles(ConcurrentLinkedQueue<Particle> allParticles) {
		for(Particle x : allParticles) {
			x.countDelay();
			if(x.isBombDone()) {allParticles.remove(x); continue;}
			x.draw(gc);
		}
	}
	
	public static TimeAndScorePane getTimerAndScorePane() {
		return timerAndScorePane;
	}
	public static void setTimerAndScorePane(TimeAndScorePane timerAndScorePane) {
		GameLogic.timerAndScorePane = timerAndScorePane;
	}


}
