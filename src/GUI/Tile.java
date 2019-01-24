package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import javax.imageio.ImageIO;

import Ecosystem.*;

/**
 * Defines a single combination of an animal and a territory.
 * <p>
 * The Tile class represents the smallest unit of the game's landscape, where individual animals, plants, and resources are contained on top of the defined floor.
 * The tile uses the Territory class to define the ground; all other fields are not required to be initialized.
 */
public class Tile {

    public Animal animal = null;
    public Territory territory;

    /**
     * Creates a new tile with the passed floor pattern by itself.
     * 
     * @param land Shortcut file name of the ground image
     */
    public Tile(String land) {
        territory = new Territory(land);
    }

	/**
	 * Creates a new tile with a plant on the underlying ground image, as well as the initial size of the plant.
	 * 
	 * @param land Shortcut file name of the ground image
	 * @param plant Shortcut file name of the plant
	 * @param size Desired size of the plant
	 */
	public Tile(String land, String plant, int size) {
        territory = new Territory(land, plant, size);
    }
    
    /**
     * Copy constructor to create an identical tile to the passed tile.
     * 
     * @param copy Tile to be duplicated
     */
    public Tile(Tile copy) {
    	this.animal = copy.animal;
    	this.territory = copy.territory;
    }
    
    /**
     * Sets the animal on the tile to the passed animal.
     * 
     * @param animal Desired animal on the tile
     */
    public void add(Animal animal) {
    	this.animal = animal;
    }
    
    /**
     * Checks whether a plant is on the tile.
     * 
     * @return If a plant has been set
     */
    public boolean planted() {
    	return territory.plant != null;
    }
    
    /**
     * Checks whether an animal is on the tile.
     * 
     * @return If an animal has been set
     */
    public boolean occupied() {
    	return animal != null;
    }
}