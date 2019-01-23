package Ecosystem;

public class Turtle extends Reptile implements Herbivore{
	private int shellHardness;
	
	public Turtle(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		shellHardness = 50;
	}
	
	public Turtle(Turtle t) {
		super (t);
		this.shellHardness = t.shellHardness;
	}
	
	public Turtle(Turtle t, boolean canMate) {
		super(t, canMate);
		this.shellHardness = t.shellHardness;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Turtle(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void withdraw(){
		
	}
	
	public void feed (String plant){
		
	}
}
