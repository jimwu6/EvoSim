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
		
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				// draw ground and plant --> maybe move this somewhere?
				g.drawImage(land[row][col].territory.groundImg, col * 7, row * 7, 10, 10, null);
				
				if (land[row][col].territory.plant != null)
					g.drawImage(land[row][col].territory.plantImg, col * 7, row * 7, 10, 10, null);
			}
		}
	}

	public void advance() {

		Tile nextGen[][] = new Tile [land.length][land[0].length];

		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				for (int i = 0; i < land[row][col].animals.size(); i++)
				{
					int upDown = (int) (Math.random() * 3 - 1);
					int leftRight = (int) (Math.random() * 3 - 1);
	
					nextGen[row + upDown][col + leftRight].animals.add(land[row][col].animals.get(0));
					land[row][col].animals.remove(0);
				}
			}
		}

		land = nextGen; 
	}
}
