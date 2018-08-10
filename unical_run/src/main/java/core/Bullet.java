package core;

public class Bullet{

	private double x_centro;
	private double y_centro;
	private final int raggio;
	private final int speed;
	private final double dx, dy;
	
	Bullet(int x, int y, int r, int s, double d, double e)
	{
		this.x_centro=x;
		this.y_centro=y;
		this.raggio=r;
		this.speed=s;
		this.dx=d;
		this.dy=e;
		//xc, yc, rad, speed, dx,dy
	}
	
	
	
	public int getSpeed() {
		return speed;
	}

	public double getX_centro() {
		return x_centro;
	}
	public double getY_centro() {
		return y_centro;
	}
	public int getRaggio() {
		return raggio;
	}


	public synchronized void update()
	{
		x_centro=(x_centro+(speed*dx));
		y_centro=(y_centro+(speed*dy));
	}



	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Bullet)
		{ Bullet b= (Bullet) obj;
		return this.x_centro==b.x_centro && y_centro==b.y_centro && this.raggio==b.raggio && this.speed==b.speed && this.dx==b.dx && this.dy==b.dy;
		}
		else return false;
	}
	
	
	
}
