package Ecosystem;

public class Cellular extends Animal {
	public int membrane;
	
	public Cellular(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		membrane = 1;
		herbivore = true;
		carnivore = false;
	}

	public Cellular(Cellular c) {
		super (c);
		membrane = c.membrane;
	}

	public Cellular(Cellular c, boolean canMate) {
		super(c, canMate);
		membrane = 1;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 1;
			if (membrane >= 5 && Math.random() > 0.5) {
				if (this.water())
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
