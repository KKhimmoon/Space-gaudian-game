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
import screenDrawing.MainGameScreen;
import sharedObject.RenderableHolder;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;

public class GameLogic {

	private static Space player;
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
	public static int counterBomb;
	public static int countBullet;
	public static int counterBullet;
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
		player = new Space(WIDTH/2,HEIGHT-PLAYER_SIZE-30,PLAYER_SIZE);
		setAmountBomb(0);
		setCounterBomb(0);
		setCountBullet(1200);
		setCounterBullet(0);
		setCountBomb(1000);
		setBulletState(0);
		setScore(0);
		setHasAlien(false);
	}
	
	public static ConcurrentLinkedQueue<Shot> getAllEnemysShots() {
		return allEnemysShots;
	}
	public static void setAllEnemysShots(ConcurrentLinkedQueue<Shot> allEnemysShots) {
		GameLogic.allEnemysShots = allEnemysShots;
	}
	public static ConcurrentLinkedQueue<BombItem> getAllBombitems() {
		return allBombitems;
	}
	public static void setAllBombitems(ConcurrentLinkedQueue<BombItem> allBombitems) {
		GameLogic.allBombitems = allBombitems;
	}

	public static ConcurrentLinkedQueue<BulletItem> getAllBulletitems() {
		return allBulletitems;
	}

	public static void setAllBulletitems(ConcurrentLinkedQueue<BulletItem> allBulletitems) {
		GameLogic.allBulletitems = allBulletitems;
	}

	public static ConcurrentLinkedQueue<Enemy> getAllEnemys() {
		return allEnemys;
	}
	public static void setAllEnemys(ConcurrentLinkedQueue<Enemy> allEnemys) {
		GameLogic.allEnemys = allEnemys;
	}

	public static ConcurrentLinkedQueue<Particle> getAllParticles() {
		return allParticles;
	}

	public static void setAllParticles(ConcurrentLinkedQueue<Particle> allParticles) {
		GameLogic.allParticles = allParticles;
	}

	public static int getAmountBomb() {
		return amountBomb;
	}

	public static void setAmountBomb(int amountBomb) {
		GameLogic.amountBomb = amountBomb;
	}

	public static int getCounterBomb() {
		return counterBomb;
	}

	public static void setCounterBomb(int counterBomb) {
		GameLogic.counterBomb = counterBomb;
	}

	public static int getCountBullet() {
		return countBullet;
	}

	public static void setCountBullet(int countBullet) {
		GameLogic.countBullet = countBullet;
	}

	public static int getCounterBullet() {
		return counterBullet;
	}

	public static void setCounterBullet(int countterBullet) {
		GameLogic.counterBullet = countterBullet;
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
		GameLogic.bulletState = bulletState;
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
	public static Space getPlayer() {
		return player;
	}
	public static void setPlayer(Space player) {
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
		collideBombItem(allBombitems,player);
		collideBulletItem(allBulletitems,player);
		removeDeadEnemy(allEnemys);
		releaseBulletItem(allBulletitems,getCounterBullet(),getCountBullet());
		releaseBombItem(allBombitems,getCounterBomb(),getCountBomb());
		attackAllEnemys(allShots,allEnemys);
		checkAllParticles(allParticles);
		player.update();
		player.draw(gc);
		player.setPosX((int) screenDrawing.MainGameScreen.getInstance().getMouseX());
	}

	public static void collideEnemyShot(ConcurrentLinkedQueue<Shot> allEnemysShots,Space player) {
		for(Shot shot: allEnemysShots) {
			if(shot.getPosY()< 0 || shot.isRemove) {
				allEnemysShots.remove(shot);
				continue;
			}
			shot.update();
			shot.draw(gc);
			if(shot.collide(player)) {
					setScore(getScore()-1);
					shot.setRemove(true);
			}
		}
	}
	public static void releaseBulletItem(ConcurrentLinkedQueue<BulletItem> allBulletitems,int countterBullet,int countBullet){
		for(BulletItem x: allBulletitems) {
			setCounterBullet(getCounterBullet()+1);
			if(getCounterBullet() >=  getCountBullet()) {
				x.draw(gc);
				x.update();
				if(x.isDestroyed() || x.isExploding()) {
					setCounterBullet(0);
					allBulletitems.remove(x);
				}
			}
		}	
	}
	public static void releaseBombItem(ConcurrentLinkedQueue<BombItem> allBombitems,int countterBomb,int countBomb) {
		for(BombItem x: allBombitems) {
			setCounterBomb(getCounterBomb()+1);
			if(getCounterBomb() >= getCountBomb()) {
				x.draw(gc);
				x.update();
				if(x.isDestroyed() || x.isExploding()) {
					setCounterBomb(0);
					allBombitems.remove(x);
			}
		 }
	  }
	}
	public static void collideBombItem(ConcurrentLinkedQueue<BombItem> allBombitems,Space player) {
		 for(BombItem x: allBombitems) {
			if(player.collide(x) && !player.isExploding()) {
				sharedObject.RenderableHolder.collectedSound.play();
				x.explode();
				setAmountBomb(getAmountBomb()+1);
			}
		  }
	}	
	public static void collideBulletItem(ConcurrentLinkedQueue<BulletItem> allBulletitems,Space player) {
		for(BulletItem x: allBulletitems) {
			if(player.collide(x) && !player.isExploding()) {
				sharedObject.RenderableHolder.collectedSound.play();
				x.explode();
				setBulletState(getBulletState()+1);
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
				if(shot.collide(x) && !x.isExploding()) {
					shot.setRemove(true);
					x.attack(player);
					if(shot.getName().equals("Bomb Shot")) {
							sharedObject.RenderableHolder.destroySound.play();
							allParticles.add(new Particle(shot.getPosX(),shot.getPosY()));
							x.explode();
					}
					if(x.getBlood() == 0) {
							x.explode();
							setScore(getScore()+ x.getOwnScore());
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
