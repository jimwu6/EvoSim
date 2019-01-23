package Ecosystem;

public class smallBird extends Bird {

	public int featherVibrance;

	public smallBird(String imageName, int size, int speed, int lifespan, String gender, boolean flight) {
		super(imageName, size, speed, lifespan, gender, flight);
		featherVibrance = 20;
		carnivore = true;
		herbivore = true;
	}

	public smallBird(smallBird bird) {
		super (bird);
		this.featherVibrance = bird.featherVibrance;
	}

	public smallBird(smallBird bird, boolean canMate) {
		super(bird, canMate);
		this.featherVibrance = 20;
	}

	public smallBird(Lizard lizard, boolean canMate) {
		super(lizard, canMate);
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			if (featherVibrance > 35) {
				if (size > 35)
					return new Raptor(this, this.canMate(mate));
				else
					return new Rodent(this, this.canMate(mate));
			}
			
			return new smallBird(this, this.canMate(mate));
		}

		return null;
	}

	public void update() {
		super.update();

		if (Math.random() > 0.8)
			featherVibrance++;
	}

	public void sing(){

	}

	public void feed(String plant){

	}

	public void chase(Animal prey){

	}
	public void feed(Animal prey){

	}
}
