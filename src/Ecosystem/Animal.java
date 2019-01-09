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
	
	public void drink(){
		
	}
	public void mate(Animal mate){
		
	}
	public Animal birth(){
		
	}
	public void flee (Animal predator){
		
	}
	public void seek (Animal animal){
		
	}
	
	public void injured (Integer damage) {
		health -= damage;
	}
}

interface Herbivore {
	public void feed (String food);
}

interface Carnivore {
	public void feed (Animal prey);
	public void chase (Animal prey);
}
