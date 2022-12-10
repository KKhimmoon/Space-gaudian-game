package mySpaceship;

import javafx.scene.canvas.GraphicsContext;

public class Rocket {
	private int posX;
	private int posY;
	private int size;
	private boolean exploding;
	private boolean destroyed;
	private int explodingStep;
	
	public Rocket(int posX,int posY,int size) {
		setPosX(posX);
		setPosY(posY);
		setSize(size);
		setExplodingStep(0);
		setDestroyed(false);
		setExploding(false);
		// TODO Auto-generated constructor stub
	}
	public Shot shoot() {
		return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() - Shot.size);
	}
	
	public void update() {
		if(isExploding()) setExplodingStep(getExplodingStep()+1);
		setDestroyed(getExplodingStep() > 15);//not finish
	}
	
	public void draw() {
		if(isExploding()) {
			
			
		}
	}
	
	public boolean colide(Rocket other) {
		int d = distance(this.getPosX() + getSize()/2,this.posY + getSize()/2, other.getPosX() + other.size/2,other.getPosY() + other.getSize()/2);
		return d < other.getSize()/2 + other.getSize()/2;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public int getExplodingStep() {
		return explodingStep;
	}

	public void setExplodingStep(int explodingStep) {
		this.explodingStep = explodingStep;
	}

}
