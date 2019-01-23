package Ecosystem;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	
<<<<<<< HEAD
	protected int heal;
	protected String name;
=======
	int heal;
	String name;
	public Image resourceImage = null;
>>>>>>> 4cdd01bdd11c2cf92dffd0f66b4e2024f3d70dad
	
	public Resource(String n) {
		name = n;
		
		try
		{
			resourceImage = ImageIO.read(new File("Summative Graphics\\" + name + ".png"));
		}
		catch (IOException e)
		{
			System.out.println("error resource");
		}
	}
	
	public int heal() {
		return heal;
	}
	
	public String getName() {
		return name;
	}
}