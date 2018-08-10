package core;
import interfaces.Movable;
public class EnemyCharger extends Entity implements Movable, Runnable {

	
	private int i_player;
	private int j_player;
	
	public void setPlayerPosition(int x, int y)
	{
		this.i_player = x;
		this.j_player = y;
		//move();
	}
	
	
	public void move()
	{
		
			int distance= (int) Math.sqrt((i_player-x)*(i_player-x) + (j_player-y)*(j_player-y));
			
			try {
			
			this.x+=((speed*(i_player-x))/distance);
			
			this.y+=((speed*(j_player-y))/distance);
			}
			catch(Exception e)
			{
				
			}
			
			
		
	}
	
	public EnemyCharger(int s, int h, int i, int j, int l)
	{
		super(s,h, i, j, l);
	}

	@Override
	public String toString() {
		return "c";
	}

	
	public int getX_player() {
		return i_player;
	}



	public int getY_player() {
		return j_player;
	}



	public void run() {
		while(this.hp>0)
		{
		this.move();
		try {
			Thread.sleep((long) (16));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		this.active=false;
	}
	
	
}
