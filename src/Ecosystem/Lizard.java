package Ecosystem;

public class Lizard extends Reptile implements Carnivore{
	public boolean tailDecoy;
	
	public Lizard(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		tailDecoy = false;

	}
	
	public Lizard(Lizard i) {
		super(i);
		this.tailDecoy = i.tailDecoy;
	}
	
	public Lizard(Lizard i, boolean canMate) {
		super(i, canMate);
		this.tailDecoy = i.tailDecoy;
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			return new Lizard(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void visDisplay(){
		
	}

	public void camouflage(){
		
	}
	
	public void decoy(){
		
	}
	
	public void chase(Animal prey){
		
	}
	
	public void feed (Animal prey){
		
	}
}
