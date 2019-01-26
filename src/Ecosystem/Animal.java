package Ecosystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public abstract class Animal {

	//fields

	protected int health, size, speed, age, lifespan, hunger, thirst, mateTimer, damage;
	/**
	 * represents the outward appearance of an animal
	 */
	public BufferedImage appearance = null;

	protected boolean land, water, carnivore, herbivore;

	protected String gender, type;
	protected ArrayList<String> bodyParts = new ArrayList<String>();

	/**
	 * Represents an animal's list of moves it will take to move 
	 */
	public ArrayList<String> moveList;

	protected static final int maxStat = 100;

	// constructors
	/** main constructor for an animal
	 * an animal is an organism that can move around the ecosystem, and eat and drink to maintain it's health
	 * animals also interact with others through hunting and mating
	 * this constructor initializes all of the necessary variables for an effective animal
	 * @param type - String representing what class or kind of animal it is
	 * @param size - integer representing how large the animal it is
	 * @param speed - integer representing the speed of the animal's movement
	 * @param lifespan - integer for how long an animal may live
	 * @param gender - String for whether the animal is male or female
	 */
	public Animal (String type, int size, int speed, int lifespan, String gender) {	// new animal
		this.health = maxStat;
		this.hunger = maxStat;
		this.thirst = maxStat;
		this.type = type;
		this.size = size;
		this.speed = speed;
		this.age = 0;
		this.calculateDamage();
		this.lifespan = lifespan;

		this.mateTimer = 4;
		
		this.gender = gender;

		bodyParts = new ArrayList<String>();

		//bodyParts.add("cellular");

		bodyParts.add("cellular");

		
		appearance = makeImage(bodyParts); // make it access package

		moveList = new ArrayList<String>();
		
	}

	/**copy constructor for an animal
	 * @param animal represents the instance that is being duplicated
	 */
	public Animal(Animal animal) {	// copy constructor
		this.health = animal.health;
		this.hunger = animal.hunger;
		this.thirst = animal.thirst;
		this.type = animal.type;
		this.size = animal.size;
		this.speed = animal.speed;
		this.age = animal.age;
		this.lifespan = animal.lifespan;

		this.carnivore = animal.carnivore;
		this.herbivore = animal.herbivore;
		
		if (Math.random() > 0.5)
			this.gender = "Male";
		else
			this.gender = "Female";

		bodyParts = animal.bodyParts;
		
		appearance = makeImage(bodyParts);// make it access package

		moveList = animal.moveList;
	}

	/** constructor for newborn animals from mating
	 * @param animal is the parent animal 
	 * @param birth represents if a birth occurred
	 */
	public Animal(Animal animal, boolean birth) {	// construct newborn
		this(animal);
		this.health = maxStat;
		this.hunger = maxStat;
		this.thirst = maxStat;
		this.size = 10;
		this.age = 0;
		this.mateTimer = 4;
		this.bodyParts = animal.bodyParts;
		this.appearance = makeImage(bodyParts);
	}

	// methods
	/**changes the appearance of an animal
	 * @param imageName is the string representing the name of the new image
	 */
	public void updateImage(String imageName) {
		try {
			appearance = ImageIO.read(new File(imageName));
		}
		catch (Exception ex) {
		}
	}

	/**
	 * updates an animal's thirst and health when it drinks
	 */
	public void drink(){
		thirst += Math.random() * 5 + 8;
		thirst = Math.min(thirst, maxStat);
		health += 20;
		health = Math.min(health, maxStat);	
	}

	/**
	 * @param mate represents the mate of an animal
	 * @return a new instance of an animal created by the mating process
	 */
	public abstract Animal mate(Animal mate);
	
	public abstract Animal mate(Animal animal, boolean b);
	
	/**
	 * represents the standard amount of damage an animal can inflict to other animals
	 */
	public void calculateDamage() {
		damage = 2;
	}

	/**
	 * @param mate represents the animal that this animal is attempting to mate with
	 * @return a boolean representing if mating is viable
	 */
	public boolean canMate(Animal mate) {
		return this.type().equals(mate.type()) && !this.gender.equals(mate.gender) && mateTimer == 0 && mate.mateTimer == 0;
	}

	/** decreases an animal's health
	 * @param damage represents amount that health decreases
	 */
	public void injured (int damage) {
		health -= damage;
	}
	
	/** does damage to another animal
	 * @param prey represents the animal being hunted
	 */
	public void hurt (Animal prey) {
		prey.injured(damage);
	}
	
	/**
	 * updates an animal's status 
	 * includes an animal's aging process, getting hungry, and getting thirsty
	 * also hurts the animal if conditions are not met
	 */
	public void update () {
		// aging
		age++;

		// update health
		if (Math.random() < .5) {
			this.hunger -= 3;
			if (Math.random() < .25) 
				this.thirst -= 2;
		}
		
		if (thirst < 25) {
			this.health -= 5;
		}

		if (hunger < 25) {
			this.health -= 5;
		}
		
		hunger = Math.max(0, hunger);
		thirst = Math.max(0, thirst);
		health = Math.max(0, health);
		
		// update aging conditions
		double progress = (age + 0.0) / (lifespan + 0.0);
		if (progress < 0.3) {
			speed += (int) (Math.random() * 3);
			size += (int) (Math.random() * 3);
		}
		else if (progress > 0.9) {
			speed -= (int) (Math.random() * 2 + 1);
		}
		else if (progress > 0.6) {
			speed -= (int) (Math.random() * 2);
		}

		mateTimer--;
		//System.out.println(this.hunger + ", " + this.thirst + ", " + this.health);
	}

	// accessors
	/**
	 * @return integer representing animal's health
	 */
	public int health() {
		return this.health;
	}
	
	/**
	 * @return integer representing an animal's age
	 */
	public int age() {
		return this.age;
	}
	
	/**
	 * @return integer representing animal's lifespan
	 */
	public int lifespan() {
		return this.lifespan;
	}

	/**
	 * @return integer representing the size of the animal
	 */
	public int size() {
		return this.size;
	}

	/**
	 * @return integer representing the animal's speed
	 */
	public int speed() {
		return this.speed;
	}

	/**
	 * @return String representing animal's gender
	 */
	public String gender() {
		return this.gender;
	}

	/**
	 * @return boolean representing if animal can walk on land
	 */
	public boolean land() {
		return land;
	}
	
	/**
	 * @return boolean representing if animal can walk on water
	 */
	public boolean water() {
		return water;
	}

	/**
	 * @return String representing the type of an animal
	 */
	public String type() {
		return type;
	}

	/**
	 * @return integer representing the thirst level of an animal
	 */
	public int thirst() {
		return thirst;
	}
	
	/**
	 * @return integer representing the hunger level of an animal
	 */
	public int hunger() {
		return hunger;
	}
	
	/**
	 * method that updates an animal's health and hunger levels after eating
	 */
	public void feed() {
		hunger += Math.random() * 25 + 15;
		hunger = Math.min(hunger, maxStat);
		health +=  Math.random() * 33 + 5;
		health = Math.min(health, maxStat);
		
	}
	
	/**
	 * @return a boolean representing whether an animal hunts 
	 */
	public boolean carnivore() {
		return carnivore;
	}
	
	/**
	 * @return boolean representing if an animal is an herbivore
	 */
	public boolean herbivore() {
		return herbivore;
	}
	
	/**
	 * @param strings represents a list of body parts that make up the animal's image
	 * @return a Buffered Image that draws out the complete animal
	 */
	public BufferedImage makeImage(ArrayList<String> strings)
    {
    	BufferedImage[] input = new BufferedImage[strings.size()];
        for ( int i = 0; i < input.length; i++ ) {
            try {
                File f = new File( "Summative Graphics\\Animals\\" + strings.get(i) + ".png" );
                input[i] = ImageIO.read( f );
            }
            catch ( Exception x ) {
                // Complain if there is any problem loading 
                // an input image.
                x.printStackTrace();
            }
        }
         
        // Create the output image.
        // It is the same size as the first
        // input image.  I had to specify the type
        // so it would keep it's transparency.
        BufferedImage output = new BufferedImage( 
                input[0].getWidth()*2, 
                input[0].getHeight()*2, 
                BufferedImage.TYPE_INT_ARGB );
         
        // Draw each of the input images onto the output image.
        Graphics g = output.getGraphics();
        int headIndex = 1;
        for ( int i = 0; i < input.length; i++ ) {
        	//checks if certain strings are contained in the file name to draw in appropriate location
        	if (strings.get(i).contains("Bird\\Leg"))
        	{
        		g.drawImage( input[i],  2*input[0].getWidth()/5 , 6*input[0].getHeight()/5, input[0].getWidth()/2 , 3*input[0].getHeight()/4,  null);
        		g.drawImage( input[i],  input[0].getWidth() , input[0].getHeight(), input[0].getWidth()/2 , 3*input[0].getHeight()/4,  null);
        	}
        	else if (strings.get(i).contains("leg"))
        	{
        		g.drawImage( input[i],  3*input[0].getWidth()/11 , input[0].getHeight(), input[0].getWidth()/2 , 3*input[0].getHeight()/4,  null);
        		g.drawImage( input[i],  6*input[0].getWidth()/7 , 6*input[0].getHeight()/7, input[0].getWidth()/2 , 3*input[0].getHeight()/4,  null);
           		g.drawImage( input[i],  2*input[0].getWidth()/5 , 6*input[0].getHeight()/5, input[0].getWidth()/2 , 3*input[0].getHeight()/4,  null);
        		g.drawImage( input[i],  input[0].getWidth() , input[0].getHeight(), input[0].getWidth()/2 , 3*input[0].getHeight()/4,  null);
        	}
        	else if (strings.get(i).contains("head"))
        		headIndex = i;	
        	else if (strings.get(i).contains("tail"))
        		g.drawImage( input[i],  input[0].getWidth() , 3*input[0].getHeight()/4, input[0].getWidth()/2 , input[i].getHeight(),  null);            	
        }
        g.drawImage( input[0], input[0].getWidth()/2 , input[0].getHeight()/2, input[0].getWidth() , input[0].getHeight(), null );
    	
        return output;           
    }
}
