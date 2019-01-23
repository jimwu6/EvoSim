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
				if (Math.floor(Math.random () * 200) < 1)
				{
					Animal newAnimal = null;
					if (animal.type().equals("cellular"))
						newAnimal = new Cellular((Cellular) animal);
					else if (animal.type().equals("mammal") && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Mammal((Mammal) animal);
					else if (animal.type().equals("fish") && land[r][c].territory.ground.equals("water"))
						newAnimal = new Fish((Fish) animal);
					else if (animal.type().equals("amphibian") && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Amphibian((Amphibian) animal);
					land[r][c].add(newAnimal);
				}
			}
		}
	}

	public void populate() {
		for (int r = 0 ; r < land.length ; r++)
		{
			for (int c = 0 ; c < land[0].length ; c++)
			{
				if (Math.floor(Math.random () * 650) < 1)
				{
					double rand = Math.random();
					String gender = Math.random() < 0.5? "Male" : "Female";
					Animal newAnimal = null;
					if (rand > 0.9 && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Mammal("mammal", 60, 20, 80, gender);
					else if (rand > 0.8 && land[r][c].territory.ground.equals("water"))
						newAnimal = new Fish("fish", 20, 30, 50, gender);
					else if (rand > 0.7)
						newAnimal = new Amphibian("amphibian", 10, 40, 15, gender);
					else if (rand > 0.6 && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Raptor("raptor", 10, 40, 15, gender, true);
					else if (rand > 0.5 && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Amphibian("amphibian", 10, 40, 15, gender);
					else if (rand > 0.4 && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Amphibian("amphibian", 10, 40, 15, gender);
					
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
				curx = cur.x;
				cury = cur.y;
				cur = vis[curx][cury];
			}
			
			if (!instruct.isEmpty())
				instruct.remove(instruct.size()-1);
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

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(r);
		q.add(c);
		
		int wantX = -1, wantY = -1;

		while (!q.isEmpty()) {
			
			boolean keepSearching = true;

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

			if (keepSearching) {
				//left
				if (cury-1 >= 0) {
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

		return makeInstructions(vis, wantX, wantY);
	}

	public ArrayList<String> findAnimal(int r, int c, Animal animal, Animal a) {

		Pair vis[][] = new Pair[land.length][land[0].length];
		
		for (int row = 0; row < land.length; row++)
			for (int col = 0; col < land[0].length; col++)
				vis[row][col] = new Pair();
		
		vis[r][c].x = -1;
		vis[r][c].y = -1;
		vis[r][c].visited = true;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(r);
		q.add(c);
		
		int wantX = -1, wantY = -1;

		while (!q.isEmpty()) {

			boolean keepSearching = true;

			int curx = q.poll();
			int cury = q.poll();


	
			if (curx != -1) {
				if (land[curx][cury].animal.type().equals(animal.type())){
					wantX = curx;
					wantY = cury;
					while (!q.isEmpty()) {
						q.poll();
					}
					keepSearching = false;
				}
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
				Animal curAnimal = land[r][c].animal;
				
				if (land[r][c].planted() || land[r][c].territory.ground.equals("water")) {
					land[r][c].territory.release();
				}

				if (land[r][c].occupied() && curAnimal.health() >= 1 
						&& (curAnimal.age() < curAnimal.lifespan() || Math.random() < 0.7 - 0.2 * (curAnimal.age() - curAnimal.lifespan()))) 
				{	
					if (curAnimal != null && (curAnimal.moveList == null || curAnimal.moveList.isEmpty())) {
						if (curAnimal.thirst() < 75 ) 
							curAnimal.moveList = findResource(r, c, new Resource ("waterResource"), curAnimal);
						else if (curAnimal.hunger() < 60 && curAnimal.land()) 
							curAnimal.moveList = findResource(r, c, new Resource ("fruit"), curAnimal);
						else if (curAnimal.hunger() < 60 && curAnimal.water()) 
							curAnimal.moveList = findResource(r, c, new Resource ("seaweed"), curAnimal);
						
					}
					
					curAnimal.update();
					
					if (curAnimal.hunger() < 60)
					{
						if (r != 0)
						{
							
							if (curAnimal.carnivore() && land[r-1][c].animal != null) {
								curAnimal.hurt(land[r-1][c].animal);
								if (land[r-1][c].animal.health() <= 0 && land[r-1][c].animal.size() < curAnimal.size()) {
									curAnimal.feed();
									land[r-1][c].animal = null;	
								}
							}
							else {
								ArrayList<Resource> res = land[r-1][c].territory.resources();
								boolean stopSearch = false;

								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
								
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}
						
						else if (r != land.length-1)
						{
							if (curAnimal.carnivore() && land[r+1][c].animal != null) {
								curAnimal.hurt(land[r+1][c].animal);
								if (land[r+1][c].animal.health() <= 0 && land[r+1][c].animal.size() < curAnimal.size()) {
									curAnimal.feed();
									land[r+1][c].animal = null;	
								}
							}
							else {
								ArrayList<Resource> res = land[r+1][c].territory.resources();
								boolean stopSearch = false;

								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
								
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}
						
						else if (c != 0)
						{
							if (curAnimal.carnivore() && land[r][c-1].animal != null) {
								curAnimal.hurt(land[r][c-1].animal);
								if (land[r][c-1].animal.health() <= 0 && land[r][c-1].animal.size() < curAnimal.size()) {
									curAnimal.feed();
									land[r][c-1].animal = null;	
								}
							}
							else {
								ArrayList<Resource> res = land[r][c-1].territory.resources();
								boolean stopSearch = false;

								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
								
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}
						else if (c != land[0].length-1)
						{
							if (curAnimal.carnivore() && land[r][c+1].animal != null) {
								curAnimal.hurt(land[r][c+1].animal);
								if (land[r][c+1].animal.health() <= 0 && land[r][c+1].animal.size() < curAnimal.size()) {
									curAnimal.feed();
									land[r][c+1].animal = null;	
								}
							}
							else {
								ArrayList<Resource> res = land[r][c+1].territory.resources();
								boolean stopSearch = false;

								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
								
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}
					}
					
					if (curAnimal.thirst() < 60)
					{
						if (r != 0 && land[r-1][c].territory.resources.contains("waterResource"))
						{
							curAnimal.drink();
							land[r-1][c].territory.resources.remove(land[r-1][c].territory.resources.indexOf("waterResource"));
						}
						
						else if (r != land.length-1 && land[r+1][c].territory.resources.contains("waterResource"))
						{
							curAnimal.drink();
							land[r+1][c].territory.resources.remove(land[r+1][c].territory.resources.indexOf("waterResource"));
						}
						
						else if (c != 0 && land[r][c-1].territory.resources.contains("waterResource"))
						{
							curAnimal.drink();
							land[r][c-1].territory.resources.remove(land[r][c-1].territory.resources.indexOf("waterResource"));
						}
						
						else if (c != land[0].length-1 && land[r][c+1].territory.resources.contains("waterResource"))
						{
							curAnimal.drink();
							land[r][c+1].territory.resources.remove(land[r][c+1].territory.resources.indexOf("waterResource"));
						}
					}
					
					if (curAnimal != null && !(curAnimal.moveList == null || curAnimal.moveList.isEmpty())) {
						String dir = curAnimal.moveList.remove(0);
						
						if (dir.equals("up"))
							nextGen[r][c-1].add(curAnimal);
						else if (dir.equals("down"))
							nextGen[r][c+1].add(curAnimal);
						else if (dir.equals("left"))
							nextGen[r-1][c].add(curAnimal);
						else if (dir.equals("right"))
							nextGen[r+1][c].add(curAnimal);
					}
					else {					
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
							nextGen[r + upDown][c + leftRight].add(curAnimal);
						else if (!nextGen[r + upDown][c].occupied() && !nextGen[r + upDown][c].territory.ground.equals("water"))
							nextGen[r + upDown][c].add(curAnimal);
						else if (!nextGen[r][c + leftRight].occupied() && !nextGen[r][c + leftRight].territory.ground.equals("water"))
							nextGen[r][c + leftRight].add(curAnimal);
						else if (!nextGen[r][c].occupied())
							nextGen[r][c + leftRight].add(curAnimal);
					}
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

					if (baby != null && emptyRow != -1 && Math.random() > 0.7) {
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
