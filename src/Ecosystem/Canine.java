package Ecosystem;

public class Canine extends Mammal implements Carnivore{

	public int fangSize, claw;

	public Canine(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 15;
		furLength = 30;
		intelligence = 35;
		fangSize = 5;
		claw = 5;
	}
	
	public Canine(Canine canine) {
		super(canine);
		this.fangSize = canine.fangSize;
		this.claw = canine.claw;
	}
	
	public Canine(Canine canine, boolean canMate) {
		super(canine, canMate);
		this.fangSize = canine.fangSize;
		this.claw = canine.claw;
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Canine(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void findPack(Canine pack){
		
	}

	public void chase(Animal prey){
		
	}

	public void feed(Animal prey){
		
	}
}
