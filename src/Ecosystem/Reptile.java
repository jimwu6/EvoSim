package Ecosystem;

public class Reptile extends Animal {
	protected int scaleHardness, fangSize, biteStrength;
	
	public Reptile(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		scaleHardness = 50;
		fangSize = 30;
		biteStrength = 50;
	}
	
	public Reptile(Reptile r) {
		super (r);
		this.scaleHardness = r.scaleHardness;
		this.fangSize = r.fangSize;
		this.biteStrength = r.biteStrength;
	}
	
	public Reptile(Reptile r, boolean canMate) {
		super(r, canMate);
		this.scaleHardness = r.scaleHardness;
		this.fangSize = r.fangSize;
		this.biteStrength = r.biteStrength;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Reptile(this, this.canMate(mate));
		}
		
		return null;	
	}
}
