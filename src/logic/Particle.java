package logic;

import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;

public class Particle extends Entity {
	private int countTime;
	private int countTerTime;

	public Particle(int posX,int posY) {
		super();
		setPosX(posX);
		setPosY(posY);
		setCountTerTime(0);
		setCountTime(20);
		// TODO Auto-generated constructor stub
	}
	public int getCountTime() {
		return countTime;
	}
	public void setCountTime(int countTime) {
		this.countTime = countTime;
	}
	public int getCountTerTime() {
		return countTerTime;
	}
	public void setCountTerTime(int countTerTime) {
		this.countTerTime = countTerTime;
	}
	public void countDelay() {
		if(!(getCountTerTime() >= getCountTime())) {
			setCountTerTime(getCountTerTime()+1);
		}
	}
	public boolean isBombDone() {
		return getCountTerTime() >= getCountTime();
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
