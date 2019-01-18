package Ecosystem;

import java.awt.Image;
import java.util.ArrayList;

public class Territory{
	Plant plant;
    ArrayList <Resource> resource;
    Image groundImg;
    Image plantImg;

    public Territory() {
        resource = new ArrayList<Resource>();
    }
    
    public void grow() {
    	
    }
    
    public void release() {
    	resource.add(plant.resource);
    }

}
