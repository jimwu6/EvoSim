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
		poisonous = Math.random() > 0.93;
		venomous = a.venomous;
	}

	public Amphibian(Fish fish, boolean canMate) {
		super("frog", fish.size(), fish.speed(), fish.lifespan(), fish.gender());
		hydration = 100;
		poisonous = fish.poisonous;
		venomous = Math.random() > 0.8;
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			
			if (Math.random() > 0.9 && this.poisonous)
				return new Lizard(this, this.canMate(mate));
			return new Amphibian(this, this.canMate(mate));
		}
		
		return null;	
	}

	public void update() {
		super.update();
		hydration--;
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
