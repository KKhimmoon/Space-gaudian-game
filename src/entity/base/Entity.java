package entity.base;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable,Cloneable {
	protected int posX;
	protected int posY;
	protected boolean isDestroyed;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError("can not clone");
		}
	}
	public String getCoordinate() {
		return "("+this.getPosX()+","+this.getPosY()+")";
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

	public boolean isDestroyed() {
		return isDestroyed;
	}
	
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
}
