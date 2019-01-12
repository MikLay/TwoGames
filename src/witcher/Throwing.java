package witcher;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;

public class Throwing
{
	public static GImage[] get_pictures(int[] array_of_diceNumbers)
	{
		GImage[] images = new GImage[10];
		GImage what_png;

		for (int m = 0; m < 10; m++)
		{
			switch (array_of_diceNumbers[m])
			{
			case 1:
				what_png = new GImage("one.png");
				break;
			case 2:
				what_png = new GImage("two.png");
				break;
			case 3:
				what_png = new GImage("three.png");
				break;
			case 4:
				what_png = new GImage("four.png");
				break;
			case 5:
				what_png = new GImage("five.png");
				break;
			default:
				what_png = new GImage("six.png");
				break;
			}

			what_png.setSize(100, 100);
			images[m] = what_png;
			images[m].addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{

					for (int i = 0; i < 10; i++)
					{
						if (e.getSource().equals(images[i]))
						{
							images[i] = Throwing.choose(array_of_diceNumbers[i]);
							WGame.numbers[i] = 0;
						}
					WGame.array_painter(images);
					
					super.mouseClicked(e);
				}
			}
			});

		}
		return images;
	}

	public static GImage choose(int i)
	{
		GImage yellow_dice;
		switch (i)
		{
		case 1:
			yellow_dice = new GImage("onec.png");
			break;
		case 2:
			yellow_dice = new GImage("twoc.png");
			break;
		case 3:
			yellow_dice = new GImage("threec.png");
			break;
		case 4:
			yellow_dice = new GImage("fourc.png");
			break;
		case 5:
			yellow_dice = new GImage("fivec.png");
			break;
		default:
			yellow_dice = new GImage("sixc.png");
		}
		yellow_dice.setSize(100, 100);
		return yellow_dice;
	}
}
