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

import Ecosystem.*;

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
					try 
					{
						land[counter][col] = new Tile (tiles[col]);
					} 
					catch (ArrayIndexOutOfBoundsException e) 
					{
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
		
		// generate plants
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				if (land[row][col].territory.ground.equals("grass"))
				{
					int chance = (int) (Math.random() * 201);
					
					if (chance == 93)
						land[row][col].territory.grow("tree");
					/*else if (chance >= 70)									OTHER PLANTs
						land[row][col].territory.grow("tree");
					else if (chance >= 60)
						land[row][col].territory.grow("tree");
					else if (chance >= 50)
						land[row][col].territory.grow("tree");
						*/
				}
			}
		}
	}

	public void show(Graphics g) {
		
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try 
				{	
					g.drawImage(land[row][col].territory.groundImg, col * 10, row * 10, 10, 10, null);
					
				} 
				catch (NullPointerException e) {}
			}
		}
		
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try {	
					if (land[row][col].animal != null)
						g.drawImage(land[row][col].animal.appearance, col * 10 - 10, row * 10 - 10, 40, 40, null);					
					if (land[row][col].territory.plant != null)
						g.drawImage(land[row][col].territory.plantImg, col * 10 -land[row][col].territory.plant.size/2 , row * 10 - land[row][col].territory.plant.size/2, land[row][col].territory.plant.size, land[row][col].territory.plant.size, null);
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
                if (Math.floor(Math.random () * 650) < 1 && !land[row][col].territory.ground.equals("water"))
                {
                	Animal newAnimal = null;
                	if (animal instanceof Mammal)
						newAnimal = new Mammal((Mammal) animal);
                	
                	land[row][col].add(newAnimal);
                }
            }
        }
	}
	
	public  ArrayList<String> findResource(int row, int col, Resource r) {
		ArrayList<String> arr = new ArrayList<String>();
		
		int vis[][] = new int[land.length][land[0].length];
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(row);
		q.add(col);
		
		while (!q.isEmpty()) {
			int curX = q.poll();
			int curY = q.poll();
				
			// mark the wanted thing with -1
			
			//left
			if (curY-1 > 0) {
				if (vis[curX][curY] == 0) {
					q.add(curX);
					q.add(curY-1);
				}
			}
			// right
			if (curY+1 > 0) {
				if (vis[curX][curY+1] == 0) {
					q.add(curX);
					q.add(curY+1);
				}
			}
			// up
			if (curX-1 > 0) {
				if (vis[curX-1][curY] == 0) {
					q.add(curX-1);
					q.add(curY);
				}
			}
			// down
			if (curX+1 > 0) {
				if (vis[curX+1][curY] == 0) {
					q.add(curX+1);
					q.add(curY);
				}
			}
		}
		
		return arr;
	}
	
	public void advance() {

		Tile nextGen[][] = new Tile [land.length][land[0].length];
		
		for (int row = 0; row < land.length; row++) {
			for (int col = 0; col < land[0].length; col++)
			{
				nextGen[row][col] = new Tile(land[row][col]);
				nextGen[row][col].animal = null;
			}
			
		}
		
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				if (land[row][col].occupied() && land[row][col].animal.health() >= 1)
				{	
					land[row][col].animal.updateAppetite();
					
					int upDown = (int) (Math.random() * 3) - 1;
					int leftRight = (int) (Math.random() * 3) - 1;
					
					if (col == 0 && leftRight == -1)
						leftRight = 1;
					if (col == land[0].length-1 && leftRight == 1)
						leftRight = -1;
					if (row == 0 && upDown == -1)
						upDown = 1;
					if (row == land.length-1 && upDown == 1)
						upDown = -1;
					
					if (!nextGen[row + upDown][col + leftRight].occupied() && !nextGen[row + upDown][col + leftRight].territory.ground.equals("water"))
						nextGen[row + upDown][col + leftRight].add(land[row][col].animal);
					else if (!nextGen[row + upDown][col].occupied() && !nextGen[row + upDown][col].territory.ground.equals("water"))
						nextGen[row + upDown][col].add(land[row][col].animal);
					else if (!nextGen[row][col + leftRight].occupied() && !nextGen[row][col + leftRight].territory.ground.equals("water"))
						nextGen[row][col + leftRight].add(land[row][col].animal);
					else if (!nextGen[row][col].occupied())
						nextGen[row][col + leftRight].add(land[row][col].animal);
				}
			}
		}

		land = nextGen; 
	}
	
}
