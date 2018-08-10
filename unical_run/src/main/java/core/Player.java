package core;


import interfaces.Direction;
import interfaces.Movable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import interfaces.Shootable;

public class Player extends Entity implements Shootable, Movable {

	private float rateOfFire;
	private List<Bullet> bullets;
	double where_x, where_y;
	
	

	public List<Bullet> getBullets() {
		return bullets;
	}


	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}


	public void shoot() {
		
		double new_x=Math.abs(where_x-this.x);
		double new_y=Math.abs(where_y- this.y);
		double cosx=(this.x>where_x) ? Math.cos(Math.atan2(new_y, new_x)) *-1.0 : Math.cos(Math.atan2(new_y, new_x));
		double sinx=(this.y>where_y) ? Math.sin(Math.atan2(new_y, new_x)) *-1.0 : Math.sin(Math.atan2(new_y, new_x));
		//xc, yc, rad, speed, dx,dy
		Bullet b=new Bullet(this.x+(this.lato_hitbox)/2, this.y+(this.lato_hitbox)/2, 7, 9, cosx, sinx );
		bullets.add(b);
		

	}
	
	
	
	public void setWhere_x(double where_x) {
		this.where_x = where_x;
	}


	public void setWhere_y(double where_y) {
		this.where_y = where_y;
	}


	public void move()
	{
		switch (getDirection())
        {
            case UP:
                setY(getY() - getSpeed());
                break;
            case DOWN:
                setY(getY() + getSpeed());
                break;
            case LEFT:
                setX(getX() - getSpeed());
                break;
            case RIGHT:
                setX(getX() + getSpeed());
                break;

            default:
                break;
        }
		
	}

	public float getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(float rateOfFire) {
		this.rateOfFire = rateOfFire;
	}
	
	Player(int s, int h, float r, int i, int j, int l)
	{
		super(s, h, i, j, l);
		this.rateOfFire=r;
		this.direction=Direction.STOP;
		bullets= Collections.synchronizedList(new ArrayList<Bullet>());
	}

	@Override
	public String toString() {
		return "p";
	}



	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
