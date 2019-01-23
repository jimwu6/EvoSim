package Ecosystem;

public class Fish extends Animal implements Herbivore, Carnivore {
	public int scaleHardness;
	public boolean poisonous, luminant;
	
	public Fish(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		scaleHardness = 50;
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
		poisonous = f.poisonous;
		luminant = f.luminant;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Fish(this, this.canMate(mate));
		}
		
		return null;	
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
