package GUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;

import Ecosystem.*;

/**
 * The Display class represents the class containing and harmonizing all the components of the game.
 * <p>
 * Each of the GUI components of the game are assembled into a user event-based game experience.
 * The user may traverse to panels for instructions, credits, and the various game modes as well.
 * The game is collectively named "EvoSim".
 */
@SuppressWarnings("serial")
public class Display extends JFrame implements MouseListener, ActionListener{
	Game game;
	MainMenu menu;
	int w,h;
	boolean selected = false;
	
	/**
	 * Constructs each of the game components and layers them into a single JFrame to be displayed as a GUI.
	 * User interactions on each of the screens will set other screens as visible or invisible based on choice.
	 * Events occurring on each of the respective screens will be interpreted by the component classes that construct each screen.
	 * 
	 * @param width The width of the desired screen
	 */
	public Display(int width)
	{
		// set size
		w = width;
		h = w * 5 / 6;
		setSize(w, h);
				
		game = new Game(this.getSize().width);
		game.pauseTimer();
		menu = new MainMenu(this.getSize().width);
		
		menu.simMode.addActionListener(this);
		menu.gameMode.addActionListener(this);
		
		Animal cell = new Cellular("cellular", 1, 1, 10000, "Female");
		Animal cell2 = new Cellular ("cellular", 1, 1, 10000, "Male");
		game.landscape.populate(cell);
		game.landscape.populate(cell2);
		
		
		//setContentPane(game);
		setContentPane(menu);
		
	}
/*
	class drawArea extends JPanel {

    	public drawArea (int width, int height)
		{
			// set size 
			this.setPreferredSize(new Dimension (width, height));
		}

		public void paintComponent (Graphics g)
		{			
			Color green = new Color (0, 255, 0);
			g.setColor(green);
			g.fillRect(0, 0, 700, 700);
			
		}
    }
    */

	 public void actionPerformed(ActionEvent e) {
		 if (e.getSource().equals(menu.simMode))
         {
          	  game.changeMode(true);
           	  selected = true;
           	  menu.setVisible(false);
           	  game.setVisible(true);
           	  setContentPane(game);
           	  game.resumeTimer();
         }   
		 
		 if (e.getSource().equals(menu.gameMode))
         {
          	  game.changeMode(false);
           	  selected = true;
           	  menu.setVisible(false);
           	  game.setVisible(true);
           	  setContentPane(game);
           	  game.resumeTimer();
         }   
		 
		 this.repaint();
	 }
	 
//    class Advance implements ActionListener {
//    	
//        Landscape landscape;
//        
//        public Advance(Landscape l) {
//        	this.landscape = l;
//        }
//        
//        public void actionPerformed(ActionEvent e) {     	
//        	landscape.advance();
//        	repaint();
//        }
//    }
	 
    public static void main (String[] args) {
    	Display window = new Display(1200);
		window.setVisible(true);
		window.setResizable(false);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}