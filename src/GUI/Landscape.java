package GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Landscape {

	Tile[][] land;
	String weather;
	int temperature;

	public Landscape() {

	}

	public void show(Graphics g) {
		
	}
	
	public void advance() {

		Tile nextGen[][] = new Tile [land.length][land[0].length];

		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				int upDown = (int) (Math.random() * 3 - 1);
				int leftRight = (int) (Math.random() * 3 - 1);
				
				nextGen[row + upDown][col + leftRight].animals.add(land[row][col].animals.get(0));
				land[row][col].animals.remove(0);
			}
		}
		
		land = nextGen; 
	}
}
