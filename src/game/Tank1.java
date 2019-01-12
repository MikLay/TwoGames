package game;

import acm.graphics.GImage;

public class Tank1
{

	public  static GImage newtank(int i)
	{

		GImage newtank;
		if (i == 0)
		{
			newtank = new GImage("giphy.gif");
			newtank.setSize(100, 100);

		} else if (i == 1)
		{
			newtank = new GImage("giphy (3).gif");
			newtank.setSize(100, 100);

		} else if (i == 2)
		{

			newtank = new GImage("200.gif");
			newtank.setSize(180, 100);

		} else if (i == 3)
		{
			newtank = new GImage("giphy1.gif");
			newtank.setSize(120, 100);
		} else if (i == 4)
		{
			newtank = new GImage("tank4.gif");
			newtank.setSize(150, 100);

		} else if(i ==5)
			newtank = new GImage("giphy2.gif");
		else
		{
			newtank = new GImage("boss.gif");
			newtank.setSize(150, 100);
		}
			return newtank;
		
	}

}
