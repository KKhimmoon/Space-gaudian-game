package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Enemy extends Rocket{
	private int speed;
	private final String img;
	private int h,w,r,g,b;
	private double opacity;

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
		if(getPosY() > SpaceInvaders.HEIGHT) setDestroyed(true); 
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
			setPosY(getPosY()+5);
		}
	
		
	}
}
