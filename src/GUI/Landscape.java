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
					int chance = (int) (Math.random() * 401);

					if(chance == 91 || chance == 92)
						land[row][col].territory.grow("shrub", 44);
					else if (chance == 93)
						land[row][col].territory.grow("tree", 111);
					 
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
					if (land[row][col].occupied())
						g.drawImage(land[row][col].animal.appearance, col * 10 - 10, row * 10 - 10, 40, 40, null);					
					if (land[row][col].territory.plant != null)
						g.drawImage(land[row][col].territory.plantImg, col * 10 -land[row][col].territory.plant.size/2 , row * 10 - land[row][col].territory.plant.size/2, land[row][col].territory.plant.size, land[row][col].territory.plant.size, null);
					if (land[row][col].territory.hasResource())
					{
						for (int x = 0; x < land[row][col].territory.resourceList().size() -1; x++) 
							g.drawImage(land[row][col].territory.resourceList().get(x).resourceImage, col * 10 - 15, row * 10 - 15, 30, 30, null);
								
					}
						
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

		// set up nextGen array
		Tile nextGen[][] = new Tile [land.length][land[0].length];

		for (int row = 0; row < land.length; row++) {
			for (int col = 0; col < land[0].length; col++)
			{
				nextGen[row][col] = new Tile(land[row][col]);
				nextGen[row][col].animal = null;
			}

		}

		// coordinate animal movement
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				if (land[row][col].planted())
				{
					land[row][col].territory.release();
				}
				
				if (land[row][col].occupied() && land[row][col].animal.health() >= 1)
				{	
					//System.out.println(row + ", " + col);
					land[row][col].animal.update();

					
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

		// coordinate animal interactions
		for (int row = 0; row < nextGen.length; row++)
		{
			for (int col = 0; col < nextGen[0].length; col++)
			{
				if (nextGen[row][col].occupied())
				{
					Animal baby = null;
					int emptyRow = -1, emptyCol = -1;

					if (row + 1 < nextGen.length && nextGen[row + 1][col].occupied())
						baby = new Animal(nextGen[row + 1][col].animal); //nextGen[row][col].animal.mate(nextGen[row + 1][col].animal);
					else {
						emptyRow = row + 1;
						emptyCol = col;
					}

					int compare = row - 1;				
					while (col + 1 < nextGen[0].length && compare <= row + 1 && compare > -1 && compare < nextGen.length) {
						if (nextGen[compare][col + 1].occupied() && baby == null)
							baby = nextGen[row][col].animal.mate(nextGen[compare][col + 1].animal);
						else {
							emptyRow = compare;
							emptyCol = col + 1;
						}

						compare++;
					}

					if (baby != null) {
						nextGen[emptyRow][emptyCol].add(baby);
						System.out.println("BABY MADE" + emptyRow + emptyCol);
					}
				}
			}
		}

		land = nextGen; 
	}
	
	public Animal find(int r, int c, Tile[][] nextGen) {
		for(int i = 0; i < 8; i ++)
		{
			try		//check the different edges once
			{
				if (i == 0 && nextGen[r][c - 1].occupied())		//check the 8 different edges
					return nextGen[r][c - 1].animal;
				else if (i == 1 && nextGen [r - 1][c].occupied())
					return nextGen[r -1][c].animal;
				else if (i == 2 && nextGen [r - 1][c + 1].occupied())
					return nextGen[r - 1][c + 1].animal;
				else if (i == 3 && nextGen [r - 1][c - 1].occupied())
					return nextGen[r - 1][c - 1].animal;
				else if (i == 4 && nextGen [r][c + 1].occupied())
					return nextGen[r][c + 1].animal;
				else if (i == 5 && nextGen [r + 1][c - 1].occupied())
					return nextGen[r + 1][c - 1].animal;
				else if (i == 6 && nextGen [r + 1][c].occupied())
					return nextGen[r + 1][c - 1].animal;
				else if (i == 7 && nextGen [r][c + 1].occupied())
					return nextGen[r][c + 1].animal;
			}
			catch (ArrayIndexOutOfBoundsException e)		//catch the exception
			{
				System.out.println("out of bounds");
			}
		}

		return null;
	}
}
