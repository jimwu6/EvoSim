package GUI;

import java.util.*;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.event.*;
import Ecosystem.*;


/**
 * Defines a game screen that runs the automated simulation and receives user input to change various conditions.
 * <p>
 * The game class initializes a screen based on a JInternalFrame to be accepted by the Display class, showing the environment with animals and their interactions with it.
 * A timer orchestrates the generational advance of the environment and populations, responding to both internal and external factors.
 * 
 */
/**
 * @author r_li
 *
 */
@SuppressWarnings("serial")
public class Game extends JInternalFrame implements MouseListener, KeyListener, ActionListener {

	int w, h;
	Landscape landscape;
	static Timer t;
	gameBtn settings, addAnimal;
	addAnimalsPanel animalMenu;
	DrawArea board;
	boolean settingOn = false;
	boolean simMode = false;
	Settings settingsMenu;
	Image cold = null, hot = null, sun = null, cloud = null, rain = null, dis = null;
	JPanel coldP, hotP, sunP, cloudP, rainP, disP;
	
	boolean userWeather = true;
	
	/**
	 * Constructs a Game screen instance with animals and environment based on passed width of screen.
	 * The screen also contains toggle buttons in corners for interaction with user, as to change settings and personalize the game aspects.
	 * 
	 * @param width The width of the screen
	 */
	public Game(int width) {
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		
		landscape = new Landscape();
		t = new Timer(1999, this);
		t.start();
		t.addActionListener(this);
		
		// set size
		w = width;
		h = w * 5 / 6;
		setSize(w, h);
	
		try
		{
			cold = ImageIO.read(new File ("Summative Graphics\\cold.png"));
			hot = ImageIO.read(new File ("Summative Graphics\\hot.png"));
			rain = ImageIO.read(new File ("Summative Graphics\\rain.png"));
			sun = ImageIO.read(new File ("Summative Graphics\\sun.png"));
			cloud = ImageIO.read(new File ("Summative Graphics\\cloud.png"));
			dis = ImageIO.read(new File ("Summative Graphics\\natDis.png"));
		}
		catch (IOException e)
		{
		}
		settingsMenu = new Settings(this.w*4/5);
		settingsMenu.setBounds(this.getSize().height/24, this.getSize().height/12, settingsMenu.getWidth(), settingsMenu.getHeight()); 
		settingsMenu.addMouseListener(this);
		settingsMenu.setVisible(false);
		settingsMenu.sun.addActionListener(this);
		settingsMenu.cloud.addActionListener(this);
		settingsMenu.rain.addActionListener(this);
		settingsMenu.reset.addActionListener(this);
		
		animalMenu = new addAnimalsPanel(this.w*4/5);
		animalMenu.setBounds(this.getSize().height/24, this.getSize().height/12, settingsMenu.getWidth(), settingsMenu.getHeight()); 
		animalMenu.addMouseListener(this);
		animalMenu.setVisible(false);
		
		// create things to add to pane  --------------------- PICK A DIFFERENT BUTTON
		settings = new gameBtn("Summative Graphics\\Menu\\settings.png",this.getSize().height/8, this.getSize().height/8);
		addAnimal = new gameBtn("Summative Graphics\\addAnimal\\ownAnimalButton.png",this.getSize().height/8, this.getSize().height/8);
		addAnimal.setBounds(this.getSize().height, 13 * this.getSize().height/16, this.getSize().height/8, this.getSize().height/8);
		settings.setBounds(this.getSize().height, this.getSize().height/16, this.getSize().height/8, this.getSize().height/8);
		settings.setVisible(false);
		addAnimal.setVisible(false);
	
		coldP = new JPanel();
		cold = cold.getScaledInstance(this.getSize().width, this.getSize().width, Image.SCALE_DEFAULT);
		JLabel coldL = new JLabel(new ImageIcon(cold));
		coldP.add(coldL);
		coldP.setSize(this.getSize().width, this.getSize().height);
		coldP.setVisible(false);
		coldP.setOpaque(false);
		
		hotP = new JPanel();
		hot = hot.getScaledInstance(this.getSize().width, this.getSize().width, Image.SCALE_DEFAULT);
		JLabel hotL = new JLabel(new ImageIcon(hot));
		hotP.add(hotL);
		hotP.setSize(this.getSize().width, this.getSize().height);
		hotP.setVisible(false);
		hotP.setOpaque(false);
		
		sunP = new JPanel();
		sun = sun.getScaledInstance(this.getSize().width, this.getSize().width, Image.SCALE_DEFAULT);
		JLabel sunL = new JLabel(new ImageIcon(sun));
		sunP.add(sunL);
		sunP.setSize(this.getSize().width, this.getSize().height);
		sunP.setVisible(false);
		sunP.setOpaque(false);
		
		cloudP = new JPanel();
		cloud = cloud.getScaledInstance(this.getSize().width, this.getSize().width, Image.SCALE_DEFAULT);
		JLabel cloudL = new JLabel(new ImageIcon(cloud));
		cloudP.add(cloudL);
		cloudP.setSize(this.getSize().width, this.getSize().height);
		cloudP.setVisible(false);
		cloudP.setOpaque(false);
		
		rainP = new JPanel();
		rain = rain.getScaledInstance(this.getSize().width, this.getSize().width, Image.SCALE_DEFAULT);
		JLabel rainL = new JLabel(new ImageIcon(rain));
		rainP.add(rainL);
		rainP.setSize(this.getSize().width, this.getSize().height);
		rainP.setVisible(false);
		rainP.setOpaque(false);
		
		settings.addActionListener(this);
		addAnimal.addActionListener(this);
		settingsMenu.simSpeed.addMouseListener(this);
		settingsMenu.RR.addMouseListener(this);
		settingsMenu.temp.addMouseListener(this);
		settingsMenu.onA.addActionListener(this);
		settingsMenu.onB.addActionListener(this);
		settingsMenu.offA.addActionListener(this);
		settingsMenu.offB.addActionListener(this);
		
		disP = new JPanel();
		dis = dis.getScaledInstance(this.getSize().width, this.getSize().width, Image.SCALE_DEFAULT);
		JLabel disL = new JLabel(new ImageIcon(dis));
		disP.add(disL);
		disP.setSize(this.getSize().width, this.getSize().height);
		disP.setVisible(false);
		disP.setOpaque(false);
		
		for (int x = 0; x < animalMenu.list.size(); x++)
		{
			animalMenu.list.get(x).addActionListener(this);
		}
		
		animalMenu.s1.addActionListener(this);
		animalMenu.s2.addActionListener(this);
		animalMenu.s3.addActionListener(this);
		
		// panels to add
		board = new DrawArea(w, h);
		
		add(settings);
		add(addAnimal);
		add(animalMenu);
		add(settingsMenu);
		add(coldP);
		add(hotP);
		add(sunP);
		add(cloudP);
		add(rainP);
		add(disP);
		add(board);	
	}
	
