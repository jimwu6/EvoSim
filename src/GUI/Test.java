package GUI;

import Ecosystem.*;

/**
 * class used for testing of the program
 */
public class Test {

	public static void main(String[] args) {
		//Animal animal = new Mammal("Summative Graphics\\Animals\\animal2.png", 1, 1, 1, "Male");

		//animal.mate(new Mammal("Summative Graphics\\Animals\\animal2.png", 1, 1, 1, "Female"));
		
		Animal animal2 = new Canine("Summative Graphics\\Animals\\animal2.png", 1, 1, 1, "Male");
		animal2.mate(new Canine("Summative Graphics\\Animals\\animal2.png", 1, 1, 1, "Female"));

	}

}
