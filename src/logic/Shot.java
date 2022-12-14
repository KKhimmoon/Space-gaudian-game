package logic;
import entity.base.Collidable;
import entity.base.Entity;
import entity.base.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class Shot extends Entity implements Updateable,Collidable {
	public boolean isRemove;
	private String name;
	private int speed;
	public static final int SIZE = 6;
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
		if(getName().equals("Enemy Shot")) {
			gc.setFill(Color.BLUE);
			gc.fillOval(posX, posY, SIZE, SIZE+15);
		}else if(getName().equals("Bomb Shot")) {
			gc.drawImage(sharedObject.RenderableHolder.bombshot,Math.min(745,getPosX()), posY, SIZE+50, SIZE+50);
		}else {
			if (GameLogic.getBulletState() == 1) {
				gc.drawImage(sharedObject.RenderableHolder.shot2,Math.min(742,getPosX()-5), posY, SIZE+10, SIZE+25);
			} else if  (GameLogic.getBulletState() == 2) {
				gc.drawImage(sharedObject.RenderableHolder.shot3,Math.min(745,getPosX()), posY, SIZE, SIZE+25);
			} else if  (GameLogic.getBulletState() >= 3) {
				gc.drawImage(sharedObject.RenderableHolder.shot3,Math.min(745,getPosX()+ SIZE/2 + 1), posY, SIZE, SIZE+25);
				gc.drawImage(sharedObject.RenderableHolder.shot3,Math.min(745,getPosX()- SIZE/2 - 1), posY, SIZE, SIZE+25);
			}else {
				gc.drawImage(sharedObject.RenderableHolder.shot1,Math.min(748,getPosX()+1), posY, SIZE-2, SIZE+25);
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
	public boolean collide(Space other) {
		// TODO Auto-generated method stub
		int d = GameLogic.distance(this.getPosX() + SIZE/5,this.posY + SIZE/5, other.getPosX() + other.getSize()/5,other.getPosY() + other.getSize()/5);
		return d < other.getSize()/3 + other.getSize()/3;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(isRemove) {setDestroyed(isRemove);}
		setPosY(getPosY()-getSpeed());
	}

}
