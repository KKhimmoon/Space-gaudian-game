package logic;

import entity.base.Collidable;
import entity.base.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot implements Updateable,Collidable {
	public boolean isRemove;
	private String name;
	private int posX;
	private int posY;
	private int speed;
	static final int size = 6;
	public Shot(int posX,int posY,String name) {
		super();
		setPosX(posX);
		setPosY(posY);
		setSpeed(10);
		setName(name);
		// TODO Auto-generated constructor stub
	}
	public Shot(int posX,int posY,int speed) {
		super();
		setPosX(posX);
		setPosY(posY);
		setSpeed(speed);
		setName("Enemy Shot");
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void draw(GraphicsContext gc) {
		if(getName() == "Enemy Shot") {
			gc.setFill(Color.BLUE);
			gc.fillOval(posX, posY, size, size);
		}else if(getName() == "Bomb Shot") {
			gc.setFill(Color.ORANGE);
		    gc.fillOval(Math.min(745,getPosX()), posY, size, size);
		}else {
			if (GameLogic.BulletState == 1) {
				gc.setFill(Color.YELLOWGREEN);
				gc.fillOval(Math.min(745,getPosX()), posY, size, size);
			} else if  (GameLogic.BulletState == 2) {
				gc.setFill(Color.YELLOW);
			    gc.fillOval(Math.min(745,getPosX()), posY, size, size);
			} else if  (GameLogic.BulletState >= 3) {
				gc.setFill(Color.PINK);
			    gc.fillOval(Math.min(745,getPosX()), posY, size, size);
			}else {
				gc.setFill(Color.RED);
			    gc.fillOval(Math.min(745,getPosX()), posY, size, size);
			}
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
		int d = GameLogic.distance(this.getPosX() + size/5,this.posY + size/5, other.getPosX() + other.getSize()/5,other.getPosY() + other.getSize()/5);
		return d < other.getSize()/3 + other.getSize()/3;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		setPosY(getPosY()-getSpeed());
	}


}
