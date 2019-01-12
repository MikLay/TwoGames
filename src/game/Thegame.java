package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;


import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Thegame extends GraphicsProgram
{
	GRect rect = new GRect(1500, 800);
	boolean end = true;
	public ArrayList<GImage> TankArray = new ArrayList<>();
	GImage backgr = new GImage("backgr.gif");
	Fighter helic = new Fighter(this);

	public void init()
	{
		this.setSize(1500, 800);

		backgr.setSize(1500, 800);
		rect.setSize(1500, 800);
		add(rect, 0, 100);
		add(backgr, 0, 0);
	}

	public void run()
	{

		helic.newfighter();
		tankgener();
		// int lifes = 3;
		rect.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				mouseMoved(e);
			}

			@Override
			public void mouseMoved(MouseEvent e)
			{

				double x = e.getX() - 130;
				double y = e.getY() - 130;

				end = helic.movehell(x, y);
				if (end == false)
				{
					endgame();
					rect.removeMouseMotionListener(this);
					return;
				}
			}
		});
		Bomb bomb = new Bomb(this);

		rect.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (end == false)
					rect.removeMouseListener(this);
				bomb.moveBomb(e.getX() - 50, e.getY());
			}
		});
	}

	public void tankgener()
	{

		Thread tankmove = new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					Random rand = new Random();
					int imgtank = rand.nextInt(7);
					GImage tank = Tank1.newtank(imgtank);

					add(tank, 1500, 700);
					TankArray.add(tank);

					Thread tankmove = new Thread(new Runnable()
					{
						public void run()
						{
							while (tank.getX() > -150)
							{
								tank.move(-1, 0);
								pause(10);
								if (tank.getX() < -140)
								{
									remove(tank);
									TankArray.remove(tank);
								}
							}
						}
					});
					tankmove.start();

					try
					{
						imgtank = rand.nextInt(5000) + 1000;
						Thread.sleep(imgtank);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		tankmove.start();
	}

	private void endgame()
	{
		GImage statistic = new GImage("gameover.jpg");
		statistic.setSize(this.getWidth() / 6 * 4, this.getHeight() / 4 * 2);
		this.add(statistic, this.getWidth() / 6, this.getHeight() / 4);
	}
}
