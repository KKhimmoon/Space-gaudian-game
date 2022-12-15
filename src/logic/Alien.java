package logic;

import javafx.scene.canvas.GraphicsContext;

public class Alien extends Enemy {
	private boolean isMoveLeft;

	public Alien(int posX, int posY, int size) {
		super(posX, posY, size);
		setSpeed(3);
		setBlood(30);
		setOwnScore(15);
		setMoveLeft(false);
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveLeft() {
		return isMoveLeft;
	}
	public void setMoveLeft(boolean isMoveLeft) {
		this.isMoveLeft = isMoveLeft;
	}
	public void update() {
		if(isExploding()){
			setDestroyed(true);
		}if(getPosY() != 150) {
		  setPosY(getPosY()+ getSpeed());
	    }else {
		  if(isMoveLeft()) {
			 setPosX(getPosX()-getSpeed());
			 if(getPosX() <= 0) {
				setPosX(0);
				setMoveLeft(false);
		   }
		  }else {
			setPosX(getPosX() + getSpeed());
			if(getPosX() >= GameLogic.WIDTH-this.getSize()) {
				setPosX(GameLogic.WIDTH-this.getSize());
				setMoveLeft(true);
			}
		}
	  } 
   }
	public Shot shoot() {
		return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() +getSize()/2 ,-10);
	}
	public void draw(GraphicsContext gc) {
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.enemy,getPosX(),getPosY(),getSize(),getSize());
	   }
	}
}
