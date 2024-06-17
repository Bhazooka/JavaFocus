import java.io.File;


import acsse.csc2a.file.FileHandler;
import acsse.csc2a.model.Ship;

/**
 * Class containing main method.
 * 
 * @author Baraka Bukanga
 * @version P03
 */
public class Main {

	/**
	 * Main method to test the functionality of classes.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a list of filenames to test the ShipFileHandler against.
		String[] filenames = { "data/normal.txt", "data/corrupt.txt", "data/large.txt" };
		
		for (String filename : filenames) { //Loop through each filename.
			File currentFile = new File(filename); //Create a filehandler object.
			Ship[] ships = FileHandler.readShips(currentFile); //Read in the ship using the file handle.
			System.out.printf("%d ships found in %s%n", ships.length, filename); //Display how many ships were found in the current file.
			
			//Loop through the valid ships found in the file.
			for (Ship ship : ships) {
				//Check for null references.
				if (ship != null) {
					//System.out.printf("%s\t%s%n%s%n", ship.getID(), ship.hashCode(), ship.getName());
					ship.printMessages();
				} 
				else {
					System.err.println("Cannout process a null ship");
				}
				
			}
		}
		
	}
}

