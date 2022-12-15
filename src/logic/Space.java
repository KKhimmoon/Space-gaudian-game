package logic;

import entity.base.Collidable;
import entity.base.Entity;
import entity.base.Updateable;
import gui.SelectedController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Space extends Entity implements Updateable,Collidable{
	private int power;
	private int size;
	private boolean isExploding;
	public Space(int posX,int posY,int size) {
		setPosX(posX);
		setPosY(posY);
		setSize(size);
		setDestroyed(false);
		setExploding(false);
		setPower(5);
		// TODO Auto-generated constructor stub
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		if(power > 14) {
			this.power = 14;
		}
		else{
			this.power = power;
	    }
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public boolean isExploding() {
		return isExploding;
	}
	public void setExploding(boolean isExploding) {
		this.isExploding = isExploding;
	}
	public void explode() {
		setExploding(true);
	}
	public boolean collide(Space other) {
		int d = GameLogic.distance(this.getPosX() + size/2,this.posY + size/2, other.getPosX() + other.getSize()/2,other.getPosY() + other.getSize()/2);
		return d < other.getSize()/2 + this.getSize()/2;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(isExploding() || getPosY() > GameLogic.HEIGHT) {
			setDestroyed(true);
		}
	}	
	public Shot shoot(String name) {
		return new Shot(getPosX()+getSize()/2 - Shot.SIZE/2,getPosY() - Shot.SIZE,name);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(!isExploding()) {
			gc.drawImage(SelectedController.getSelectedSpaceShip(),Math.min(800-this.size,getPosX()),getPosY(),getSize(),getSize());
		}
	}
}
