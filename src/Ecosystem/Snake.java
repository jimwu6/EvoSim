package Ecosystem;

public class Snake extends Reptile{
	public boolean venomous, hooded;
	
	public Snake(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		venomous = false;
		hooded = false;
	}
	
	public Snake(Snake s) {
		super (s);
		this.venomous = s.venomous;
		this.hooded = s.hooded;
	}
	
	public Snake(Snake s, boolean canMate) {
		super(s, canMate);
		this.venomous = Math.random() > 0.9;
		this.hooded = Math.random() > 0.7? true : false;
	}

	public Snake(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.venomous = Math.random() > 0.9;
		this.hooded = Math.random() > 0.7? true : false;
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 2;
			return new Snake(this, this.canMate(mate));
		}
		
		return a;
	}
	
	public void update() {
		super.update();
		
		
	}
	
	public void stalk(){
		
	}
	
	public void hoodDisplay(){
		
	}
	
	public void chase(Animal prey){
		
	}
	
	public void feed(Animal prey){
		
	}
}
