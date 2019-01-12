package GUI;

import java.util.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;

public class Display extends JFrame implements MouseListener{

	public Display()
	{
		JPanel north = new JPanel(new FlowLayout());
		settingsBtn settings = new settingsBtn();
		settings.addMouseListener(this);
		settings.setPreferredSize(new Dimension(111,111));
		
		drawArea board = new drawArea(200, 200);
		
		north.add(board);
		north.add(settings);

		// set window attributes
		setContentPane(north);
		pack();
		setTitle("Evolution Simulation");
		setSize(765, 790);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
    
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

    class Advance {

        Territory territory;

    }

    public static void main (String[] args) {
    	Display window = new Display();
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