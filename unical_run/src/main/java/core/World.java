package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class World {

	private final int height;
	private final int width;

	
	private Player player;
	
	private List<Entity> enemies;
	
	public World(int w, int h) 
	{
		
		
		height=h; width=w;
		int py=(int) ((int) height*0.6);
		int px=(int) ((int) width*0.5); 
		player= new Player(6, 30, 1,px, py, 40); 
									//speed, hp, rateoffire, x,y, l hitbox
		enemies= Collections.synchronizedList(new ArrayList<Entity>());
		enemies.add(0, new EnemyShooter(1,10, 1, 300, 60,40)); //cc
		enemies.add(1, new EnemyCharger(4,10, w-(w/4), h/2,40)); //rj
		enemies.add(2, new EnemyCharger(4,10, w/2, 0-h/4,40)); //sp
		enemies.add(3, new EnemyShooter(1,10,(float) 1.5,(int) ((int) w*0.6), 60,40)); //tt 
		enemies.add(4, new EnemyCharger(4,10, 0-w/4, h/4,40)); //fs
		enemies.add(5, new EnemyShooter(1,15, (float) 2, w/2, h/6, 40)); //vb
		
		for(int i=0; i<5; i++)
			enemies.get(i).setPlayerPosition(px, py);
		
		enemies.get(0).active=true;
		Thread t= new Thread(enemies.get(0));
		t.start();
		
	}

	@Override
	public String toString() {
		String s="";
		
		for(int i=0; i<height; i++)
		{
			
				s+="_";
		}
			s+="\n";
		
		return s;
	}
	
	
	
	
	public List<Entity> getEnemies() {
		return enemies;
	}


	public Player getPlayer() {
		return player;
	}

	public Entity getEnemy(int i){
		return enemies.get(i);
	}

	public synchronized void update()
	{
		for(Entity e: enemies)
		{
			if (e instanceof EnemyShooter)
			{
				for(Bullet b:((EnemyShooter) e).getBullets())
				{
					b.update(); 
					
				}
			}
		}
		
		for(Entity e: enemies)
		{
			if(e instanceof EnemyShooter && e.isActive())
			{
				Iterator<Bullet> it=((EnemyShooter) e).getBullets().iterator();
				while(it.hasNext())
				{
					Bullet b=it.next();
					if(b.getX_centro()>width || b.getY_centro()>height || b.getX_centro()<0 || b.getY_centro()<0) 
						it.remove();
					
					if(checkCollision(b.getX_centro(), b.getY_centro(), b.getRaggio(), player.x, player.y))
					{
						player.hp--;
						it.remove();
					}
				}
			}
		}
		
		for(Bullet b: player.getBullets())
		{
			b.update();
		}
		
		for(Entity e: enemies)
		{
			Iterator<Bullet> it=player.getBullets().iterator();
			while(it.hasNext())
			{
				Bullet b=it.next();
				if(b.getX_centro()>width || b.getY_centro()>height || b.getX_centro()<0 || b.getY_centro()<0) 
				it.remove();
				if(checkCollision(b.getX_centro(), b.getY_centro(), b.getRaggio(), e.x, e.y))
				{
					e.hp--;
					it.remove();
				}
			}
		}
	}
	
	public boolean checkCollision(double xbcentro, double ybcentro, double raggio, double xtarget, double ytarget)
	{
		return (20 + raggio) > Math.abs(xtarget -xbcentro) && (20 + raggio) > Math.abs(ytarget -ybcentro);
	}

	
}
