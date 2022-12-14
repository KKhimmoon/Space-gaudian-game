package logic;

public class Alien extends Enemy {
	private boolean moveleft;

	public Alien(int posX, int posY, int size) {
		super(posX, posY, size);
		setSpeed(3);
		setBlood(30);
		setOwnscore(15);
		setMoveleft(false);
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveleft() {
		return moveleft;
	}
	public void setMoveleft(boolean moveleft) {
		this.moveleft = moveleft;
	}
	public void update() {
		if(isExploding()){
			setDestroyed(true);
		}if(getPosY() != 150) {
		  setPosY(getPosY()+ getSpeed());
	    }else {
		  if(isMoveleft()) {
			 setPosX(getPosX()-getSpeed());
			 if(getPosX() <= 0) {
				setPosX(0);
				setMoveleft(false);
		   }
		  }else {
			setPosX(getPosX() + getSpeed());
			if(getPosX() >= GameLogic.WIDTH-this.getSize()) {
				setPosX(GameLogic.WIDTH-this.getSize());
				setMoveleft(true);
			}
		}
	  } 
   }
	public Shot shoot() {
		return new Shot(getPosX()+getSize()/2 - Shot.size/2,getPosY() +getSize()/2 ,-10);
	}	
}
