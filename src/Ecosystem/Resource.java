package Ecosystem;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	
	int heal;
	String name;
	public Image resourceImage = null;
	
	public Resource(String n) {
		name = n;
		
		if (name != "waterResource")
		{
			try
			{
				resourceImage = ImageIO.read(new File("Summative Graphics\\" + name + ".png"));
			}
			catch (IOException e)
			{
				System.out.println("error resource");
			}
		}
		
	}
	
	public int heal() {
		return heal;
	}
	
	public String getName() {
		return name;
	}
}