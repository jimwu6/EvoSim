package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*; // allows file access
import javax.imageio.*; // allows image loading

/**
 * Defines a JButton to be used by the game class.
 * <p>
 * Game buttons contain an image and size, and can be constructed to create a button responsive to mouse actions that are relevant to the button.
 * Visually, the button is rounded and has a transparent background which does not block any underlying graphics.
 */
@SuppressWarnings("serial")
public class gameBtn extends JButton implements MouseListener {
	Image i;
	public boolean entered, clicked = false;
	int width, height;
	int value;

	/**
	 * Constructs a button with a transparent background and a set image. 
	 * The button has rounded edges and takes on the appearance of the image being read.
	 * 
	 * @param fileName Shortcut name of the image file to be read
	 * @param w Width of the desired button
	 * @param h Height of the desired button
	 */
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

	/**
	 * Constructs a button with a preset image and an inherent value that is returned to assess the character of the button.
	 * 
	 * @param fileName Shortcut name of image file to be read
	 * @param w Width of the button
	 * @param h Height of the button
	 * @param selection Value of button to be assessed upon button actions
	 */
	public gameBtn(String fileName, int w, int h, int selection) {
		this(fileName, w, h);
		value = selection;
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
	
	public void mouseClicked(MouseEvent arg0) {
		clicked = true;
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
		gameBtn button = new gameBtn("src\\settings.png", 214, 214);
		//button = new gameBtn("C://work//Projects//2018//EvoSim//src//settings.png", 114, 114);

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