package Ecosystem;

public class Lizard extends Reptile {
	public boolean tailDecoy;

	public Lizard(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		tailDecoy = true;
		carnivore = true;
		herbivore = false;
	}

	public Lizard(Lizard i) {
		super(i);
		this.tailDecoy = i.tailDecoy;
	}

	public Lizard(Lizard i, boolean canMate) {
		super(i, canMate);
		this.tailDecoy = i.tailDecoy;
	}

	public Lizard(Amphibian amph, boolean canMate) {
		super(amph);
		this.tailDecoy = true;
	}

	public Lizard(Cellular cellular, boolean canMate) {
		super(cellular);
		this.tailDecoy = true;
	}

	public Lizard(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.tailDecoy = true;
	}

	public Animal mate(Animal mate) {

		Animal a;
		
		if (scaleHardness > 85){
			return new smallBird(this, this.canMate(mate));
		}
		else {
			a = super.mate(mate);

			if (a == null && this.canMate(mate))
			{
				this.mateTimer = 2;
				return new Lizard(this, this.canMate(mate));
			}
		}
		return a;
	}

	public void update() {
		super.update();
	}

	public void visDisplay(){

	}

	public void camouflage(){

	}

	public void decoy(){

	}

}
