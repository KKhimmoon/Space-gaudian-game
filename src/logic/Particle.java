package logic;

import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;

public class Particle extends Entity {
	private int countTime;
	private int counterTime;

	public Particle(int posX,int posY) {
		super();
		setPosX(posX);
		setPosY(posY);
		setCounterTime(0);
		setCountTime(20);
		// TODO Auto-generated constructor stub
	}
	public int getCountTime() {
		return countTime;
	}
	public void setCountTime(int countTime) {
		this.countTime = countTime;
	}
	public int getCounterTime() {
		return counterTime;
	}
	public void setCounterTime(int countterTime) {
		this.counterTime = countterTime;
	}
	public void countDelay() {
		if(!(getCounterTime() >= getCountTime())) {
			setCounterTime(getCounterTime()+1);
		}
	}
	public boolean isBombDone() {
		return getCounterTime() >= getCountTime();
	}
//	@Override
//	public int getZ() {
//		// TODO Auto-generated method stub
//		return 99;
//	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(sharedObject.RenderableHolder.destroyGif,getPosX()-50,getPosY()-20,100,100);
	}
}
