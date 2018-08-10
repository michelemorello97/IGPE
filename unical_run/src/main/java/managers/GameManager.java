package managers;


import core.World;

public class GameManager implements Runnable{

	World world;

	
	
	
	public World getWorld() {
		return world;
	}


	public GameManager(int w, int h)
	{
		world= new World(w, h);
		world.update();
	}

	@Override
	public String toString() {
		return world.toString();
	}

	public synchronized void run()
	{ 
		int indexcurrentenemy=0;
		int timer=0;
		try        
		{
			while(world.getPlayer().getHp()>0)
			{
				world.update();
				//world.checkCollision();
				Thread.sleep(16);
				timer++; System.out.println(timer +"\n");
				if(timer==150 && indexcurrentenemy<5)
				{   indexcurrentenemy++;
					world.getEnemy(indexcurrentenemy).setActive(true);
					
					Thread x= new Thread(world.getEnemy(indexcurrentenemy));
					x.start();
					
					timer=0;
				}
			} 
		}
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		
	}
}
