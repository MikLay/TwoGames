package witcher;

import acm.graphics.GImage;

public class Combo
{
	
	public static void combination(int[] numbers)
	{
		int[] first_num = new int[6];
		int[] second_num = new int[6];

		for (int i = 0; i < 10; i++)
		{
			if (i < 5)
			{
				if (numbers[i] == 1)
					first_num[0]++;
				else if (numbers[i] == 2)
					first_num[1]++;
				else if (numbers[i] == 3)
					first_num[2]++;
				else if (numbers[i] == 4)
					first_num[3]++;
				else if (numbers[i] == 5)
					first_num[4]++;
				else if (numbers[i] == 6)
					first_num[5]++;
			} else
			{
				if (numbers[i] == 1)
					second_num[0]++;
				else if (numbers[i] == 2)
					second_num[1]++;
				else if (numbers[i] == 3)
					second_num[2]++;
				else if (numbers[i] == 4)
					second_num[3]++;
				else if (numbers[i] == 5)
					second_num[4]++;
				else if (numbers[i] == 6)
					second_num[5]++;

			}
		}

		GImage combo1 = pic(first_num);
		GImage combo2 = pic(second_num);
		combo1.setSize(200, 200);
		combo2.setSize(200, 200);
		WGame.game.add(combo1, WGame.width/6*4,WGame.height/8);
		WGame.game.add(combo2,WGame.width/6,WGame.height/8*5);
	}

	private static GImage pic(int[] amount)
	{
		GImage nothing = new GImage("nothing.png");
		GImage pair = new GImage("pair.png");
		GImage two_pairs = new GImage("two_pairs.png");
		GImage three_of_a_kind = new GImage("three_of_a_kind.png");
		GImage Five_High_Straight = new GImage("Five_High_Straight.png");
		GImage Six_High_Straight = new GImage("Six_High_Straight.png");
		GImage Full_House = new GImage("Full_House.png");
		GImage Four_of_a_Kind = new GImage("Four_of_a_Kind.png");
		GImage Five_of_a_Kind = new GImage("Five_of_a_Kind.png");

		int two = 0, three = 0, four = 0,five = 0;
		for (int i = 0; i < 6; i++)
		{
			if(amount[i] == 5)
			{
				five++;
			}
			if(amount[i] == 4)
			{
				four++;
			}
			if (amount[i] == 3)
			{
				three++;
			}
			if (amount[i] == 2)
			{
				two++;
			}
		}
		if(five == 1)
			return Five_of_a_Kind;
		if(four == 1)
			return Four_of_a_Kind;
		if (three == 1 && two == 1)
			return Full_House;
		if(amount[1]== 1&& amount[2]==1&&amount[3]==1&&amount[4]==1&&amount[5]==1)
			return Six_High_Straight;
		if(amount[0]== 1&& amount[1]==1&&amount[2]==1&&amount[3]==1&&amount[4]==1)
			return Five_High_Straight;
		if (three == 1)
			return three_of_a_kind;
		if (two == 2)
			return two_pairs;
		if(two == 1)
			return pair;
		else
			return nothing;
	}
}

