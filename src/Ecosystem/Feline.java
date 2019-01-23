package Ecosystem;

public class Feline extends Mammal implements Carnivore{

	public int fangSize, claw;
	
	public Feline(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 20;
		furLength = 30;
		intelligence = 30;
		fangSize = 30;
		claw = 30;
	}
	
	public Feline(Feline feline) {
		super(feline);
		this.fangSize = feline.fangSize;
		this.claw = feline.claw;
	}
	
	public Feline(Feline feline, boolean canMate) {
		super(feline, canMate);
		this.fangSize = feline.fangSize;
		this.claw = feline.claw;
	}
	
	public Feline(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Feline(this, this.canMate(mate));
		}
		
		return a;
	}
	
	public void update() {
		super.update();
		double rand = Math.random();
		
		if (rand > 0.7) {
			fangSize++;
			claw++;
		}
	}
	
	public void stalk(){
		
	}
	public void chase(Animal prey){
		
	}
	public void feed(Animal prey){
		
	}
}