	/**
	 * Defines a drawing area for the Graphics class to draw items that appear.
	 */
	class DrawArea extends JPanel									// drawarea class for drawing landscape
	{
		/**
		 * Constructs an area for the graphics to appear.
		 * 
		 * @param width The width of the DrawArea board
		 * @param height The height of the DrawArea board
		 */
		public DrawArea (int width, int height)
		{
			// set size 
			this.setPreferredSize(new Dimension (width, height));
		}

		public void paintComponent (Graphics g)
		{
			landscape.show(g);
		}
	}
	
	/**
	 * Stops the game's timer.
	 */
	public void pauseTimer() {
		t.stop();
	}
	
	/**
	 * Starts the game's timer.
	 */
	public void resumeTimer() {
		t.start();
	}
	
	public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(t)) {
            landscape.advance();
          // System.out.println("_____________________________________");
        }
        else if (e.getSource().equals(settings)) {
            settingOn = !settingOn;
            settingsMenu.setVisible(settingOn);
        }
        else if (e.getSource().equals(addAnimal)) {
        	animalMenu.setVisible(!animalMenu.isVisible());
        }
        else if (e.getSource().equals(animalMenu.addAmphibian)) {
        	landscape.populate(new Amphibian("amphibian", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addCanine)) {
        	landscape.populate(new Canine("canine", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addCellular)) {
        	landscape.populate(new Cellular("cellular", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addFeline)) {
        	landscape.populate(new Feline("feline", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addFish)) {
        	landscape.populate(new Fish("fish", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addHooved)) {
        	landscape.populate(new Hooved("hooved", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addLizard)) {
        	landscape.populate(new Lizard("lizard", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addPrimate)) {
        	landscape.populate(new Primate("primate", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addRaptor)) {
        	landscape.populate(new Raptor("raptor", 1, 1, 100000, "male", true));
        }
        else if (e.getSource().equals(animalMenu.addRodent)) {
        	landscape.populate(new Rodent("rodent", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addSmallBird)) {
        	landscape.populate(new smallBird("smallBird", 1, 1, 100000, "male", true));
        }
        else if (e.getSource().equals(animalMenu.addSnake)) {
        	landscape.populate(new Snake("snake", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(animalMenu.addTurtle)) {
        	landscape.populate(new Turtle("turtle", 1, 1, 100000, "male"));
        }
        else if (e.getSource().equals(settingsMenu.sun)) {
        	landscape.weather = "sun";
        	sunP.setVisible(true);
        	cloudP.setVisible(false);
        	rainP.setVisible(false);
        }
        else if (e.getSource().equals(settingsMenu.cloud)) {
        	landscape.weather = "cloud";
        	sunP.setVisible(false);
        	cloudP.setVisible(true);
        	rainP.setVisible(false);
        }
        else if (e.getSource().equals(settingsMenu.rain)) {
        	landscape.weather = "rain";
        	sunP.setVisible(false);
        	cloudP.setVisible(false);
        	rainP.setVisible(true);
        }
        else if (e.getSource().equals(settingsMenu.reset)) {
        	landscape.weather = "none";
        	sunP.setVisible(false);
        	cloudP.setVisible(false);
        	rainP.setVisible(false);
        }
        else if (e.getSource().equals(settingsMenu.onA) || e.getSource().equals(settingsMenu.offB)) {
        	landscape.natDisToggle = false;
        }
        else if (e.getSource().equals(settingsMenu.onB) || e.getSource().equals(settingsMenu.offA)) {
        	landscape.natDisToggle = true;
        }

        else if (e.getSource().equals(settingsMenu.onB) || e.getSource().equals(settingsMenu.offA))
        {
        	landscape.natDisToggle = true;
        }

        else if (e.getSource().equals(settingsMenu.onB) || e.getSource().equals(settingsMenu.offA))
        {
        	landscape.natDisToggle = true;
        }
        
        //scenario one adds many cellular organisms to showcase evolution
        else if (e.getSource().equals(animalMenu.s1))
        {
        	Landscape newLand = new Landscape();
        	newLand.populate((new Cellular("cellular", 1, 1, 100000, "Male")));
        	newLand.populate((new Cellular("cellular", 1, 1, 100000, "Female")));
        	newLand.populate((new Cellular("cellular", 1, 1, 100000, "Female")));
        	landscape = newLand;
        	
        }
        
        //sim 2 shows the impact of natural disaster
        else if (e.getSource().equals(animalMenu.s2))
        {
        	Landscape newLand = new Landscape();
        	newLand.setDRate(.5);
        	newLand.natDisToggle = true;
        	newLand.weather = "cloud";
        	newLand.populate((new Rodent("rodent", 1, 1, 100000, "Male")));
        	newLand.populate((new Raptor("raptor", 1, 1, 100000, "Female", true)));
        	newLand.populate((new Canine("canine", 1, 1, 100000, "Female")));
        	landscape = newLand;
        	
        } 
        
        //scenario 3 shows the predator/prey relationship of rodents and raptors
        else if (e.getSource().equals(animalMenu.s3))
        {
        	Landscape newLand = new Landscape();
        	newLand.weather = "cloud";
        	newLand.populate((new Rodent("rodent", 1, 1, 100000, "Male")));
        	newLand.populate((new Raptor("raptor", 1, 1, 100000, "Female", true)));
        	landscape = newLand;
        	
        }
        
        if (!simMode) {
			settings.setVisible(true);
			addAnimal.setVisible(true);
		}
       
        if (landscape.disaster) {
			disP.setVisible(true);
		} 
        else
        	disP.setVisible(false);
        
        this.repaint();
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_UP) {						// for movement
			
		} 
		else if(keyCode == KeyEvent.VK_LEFT) {
			
		}
		else if(keyCode == KeyEvent.VK_RIGHT) {
			
		}
		else if(keyCode == KeyEvent.VK_DOWN) {
			
		}
		
	}

	/**
	 * Changes the status of the game between simulation and game mode based on the accepted parameter.
	 * 
	 * @param sim If simulation mode was selected
	 */
	public void changeMode(boolean sim) {
		simMode = sim;
	}
	
	public void keyReleased(KeyEvent e) {	// maybe have a boolean thats set false when released for movement
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getSource().equals(settingsMenu.simSpeed))
        {
           	int delay = 4000 - settingsMenu.simSpeed.value()*39;
        	if (delay == 4000)
        		t.stop();
        	else
        	{
        		t.setDelay(delay);
        		t.start();
        	}
        }
		
		else if (e.getSource().equals(settingsMenu.RR))
        {
           	double rate = (settingsMenu.RR.value()) * .001;
        	landscape.updateRR(rate);
        }
		
		else if (e.getSource().equals(settingsMenu.temp))
        {
           	int temp = settingsMenu.temp.value();
        	landscape.updateTemp(temp);
        	if (landscape.temperature <= 25)
            {
            	coldP.setVisible(true);
            	hotP.setVisible(false);
            }
        	
        	else if (landscape.temperature >= 75)
            {
            	coldP.setVisible(false);
            	hotP.setVisible(true);
            }
        	else
            {
            	coldP.setVisible(false);
            	hotP.setVisible(false);
            }
        }		
        
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public static void main(String[] args) {
//		Game game = new Game(1200);
//		Animal animal = new Mammal("Summative Graphics\\Animals\\animal2.png", 1, 1, 50, "Male");
//		game.landscape.populate(animal);
//		game.setVisible(true);
//		
//		JFrame frame = new JFrame();
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(game);
//
//		frame.setSize(1200, 1000);
//		frame.setVisible(true);
//		
//	}
}