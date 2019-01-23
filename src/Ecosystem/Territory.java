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
    double rRate = .04;
    
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
    
    public Territory(String gName, String pName, int size) {
    	this(gName);
    	plant = new Plant(pName, size);

    	try
		{
			plantImg = ImageIO.read(new File("Summative Graphics\\" + pName + ".png"));
		}
		catch (IOException e)
		{
			System.out.println("error");
		}
    }
    
    public ArrayList<Resource> resourceList()
    {
    	return resources;
    }
    
    public void grow(String pName, int size) {
    	if (plant == null) {
	    	plant = new Plant(pName, size);
	
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
    	if (plant != null && resources.size() < 5)
    	{
    		if (Math.random() < rRate)
    			resources.add(new Resource("fruit"));
    	}
    	
    	if (ground.equals("water") && resources.size() < 6)
    	{
    		if (Math.random() < (rRate/70))
    			resources.add(new Resource("seaweed"));
    		
    		if (ground.equals("water") && resources.size() < 4)
    		resources.add(new Resource("waterResource"));    	
    	}
    }
    
    public boolean hasResource() {
    	return resources != null;
    }
    
    public ArrayList<Resource> resources() {
    	return resources;
    }
    
    public void changeRate(double rate) {
    	rRate = rate;
    }

}
