package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class Enemy extends Rocket{
	    private int  blood;
		private int speed;
		private int ownscore;
		public Enemy(int posX, int posY, int size) {
			super(posX,posY,size);
			setSpeed(3);
			setBlood(30);
			setOwnscore(15);
			// TODO Auto-generated constructor stub
		}
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
		   }
		}
}
