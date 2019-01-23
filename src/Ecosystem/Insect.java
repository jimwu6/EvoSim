package Ecosystem;

public class Insect extends Animal{
	protected boolean flight;
	public boolean poisonous, venomous;
	
	public Insect(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		flight = false;
		poisonous = false;
		venomous = false;
	}
	
	public Insect(Insect i) {
		super(i);
		this.flight = i.flight;
		this.poisonous = i.poisonous;
		this.venomous = i.venomous;
	}
	
	public Insect(Insect i, boolean canMate) {
		super(i, canMate);
		this.flight = i.flight;
		this.poisonous = i.poisonous;
		this.venomous = i.venomous;
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 1;
			return new Insect(this, this.canMate(mate));
		}
		
		return null;
	}
}
