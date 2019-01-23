package Ecosystem;

public class Cellular extends Animal {
	public boolean terrestrial;
	public String membrane;
	
	public Cellular(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		terrestrial = false;
		membrane = "Thin";
	}
	
	public Cellular(Cellular c) {
		super (c);
		terrestrial = c.terrestrial;
		membrane = c.membrane;
	}
	
	public Cellular(Cellular c, boolean canMate) {
		super(c, canMate);
		terrestrial = c.terrestrial;
		membrane = c.membrane;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 1;
			return new Cellular(this, this.canMate(mate));
		}
		
		return null;	
	}
}
