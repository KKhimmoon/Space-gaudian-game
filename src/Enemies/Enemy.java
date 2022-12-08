package Enemies;

import javafx.scene.image.Image;

public abstract class Enemy {
	private int posX;
	private int posY;
	private int blood;
	private int plusScore;
	private String name;







	public Enemy( String name, int blood,int plusScore,int posX,int posY) {
		super();
		setBlood(blood);
		setName(name);
		setPlusScore(plusScore);
	}
	
	public int getPlusScore() {
		return plusScore;
	}

	public void setPlusScore(int plusScore) {
		this.plusScore = plusScore;
	}

	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void se
	
	
	
	
	
	
	
}
