package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.Shootable;

public class EnemyShooter extends Entity implements Shootable, Runnable {

	private float rateOfFire;
	private List<Bullet> bullets;
	double x_player, y_player;
	
	public void shoot() {
		double new_x=Math.abs(x_player-this.x);
		double new_y=Math.abs(y_player- this.y);
		double cosx=(this.x>x_player) ? Math.cos(Math.atan2(new_y, new_x)) *-1.0 : Math.cos(Math.atan2(new_y, new_x));
		double sinx=(this.y>y_player) ? Math.sin(Math.atan2(new_y, new_x)) *-1.0 : Math.sin(Math.atan2(new_y, new_x));
		//xc, yc, rad, speed, dx,dy
		Bullet b=new Bullet(this.x, this.y, 7, 9, cosx, sinx );
		bullets.add(b);
		
	}
	

	
	
	public List<Bullet> getBullets() {
		return bullets;
	}

/*
	public double getX_player() {
		return x_player;
	}


	public double getY_player() {
		return y_player;
	}
*/
	public void setPlayerPosition(int x, int y)
	{
		x_player=(double)x; y_player=(double)y;
	}

	public float getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(float rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	public EnemyShooter(int s, int h, float r, int i, int j, int l)
	{
		super(s, h, i ,j, l);
		this.rateOfFire=r;
		bullets= Collections.synchronizedList(new ArrayList<Bullet>());
		
	}

	@Override
	public String toString() {
		return "s";
	}
	
	public synchronized void run()
	{
		while(this.hp>0)
		{
			
			try        
			{
				this.shoot();
			    Thread.sleep((long) (1500/rateOfFire));
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		}
		this.active=false;
		
	}
	
}
