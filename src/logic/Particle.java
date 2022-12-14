package logic;

import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;

public class Particle extends Entity {
	private int countTime;
	private int countterTime;

	public Particle(int posX,int posY) {
		super();
		setPosX(posX);
		setPosY(posY);
		setCountterTime(0);
		setCountTime(20);
		// TODO Auto-generated constructor stub
	}
	public int getCountTime() {
		return countTime;
	}
	public void setCountTime(int countTime) {
		this.countTime = countTime;
	}
	public int getCountterTime() {
		return countterTime;
	}
	public void setCountterTime(int countterTime) {
		this.countterTime = countterTime;
	}
	public void countDelay() {
		if(!(getCountterTime() >= getCountTime())) {
			setCountterTime(getCountterTime()+1);
		}
	}
	public boolean isBombDone() {
		return getCountterTime() >= getCountTime();
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 99;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(sharedObject.RenderableHolder.destroyGif,getPosX()-50,getPosY()-20,100,100);
	}
}
