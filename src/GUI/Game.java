package GUI;

import java.util.*;

import java.awt.*;
import javax.swing.*;

import java.awt.Event;
import java.awt.event.*;
import javax.swing.event.*;

import Ecosystem.*;

public class Game extends JLayeredPane implements MouseListener, KeyListener, ActionListener, ChangeListener {

	int w, h;
	Landscape landscape;
	
	
	public Game(int width) {
		w = width;
		h = w * 3 / 4;
		setSize(w, h);
	}
	
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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

	public void keyReleased(KeyEvent e) {						// maybe have a boolean thats set false when released for movement
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}