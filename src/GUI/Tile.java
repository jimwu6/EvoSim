package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import javax.imageio.ImageIO;

import Ecosystem.*;

public class Tile {

    public Animal animal = null;
    public Territory territory;

    public Tile(String land) {
        territory = new Territory(land);
    }
    
    public Tile(String land, String plant) {
        territory = new Territory(land, plant);
    }

    public void add(Animal animal) {
    	this.animal = animal;
    }
    
    public boolean planted() {
    	return territory.plant != null;
    }
    
    public boolean occupied() {
    	return animal != null;
    }
}