package GUI;

import java.util.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

public class MainMenu extends JLayeredPane implements MouseListener {

	int btnChoice = 1;
	int w, h;
	Image bg = null, title = null;
	
	public MainMenu(int width) {
		w = width;
		h = w * 3 / 4;
		
		setSize(w, h);
		
		try
        {
        	bg = ImageIO.read(new File("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\MainMenu\\bg.png"));
        	title = ImageIO.read(new File("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\MainMenu\\title.png"));
        	
        }
        catch (IOException e)
        {
        }
		
		Image newBG = bg.getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT);
		Image newTitle = title.getScaledInstance(3*this.getSize().width/4, 2*this.getSize().height/3, Image.SCALE_DEFAULT);
		ImageIcon background = new ImageIcon(newBG);
		ImageIcon finalTitle = new ImageIcon(newTitle);
		
		 JLabel pic = new JLabel(background);	 
		 JLabel ttl = new JLabel(finalTitle);
		 JPanel bg = new JPanel();
		 JPanel ttlPanel = new JPanel();
		 bg.add(ttl);
		 bg.add(pic);
		 
		 //bg.setVisible(true);
		 ttl.setVisible(true);
		 bg.setBounds(0, 0, getSize().width, getSize().height);
		 ttl.setBounds(1, 1, getSize().width/2, getSize().height/6);
		 
		 add (bg, new Integer(0));
		 add (ttlPanel, new Integer(1));
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
		MainMenu menu = new MainMenu(555);
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(menu);

		//frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(1200, 1200);
		frame.setVisible(true);
	}

}