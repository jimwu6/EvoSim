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
	gameBtn gameMode, simMode, howTo, creds;
	
	public MainMenu(int width) {
		w = width;
		h = w * 3 / 4;
		setSize(w, h);
		
		addMouseListener(this);
		
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
		
		gameMode = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\MainMenu\\gameModeBtn.png",this.getSize().width/4, this.getSize().height/7);
		simMode = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\MainMenu\\simModeBtn.png",this.getSize().width/4, this.getSize().height/7);
		creds = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\MainMenu\\credits.png",this.getSize().width/4, this.getSize().height/7);
		howTo = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\MainMenu\\howToPlayBtn.png",this.getSize().width/4, this.getSize().height/7);
		
		JLabel pic = new JLabel(background);	 
		JLabel ttl = new JLabel(finalTitle);
		JPanel bg = new JPanel();
		JPanel ttlPanel = new JPanel();
		ttlPanel.add(ttl);
		bg.add(pic);
		
		bg.setBounds(0, 0, getSize().width, getSize().height);
		ttlPanel.setBounds(1, 1, getSize().width, getSize().height);
		ttlPanel.setBackground(null);
		ttlPanel.setOpaque(false);
		gameMode.setBounds(getSize().width/2 + getSize().width/12, 7* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		simMode.setBounds (getSize().width/2 - getSize().width/3, 7* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		creds.setBounds(getSize().width/2 + getSize().width/12, 9* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		howTo.setBounds(getSize().width/2 - getSize().width/3, 9* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
				
		add (bg, new Integer(0));
		add (ttlPanel, new Integer(1));
		add (gameMode, new Integer(2));
		add (simMode, new Integer(2));
		add (howTo, new Integer(2));
		add (creds, new Integer(2));
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
		MainMenu menu = new MainMenu(611);
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(menu);

		//frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(1200, 1200);
		frame.setVisible(true);
	}

}