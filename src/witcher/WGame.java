package witcher;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class WGame extends GraphicsProgram
{
	public static GCanvas game = new GCanvas();

	protected final static int width = 1500;
	protected final static int height = 800;
	protected final static int[][] array_firplayer = new int[5][2];
	protected final static int[][] array_secplayer = new int[5][2];
	protected static boolean player_turn = true;

	private GImage table = new GImage("table.jpg");
	private GImage tap = new GImage("tap.png");
	private GLabel text_Label = new GLabel("Are you ready?");
	private Random rand = new Random();

	// 50/50
	protected static int[] numbers = new int[10];

	public void init()
	{
		this.setSize(width, height);
		game.setSize(width, height);
		this.add(game, 0, 0);

		table.setSize(width, height);
		game.add(table);

	}

	public void run()
	{
		// making a table
		newtable();
		// throw dices
		for (int i = 0, x = width / 10 * 2, y = height / 8; i < 5; i++, x += 120)
		{
			numbers[i] = 0;
			array_firplayer[i][0] = y + 100;
			array_firplayer[i][1] = x;

			numbers[i + 5] = 0;
			array_secplayer[i][0] = y * 6;
			array_secplayer[i][1] = x + 250;
		}
		throw_dices();
		// show dices

		array_painter(Throwing.get_pictures(numbers));
		// show combination

		Combo.combination(numbers);

	}

	private void throw_dices()
	{
		for (int m = 0; m < 5; m++)
		{
			if (numbers[m] == 0)
				numbers[m] = rand.nextInt(6) + 1;
			if (numbers[m + 5] == 0)
				numbers[m + 5] = rand.nextInt(6) + 1;

		}
	}

	public static void array_painter(GImage[] dices)
	{
		for (int i = 0, j = 0; i < 10; i++)
		{
			if (i < 5)
				dices[i].setLocation(array_firplayer[i][1], array_firplayer[i][0]);
			else
			{
				dices[i].setLocation(array_secplayer[j][1], array_secplayer[j][0]);
				j++;
			}
			game.add(dices[i]);
		}
	}

	private void newtable()
	{
		GRect label = new GRect(width / 4 * 2, height / 4 * 2);
	
		tap.setSize(200, 200);

		tap.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				game.removeAll();
				Click_clack(tap);
				throw_dices();
				array_painter(Throwing.get_pictures(numbers));

				super.mouseClicked(e);
				Combo.combination(numbers);
				tap.removeMouseListener(this);
			}
		});

		label.setFilled(true);
		label.setFillColor(Color.BLACK);
		text_Label.setColor(Color.WHITE);
		text_Label.setFont("Serif-100");
		game.add(label, width / 4, height / 4);
		game.add(text_Label, width / 4 + 50, height / 2);
		game.add(tap, width / 6*3 - 100, height / 5*2+25);
		this.pause(1000);

		game.remove(label);
		game.remove(text_Label);
	}

	public void Click_clack(GImage click)
	{
		Thread stopper = new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				click.setImage("tap_ch.png");
				click.setSize(200, 200);
				game.add(table);
				game.add(click);
				try
				{
					Thread.sleep(500);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				click.setImage("tap.png");
				click.setSize(200, 200);
				game.add(click);
				
			}
		});stopper.start();
	}
}