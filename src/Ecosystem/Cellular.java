package Ecosystem;

public class Cellular extends Animal {
	public int membrane;
	
	public Cellular(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		membrane = 1;
		herbivore = true;
		carnivore = false;
		water = true;
		land = false;
	}

	public Cellular(Cellular c) {
		super (c);
		membrane = c.membrane;
		herbivore = true;
		carnivore = false;
		water = true;
		land = false;
	}

	public Cellular(Cellular c, boolean canMate) {
		super(c, canMate);
		membrane = 1;
		herbivore = true;
		carnivore = false;
		water = true;
		land = false;
	}

	public Animal mate(Animal mate) {
		return null;
	}
	
	public Animal mate(Animal mate, boolean landSafe) {
		if (this.canMate(mate))
		{
			if (membrane >= 5 && Math.random() > 0.5) {
				if (landSafe)
					return new Fish(this, this.canMate(mate));
				else
					return new Lizard(this, this.canMate(mate));
			}
			return new Cellular(this, this.canMate(mate));
		}
		
		return null;	
	}
	
	public void update() {
		super.update();
		
		if (Math.random() > 0.5)
			membrane++;
	}
}
