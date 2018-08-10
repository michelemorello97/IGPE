package graphics;
import core.*;
import interfaces.Direction;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import managers.GameManager;

public class MyPanel extends JPanel {

	GameManager gameManager;
	Image character;
	Image bullet;
	Image[] enemy_sprites;
	Set<Character> pressed = new HashSet<Character>();

	public MyPanel(int w, int h) {
		super();
		gameManager = new GameManager(w, h);
		enemy_sprites=new Image[10];
		initGUI();
		initEH();
		Thread t=new Thread(gameManager);
		t.start();
		new Thread() {
			@Override
			public void run() {
				
				while(true) {
					
				repaint();
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}.start();
	}

	@Override
	public synchronized void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(character, (gameManager.getWorld().getPlayer().getX())-20, gameManager.getWorld().getPlayer().getY()-20, null);
		for(int i=0; i<6; i++)
		{
			if(gameManager.getWorld().getEnemy(i).isActive())
			{
				g.drawImage(enemy_sprites[i], (gameManager.getWorld().getEnemy(i).getX()-20), (gameManager.getWorld().getEnemy(i).getY()-20), null);
				//g.drawImage(enemy_sprites[0], (gameManager.getWorld().getEnemy(1).getX()-20), (gameManager.getWorld().getEnemy(1).getY()-20), null);
			}
		}
		//System.out.println((gameManager.getWorld().getEnemy(1).getX_player()));
		
		for(Entity e: gameManager.getWorld().getEnemies())
		{
			if(e instanceof EnemyShooter && e.isActive())
			{
				for(Bullet b:  ((EnemyShooter) e).getBullets())
				{
					g.drawImage(bullet, (int)b.getX_centro()-b.getRaggio(),(int) b.getY_centro()-b.getRaggio(), null);
					
				}
			}
		}
		
		for(Bullet b: gameManager.getWorld().getPlayer().getBullets())
		{
			g.drawImage(bullet, (int)b.getX_centro()-b.getRaggio(),(int) b.getY_centro()-b.getRaggio(), null);
		}
		
	}

	public void initGUI() {
		character = Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/character.png");
		bullet = Toolkit.getDefaultToolkit()
				.getImage("C:/Users/Michele/Desktop/unical_run2/target/classes/bullet.png");
		enemy_sprites[0]=Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/enemy0.png");
		enemy_sprites[1]=Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/enemy1.png");
		enemy_sprites[2]=Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/enemy2.png");
		enemy_sprites[3]=Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/enemy3.png");
		enemy_sprites[4]=Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/enemy4.png");
		enemy_sprites[5]=Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Michele\\Desktop\\unical_run2\\target\\classes/enemy5.png");
		repaint();

	}

	public synchronized void initEH() {
		setFocusable(true);

		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				Character c=e.getKeyChar();
				if (c == 'w') {
					gameManager.getWorld().getPlayer().setDirection(Direction.UP);
					gameManager.getWorld().getPlayer().move();				

				}
				if (c == 'd') {
					gameManager.getWorld().getPlayer().setDirection(Direction.RIGHT);
					gameManager.getWorld().getPlayer().move();

				}
				if (c == 'a') {
					gameManager.getWorld().getPlayer().setDirection(Direction.LEFT);
					gameManager.getWorld().getPlayer().move();
				}
				if (c == 's') {
					gameManager.getWorld().getPlayer().setDirection(Direction.DOWN);
					gameManager.getWorld().getPlayer().move();
				}
				
				
				
				for(Entity en: gameManager.getWorld().getEnemies())
				{
					if(en!=null)
					en.setPlayerPosition(gameManager.getWorld().getPlayer().getX(), gameManager.getWorld().getPlayer().getY());
				}
			};
	
			

			public void keyReleased(KeyEvent e) {
				gameManager.getWorld().getPlayer().setDirection(Direction.STOP);
			}


			public void keyPressed(KeyEvent e) {
								
			}

		}

		);

		
		this.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e)
			{/*
				Player daaggiornare=gameManager.getWorld().getPlayer();
				daaggiornare.setWhere_x(e.getX());
				daaggiornare.setWhere_y(e.getY());
				daaggiornare.shoot(); */
				
				gameManager.getWorld().getPlayer().setWhere_x(e.getX());
				gameManager.getWorld().getPlayer().setWhere_y(e.getY());
				gameManager.getWorld().getPlayer().shoot();
				
			}
			
			
		});
		
		
	}



}
	

