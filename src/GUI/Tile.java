package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import javax.imageio.ImageIO;

import Ecosystem.Animal;

public class Tile {

    ArrayList<Animal> animals;
    String habitat, decor = null;
    Image visual = null, decorPic = null;

    public Tile(String h) {
        animals = new ArrayList<Animal>();
        habitat = h;
        
        try
		{
			visual = ImageIO.read(new File("Summative Graphics\\" + habitat + ".png"));
		}
		catch (IOException e)
		{
		}
    }
    
    public Tile(String h, String d) {
        this(h);
        decor = d;
        try
		{
			decorPic = ImageIO.read(new File("Summative Graphics\\" + decor + ".png"));
		}
		catch (IOException e)
		{
		}
        
    }

}