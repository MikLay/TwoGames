package game;

import acm.graphics.GImage;

public class Bomb
{
	Thegame game;

	public Bomb(Thegame thegame)
	{
		this.game = thegame;
	}

	public void moveBomb(double x, double y)
	{
		GImage bomb = new GImage("bomb.png");
		Thread myThready = new Thread(new Runnable()
		{
			public void run()
			{
				bomb.setSize(30, 30);
				game.add(bomb, x, y);
				bomb.sendBackward();
				GImage explos = new GImage("source.gif");
				explos.setSize(100, 100);
				while (bomb.getY() < 800)
				{
					for (int i = game.TankArray.size()-1; i >=0; i--)
					{
						if (game.TankArray.size() != 0 && bomb.getY() + bomb.getHeight() * 0.5 >= 700 && game.TankArray.get(i).getX() < bomb.getX()
								&& game.TankArray.get(i).getX() + game.TankArray.get(i).getWidth() > bomb.getX()
										+ bomb.getWidth())
						{
							game.remove(bomb);
							game.remove(game.TankArray.get(i));
							game.TankArray.remove(game.TankArray.get(i));
							game.add(explos, bomb.getX(), bomb.getY());
							try
							{
								Thread.sleep(200);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							game.remove(explos);
						}
					}
					bomb.move(0, 1);
					try
					{
						Thread.sleep(2);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					if (bomb.getY() >= 800)
						game.remove(bomb);
				}
			}
		});
		myThready.start();

	}
}
