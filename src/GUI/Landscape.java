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
	int temperature = 50;

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

				for (int c = 0; c < land[0].length; c++)
				{
					try 
					{
						land[counter][c] = new Tile (tiles[c]);
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
		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				if (land[r][c].territory.ground.equals("grass"))
				{
					int chance = (int) (Math.random() * 401);

					if(chance == 91 || chance == 92)
						land[r][c].territory.grow("shrub", 44);
					else if (chance == 93)
						land[r][c].territory.grow("tree", 111);
					 
					/*else if (chance >= 70)									OTHER PLANTs
						land[r][c].territory.grow("tree");
					else if (chance >= 60)
						land[r][c].territory.grow("tree");
					else if (chance >= 50)
						land[r][c].territory.grow("tree");
					 */
				}
			}
		}
	}

	public void show(Graphics g) {

		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try 
				{	
					g.drawImage(land[r][c].territory.groundImg, c * 10, r * 10, 10, 10, null);

				} 
				catch (NullPointerException e) {}
			}
		}

		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try {	
					if (land[r][c].occupied())
					{
						if (land[r][c].animal.controlled())
							g.drawImage(land[r][c].animal.appearance, c * 10 - 20, r * 10 - 20, 60, 60, null);					
						else
							g.drawImage(land[r][c].animal.appearance, c * 10 - 10, r * 10 - 10, 40, 40, null);					
						
					}
					if (land[r][c].territory.plant != null)
						g.drawImage(land[r][c].territory.plantImg, c * 10 -land[r][c].territory.plant.size/2 , r * 10 - land[r][c].territory.plant.size/2, land[r][c].territory.plant.size, land[r][c].territory.plant.size, null);
					if (land[r][c].territory.hasResource())
					{
						for (int x = 0; x < land[r][c].territory.resourceList().size(); x++) 
							g.drawImage(land[r][c].territory.resourceList().get(x).resourceImage, c * 10 - 15, r * 10 - 15, 30, 30, null);
								
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
		for (int r = 0 ; r < land.length ; r++)
		{
			for (int c = 0 ; c < land[0].length ; c++)
			{
				if (Math.floor(Math.random () * 650) < 1 && !land[r][c].territory.ground.equals("water"))
				{
					Animal newAnimal = null;
					if (animal instanceof Mammal)
						newAnimal = new Mammal((Mammal) animal);

					land[r][c].add(newAnimal);
				}
			}
		}
	}

	public ArrayList<String> makeInstructions(Pair[][] vis, int wantX, int wantY) {
		ArrayList<String> instruct = new ArrayList<String>();
		
		if (wantX != -1) {
			Pair cur = vis[wantX][wantY];
			int curx = wantX, cury = wantY;
			
			while (cur.x != -1) {
				if (cur.x == curx + 1) {
					instruct.add(0, "up");
				}
				else if (cur.x == curx - 1) {
					instruct.add(0, "down");
				}
				else if (cur.y == cury + 1) {
					instruct.add(0, "left");
				}
				else {
					instruct.add(0, "right");
				}
				cur = vis[cur.x][cur.y];
				curx = cur.x;
				cury = cur.y;
			}
		}
		
		return instruct;
	}
	
	public ArrayList<String> findResource(int r, int c, Resource resource, Animal a) {

		Pair vis[][] = new Pair[land.length][land[0].length];
		
		for (int row = 0; row < land.length; row++)
			for (int col = 0; col < land[0].length; col++)
				vis[row][col] = new Pair();
		
		vis[r][c].x = -1;
		vis[r][c].y = -1;
		vis[r][c].visited = true;
		
//		Queue<Pair> q = new LinkedList<Pair>();

//		q.add(vis[r][c]);
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(r);
		q.add(c);
		int wantX = -1, wantY = -1;
		
		while (!q.isEmpty()) {
			System.out.println("x");
			boolean keepSearching = true;
			
//			Pair cur = q.poll();
			int curx = q.poll();
			int cury = q.poll();
			
			if (curx != -1) {
				ArrayList<Resource> res = land[curx][cury].territory.resources();
				if (res.contains(resource)){
					wantX = curx;
					wantY = cury;
					while (!q.isEmpty()) {
						q.poll();
					}
					keepSearching = false;
				}
			}
			// mark the wanted thing with -1
			
			System.out.println(curx);
			System.out.println(cury);
			
			
			if (keepSearching) {
				System.out.println("sera");
				//left
				if (cury-1 >= 0) {
					System.out.println("left");
					if (!vis[curx][cury-1].visited
							&& (land[curx][cury-1].territory.resources().contains(resource) 
							|| (a.land() && !land[curx][cury-1].territory.ground.equals("water"))
							|| (a.water() && land[curx][cury-1].territory.ground.equals("water")))) {
						q.add(curx);
						q.add(cury-1);
						vis[curx][cury-1].x = curx;
						vis[curx][cury-1].y = cury;
						vis[curx][cury-1].visited = true;
					}
				}
				// right
				if (cury+1 < land[0].length) {
					System.out.println("right");
					if (!vis[curx][cury+1].visited 
							&& (land[curx][cury+1].territory.resources().contains(resource) 
							|| (a.land() && !land[curx][cury+1].territory.ground.equals("water"))
							|| (a.water() && land[curx][cury+1].territory.ground.equals("water")))) {
						q.add(curx);
						q.add(cury+1);
						vis[curx][cury+1].x = curx;
						vis[curx][cury+1].y = cury;
						vis[curx][cury+1].visited = true;
					}
				}
				// up
				if (curx-1 >= 0) {
					System.out.println("up");
					if (!vis[curx-1][cury].visited 
							&& (land[curx-1][cury].territory.resources().contains(resource) 
							|| (a.land() && !land[curx-1][cury].territory.ground.equals("water"))
							|| (a.water() && land[curx-1][cury].territory.ground.equals("water")))) {
						q.add(curx-1);
						q.add(cury);
						vis[curx-1][cury].x = curx;
						vis[curx-1][cury].y = cury;
						vis[curx-1][cury].visited = true;
					}
				}
				// down
				if (curx+1 < land.length) {
					System.out.println("down");
					if (!vis[curx+1][cury].visited 
							&& (land[curx+1][cury].territory.resources().contains(resource) 
							|| (a.land() && !land[curx+1][cury].territory.ground.equals("water"))
							|| (a.water() && land[curx+1][cury].territory.ground.equals("water")))) {
						q.add(curx+1);
						q.add(cury);
						vis[curx+1][cury].x = curx;
						vis[curx+1][cury].y = cury;
						vis[curx+1][cury].visited = true;
					}
				}
			}
		}
		
		for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < land[0].length; j++) {
				if (vis[i][j].visited == true)
					System.out.print(1);
				else
					System.out.print(0);
			}
			System.out.println();
		}
		
		System.out.println(wantX);
		System.out.println(wantY);
		ArrayList<String> z = makeInstructions(vis, wantX, wantY);
		for (int i = 0; i < z.size(); i++) {
			System.out.println(z.get(i));
		}
		return makeInstructions(vis, wantX, wantY);
	}
	
	public ArrayList<String> findAnimal(int r, int c, Animal animal, Animal a) {

		Pair vis[][] = new Pair[land.length][land[0].length];
		
		vis[r][c].x = -1;
		vis[r][c].y = -1;
		vis[r][c].visited = true;
		
//		Queue<Pair> q = new LinkedList<Pair>();
		Queue<Integer> q = new LinkedList<Integer>();
//		q.add(vis[r][c]);

		q.add(r);
		q.add(c);
		int wantX = -1, wantY = -1;
		
		while (!q.isEmpty()) {
			
			boolean keepSearching = true;
			
			int curx = q.poll();
			int cury = q.poll();
			
			if (land[curx][cury].animal.type().equals(animal.type())){
				wantX = curx;
				wantY = cury;
				while (!q.isEmpty()) {
					q.poll();
				}
				keepSearching = false;
			}
			
			// mark the wanted thing with -1
			
			if (keepSearching) {
				//left
				if (cury-1 > 0) {
					if (!vis[curx][cury-1].visited 
							&& (land[curx][cury-1].animal.type().equals(animal.type())
							|| (a.land() && !land[curx][cury-1].territory.ground.equals("water"))
							|| (a.water() && land[curx][cury-1].territory.ground.equals("water")))) {
						q.add(curx);
						q.add(cury-1);
						vis[curx][cury-1].x = curx;
						vis[curx][cury-1].y = cury;
						vis[curx][cury-1].visited = true;
					}
				}
				// right
				if (cury+1 > 0) {
					if (!vis[curx][cury+1].visited 
							&& (land[curx][cury+1].animal.type().equals(animal.type())
							|| (a.land() && !land[curx][cury+1].territory.ground.equals("water"))
							|| (a.water() && land[curx][cury+1].territory.ground.equals("water")))) {
						q.add(curx);
						q.add(cury+1);
						vis[curx][cury+1].x = curx;
						vis[curx][cury+1].y = cury;
						vis[curx][cury+1].visited = true;
					}
				}
				// up
				if (curx-1 > 0) {
					if (!vis[curx-1][cury].visited 
							&& (land[curx-1][cury].animal.type().equals(animal.type()) 
							|| (a.land() && !land[curx-1][cury].territory.ground.equals("water"))
							|| (a.water() && land[curx-1][cury].territory.ground.equals("water")))) {
						q.add(curx-1);
						q.add(cury);
						vis[curx-1][cury].x = curx;
						vis[curx-1][cury].y = cury;
						vis[curx-1][cury].visited = true;
					}
				}
				// down
				if (curx+1 > 0) {
					if (!vis[curx+1][cury].visited 
							&& (land[curx+1][cury].animal.type().equals(animal.type())
							|| (a.land() && !land[curx+1][cury].territory.ground.equals("water"))
							|| (a.water() && land[curx+1][cury].territory.ground.equals("water")))) {
						q.add(curx+1);
						q.add(cury);
						vis[curx+1][cury].x = curx;
						vis[curx+1][cury].y = cury;
						vis[curx+1][cury].visited = true;
					}
				}
			}
		}
		
		return makeInstructions(vis, wantX, wantY);
	}

	public void advance() {

		// set up nextGen array
		Tile nextGen[][] = new Tile [land.length][land[0].length];

		for (int r = 0; r < land.length; r++) {
			for (int c = 0; c < land[0].length; c++)
			{
				nextGen[r][c] = new Tile(land[r][c]);
				nextGen[r][c].animal = null;
			}
		}

		// coordinate animal movement
		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				if (land[r][c].planted() || land[r][c].territory.ground.equals("water"))
				{
					land[r][c].territory.release();
				}
				
				if (land[r][c].occupied() && land[r][c].animal.health() >= 1)
				{	
					//System.out.println(r + ", " + c);
					land[r][c].animal.update();

					
					int upDown = (int) (Math.random() * 3) - 1;
					int leftRight = (int) (Math.random() * 3) - 1;

					if (c == 0 && leftRight == -1)
						leftRight = 1;
					if (c == land[0].length-1 && leftRight == 1)
						leftRight = -1;
					if (r == 0 && upDown == -1)
						upDown = 1;
					if (r == land.length-1 && upDown == 1)
						upDown = -1;

					if (!nextGen[r + upDown][c + leftRight].occupied() && !nextGen[r + upDown][c + leftRight].territory.ground.equals("water"))
						nextGen[r + upDown][c + leftRight].add(land[r][c].animal);
					else if (!nextGen[r + upDown][c].occupied() && !nextGen[r + upDown][c].territory.ground.equals("water"))
						nextGen[r + upDown][c].add(land[r][c].animal);
					else if (!nextGen[r][c + leftRight].occupied() && !nextGen[r][c + leftRight].territory.ground.equals("water"))
						nextGen[r][c + leftRight].add(land[r][c].animal);
					else if (!nextGen[r][c].occupied())
						nextGen[r][c + leftRight].add(land[r][c].animal);
				}
			}
		}

		// coordinate animal interactions
		for (int r = 0; r < nextGen.length; r++)
		{
			for (int c = 0; c < nextGen[0].length; c++)
			{
				if (nextGen[r][c].occupied())
				{
					Animal baby = null;
					int emptyRow = -1, emptyCol = -1;

					if (r + 1 < nextGen.length && nextGen[r + 1][c].occupied())
						baby = nextGen[r][c].animal.mate(nextGen[r + 1][c].animal);
					else {
						emptyRow = r + 1;
						emptyCol = c;
					}

					int compare = r - 1;				
					while (c + 1 < nextGen[0].length && compare <= r + 1 && compare > -1 && compare < nextGen.length) {
						if (nextGen[compare][c + 1].occupied() && baby == null) {
							baby = nextGen[r][c].animal.mate(nextGen[compare][c + 1].animal);
						}
						else {
							emptyRow = compare;
							emptyCol = c + 1;
						}

						compare++;
					}

					if (baby != null && emptyRow != -1 && Math.random() < 0.3) {
						nextGen[emptyRow][emptyCol].add(baby);
						System.out.println("BABY MADE" + emptyRow + emptyCol);
					}
				}

			}
		}

		land = nextGen; 
	}
	
	public void updateRR(double rate)
	{
		for (int row = 0; row < land.length; row++)
		{
			for (int col = 0; col < land[0].length; col++)
			{
				land[row][col].territory.changeRate(rate);
			}
				
		}
	}
	
	public void updateTemp(int temp)
	{
		temperature = temp;
	}
	
	public static void main(String[] args) {
		Landscape landscape = new Landscape();
		Animal animal = new Mammal("Summative Graphics\\Animals\\animal2.png", 1, 1, 1, "Male");
		landscape.populate(animal);
	}
}

class Pair {
	
	public int x, y;
	public boolean visited = false;
	
	public Pair() {
		this.x = 0;
		this.y = 0;
		visited = false;
	}
		
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}
