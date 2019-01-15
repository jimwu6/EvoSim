package GUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

public class Settings extends JLayeredPane implements MouseListener, ActionListener, ChangeListener {
	//field
	gameBtn sun, cloud, rain, reset, on, off;
	JSlider RR, simSpeed, temp;
	Image image = null;
	
	public Settings(int w)
	{
		setSize(w, w*5 / 6);
		 try
	        {
	        	image = ImageIO.read(new File("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\settings bg.png"));
	        }
	        catch (IOException e)
	        {
	        }
		 
		 // Create 3 buttons
	    gameBtn sun = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\sunButton.png",this.getSize().height/5, this.getSize().height/5);
	    gameBtn cloud = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\cloudButton.png",this.getSize().height/5, this.getSize().height/5);
	    gameBtn rain = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\rainButton.png",this.getSize().height/5, this.getSize().height/5);
	    gameBtn reset = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\resetButton.png",this.getSize().height/5, this.getSize().height/5);
	    gameBtn onA = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\onButtonA.png",this.getSize().height/4, this.getSize().height/8);
	    gameBtn onB = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\onButtonB.png",this.getSize().height/4, this.getSize().height/8);
	    gameBtn offA = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\offButtonA.png",this.getSize().height/4, this.getSize().height/8);
	    gameBtn offB = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\offButtonB.png",this.getSize().height/4, this.getSize().height/8);
	    
	    sun.setBounds(this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	    cloud.setBounds(2*this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	    rain.setBounds(3*this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	    reset.setBounds(4*this.getSize().height/5, this.getSize().height/3 + this.getSize().height/25, this.getSize().height/5, this.getSize().height/5);
	 
	    onA.setBounds(5*this.getSize().height/6 + this.getSize().height/16, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    onB.setBounds(5*this.getSize().height/6 + this.getSize().height/16, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    offA.setBounds(4*this.getSize().height/6 - this.getSize().height/12, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);
	    offB.setBounds(4*this.getSize().height/6 - this.getSize().height/12, 2*this.getSize().height/3, this.getSize().height/4, this.getSize().height/8);

	    
	    Image newImage = image.getScaledInstance(this.getSize().width-20, this.getSize().height-50, Image.SCALE_DEFAULT);
		 ImageIcon icon = new ImageIcon(newImage);
		// back.setIcon(icon);
		 
		 JLabel pic = new JLabel(icon);	 
		 JPanel bg = new JPanel();
		 bg.add(pic);
		 bg.setVisible(true);
		 bg.setBounds(10, 10, getSize().width-20, getSize().height-20);
		 
	    // Place the buttons in different layers
	   add(sun, new Integer(2));
	   add(cloud, new Integer(2));
	   add(bg, new Integer(1));
	   add(rain, new Integer(2));
	   add(reset, new Integer(2));
	   add(onA, new Integer(3));
	   add(offB, new Integer(3));
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
		Settings window = new Settings(825);
		// Create a frame in which to show the button.
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(window);

		//frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(1200, 1200);
		frame.setVisible(true);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseEntered( MouseEvent e )
			{}

			public void mouseExited( MouseEvent e )
			{}

			public void mouseClicked( MouseEvent e )
			{
				System.out.println( "clicked " );
			}

			public void mousePressed( MouseEvent e )
			{
				System.out.println( "pressed " );
			}

			public void mouseReleased( MouseEvent e )
			{
				System.out.println( "released ");
			}
		};
		window.addMouseListener( mouseListener );
	
	}
}

