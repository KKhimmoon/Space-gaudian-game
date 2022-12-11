package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Rocket {
	    private ArrayList<Shot> allShots;
		private int speed;
		private final String img;
		public Enemy(int posX, int posY, int size) {
			super(posX,posY,size);
			this.img = "4ced2fc5b95a6026.png";
			allShots = new ArrayList<Shot>();
			setSpeed(2);
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
				if(getPosY() != 150) {
					setPosY(getPosY()+ getSpeed());
				}
		   }
		}
		public Shot shoot() {
			return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY()+getSize() ,-10);
		}

		public ArrayList<Shot> getAllShots() {
			return allShots;
		}
		
}
