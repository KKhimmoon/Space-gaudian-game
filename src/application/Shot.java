package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Distant;
import javafx.scene.paint.Color;

public class Shot {
	
	public boolean isRemove;
	int posX;
	int posY;
	int speed;
	static final int size = 6;
	public Shot(int posX,int posY) {
		super();
		setPosX(posX);
		setPosY(posY);
		setSpeed(10);
		// TODO Auto-generated constructor stub
	}
	public void update(){
		setPosY(getPosY()-getSpeed());
	}
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(getPosX(), getPosY(), size, size);
		
	}
	
	public boolean colide(Rocket Rocket) {
			int d = SpaceInvaders.distance(this.getPosX() + size/2,this.posY + size/2, Rocket.getPosX() + Rocket.getSize()/2,Rocket.getPosY() + Rocket.getSize()/2);
			return d < Rocket.getSize()/2 + Rocket.getSize()/2;
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
	
	

}
