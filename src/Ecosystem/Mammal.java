package Ecosystem;

public class Mammal extends Animal {
	protected int furDensity, furLength, hornSize, intelligence;
	
	public Mammal(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName,size, speed, lifespan, gender);
	}
	
	public Mammal(Mammal m) {
		super (m);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
	}
}
