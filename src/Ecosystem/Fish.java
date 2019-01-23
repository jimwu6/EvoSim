package Ecosystem;

public class Fish extends Animal implements Herbivore, Carnivore {
	public int scaleHardness;
	public boolean poisonous, luminant;
	
	public Fish(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		scaleHardness = 20;
		poisonous = false;
		luminant = false;
	}
	
	public Fish(Fish f) {
		super (f);
		scaleHardness = f.scaleHardness;
		poisonous = f.poisonous;
		luminant = f.luminant;
	}
	
	public Fish(Fish f, boolean canMate) {
		super(f, canMate);
		scaleHardness = f.scaleHardness;
		poisonous = f.poisonous? f.poisonous : Math.random() > 0.9;
		luminant = Math.random() > 0.9? !f.luminant : f.luminant;
	}

	public Fish(Cellular cellular, boolean canMate) {
		super("fish", 10, 20, 50 + (int) (Math.random() * 21 - 10), cellular.gender());
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			
			if (this.scaleHardness > 50 && Math.random() > 0.97 && this.poisonous)
				return new Amphibian(this, this.canMate(mate));
			return new Fish(this, this.canMate(mate));
		}
		return null;	
	}
	
	public void update() {
		super.update();
		double rand = Math.random();
		
		if (rand > 0.7)
			scaleHardness += 4;
		else if (rand > 0.4)
			scaleHardness -= 4;
		
	}

	@Override
	public void feed(Animal prey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chase(Animal prey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feed(String food) {
		// TODO Auto-generated method stub
		
	}
}
