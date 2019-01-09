package Ecosystem;

import java.awt.Image;
import java.util.*;

public abstract class Animal {
	//fields
	public int health, size, speed, gender;
	protected int lifespan, hunger, thirst;
	public Image appearance;
	protected boolean controlled;
	protected ArrayList<Disease> disease;
	
	public Animal () {
		this.health = 100;
	}
	
	public abstract void drink();
	public abstract void mate(Animal mate);
	public abstract Animal birth();
	public abstract void flee (Animal predator);
	public abstract void seek (Animal animal);
	public abstract void hurt (Integer damage);
}

interface Herbivore {
	public void feed (String food);
}

interface Carnivore {
	public void feed (Animal prey);
	public void chase (Animal prey);
}
