package Ecosystem;

public class Amphibian extends Animal implements Herbivore, Carnivore {
	protected int hydration;
	public boolean poisonous, venomous;
	
	public Amphibian(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		hydration = 100;
		poisonous = false;
		venomous = false;
	}
	
	public Amphibian(Amphibian a) {
		super (a);
		hydration = a.hydration;
		poisonous = a.poisonous;
		venomous = a.venomous;
	}
	
	public Amphibian(Amphibian a, boolean canMate) {
		super(a, canMate);
		hydration = a.hydration;
		poisonous = a.poisonous;
		venomous = a.venomous;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			return new Amphibian(this, this.canMate(mate));
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
