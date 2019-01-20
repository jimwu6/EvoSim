package Ecosystem;

public class Mammal extends Animal {
	protected int furDensity, furLength, hornSize, intelligence;
	
	public Mammal(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName,size, speed, lifespan, gender);
	}
	
}
