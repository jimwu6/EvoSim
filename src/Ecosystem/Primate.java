package Ecosystem;

public class Primate extends Mammal implements Carnivore, Herbivore{
	public int toolStrength;
	
	public Primate(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 30;
		furLength = 15;
		intelligence = 50;
	}
	
	public Primate(Primate primate) {
		super(primate);
		this.toolStrength = primate.toolStrength;
		
	}
	
	public Primate(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
	}
	
	public Primate(Primate primate, boolean canMate) {
		super(primate, canMate);
		this.toolStrength = primate.toolStrength;
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Primate(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void buildTool(){
		
	}
	
	public void feed(String plant){
		
	}
	
	public void chase(Animal prey){
		
	}
	
	public void feed(Animal prey){
		
	}
}
