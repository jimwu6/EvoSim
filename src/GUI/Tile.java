package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import javax.imageio.ImageIO;

import Ecosystem.*;

public class Tile {

    public ArrayList<Animal> animals;
    public Territory territory;

    public Tile(String land) {
        animals = new ArrayList<Animal>();
        territory = new Territory(land);
    }
    
    public Tile(String land, String plant) {
    	animals = new ArrayList<Animal>();
        territory = new Territory(land, plant);
    }

}