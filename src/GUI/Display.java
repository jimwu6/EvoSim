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
public class Display extends JFrame implements MouseListener, KeyListener, ActionListener{

	/**
	 * Game object to display and play the simulation itself.
	 */
	private Game game;
	/**
	 * MainMenu object that displays the menu with game options.
	 */
	private MainMenu menu;
	/**
	 * Dimension for the display window.
	 */
	private int w,h;
	private boolean selected = false;
	
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
			
		//initialize new game and main menu
		game = new Game(this.getSize().width);
		game.pauseTimer();
		menu = new MainMenu(this.getSize().width);
		
		//add action listeners
		menu.simMode.addActionListener(this);
		menu.gameMode.addActionListener(this);
		menu.addKeyListener(this);
		
		//populate the game with animals
		Animal pred = new Raptor("raptor", 15, 1, 10000, "Female", true);
		Animal pred2 = new Feline ("feline", 11, 1, 10000, "Male");
		Animal prey = new Rodent ("rodent", 1, 1, 10000, "Male");
		game.landscape().populate(pred);
		game.landscape().populate(pred2);
		game.landscape().populate(prey);
		
		//setContentPane(game);
		setContentPane(menu);		//add main menu to the pain as a default
		
	}

	 /**
	  * 
	  * 
	  * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	  */
	public void actionPerformed(ActionEvent e) {
		 //if simMode is pressed, start game with sim mode
		 if (e.getSource().equals(menu.simMode))
         {
          	  game.changeMode(true);
           	  selected = true;
           	  menu.setVisible(false);
           	  game.setVisible(true);
           	  setContentPane(game);
           	  game.resumeTimer();
         }   
		 
		 //if game mode is pressed, start simulation with game mode
		 else if (e.getSource().equals(menu.gameMode))
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}