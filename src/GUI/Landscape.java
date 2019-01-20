package GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Landscape {

	Tile[][] land;
	String weather;
	int temperature;
	
	public Landscape() {
		land = new Tile[120][100];
		String[] tiles = null;
		int counter = 0;
		
		try
		{
			
			Scanner reader = new Scanner(new File("Summative Graphics\\landscape.txt"));
			while (reader.hasNextLine())
			{
				String in =	reader.nextLine();
				tiles = in.split(",");
				
				for (int col = 0; col < land[0].length; col++)
				{
					try {
						land[counter][col] = new Tile (tiles[counter * land[0].length + col]);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("ohno");
					}
				}
				
				counter++;
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			System.out.println("error landscape");
		}
	}

	public void show(Graphics g) {
		
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try {
					g.drawImage(land[row][col].territory.groundImg, col * 10, row * 10, 10, 10, null);
				} catch (NullPointerException e) {}

				//if (land[row][col].territory.plant != null)
					//g.drawImage(land[row][col].territory.plantImg, col * 10, row * 10, 10, 10, null);
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
