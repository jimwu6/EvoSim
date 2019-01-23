package Ecosystem;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.*;

public class Territory{
	
	public Plant plant = null;
    ArrayList <Resource> resources;
    public String ground;
    public Image groundImg = null, plantImg = null;

    public Territory(String gName) {
        resources = new ArrayList<Resource>();
        ground = gName;
        
        try
		{
			groundImg = ImageIO.read(new File("Summative Graphics\\" + gName + ".png"));
		}
		catch (IOException e)
		{
			System.out.println("error");
		}
    }
    
    public Territory(String gName, String pName) {
    	this(gName);
    	plant = new Plant(pName);

    	try
		{
			plantImg = ImageIO.read(new File("Summative Graphics\\" + pName + ".png"));
		}
		catch (IOException e)
		{
			System.out.println("error");
		}
    }
    
    public void grow(String pName) {
    	if (plant == null) {
	    	plant = new Plant(pName);
	
	    	try
			{
				plantImg = ImageIO.read(new File("Summative Graphics\\" + pName + ".png"));
			}
			catch (IOException e)
			{
				System.out.println("error plant");
			}
    	}
    }
    
    public void release() {
    	resources.add(plant.resource);
    }
    
    public ArrayList<Resource> resources() {
    	return resources;
    }

}
