package Ecosystem;

public class Turtle extends Reptile{
	private int shellHardness;
	
	public Turtle(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		shellHardness = 50;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;
	}
	
	public Turtle(Turtle t) {
		super (t);
		this.shellHardness = t.shellHardness;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;
	}
	
	public Turtle(Turtle t, boolean canMate) {
		super(t, canMate);
		this.shellHardness = 20;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;
	}

	public Turtle(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.shellHardness = 30;
		type = "turtle";
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Turtle(this, this.canMate(mate));
		}
		
		return a;
	}
	
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	public void update() {
		super.update();
		
		shellHardness += (int) (Math.random() * 4 - 1);
	}
	
	public void withdraw(){
		
	}
	
	public void feed (String plant){
		
	}
}
