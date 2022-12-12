package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Rocket {
	    private int  blood;
		private int speed;
		private int ownscore;
		private final String img;
		public Enemy(int posX, int posY, int size) {
			super(posX,posY,size);
			this.img = "enemy.png";
			setSpeed(5);
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
			if(getPosY() > 600) setDestroyed(true); 
		}
		public void draw(GraphicsContext gc) {
			if(!isExploding()) {
				gc.drawImage(sharedObject.RenderableHolder.enemy,getPosX(),getPosY(),getSize(),getSize());
				if(getPosY() != 150) {
					setPosY(getPosY()+ getSpeed());
				}
		   }
		}
		public Shot shoot() {
			return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() +getSize()/2 ,-10);
		}
		
}
