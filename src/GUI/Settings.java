package GUI;

import java.util.*;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

public class Settings extends JFrame implements MouseListener, ActionListener, ChangeListener {
	//field
	gameBtn sun, cloud, rain, reset, on, off;
	JSlider RR, simSpeed, temp;
	Image image = null;
	
	public Settings()
	{
		JLayeredPane content = new JLayeredPane();
		JLabel back = new JLabel("HI");
		 try
	        {
	        	image = ImageIO.read(new File("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\settings bg.png"));
	        }
	        catch (IOException e)
	        {
	        }
		 ImageIcon icon = new ImageIcon(image);
		// back.setIcon(icon);
		 
		content.add(back,(new Integer(1)));
		
		setContentPane(content);
		pack();
		setTitle("Evolution Simulation");
		setSize(765, 790);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
	
	public static void main (String[] args) {
		Settings window = new Settings();
		window.setVisible(true);
		window.setResizable(false);
	}
	
}

