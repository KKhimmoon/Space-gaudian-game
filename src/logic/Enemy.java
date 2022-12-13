package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class Enemy extends Rocket {
	    private int  blood;
		private int speed;
		private int ownscore;
	
//		private boolean isBombed;
		public Enemy(int posX, int posY, int size) {
			super(posX,posY,size);
			setSpeed(5);
			setBlood(30);
			setOwnscore(15);
			setExplosionStep(0);
//			setBombed(false);
			// TODO Auto-generated constructor stub
		}
		
//		public boolean isBombed() {
//			return isBombed;
//		}
//
//		public void setBombed(boolean isBombed) {
//			this.isBombed = isBombed;
//		}

		public int getOwnscore() {
			return ownscore;
		}

		public void setOwnscore(int ownscore) {
			this.ownscore = ownscore;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}
		
		public int getBlood() {
			return blood;
		}
		public void setBlood(int blood) {
			if(blood <=  0) {
				this.blood = 0;
			}else {
				this.blood = blood;
			}
		}
		public void attack(Rocket other) {
			if(getBlood() <= other.getPower()) {
				setBlood(0);
			}else {
				setBlood(this.getBlood()- other.getPower());
			}
		}
		public void update() {
			super.update();
			if(!isExploding() && !isDestroyed()) {
				setPosY(getPosY()+ getSpeed());
			}
			if(getPosY() > GameLogic.HEIGHT) setDestroyed(true); 
		}
		public void draw(GraphicsContext gc) {
			if(!isExploding()) {
				gc.drawImage(sharedObject.RenderableHolder.enemy,getPosX(),getPosY(),getSize(),getSize());
				if(getPosY() != 150) {
					setPosY(getPosY()+ getSpeed());
				}
		   }
		}
//		public void draw(GraphicsContext gc,boolean x) {
//			if(x) {
//				super.update();
//				gc.drawImage(sharedObject.RenderableHolder.destroy,getPosX(),getPosY(),100,100);
//			}
//		}
		public Shot shoot() {
			return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() +getSize()/2 ,-10);
		}
		
}
