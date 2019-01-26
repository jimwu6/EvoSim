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

/**
 * Defines a 2D Tile array with animals and the ground type both defined, as well as qualities of weather.
 * <p>
 * The Landscape class defines the main grid used for the game, housing both animals and territory, the latter of which defines the ground and resources on the tile.
 * Interactions between the factors of the environment are coordinated in methods within this class, as well as environmental factors such as weather that can act directly on interactions.
 */
/**
 * @author Jim
 *
 */
public class Landscape {

	//fields
	/**
	 * 2D array of Tiles to represent the overall landscape
	 */
	public Tile[][] land;
	private String weather = "none";
	private boolean natDisToggle = false, disaster, userWeather = true;
	private double disRate = .005;

	private int weatherCnt = 0, weatherLimit = 3;
	
	private int temperature = 50, tempDir = 1;
	
	/**
	 * Creates a default landscape of a size 100x120. 
	 * The landscape tiling is based of a text file in the project folder, which contains the ordered list of tile names to be read.
	 * Plants are then randomly generated with moderate probability and general variety.
	 */
	public Landscape() {
		
		//initialize variables
		land = new Tile[100][120];
		String[] tiles = null;
		int counter = 0;

		//load in preset landscape from text file
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

		// generate plants randomly on grass tiles using loops
		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				if (land[r][c].territory.ground.equals("grass"))		//check for grass
				{
					int chance = (int) (Math.random() * 401);		//random generation

					if(chance == 91 || chance == 92)
						land[r][c].territory.grow("shrub", 44);
					else if (chance == 93)
						land[r][c].territory.grow("tree", 111);
				}
			}
		}
	}

	/**Sets landscape's disaster rate
	 * @param r represents the new disaster rate of the landscape
	 */
	public void setDRate(double r) {
		disRate = r ;
	}
	
	/**
	 * Draws the floor, animal, plant, and resources of each tile from the ground up in a grid formation.
	 * Each tile is a 10x10 square on the component object being drawn on; animals and plants are drawn larger for visibility.
	 * 
	 * @param g The graphics object used to draw
	 */
	public void show(Graphics g) {

		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				// draw ground and plant --> maybe move this somewhere?
				try 
				{	
					//draws image for each tile on ground
					g.drawImage(land[r][c].territory.groundImg, c * 10, r * 10, 10, 10, null);

				} 
				catch (NullPointerException e) {}
			}
		}

		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				// draw plant, resource, and animal if they exist on the given tile
				try {
					if (land[r][c].territory.plant != null)
						g.drawImage(land[r][c].territory.plantImg, c * 10 -land[r][c].territory.plant.size()/2 , r * 10 - land[r][c].territory.plant.size()/2, land[r][c].territory.plant.size(), land[r][c].territory.plant.size(), null);
					
					if (land[r][c].territory.hasResource())
					{
						for (int x = 0; x < land[r][c].territory.resources().size(); x++) 
							g.drawImage(land[r][c].territory.resources().get(x).resourceImage, c * 10 - 15, r * 10 - 15, 30, 30, null);

					}

					if (land[r][c].occupied())
					{				
							g.drawImage(land[r][c].animal.appearance, c * 10 - 20, r * 10 - 20, 60, 60, null);					
					}
					
				} catch (NullPointerException e) {}
			}
		}
	}

	/**
	 * Populates the grid, tile by tile, with relatively low probability of populating an individual tile.
	 * 
	 * @param animal Desired animal to populate the grid
	 */
	public void populate(Animal animal) {

		//for every cell in the grid, place true or false value --> true is more likely with a higher density
		for (int r = 0 ; r < land.length ; r++)
		{
			for (int c = 0 ; c < land[0].length ; c++)
			{
				//randomly populates 1 in 2 hundred squares
				if (Math.floor(Math.random () * 200) < 1)
				{
					//case by case generation of animals depending on user input
					Animal newAnimal = null;
					if (animal.type().equals("cellular") && (land[r][c].territory.ground.equals("water") || land[r][c].territory.ground.equals("grass") && Math.random() < .04))
						newAnimal = new Cellular((Cellular) animal);
					else if (animal.type().equals("mammal") && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Mammal((Mammal) animal);
					else if (animal.type().equals("fish") && land[r][c].territory.ground.equals("water"))
						newAnimal = new Fish((Fish) animal);
					else if (animal.type().equals("amphibian") )
						newAnimal = new Amphibian((Amphibian) animal);
					else if (animal.type().equals("bird") )
						newAnimal = new Bird((Bird) animal);
					else if (animal.type().equals("reptile")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Reptile((Reptile) animal);
					else if (animal.type().equals("canine")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Canine((Canine) animal);
					else if (animal.type().equals("feline")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Feline((Feline) animal);
					else if (animal.type().equals("hooved")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Hooved((Hooved) animal);
					else if (animal.type().equals("lizard")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Lizard((Lizard) animal);
					else if (animal.type().equals("primate")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Primate((Primate) animal);
					else if (animal.type().equals("raptor"))
						newAnimal = new Raptor((Raptor) animal);
					else if (animal.type().equals("rodent")&& !land[r][c].territory.ground.equals("water") )
						newAnimal = new Rodent((Rodent) animal);
					else if (animal.type().equals("smallBird") )
						newAnimal = new smallBird((smallBird) animal);
					else if (animal.type().equals("snake") && !land[r][c].territory.ground.equals("water"))
						newAnimal = new Snake((Snake) animal);
					else if (animal.type().equals("turtle") )
						newAnimal = new Turtle((Turtle) animal);
					land[r][c].add(newAnimal);
				}
			}
		}
	}

	/**
	 * Populates the grid with a variety of animals that are equally probable.
	 */
	public void populate() {
		for (int r = 0 ; r < land.length ; r++)
		{
			for (int c = 0 ; c < land[0].length ; c++)
			{
				//random generation
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

	/**
	 * Creates a set of instructions for an animal to reach the desired object. 
	 * The set appears as an ArrayList with the commands "up", "down", "left", or "right" to denote the direction in which the animal should move 1 index.
	 * Helper method for findResource and findAnimal for animals to determine optimal move sets.
	 * 
	 * @param vis The array of coordinates of the optimal path to the desired animal or resource
	 * @param wantX The desired x coordinate of the animal or resource
	 * @param wantY The desired y coordinate of the animal or resource
	 * 
	 * @return A string ArrayList containing the correct movement pattern in chronological order to reach the object
	 */
	public ArrayList<String> makeInstructions(Pair[][] vis, int wantX, int wantY) {
		ArrayList<String> instruct = new ArrayList<String>();

		// keep generating instructions if the parent one is not reached
		if (wantX != -1) {
			Pair cur = vis[wantX][wantY];
			int curx = wantX, cury = wantY;

			while (cur.x != -1) {
				// add up, down, left, or right depending on where the animal should move
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
				
				// go back up the path
				curx = cur.x;
				cury = cur.y;
				// get the next pair
				cur = vis[curx][cury];
			}
			
			// remove the first instruction since the resource can be accessed on an adjcanet square
			if (!instruct.isEmpty())
				instruct.remove(instruct.size()-1);
		}
		
		// return the instructions
		return instruct;
	}

	/**
	 * Finds the path to the nearest desired resource and returns an ordered ArrayList of instructions to arrive there.
	 * A breadth first search (BFS) is conducted from the position of the animal until the resource is found, upon which the set of instructions is created.
	 * 
	 * @param r The row of the animal
	 * @param c The column of the animal
	 * @param resource The resource that is desired
	 * @param a The animal in the given position
	 * @return A string ArrayList containing the correct movement pattern in chronological order to reach the object
	 */
	public ArrayList<String> findResource(int r, int c, Resource resource, Animal a) {
		
		// Pair for each coordinate that tracks the parent coordinates
		// vis --> visited
		Pair vis[][] = new Pair[land.length][land[0].length];

		// instantiate pair objects inside of vis
		for (int row = 0; row < land.length; row++)
			for (int col = 0; col < land[0].length; col++)
				vis[row][col] = new Pair();

		// set the parent of the starting coordinates as -1, -1 (to indicate it is the starting point)
		vis[r][c].x = -1;
		vis[r][c].y = -1;
		vis[r][c].visited = true;

		// create queue for that tracks x and y coordinates of the bfs
		Queue<Integer> q = new LinkedList<Integer>();
		// add the starting coordinates to the queue
		q.add(r);
		q.add(c);
		
		// start coordinates of the resource it will look for as -1, 1
		int wantX = -1, wantY = -1;

		// keep running BFS while there are still nodes to look for
		while (!q.isEmpty()) {
			
			// boolean to check if the algorithm should keep running
			boolean keepSearching = true;

			// assign curx and cury to be the current coordinates from queue
			int curx = q.poll();
			int cury = q.poll();

			// only check the animal wanted if coordinates are not the original
			if (curx != -1) {
				ArrayList<Resource> res = land[curx][cury].territory.resources();
				// if the resource is found
				if (res.contains(resource)){
					// assign the wanted resource's coordinates to the wanted variables
					wantX = curx;
					wantY = cury;
					// remove every thing in the queue because there is no need to search anymore
					while (!q.isEmpty()) {
						q.poll();
					}
					// stop searching
					keepSearching = false;
				}
			}

			if (keepSearching) {
				
				// for each side, check if the target square has not been visited and if
					// the target square contains the resource wanted or the animal can traverse it 
					// (e.g. a primate can't go through water but it can go on land)
				
				// left side
				if (cury-1 >= 0) {
					if (!vis[curx][cury-1].visited
							&& (land[curx][cury-1].territory.resources().contains(resource) 
									|| (a.land() && !land[curx][cury-1].territory.ground.equals("water"))
									|| (a.water() && land[curx][cury-1].territory.ground.equals("water")))) {
						// push the new coordinates to visit
						q.add(curx);
						q.add(cury-1);
						// set the target coordinate's parents to the current coordinates
						vis[curx][cury-1].x = curx;
						vis[curx][cury-1].y = cury;
						// mark target coordinate as visited
						vis[curx][cury-1].visited = true;
					}
				}
				// right side
				if (cury+1 < land[0].length) {
					if (!vis[curx][cury+1].visited 
							&& (land[curx][cury+1].territory.resources().contains(resource) 
									|| (a.land() && !land[curx][cury+1].territory.ground.equals("water"))
									|| (a.water() && land[curx][cury+1].territory.ground.equals("water")))) {
						// push the new coordinates to visit
						q.add(curx);
						q.add(cury+1);
						// set the target coordinate's parents to the current coordinates
						vis[curx][cury+1].x = curx;
						vis[curx][cury+1].y = cury;
						// mark target coordinate as visited
						vis[curx][cury+1].visited = true;
					}
				}
				// up
				if (curx-1 >= 0) {
					if (!vis[curx-1][cury].visited 
							&& (land[curx-1][cury].territory.resources().contains(resource) 
									|| (a.land() && !land[curx-1][cury].territory.ground.equals("water"))
									|| (a.water() && land[curx-1][cury].territory.ground.equals("water")))) {
						// push the new coordinates to visit
						q.add(curx-1);
						q.add(cury);
						// set the target coordinate's parents to the current coordinates
						vis[curx-1][cury].x = curx;
						vis[curx-1][cury].y = cury;
						// mark target coordinate as visited
						vis[curx-1][cury].visited = true;
					}
				}
				// down
				if (curx+1 < land.length) {
					if (!vis[curx+1][cury].visited 
							&& (land[curx+1][cury].territory.resources().contains(resource) 
									|| (a.land() && !land[curx+1][cury].territory.ground.equals("water"))
									|| (a.water() && land[curx+1][cury].territory.ground.equals("water")))) {
						// push the new coordinates to visit
						q.add(curx+1);
						q.add(cury);
						// set the target coordinate's parents to the current coordinates
						vis[curx+1][cury].x = curx;
						vis[curx+1][cury].y = cury;
						// mark target coordinate as visited
						vis[curx+1][cury].visited = true;
					}
				}
			}
		}

		// use a helper method to generate instructions through the parent nodes found 
		return makeInstructions(vis, wantX, wantY);
	}

	/**
	 * Finds the path to the nearest desired animal and returns an ordered ArrayList of instructions to arrive there.
	 * A breadth first search is conducted from the position of the animal until another animal is found, upon which the set of instructions is created.
	 * 
	 * @param r The row of the current animal
	 * @param c The column of the current animal
	 * @param animal The animal that is desired
	 * @param a The animal in the given position
	 * @return A string ArrayList containing the correct movement pattern in chronological order to reach the object
	 */
	public ArrayList<String> findAnimal(int r, int c, Animal animal, Animal a) {

		// Pair for each coordinate that tracks the parent coordinates
		// vis --> visited
		Pair vis[][] = new Pair[land.length][land[0].length];
		
		// instantiate pair objects inside of vis
		for (int row = 0; row < land.length; row++)
			for (int col = 0; col < land[0].length; col++)
				vis[row][col] = new Pair();
		
		// set the parent of the starting coordinates as -1, -1 (to indicate it is the starting point)
		vis[r][c].x = -1;
		vis[r][c].y = -1;
		vis[r][c].visited = true;
		
		// create queue for that tracks x and y coordinates of the bfs
		Queue<Integer> q = new LinkedList<Integer>();
		// add the starting coordinates to the queue
		q.add(r);
		q.add(c);
		
		// create queue for that tracks x and y coordinates of the bfs
		int wantX = -1, wantY = -1;

		// keep running BFS while there are still nodes to look for
		while (!q.isEmpty()) {

			// boolean to check if the algorithm should keep running
			boolean keepSearching = true;

			// assign curx and cury to be the current coordinates from queue
			int curx = q.poll();
			int cury = q.poll();

			// only check the animal wanted if coordinates are not the original
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

			// for each side, check if the target square has not been visited and if
			// the target square contains the resource wanted or the animal can traverse it 
			// (e.g. a primate can't go through water but it can go on land)
			
			if (keepSearching) {
				// left
				if (cury-1 > 0) {
					if (!vis[curx][cury-1].visited 
							&& (land[curx][cury-1].animal.type().equals(animal.type())
									|| (a.land() && !land[curx][cury-1].territory.ground.equals("water"))
									|| (a.water() && land[curx][cury-1].territory.ground.equals("water")))) {
						// push the new coordinates to visit
						q.add(curx);
						q.add(cury-1);
						// set the target coordinate's parents to the current coordinates
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
						// push the new coordinates to visit
						q.add(curx);
						q.add(cury+1);
						// set the target coordinate's parents to the current coordinates
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
						// push the new coordinates to visit
						q.add(curx-1);
						q.add(cury);
						// set the target coordinate's parents to the current coordinates
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
						// push the new coordinates to visit
						q.add(curx+1);
						q.add(cury);
						// set the target coordinate's parents to the current coordinates
						vis[curx+1][cury].x = curx;
						vis[curx+1][cury].y = cury;
						vis[curx+1][cury].visited = true;
					}
				}
			}
		}

		// use a helper method to generate instructions through the parent nodes found 
		return makeInstructions(vis, wantX, wantY);
	}
	
	
	/**
	 * Updates the weather and temperature if the user has not chosen to control the settings.
	 * Uses ticks to determine when to update the temperature and weather.
	 * Random numbers are used as probability to choose a type of weather.
	 */
	public void updateWeatherTemp() {

		// every other tick, change the temperature
		if (weatherCnt % 2 == 0) {
			// 15% chance the the weather will start going the other way (e.g. add to subtract)
			if (Math.random() < 0.15) 
				tempDir = -tempDir;
			temperature += tempDir;
		}
		
		// only change weather if user did not change it
		if (!userWeather) {
			// keep adding ticks until it reaches limit
			if (weatherCnt < weatherLimit) {	
				weatherCnt++;
			}
			else {
				// get number to determine probability
				// 0.05 percent chance for all weather types
				double randomWeather = Math.random();
				if (randomWeather <= 0.05) {
					weather = "cloud";
				}
				else if (randomWeather <= 0.10) {
					weather = "sun";
				}
				else if (randomWeather <= 0.15) {
					weather = "rain";
				}
				else  {
					weather = "none";
				}
				System.out.println(weather);
				weatherCnt = 0;
			}
		}
	}
	

	/**
	 * Sets the next generation of the landscape after all factors are considered.
	 * The changes in animal placement and conditions, as well as resources and weather impacts on the ecosystem, are all orchestrated and the new landscape replaces the original landscape.
	 * Animals must also mate and actively move with purpose.
	 */
	public void advance() {

		//checks if a natural disaster will occur based on the disaster rate
		if (natDisToggle && Math.random() < disRate)
		{
			disaster = true;
		}
		else
			disaster = false;
		
		if (natDisToggle && weather.equals("cloud")  && Math.random() < disRate)		//if cloudy, chance for a natural disaster doubles
		{
			disaster = true;
		}
		else
			disaster = false;
		
		//update temperature
		updateWeatherTemp();
		
		// set up nextGen array
		Tile nextGen[][] = new Tile [land.length][land[0].length];

		//maks a duplicate array of tiles to update into
		for (int r = 0; r < land.length; r++) {
			for (int c = 0; c < land[0].length; c++)
			{
				nextGen[r][c] = new Tile(land[r][c]);
				nextGen[r][c].animal = null;
			}
		}
		
		// coordinate animal movement with loops --> each animal in array
		for (int r = 0; r < land.length; r++)
		{
			for (int c = 0; c < land[0].length; c++)
			{
				//set curAnimal to be the current animal
				Animal curAnimal = land[r][c].animal;
				
				//if possible, release resources
				if (land[r][c].planted() || land[r][c].territory.ground.equals("water")) {
					land[r][c].territory.release();
					if (weather.equals("rain")&& land[r][c].territory.ground.equals("water") && Math.random() < .5)		//check if it's raining --> water squares may double again
						land[r][c].territory.release();
					if (weather.equals("sun") && land[r][c].planted() && Math.random() < .5)		//check if sunny --> sun squares may double again
						land[r][c].territory.release();
				}

				//check if an animal should be updated --> there's an animal and the health is high enough and lifespan not yet met and disaster hasn't struck/animal survived
				if (land[r][c].occupied() && curAnimal.health() >= 1 && (!disaster || Math.random() < .66)
						&& (curAnimal.age() < curAnimal.lifespan() || Math.random() < 0.7 - 0.2 * (curAnimal.age() - curAnimal.lifespan()))) 
				{	
					//sets movelist based on animal's needs
					if (curAnimal != null && (curAnimal.moveList == null || curAnimal.moveList.isEmpty())) {
						if (curAnimal.thirst() < 66 ) {
							//if thirsty, target water
							curAnimal.moveList = findResource(r, c, new Resource ("waterResource"), curAnimal); 
							System.out.println("target water");
						}
						else if (curAnimal.hunger() < 60 && curAnimal.land()&& curAnimal.herbivore()) { 
							//if hungry and on land, target fruit and herbivore
							System.out.println("Target fruit");
							curAnimal.moveList = findResource(r, c, new Resource ("fruit"), curAnimal); 
						}
						else if (curAnimal.hunger() < 60 && curAnimal.water()&& curAnimal.herbivore()) {
							//if hungry and on water and herbivore, target seaweed
							curAnimal.moveList = findResource(r, c, new Resource ("seaweed"), curAnimal);
						}
						
					}
					
					// update for each animal type
					String type = curAnimal.type();
					if (type.equals("rodent"))
						((Rodent)curAnimal).update();
					else if (type.equals("canine"))
						((Canine)curAnimal).update();
					else if (type.equals("feline"))
						((Feline)curAnimal).update();
					else if (type.equals("hooved"))
						((Hooved)curAnimal).update();
					else if (type.equals("primate"))
						((Primate)curAnimal).update();
					else if (type.equals("fish"))
						((Fish)curAnimal).update();
					else if (type.equals("amphibian"))
						((Amphibian)curAnimal).update();
					else if (type.equals("lizard"))
						((Lizard)curAnimal).update();
					else if (type.equals("snake"))
						((Snake)curAnimal).update();
					else if (type.equals("turtle"))
						((Turtle)curAnimal).update();
					else if (type.equals("smallBird"))
						((smallBird)curAnimal).update();
					else if (type.equals("raptor"))
						((Raptor)curAnimal).update();
					else if (type.equals("cellular"))
						((Cellular)curAnimal).update();
					
					//if animal is hungry, will either chase after food if carnivore or move towards plant food --> four cases for each boundary 
					if (curAnimal.hunger() < 60)
					{
						if (r != 0)
						{
							//hunts for food by hurting and eating if possible
							if (curAnimal.carnivore() && land[r-1][c].animal != null && !land[r][c].animal.type().equals(land[r-1][c].animal.type())) {
								curAnimal.hurt(land[r-1][c].animal);
								if (land[r-1][c].animal.health() <= 0 && land[r-1][c].animal.size() < curAnimal.size()) {
									curAnimal.feed();
									System.out.println("eat");
									land[r-1][c].animal = null;	
								}
							}
							//searches for plant if not a carnivore
							else {
								ArrayList<Resource> res = land[r-1][c].territory.resources();
								boolean stopSearch = false;

								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
								
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).getName().equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}

						else if (r != land.length-1)
						{
							//case for carnivores
							if (curAnimal.carnivore() && land[r+1][c].animal != null) {
								curAnimal.hurt(land[r+1][c].animal);
								if (land[r+1][c].animal.health() <= 0 && land[r+1][c].animal.size() < curAnimal.size() && !land[r][c].animal.type().equals(land[r+1][c].animal.type())) {
									curAnimal.feed();
									land[r+1][c].animal = null;	
								}
							}
							//case for herbivores
							else {
								ArrayList<Resource> res = land[r+1][c].territory.resources();
								boolean stopSearch = false;
	
								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
									
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).getName().equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}
							
						else if (c != 0)
						{
							//case for carnivores
							if (curAnimal.carnivore() && land[r][c-1].animal != null) {
								curAnimal.hurt(land[r][c-1].animal);
								if (land[r][c-1].animal.health() <= 0 && land[r][c-1].animal.size() < curAnimal.size()&& !land[r][c].animal.type().equals(land[r][c-1].animal.type())) {
									curAnimal.feed();
									land[r][c-1].animal = null;	
								}
							}
							else {
								//case for herbivores
								ArrayList<Resource> res = land[r][c-1].territory.resources();
								boolean stopSearch = false;
	
								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
									
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).getName().equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;				
									}
								}
							}
						}
						else if (c != land[0].length-1)
						{
							//case for carnivores
							if (curAnimal.carnivore() && land[r][c+1].animal != null && !land[r][c].animal.type().equals(land[r][c+1].animal.type())) {
								curAnimal.hurt(land[r][c+1].animal);
								if (land[r][c+1].animal.health() <= 0 && land[r][c+1].animal.size() < curAnimal.size()) {
									curAnimal.feed();
									land[r][c+1].animal = null;	
								}
	
							}
							else {
								//case for herbivores
								ArrayList<Resource> res = land[r][c+1].territory.resources();
								boolean stopSearch = false;
	
								String resourceWant = "fruit";
								if (curAnimal.water()) 
									resourceWant = "seaweed";
									
								for (int i = 0; i < res.size() && !stopSearch; i++) {
									if (res.get(i).getName().equals(resourceWant)) {
										curAnimal.feed();
										res.remove(i);
										stopSearch = true;
									}
								}
							}
						}
					}
					
					//if thirsty, check sides to see if water is nearby --> if yes, drink water
					if (curAnimal.thirst() < 50)
					{
						if (r != 0 && land[r-1][c].territory.resources.contains(new Resource("waterResource")))
						{
							System.out.println("drink");
							curAnimal.drink();
							land[r-1][c].territory.resources.remove(land[r-1][c].territory.resources.indexOf(new Resource("waterResource")));
						}
						
						else if (r != land.length-1 && land[r+1][c].territory.resources.contains(new Resource ("waterResource")))
						{
							curAnimal.drink();
							System.out.println("drink");
							land[r+1][c].territory.resources.remove(land[r+1][c].territory.resources.indexOf(new Resource ("waterResource")));
							
						}
						
						else if (c != 0 && land[r][c-1].territory.resources.contains(new Resource ("waterResource")))
						{
							curAnimal.drink();
							System.out.println("drink");
							land[r][c-1].territory.resources.remove(land[r][c-1].territory.resources.indexOf(new Resource ("waterResource")));
							
						}
						
						else if (c != land[0].length-1 && land[r][c+1].territory.resources.contains(new Resource ("waterResource")))
						{
							curAnimal.drink();
							System.out.println("drink");
							land[r][c+1].territory.resources.remove(land[r][c+1].territory.resources.indexOf(new Resource ("waterResource")));
							
						}
					}
					
					//if temperature is extreme, damage the animal by 2
					if (temperature >= 75 || temperature <= 25)
					{
						land[r][c].animal.injured(2);
					}
					
					//motion for animal based on move list if it exists
					if (curAnimal != null && !(curAnimal.moveList == null || curAnimal.moveList.isEmpty())) {
						String dir = curAnimal.moveList.remove(0);
						
						if (dir.equals("left"))
							nextGen[r][c-1].add(curAnimal);
						else if (dir.equals("right"))
							nextGen[r][c+1].add(curAnimal);
						else if (dir.equals("up"))
							nextGen[r-1][c].add(curAnimal);
						else if (dir.equals("down"))
							nextGen[r+1][c].add(curAnimal);
					}
					//otherwise, randomly generate animal's movement
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
	
						//move to designated square
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
			
		// coordinate animal mating
		for (int r = 0; r < nextGen.length; r++)
		{
			for (int c = 0; c < nextGen[0].length; c++)
			{
				//check if square is occupied; if so, check for mating
				if (nextGen[r][c].occupied())
				{
					Animal baby = null;
					int emptyRow = -1, emptyCol = -1;

					if (r + 1 < nextGen.length && nextGen[r + 1][c].occupied())
						baby = nextGen[r][c].animal.mate(nextGen[r + 1][c].animal, (nextGen[r][c].animal.land() && land[r][c].territory.ground.equals("land") || (nextGen[r][c].animal.water() && land[r][c].territory.ground.equals("water"))));

					int compare = r - 1;				
					while (c + 1 < nextGen[0].length && compare <= r + 1 && compare > -1 && compare < nextGen.length) {
						if (nextGen[compare][c + 1].occupied() && baby == null) {
							baby = nextGen[r][c].animal.mate(nextGen[compare][c + 1].animal, (nextGen[r][c].animal.land() && land[r][c].territory.ground.equals("land") || (nextGen[r][c].animal.water() && land[r][c].territory.ground.equals("water"))));
						}
						
						compare++;
					}

					for (int row = r - 1; row < r + 2; row++) {
						for (int col = c - 1; col < c + 2; col++) {
							if (row > -1 && row < land.length - 1 && col > -1 && col < land[0].length - 1
									&& !nextGen[row][col].occupied()) {
								emptyRow = row;
								emptyCol = col;
							}
						}
					}
					
					if (baby != null && emptyRow != -1) {
						nextGen[emptyRow][emptyCol].add(baby);
						System.out.println(baby.type() + " made " + emptyRow + ", " + emptyCol);
					}
				}

			}
		}

		land = nextGen; 
	}

	/**
	 * Sets the new rate for resource production on the landscape based on the passed parameter.
	 * 
	 * @param rate The desired rate of resource creation
	 */
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
	
	/**Sets the weather of the landscape based on the passed parameter
	 * @param w represents the string describing the weather
	 */
	public void setWeather(String w) {
		weather = w;
	}
	
	/**Sets whether or not landscape will have natural disasters
	 * @param nd represents whether or not natural disasters will occur
	 */
	public void natDisSet(boolean nd){
		natDisToggle = nd;
	}
	
	/**
	 * @return String representing landscape's current weather
	 */
	public String weather() {
		return weather;
	}
	
	/**
	 * @return boolean representing if disaster is possible
	 */
	public boolean disaster() {
		return disaster;
	}
	
	/**
	 * @return landscape's temperature
	 */
	public int temperature() {
		return temperature;
	}
	
	/**
	 * Sets the new temperature of the landscape based on the passed parameter.
	 * 
	 * @param temp The desired temperature of the environment
	 */
	public void updateTemp(int temp)
	{
		temperature = temp;
	}

//	public static void main(String[] args) {
//		Landscape landscape = new Landscape();
//		Animal animal = new Mammal("Summative Graphics\\Animals\\animal2.png", 1, 1, 1, "Male");
//		landscape.populate(animal);
//	}
}

/**
 * Defines a single pair of Row-Major coordinates for a 2D array in the form [x][y].
 * <p>
 * Rectangular coordinates may be stored, as well as whether the position has been visited or not in the course of traversing the landscape.
 */
class Pair {

	public int x, y;
	public boolean visited = false;

	/**
	 * Constructs a set of x and y coordinates centered at the "first" element on the array (top left), [0][0].
	 */
	public Pair() {
		this.x = 0;
		this.y = 0;
		visited = false;
	}

	/**
	 * Constructs a set of x and y coordinates for a point based on the passed values.
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}


}
