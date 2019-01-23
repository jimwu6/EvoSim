package GUI;

import java.util.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

public class MainMenu extends JLayeredPane implements MouseListener, ActionListener {

	private int btnChoice = -1;
	int w, h;
	Image bg = null, title = null, instructions = null, credits = null;
	gameBtn gameMode, simMode, howTo, creds, exit;
	JLabel howToLbl, credsLbl;
	
	public MainMenu(int width) {
		w = width;
		h = w * 5 / 6;
		setSize(w, h);
		
		addMouseListener(this);
		
		try
        {
        	bg = ImageIO.read(new File("Summative Graphics\\MainMenu\\bg.png"));
        	title = ImageIO.read(new File("Summative Graphics\\MainMenu\\title.png"));
        	instructions = ImageIO.read(new File("Summative Graphics\\MainMenu\\howTo.png"));
        	credits = ImageIO.read(new File("Summative Graphics\\MainMenu\\creds.png"));
        }
        catch (IOException e)
        {
        }
		
		bg = bg.getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_DEFAULT);
		title = title.getScaledInstance(3*this.getSize().width/4, 2*this.getSize().height/3, Image.SCALE_DEFAULT);
		instructions = instructions.getScaledInstance(15*this.getSize().width/16, 15*this.getSize().height/16-11, Image.SCALE_DEFAULT);
		credits = credits.getScaledInstance(15*this.getSize().width/16, 15*this.getSize().height/16-11, Image.SCALE_DEFAULT);
		
		ImageIcon background = new ImageIcon(bg);
		ImageIcon finalTitle = new ImageIcon(title);
		ImageIcon finalHowTo = new ImageIcon(instructions);
		ImageIcon finalCreds = new ImageIcon(credits);
		
		gameMode = new gameBtn("Summative Graphics\\MainMenu\\gameModeBtn.png",this.getSize().width/4, this.getSize().height/7);
		simMode = new gameBtn("Summative Graphics\\MainMenu\\simModeBtn.png",this.getSize().width/4, this.getSize().height/7);
		creds = new gameBtn("Summative Graphics\\MainMenu\\credits.png",this.getSize().width/4, this.getSize().height/7);
		howTo = new gameBtn("Summative Graphics\\MainMenu\\howToPlayBtn.png",this.getSize().width/4, this.getSize().height/7);
		exit = new gameBtn ("Summative Graphics\\exitBtn.png",this.getSize().height/7, this.getSize().height/7);
		
		JLabel pic = new JLabel(background);	 
		JLabel ttl = new JLabel(finalTitle);
		howToLbl = new JLabel(finalHowTo);
		credsLbl = new JLabel(finalCreds);
		
		simMode.addActionListener(this);
		gameMode.addActionListener(this);
		howTo.addActionListener(this);
		creds.addActionListener(this);
		exit.addActionListener(this);
		
		JPanel bg = new JPanel();
		JPanel ttlPanel = new JPanel();
		JPanel popup = new JPanel();
		
		ttlPanel.add(ttl);
		bg.add(pic);
		popup.add(howToLbl);
		popup.add(credsLbl);
		howToLbl.setVisible(false);
		credsLbl.setVisible(false);
		exit.setVisible(false);
		
		bg.setBounds(0, 0, getSize().width, getSize().height);
		ttlPanel.setBounds(1, 1, getSize().width, getSize().height);
		popup.setBounds(getSize().width/32, getSize().height/32, 15*getSize().width/16, 15*getSize().height/16);
		
		ttlPanel.setOpaque(false);
		popup.setOpaque(false);
		
		gameMode.setBounds(getSize().width/2 + getSize().width/12, 7* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		simMode.setBounds (getSize().width/2 - getSize().width/3, 7* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		creds.setBounds(getSize().width/2 + getSize().width/12, 9* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		howTo.setBounds(getSize().width/2 - getSize().width/3, 9* getSize().height/12, this.getSize().width/4, this.getSize().height/7);
		exit.setBounds(getSize().width/21, getSize().width/21, this.getSize().height/7, this.getSize().height/7);
			
		add (bg, new Integer(0));
		add (ttlPanel, new Integer(1));
		add (gameMode, new Integer(2));
		add (simMode, new Integer(2));
		add (howTo, new Integer(2));
		add (creds, new Integer(2));
		add (popup, new Integer(3));
		add (exit, new Integer(4));
	}
	
	protected void paintComponent(Graphics g) {
		if (btnChoice == 3)
		{
			howToLbl.setVisible(true);
			credsLbl.setVisible(false);
			exit.setVisible(true);
		}
		
		else if (btnChoice == 4)
		{
			howToLbl.setVisible(false);
			credsLbl.setVisible(true);
			exit.setVisible(true);
		}
		
		else if (btnChoice == -1)
		{
			howToLbl.setVisible(false);
			credsLbl.setVisible(false);
			exit.setVisible(false);
		}
		// This call will paint the label and the focus rectangle.
		super.paintComponent(g);
	}
	
	public int buttonChoice() 
	{
		return btnChoice;
	}
	
	public void mouseClicked(MouseEvent e) {		
		
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
		frame.setSize(1200, 1000);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(simMode))
        {
            btnChoice = 1;
        }
		
		else if (e.getSource().equals(gameMode))
        {
            btnChoice = 2;
        }
		
		else if (e.getSource().equals(howTo))
        {
            btnChoice = 3;
        }
		
		else if (e.getSource().equals(creds))
        {
            btnChoice = 4;
        }

		else if (e.getSource().equals(exit))
        {
            btnChoice = -1;
        }
		
		repaint();
		
	}

}