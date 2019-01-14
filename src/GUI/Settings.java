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
	
	public Settings(int w, int h)
	{
		setSize(w, h);
		 try
	        {
	        	image = ImageIO.read(new File("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\Summative Graphics\\Menu\\png\\settings bg.png"));
	        }
	        catch (IOException e)
	        {
	        }
		 
		 // Create 3 buttons
	    JButton top = new JButton();
	    top.setBackground(Color.white);
	    top.setBounds(20, 20, 50, 50);
	    JButton middle = new JButton();
	    middle.setBackground(Color.gray);
	    middle.setBounds(40, 40, 50, 50);
	    JButton bottom = new JButton();
	    bottom.setBackground(Color.black);
	    bottom.setBounds(60, 60, 50, 50);

	    Image newImage = image.getScaledInstance(this.getSize().width-20, this.getSize().height-50, Image.SCALE_DEFAULT);
		 ImageIcon icon = new ImageIcon(newImage);
		// back.setIcon(icon);
		 
		 JLabel pic = new JLabel(icon);	 
		 JPanel bg = new JPanel();
		 bg.add(pic);
		 bg.setVisible(true);
		 bg.setBounds(10, 10, getSize().width-20, getSize().height-20);
		 
	    // Place the buttons in different layers
	   add(middle, new Integer(2));
	   add(top, new Integer(3));
	   add(bg, new Integer(3));
	   add(bottom, new Integer(1));
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
		Settings window = new Settings(655,655);
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

