package core;

import interfaces.Direction;

public abstract class Entity implements Runnable{

	protected Direction direction;
	protected int speed;
	protected int hp;
	protected int x; //x centro
	protected int y;
	protected int lato_hitbox;
	protected boolean active;
	
	
	
	
	
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	
	public void setPlayerPosition(int x, int y) {
		
	}

	
	public int getX_player() {
	return 0;
	}



	public int getY_player() {
		return 0;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}

	public Entity (int s, int h, int i, int j, int l)
	{
		this.active=false;
		this.x=i;
		this.y=j;
		this.speed=s;
		this.hp=h;
		this.lato_hitbox=l;
	}
	
}
