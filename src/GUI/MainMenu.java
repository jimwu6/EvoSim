package GUI;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Event;
import java.awt.event.*;
import javax.swing.event.*;

public class MainMenu implements MouseListener, KeyListener, ActionListener {

	int btnChoice = 1;
	
	public MainMenu() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_UP) {						// for navigation
			btnChoice = btnChoice <= 1? btnChoice = 4 : btnChoice--;	// move up one button
		}
		else if(keyCode == KeyEvent.VK_DOWN) {
			btnChoice = btnChoice >= 4? btnChoice = 1 : btnChoice++;	// move down one button
		}			
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
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
	
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
	}

}