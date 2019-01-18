package GUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

public class Settings extends JLayeredPane implements MouseListener {
	//field
	gameBtn sun, cloud, rain, reset, onA, onB, offA, offB, exit;
	Slider RR, simSpeed, temp;
	boolean ndOn;
	Image image = null;
	
	public Settings(int w)
	{
		setSize(w, w*5 / 6);
		 try
	        {
	        	image = ImageIO.read(new File("Summative Graphics\\Menu\\png\\settings bg.png"));
	        }
	        catch (IOException e)
	        {
	        }
		 
		 // Create new buttons
		sun = new gameBtn("Summative Graphics\\Menu\\png\\sunButton.png",this.getSize().height/5, this.getSize().height/5);
	    cloud = new gameBtn("Summative Graphics\\Menu\\png\\cloudButton.png",this.getSize().height/5, this.getSize().height/5);
	    rain = new gameBtn("Summative Graphics\\Menu\\png\\rainButton.png",this.getSize().height/5, this.getSize().height/5);
	    reset = new gameBtn("Summative Graphics\\Menu\\png\\resetButton.png",this.getSize().height/5, this.getSize().height/5);
	    onA = new gameBtn("Summative Graphics\\Menu\\png\\onButtonA.png",this.getSize().height/4, this.getSize().height/8);
	    onB = new gameBtn("Summative Graphics\\Menu\\png\\onButtonB.png",this.getSize().height/4, this.getSize().height/8);
	    offA = new gameBtn("Summative Graphics\\Menu\\png\\offButtonA.png",this.getSize().height/4, this.getSize().height/8);
	    offB = new gameBtn("Summative Graphics\\Menu\\png\\offButtonB.png",this.getSize().height/4, this.getSize().height/8);
	    exit = new gameBtn("Summative Graphics\\exitBtn.png",this.getSize().height/8, this.getSize().height/8);
		   
	    simSpeed = new Slider(this.getSize().height/2);
	    RR = new Slider(this.getSize().height/2);
	    temp = new Slider(this.getSize().height/2);
		    
	    sun.setBounds(this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	    cloud.setBounds(2*this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	    rain.setBounds(3*this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	    reset.setBounds(4*this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	 
	    onA.setBounds(5*this.getSize().height/6 + this.getSize().height/16, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    onB.setBounds(5*this.getSize().height/6 + this.getSize().height/16, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    offA.setBounds(4*this.getSize().height/6 - this.getSize().height/12, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    offB.setBounds(4*this.getSize().height/6 - this.getSize().height/12, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    exit.setBounds(14*this.getSize().width/16, this.getSize().height/21, this.getSize().height/8, this.getSize().height/8);
	    
	    simSpeed.setBounds(this.getSize().height/2 + this.getSize().height/12, this.getSize().height/6 + this.getSize().height/88, this.getSize().height/2, this.getSize().height/12);
	    RR.setBounds(this.getSize().height/2 + this.getSize().height/12, this.getSize().height/2 + 17*this.getSize().height/256, this.getSize().height/2, this.getSize().height/12);
	    temp.setBounds(this.getSize().height/2 + this.getSize().height/12, 2*this.getSize().height/3+4*this.getSize().height/30, this.getSize().height/2, this.getSize().height/12);
		   
	    Image newImage = image.getScaledInstance(this.getSize().width-20, this.getSize().height-50, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(newImage);
		
		 JLabel pic = new JLabel(icon);	 
		 JPanel bg = new JPanel();
		 bg.add(pic);
		 bg.setVisible(true);
		 bg.setBounds(10, 10, getSize().width-20, getSize().height-20);
		 
	    // Place the buttons in different layers
	   add(sun, new Integer(2));
	   add(cloud, new Integer(2));
	   add(bg, new Integer(0));
	   add(rain, new Integer(2));
	   add(reset, new Integer(2));
	   add(onB, new Integer(3));
	   add(onA, new Integer(3));
	   add(offA, new Integer(3));
	   add(offB, new Integer(3));
	   add(exit, new Integer(3));
	   
	   add(simSpeed, new Integer(3));
	   add(RR, new Integer(3));
	   add(temp, new Integer(3));
	}

	protected void paintComponent(Graphics g) {
		
		if (onA.wasClicked())
		{
			onA.setVisible(false);
			onB.setVisible(true);
			offB.setVisible(false);
			offA.setVisible(true);
		}
		
		if (onB.wasClicked())
		{
			onB.setVisible(false);
			onA.setVisible(true);
			offA.setVisible(false);
			offB.setVisible(true);
		}

		if (offA.wasClicked())
		{
			offA.setVisible(false);
			offB.setVisible(true);
			onB.setVisible(false);
			onA.setVisible(true);
		}
		
		if (offB.wasClicked())
		{
			offB.setVisible(false);
			offA.setVisible(true);
			onA.setVisible(false);
			onB.setVisible(true);
		}
		
		// This call will paint the label and the focus rectangle.
		super.paintComponent(g);
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
		Settings window = new Settings(625);
		// Create a frame in which to show the button.
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(window);

		//frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(1200, 1200);
		frame.setVisible(true);

		
//		MouseListener mouseListener = new MouseAdapter() {
//			public void mouseEntered( MouseEvent e )
//			{
//				System.out.println( window.onA.wasClicked ());
//			}
//
//			public void mouseExited( MouseEvent e )
//			{}
//
//			public void mouseClicked( MouseEvent e )
//			{
//				System.out.println( "clicked " + window.onA.wasClicked ());
//				window.onA.clicked = false;
//			}
//
//			public void mousePressed( MouseEvent e )
//			{
//				System.out.println( "pressed " );
//			}
//
//			public void mouseReleased( MouseEvent e )
//			{
//				System.out.println( "released ");
//			}
//		};
//		window.onA.addMouseListener( mouseListener );
	
	}
}

