package logic;

import application.SelectedController;
import entity.base.Collidable;
import entity.base.Entity;
import entity.base.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket extends Entity implements Updateable,Collidable{
	private int power;
	private int size;
	private boolean exploding;
	private int explosionStep;
	private final Image img;
	public Rocket(int posX,int posY,int size) {
		this.img = SelectedController.getSelectedSpaceShip();
		setPosX(posX);
		setPosY(posY);
		setSize(size);
		setExplosionStep(0);
		setDestroyed(false);
		setExploding(false);
		setPower(5);
		// TODO Auto-generated constructor stub
	}

	public boolean colide(Rocket other) {
		int d = GameLogic.distance(this.getPosX() + size/2,this.posY + size/2, other.getPosX() + other.getSize()/2,other.getPosY() + other.getSize()/2);
		return d < other.getSize()/2 + other.getSize()/2;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(isExploding()) setExplosionStep(getExplosionStep()+1);
		setDestroyed(getExplosionStep() > GameLogic.HEIGHT); // fix it
	}	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
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
	public Shot shoot() {
		return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() - Shot.size);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(!isExploding()) {
			gc.drawImage(this.img,Math.min(800-this.size,getPosX()),getPosY(),getSize(),getSize());
		}
		
	}


	
	

}
