package Ecosystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public abstract class Animal {

	//fields

	protected int health, size, speed, age, lifespan, hunger, thirst, mateTimer;
	public BufferedImage appearance = null;

	protected boolean controlled, land, water;

	protected String gender, type;
	ArrayList<String> bodyParts;

	protected ArrayList<Disease> disease;
	public ArrayList<String> moveList;

	protected static final int maxStat = 100;

	// constructors
	public Animal (String type, int size, int speed, int lifespan, String gender) {	// new animal
		this.health = maxStat;
		this.hunger = maxStat;
		this.thirst = maxStat;
		this.type = type;
		this.size = size;
		this.speed = speed;
		this.age = 0;
		this.lifespan = lifespan;

		this.mateTimer = 4;
		
		this.gender = gender;

		appearance = makeImage(bodyParts);// make it access package

		disease = new ArrayList<Disease>();
		moveList = new ArrayList<String>();
	}

	public Animal(Animal animal) {	// copy constructor
		this.health = animal.health;
		this.hunger = animal.hunger;
		this.thirst = animal.thirst;
		this.type = animal.type;
		this.size = animal.size;
		this.speed = animal.speed;
		this.age = animal.age;
		this.lifespan = animal.lifespan;

		if (Math.random() > 0.5)
			this.gender = "Male";
		else
			this.gender = "Female";

		try {
			appearance = ImageIO.read(new File(type));
		}
		catch (Exception ex) {}

		disease = animal.disease;
		moveList = animal.moveList;
	}

	public Animal(Animal animal, boolean birth) {	// construct newborn
		this(animal);
		this.health = maxStat;
		this.hunger = maxStat;
		this.thirst = maxStat;
		this.size = 10;
		this.age = 0;
		this.mateTimer = 4;
		this.appearance = makeImage(bodyParts);
	}

	// methods
	public void updateImage(String imageName) {
		try {
			appearance = ImageIO.read(new File(imageName));
		}
		catch (Exception ex) {}
	}

	protected void drink(){
		thirst += Math.random() * 5 + 8;
		thirst = Math.min(thirst, maxStat);
	}

	public abstract Animal mate(Animal mate);

	public boolean canMate(Animal mate) {
		return this.type.equals(mate.type) && !this.gender.equals(mate.gender) && mateTimer == 0 && mate.mateTimer == 0;
	}

	public void mateUpdate() {
		if (mateTimer > 0)
			mateTimer --;
	}
	
	public void flee (Animal predator) {

	}

	public void seek (Animal animal){

	}

	public void injured (Integer damage) {
		health -= damage;
	}

	public boolean controlled() {
		return controlled;
	}
	
	public void underControl() {
		controlled = true;
	}
	
	public void update () {

		// aging
		age++;

		// update health
		if (Math.random() < .5) {		// randomly gets thirsty
			this.thirst -= 3;
			this.hunger -= 2;
		}

		if (thirst < 35) {
			this.health -= 5;
		}

		if (hunger < 35) {
			this.health -= 5;
		}

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

		//System.out.println(this.hunger + ", " + this.thirst + ", " + this.health);

	}

	// accessors
	public int health() {
		return this.health;
	}

	public int size() {
		return this.size;
	}

	public int speed() {
		return this.health;
	}

	public String gender() {
		return this.gender;
	}

	public boolean land() {
		return land;
	}
	
	public boolean water() {
		return water;
	}

	public String type() {
		return type;
	}

	public BufferedImage makeImage(ArrayList<String> strings)
    {
    	BufferedImage[] input = new BufferedImage[3];
        for ( int i = 0; i < input.length; i++ ) {
            try {
                File f = new File( "Summative Graphics\\" + strings[i] + ".png" );
                input[i] = ImageIO.read( f );
            }
            catch ( IOException x ) {
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
         
        // Draw each of the input images onto the
        // output image.
        Graphics g = output.getGraphics();
        for ( int i = 0; i < input.length; i++ ) {
            g.drawImage( input[i], i*55 , 0, null );
        }
        
        return output;           
    }
}

interface Herbivore {
	public void feed (String food);
}

interface Carnivore {
	public void feed (Animal prey);
	public void chase (Animal prey);
}
