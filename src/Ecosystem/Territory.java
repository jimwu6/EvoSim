package Ecosystem;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.*;

/**
 * This class represents a small unit of land.
 * This consists of the ground and plants, as well as resources of the land.
 * The Territory class is ultimately platform upon which animals interact.
 */
public class Territory{
	
	//fields
	/**
	 * This field represents plant that inhabits a territory.
	 */
	public Plant plant = null;
    /**
     * This field is a list of all the resources that are currently on the territory.
     */
    public ArrayList <Resource> resources;
    /**
     * This field is a string describing the ground type.
     */
    public String ground;
    /**
     * This image is an image for the ground and the plant's visual representation.
     */
    public Image groundImg = null, plantImg = null;
    /**
     * This field is the rate describing how often resources are generated.
     */
    double rRate = .014;
    
    /**This constructor creates a new territory, or an individual block of land.
     * It contains resources and a plant; this one creates a territory with no plant initially.
     * @param gName represents the type of land being built.
     */
    public Territory(String gName) {
        resources = new ArrayList<Resource>();
        ground = gName;
        
        //load in image
        try
		{
			groundImg = ImageIO.read(new File("Summative Graphics\\" + gName + ".png"));
		}
		catch (IOException e)
		{
			System.out.println("error");
		}
    }
    
    /**This constructor creates a new territory, but with a plant on it. 
     * @param gName represents the type of ground.
     * @param pName is a string for the type of plant.
     * @param size is the size of the plant on the territory.
     */
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
    
    /** This is a method used to add a new plant to a territory if it is empty.
     * @param pName is the plant type that is being added.
     * @param size is the size of the plant.
     */
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
    
    /**The release method is a method that releases resources on the territory.
     * It checks if there are resources present and if the appropriate conditions are met to release resources.
     */
    public void release() {
    	//checks for plant 
    	if (plant != null && resources.size() < 5)
    	{
    		//adds resource for fruit randomly if met
    		if (Math.random() < rRate)
    			resources.add(new Resource("fruit"));
    	}
    	
    	//checks for water
    	if (ground.equals("water") && resources.size() < 6)
    	{
    		//randomly adds seaweed, also adds water if not enough resources yet
    		if (Math.random() < (rRate/70))
    			resources.add(new Resource("seaweed"));
    		
    		if (ground.equals("water") && resources.size() < 4)
    		resources.add(new Resource("waterResource"));    	
    	}
    }
    
    /** The release method accepts a boolean and adds a fruit to the resources.
     * @param t is boolean indicating whether a release of a fruit is needed.
     */
    public void release(boolean t) {
    	resources.add(new Resource("fruit"));
    }
    
    /**
     * @return a boolan value showing if the territory has resources on it.
     */
    public boolean hasResource() {
    	return resources != null;
    }
    
    /**
     * @return the list of resources.
     */
    public ArrayList<Resource> resources() {
    	return resources;
    }
    
    /** This method changes the rate of resource drops.
     * @param rate is the new desired rate of change.
     */
    public void changeRate(double rate) {
    	rRate = rate;
    }

}
