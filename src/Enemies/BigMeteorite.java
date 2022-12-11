package Enemies;

import Abilities.Destroyable;
import Abilities.Moveable;

public class BigMeteorite extends Enemy implements Moveable,Destroyable{

	public BigMeteorite() {
		super("BigMeteorite", 30, 50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		if(this.getBlood()>0) return true;
		return false;
	}

	@Override
	public void updateBlood() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
