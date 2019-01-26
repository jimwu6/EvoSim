package Ecosystem;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Justin
 *class for resourcs that animals may consume
 *they are consumed so animals can maintain good health
 */
public class Resource {
	//fields
	private int heal;
	private String name;
	
	/**
	 * image that shows a graphical representation of the resource 
	 */
	public Image resourceImage = null;
	
	/** creates a new resource that can be eaten or drunk by animals
	 * @param n is the string describing the nature of the resource
	 */
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
	
	/**
	 * @return the value that a resource can heal an animal
	 */
	public int heal() {
		return heal;
	}
	
	/**
	 * @return the name of the resource
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean same = false;
		
		if (o != null && o instanceof Resource) {
			same = this.name.equals(((Resource) o).getName());
		}
		
		return same;
	}
}