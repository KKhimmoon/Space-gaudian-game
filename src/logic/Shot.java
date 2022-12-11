package logic;

import entity.base.Collidable;
import entity.base.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot implements Updateable,Collidable {
	public boolean isRemove;
	private int posX;
	private int posY;
	private int speed;
	static final int size = 6;
	private int radius;
	public Shot(int posX,int posY) {
		super();
		setPosX(posX);
		setPosY(posY);
		setSpeed(10);
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		if (GameLogic.BulletState >= 20) {
			gc.setFill(Color.YELLOWGREEN);
			speed = 50;
			gc.fillOval(posX, posY, size, size);
		} else {
		gc.fillOval(posX, posY, size, size);
		}
	}
	
	public boolean isRemove() {
		return isRemove;
	}
	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

	@Override
	public boolean colide(Rocket other) {
		// TODO Auto-generated method stub
		return Math.hypot(this.getPosX()- other.getPosX(), this.getPosY()- other.getPosY()) <= this.radius+ other.getRadius();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		setPosY(getPosY()-getSpeed());
		
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

}
