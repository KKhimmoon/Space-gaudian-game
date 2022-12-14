package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket {
		private int posX;
		private int posY;
		private int size;
		private boolean exploding;
		private boolean destroyed;
		private int explosionStep;
		private final String img;

		
		public Rocket(int posX,int posY,int size) {
			this.img = "295ef468535024b2.png";
			setPosX(posX);
			setPosY(posY);
			setSize(size);
			setExplosionStep(0);
			setDestroyed(false);
			setExploding(false);
			// TODO Auto-generated constructor stub
		}
		public Shot shoot() {
			return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() - Shot.size);
		}
		
		public void update() {
			if(isExploding()) setExplosionStep(getExplosionStep()+1);
			setDestroyed(getExplosionStep() > SpaceInvaders.EXPLOSION_STEPS);
		}	
		public void draw(GraphicsContext gc) {
			if(isExploding()) {
				gc.drawImage(SpaceInvaders.EXPLOSION_IMG, getExplosionStep() % SpaceInvaders.EXPLOSION_COL * SpaceInvaders.EXPLOSION_W,
						(getExplosionStep()/SpaceInvaders.EXPLOSION_ROWS)*SpaceInvaders.EXPLOSION_H +1,SpaceInvaders.EXPLOSION_W,
						SpaceInvaders.EXPLOSION_H,getPosX(),getPosY(),getSize(),getSize());
				
			}else {
				String image_path = ClassLoader.getSystemResource(this.img).toString();
				Image image = new Image(image_path);
				gc.drawImage(image,getPosX(),getPosY(),getSize(),getSize());
			}
		}
		
		public boolean colide(Rocket other) {
			int d = SpaceInvaders.distance(this.getPosX() + getSize()/2,this.posY + getSize()/2, other.getPosX() + other.size/2,other.getPosY() + other.getSize()/2);
			return d < other.getSize()/2 + other.getSize()/2;
		}
		
		public void explode() {
			setExploding(true);
			setExplosionStep(-1);
		}

		public int getPosX() {
			return posX;
		}

		public void setPosX(int posX) {
			this.posX = posX;
		}

		public int getPosY() {
			return posY;
		}

		public void setPosY(int posY) {
			this.posY = posY;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public boolean isExploding() {
			return exploding;
		}

		public void setExploding(boolean exploding) {
			this.exploding = exploding;
		}

		public boolean isDestroyed() {
			return destroyed;
		}

		public void setDestroyed(boolean destroyed) {
			this.destroyed = destroyed;
		}

		public int getExplosionStep() {
			return explosionStep;
		}

		public void setExplosionStep(int explosionStep) {
			this.explosionStep = explosionStep;
		}

	}

