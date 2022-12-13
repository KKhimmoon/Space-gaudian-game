package logic;

import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;

public class Particle extends Entity {
	private int counttime;
	private int countertime;
	
	public Particle(int posX,int posY) {
		super();
		setPosX(posX);
		setPosY(posY);
		setCountertime(0);
		setCounttime(20);
		// TODO Auto-generated constructor stub
	}
	
	
	public int getCounttime() {
		return counttime;
	}


	public void setCounttime(int counttime) {
		this.counttime = counttime;
	}


	public int getCountertime() {
		return countertime;
	}


	public void setCountertime(int countertime) {
		this.countertime = countertime;
	}


	public void countDelay() {
		if(getCountertime() >= getCounttime()) {
		}else {
			setCountertime(getCountertime()+1);
		}
	}
	public boolean isDone() {
		return getCountertime() >= getCounttime();
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(sharedObject.RenderableHolder.destroyGif,getPosX(),getPosY(),100,100);
	}
	
	

}
