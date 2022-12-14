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
import screendrawing.MainGameScreen;
import sharedObject.RenderableHolder;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;

public class GameLogic {

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
	private static final int MAXSHOT = 20;
	private static int score;
	private static boolean hasAlien;
	public static int amountBomb;
	public static int countBomb;
	public static int countterBomb;
	public static int countBullet;
	public static int countterBullet;
	public static int bulletState;

	
	public static GraphicsContext getGc() {
		return gc;
	}
	private static GraphicsContext gc ;

	
	public static void InitializeGame() {
		allShots = new ConcurrentLinkedQueue<>();
		allEnemysShots = new ConcurrentLinkedQueue<>();
		allBulletitems = new ConcurrentLinkedQueue<>();
		allBombitems = new ConcurrentLinkedQueue<>();
		allEnemys = new ConcurrentLinkedQueue<>();
		allParticles = new ConcurrentLinkedQueue<>();
		player = new Rocket(WIDTH/2,HEIGHT-PLAYER_SIZE-30,PLAYER_SIZE);
		setAmountBomb(0);
		setCountterBomb(0);
		setCountBullet(1200);
		setCountterBullet(0);
		setCountBomb(1000);
		setBulletState(0);
		setScore(0);
		setHasAlien(false);
	}
	
	public static int getAmountBomb() {
		return amountBomb;
	}

	public static void setAmountBomb(int amountBomb) {
		GameLogic.amountBomb = amountBomb;
	}

	public static int getCountterBomb() {
		return countterBomb;
	}

	public static void setCountterBomb(int countterBomb) {
		GameLogic.countterBomb = countterBomb;
	}

	public static int getCountBullet() {
		return countBullet;
	}

	public static void setCountBullet(int countBullet) {
		GameLogic.countBullet = countBullet;
	}

	public static int getCountterBullet() {
		return countterBullet;
	}

	public static void setCountterBullet(int countterBullet) {
		GameLogic.countterBullet = countterBullet;
	}

	public static int distance(int x1,int y1,int x2,int y2) {
		return (int)Math.sqrt(Math.pow(x1-x2,2) + Math.pow((y1-y2), 2));
	}
	public static boolean isHasAlien() {
		return hasAlien;
	}

	public static void setHasAlien(boolean hasAlien) {
		GameLogic.hasAlien = hasAlien;
	}


	public static int getCountBomb() {
		return countBomb;
	}

	public static void setCountBomb(int countBomb) {
		GameLogic.countBomb = countBomb;
	}

	public static int getBulletState() {
		return bulletState;
	}
	public static void setBulletState(int bulletState) {
		bulletState = bulletState;
	}
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
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
	public static Rocket getPlayer() {
		return player;
	}
	public static void setPlayer(Rocket player) {
		GameLogic.player = player;
	}
	public static ConcurrentLinkedQueue<Shot> getAllShots() {
		return allShots;
	}
	public static void setAllShots(ConcurrentLinkedQueue<Shot> allShots) {
		GameLogic.allShots = allShots;
	}
	public static int getMaxshot() {
		return MAXSHOT;
	}


	public static void runGameScence(GraphicsContext gc) {
		GameLogic.gc =gc;
		addEnemy(allEnemys,hasAlien);
		addShotsEnemy(allEnemys,allEnemysShots);
		collideEnemyShot(allEnemysShots,player);
		addBulletItems(allBulletitems);
		addBombItems(allBombitems);
		releaseBulletItem(allBulletitems,getCountterBullet(),getCountBullet());
		releaseBombItem(allBombitems,getCountterBomb(),getCountBomb());
		collideBombItem(allBombitems,player);
		collideBulletItem(allBulletitems,player);
		removeDeadEnemy(allEnemys);
		attackAllEnemys(allShots,allEnemys);
		checkAllParticles(allParticles);
		player.update();
		player.draw(gc);
		player.setPosX((int) screendrawing.MainGameScreen.getInstance().getMouseX());
	}

	public static void collideEnemyShot(ConcurrentLinkedQueue<Shot> allEnemysShots,Rocket player) {
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
			setCountterBullet(getCountterBullet()+1);
			if(getCountterBullet() >=  getCountBullet()) {
				x.draw(gc);
				x.update();
				if(x.isDestroyed() || x.isExploding()) {
					setCountterBullet(0);
					allBulletitems.remove(x);
				}
			}
		}	
	}
	public static void releaseBombItem(ConcurrentLinkedQueue<BombItem> allBombitems,int countterBomb,int countBomb) {
		for(BombItem x: allBombitems) {
			setCountterBomb(getCountterBomb()+1);
			if(getCountterBomb() >= getCountBomb()) {
				x.draw(gc);
				x.update();
				if(x.isDestroyed() || x.isExploding()) {
					setCountterBomb(0);
					allBombitems.remove(x);
			}
		 }
	  }
	}
	public static void collideBombItem(ConcurrentLinkedQueue<BombItem> allBombitems,Rocket player) {
		 for(BombItem x: allBombitems) {
			if(player.colide(x) && !player.isExploding()) {
				sharedObject.RenderableHolder.collectedSound.play();
				x.explode();
				setAmountBomb(getAmountBomb()+1);
			}
		  }
	}	
	public static void collideBulletItem(ConcurrentLinkedQueue<BulletItem> allBulletitems,Rocket player) {
		for(BulletItem x: allBulletitems) {
			if(player.colide(x) && !player.isExploding()) {
				sharedObject.RenderableHolder.collectedSound.play();
				x.explode();
				bulletState += 1;
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
				if(!(x instanceof BigMeteorite ||x instanceof SmallMeteorite) && x.isExploding()) {
					setHasAlien(false);
				    }
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
	


}
