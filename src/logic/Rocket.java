package logic;

import entity.base.Collidable;
import entity.base.Entity;
import entity.base.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket extends Entity implements Updateable,Collidable{
	private int size;
	private boolean exploding;
	private int explosionStep;
	private final String img;
	private int radius;
	public Rocket(int posX,int posY,int size) {
		this.img = "295ef468535024b2.png";
		setPosX(posX);
		setPosY(posY);
		setSize(size);
		setExplosionStep(0);
		setDestroyed(false);
		setExploding(false);
		setRadius(20);
		
		// TODO Auto-generated constructor stub
	}

	public boolean colide(Rocket other) {
		return Math.hypot(this.getPosX()- other.getPosX(), this.getPosY()- other.getPosY()) <= this.radius+ other.getRadius();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(isExploding()) setExplosionStep(getExplosionStep()+1);
		setDestroyed(getExplosionStep() > 600); // fix it
	}	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public void explode() {
		setExploding(true);
		setExplosionStep(-1);
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public int getExplosionStep() {
		return explosionStep;
	}

	public void setExplosionStep(int explosionStep) {
		this.explosionStep = explosionStep;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Shot shoot() {
		return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() - Shot.size);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(isExploding()) {
//			gc.drawImage(SpaceInvaders.EXPLOSION_IMG, getExplosionStep() % SpaceInvaders.EXPLOSION_COL * SpaceInvaders.EXPLOSION_W,
//					(getExplosionStep()/SpaceInvaders.EXPLOSION_ROWS)*SpaceInvaders.EXPLOSION_H +1,SpaceInvaders.EXPLOSION_W,
//					SpaceInvaders.EXPLOSION_H,getPosX(),getPosY(),getSize(),getSize());
//			
		}else {
			String image_path = ClassLoader.getSystemResource(this.img).toString();
			Image image = new Image(image_path);
			gc.drawImage(image,getPosX(),getPosY(),getSize(),getSize());
		}
		
	}


	
	

}
