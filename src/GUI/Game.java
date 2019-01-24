package GUI;

import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.event.*;
import Ecosystem.*;


public class Game extends JInternalFrame implements MouseListener, KeyListener, ActionListener, ChangeListener {

	int w, h;
	Landscape landscape;
	static Timer t;
	gameBtn settings, addAnimal;
	addAnimalsPanel animalMenu;
	DrawArea board;
	boolean settingOn = false;
	boolean simMode = false;
	Settings settingsMenu;
	
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
		
		settingsMenu = new Settings(this.w*4/5);
		settingsMenu.setBounds(this.getSize().height/24, this.getSize().height/12, settingsMenu.getWidth(), settingsMenu.getHeight()); 
		settingsMenu.addMouseListener(this);
		settingsMenu.setVisible(false);
		
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
	
		settings.addActionListener(this);
		addAnimal.addActionListener(this);
		settingsMenu.simSpeed.addMouseListener(this);
		settingsMenu.RR.addMouseListener(this);
		
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
		add(board);	
	}
	
	class DrawArea extends JPanel									// drawarea class for drawing landscape
	{
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
	
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void pauseTimer() {
		t.stop();
	}
	
	public void resumeTimer() {
		t.start();
	}
	
	public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(t))
        {
            landscape.advance();
           // System.out.println("_____________________________________");
        }
		
        else if (e.getSource().equals(settings))
        {
            settingOn = !settingOn;

            	settingsMenu.setVisible(settingOn);
        }
        
        else if (e.getSource().equals(addAnimal))
        {
        	animalMenu.setVisible(!animalMenu.isVisible());
        }
        
        else if (e.getSource().equals(animalMenu.addAmphibian))
        {
        	landscape.populate(new Amphibian("amphibian", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addCanine))
        {
        	landscape.populate(new Canine("canine", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addCellular))
        {
        	landscape.populate(new Cellular("cellular", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addFeline))
        {
        	landscape.populate(new Feline("feline", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addFish))
        {
        	landscape.populate(new Fish("fish", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addHooved))
        {
        	landscape.populate(new Hooved("hooved", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addLizard))
        {
        	landscape.populate(new Lizard("lizard", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addPrimate))
        {
        	landscape.populate(new Primate("primate", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addRaptor))
        {
        	landscape.populate(new Raptor("raptor", 1,1,1, "male", true));
        }
        
        else if (e.getSource().equals(animalMenu.addRodent))
        {
        	landscape.populate(new Rodent("rodent", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addSmallBird))
        {
        	landscape.populate(new smallBird("smallBird", 1,1,1, "male", true));
        }
        
        else if (e.getSource().equals(animalMenu.addSnake))
        {
        	landscape.populate(new Snake("snake", 1,1,1, "male"));
        }
        
        else if (e.getSource().equals(animalMenu.addTurtle))
        {
        	landscape.populate(new Turtle("turtle", 1,1,1, "male"));
        }
        
        if (!simMode)
		{
			settings.setVisible(true);
			addAnimal.setVisible(true);
		}
        
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

	public void changeMode() {
		simMode = !simMode;
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
        }
		
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		Game game = new Game(1200);
		Animal animal = new Mammal("Summative Graphics\\Animals\\animal2.png", 1, 1, 50, "Male");
		game.landscape.populate(animal);
		game.setVisible(true);
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(game);

		frame.setSize(1200, 1000);
		frame.setVisible(true);
		
	}
}