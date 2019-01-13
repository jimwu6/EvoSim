package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*; // allows file access
import javax.imageio.*; // allows image loading

public class gameBtn extends JButton implements MouseListener {
	Image i;
	public boolean entered;
	int width, height;
	
    public gameBtn(String fileName, int w, int h) {
    	addMouseListener(this);
    	width = w;
    	height = h;
    	
        try
        {
        	i = ImageIO.read(new File(fileName));
        }
        catch (IOException e)
        {
        }
        
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
 
        setBounds(11, 11, width, height);
        
        // This call causes the JButton not to paint the background.
        // This allows us to paint a round background.
        setContentAreaFilled(false);
    }

	// Paint the round background and label.
	protected void paintComponent(Graphics g) {
		Color highlight = new Color(234,221,146);
		g.setColor(highlight);
		if (entered)
			g.fillRoundRect(0, 0, getSize().width-10, getSize().height-10, 30, 30);
		g.drawImage(i, 5, 5, width-20, height-20, this);

		// This call will paint the label and the focus rectangle.
		super.paintComponent(g);
	}

	// Paint the border of the button using a simple stroke.
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		// g.drawOval(0, 0, getSize().width-1, getSize().height-1);
	}

	// Hit detection.
	Shape shape;
	public boolean contains(int x, int y) {
		// If the button has changed size, make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseEntered(MouseEvent arg0) {
		entered = true;
	}

	public void mouseExited(MouseEvent arg0) {
		entered = false;

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	//-------------------------------------------------------------------------------------------------------------
	// Test routine.
	public static void main(String[] args) {
		// Create a button with the label "Jackpot".
		gameBtn button = new gameBtn("C:\\Data\\Justin\\Grade 11\\ICS\\Summative\\EvoSim\\src\\settings.png", 114, 114);

		// Create a frame in which to show the button.
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(button);

		//        frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(500, 500);
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
				System.out.println( "released " + button.entered);
			}
		};
		button.addMouseListener( mouseListener );

	}
}