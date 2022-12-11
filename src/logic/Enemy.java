package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Rocket {
		private int speed;
		private final String img;
		public Enemy(int posX, int posY, int size) {
			super(posX,posY,size);
			this.img = "35cc4b0f4226a0f1.png";
			setSpeed(5);
		
			// TODO Auto-generated constructor stub
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
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
				String image_path = ClassLoader.getSystemResource(this.img).toString();
				Image image = new Image(image_path);
				gc.drawImage(image,getPosX(),getPosY(),getSize(),getSize());
				setPosY(getPosY()+5);
		}
	}
}
