package GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Ecosystem.Animal;

public class Landscape {

	Tile[][] land;
	String weather;
	int temperature;
	
	public Landscape() {
		land = new Tile[100][120];
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
						land[counter][col] = new Tile (tiles[col]);
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
					
					//if (land[row][col].territory.plant != null)
					//g.drawImage(land[row][col].territory.plantImg, col * 10, row * 10, 10, 10, null);
					
				} catch (NullPointerException e) {}
			}
		}
		
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try {	
					if (!land[row][col].animals.isEmpty())
						g.drawImage(land[row][col].animals.get(0).appearance, col * 10 - 10, row * 10 - 10, 40, 40, null);					
				} catch (NullPointerException e) {}
			}
		}
		
//		Image appearance = null;
//		try {
//			appearance = ImageIO.read(new File("Summative Graphics\\animal2.png"));
//		}
//		catch (Exception ex) {}
//
//		g.drawImage(appearance, 12, 12, 111,111, null);
	}

	public void populate(Animal animal) {

        //for every cell in the grid, place true or false value --> true is more likely with a higher density
        for (int row = 0 ; row < land.length ; row++)
        {
            for (int col = 0 ; col < land[0].length ; col++)
            {
                if (Math.floor(Math.random () * 120) < 1 && !land[row][col].territory.ground.equals("water"))
                {
                	land[row][col].add(animal);
                }
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
