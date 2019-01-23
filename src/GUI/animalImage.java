package GUI;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
  
import javax.imageio.ImageIO;
  
public class animalImage {
  
    public static void main(String[] args) {
         
        // Array of input images.
    	String[] strings = {"fruit", "seaweed", "water"};
        
         
        // Load each input image.
        // Assume they are called "image_0.png", "image_1.png",
        // etc.
    	BufferedImage output = makeImage(strings);
    	
    	File f = new File( "image.png" );
        try {
            ImageIO.write( output, "PNG", f );
        }
        catch ( IOException x ) {
            // Complain if there was any problem writing 
            // the output file.
        	System.out.println("NO");
            x.printStackTrace();
        }    
    }
    
    public static BufferedImage makeImage(String[] strings)
    {
    	BufferedImage[] input = new BufferedImage[3];
        for ( int i = 0; i < input.length; i++ ) {
            try {
                File f = new File( "Summative Graphics\\" + strings[i] + ".png" );
                input[i] = ImageIO.read( f );
            }
            catch ( IOException x ) {
                // Complain if there is any problem loading 
                // an input image.
                x.printStackTrace();
            }
        }
         
        // Create the output image.
        // It is the same size as the first
        // input image.  I had to specify the type
        // so it would keep it's transparency.
        BufferedImage output = new BufferedImage( 
                input[0].getWidth()*2, 
                input[0].getHeight()*2, 
                BufferedImage.TYPE_INT_ARGB );
         
        // Draw each of the input images onto the
        // output image.
        Graphics g = output.getGraphics();
        for ( int i = 0; i < input.length; i++ ) {
            g.drawImage( input[i], i*55 , 0, null );
        }
        
        return output;           
    }
}
