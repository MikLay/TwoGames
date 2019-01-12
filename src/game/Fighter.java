package game;

import acm.graphics.GImage;

public class Fighter
{
	Thegame game;
	GImage fighter = new GImage("fighter.gif");
	GImage explos = new GImage("source.gif");

	public Fighter(Thegame thegame)
	{
		this.game = thegame;
	}

	public void newfighter()
	{
		fighter.setSize(200, 200);
		game.add(fighter, 0, 0);
	}

	public boolean movehell(double x, double y)
	{
		if (y > 650)
		{
			game.remove(fighter);
			explos.setSize(100, 100);
			Thread myThready = new Thread(new Runnable()
			{
				public void run()
				{
					game.add(explos, x, y);
					explos.pause(200);
					game.remove(explos);
				}
			});
			myThready.start();

			return false;
		}
		fighter.setLocation(x, y);
		return true;
	}
}