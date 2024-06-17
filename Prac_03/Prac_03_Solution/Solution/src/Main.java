import java.io.File;

import acsse.csc2a.file.FileHandler;
import acsse.csc2a.model.Ship;

/**
 * @author Mr D Ogwok
 * @version P03 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a list of filenames to read
		String[] filenames = { "data/normal.txt", "data/corrupt.txt", "data/large.txt" };
		for(String fileName : filenames) {
			System.out.println("===============================================================================================");
						
			Ship[] ships = FileHandler.readFile(fileName);
			
			for (Ship ship : ships)
			{
				if (ship != null)
				{
					ship.printMessages();;
				}
				else
				{
					// null reference in array
					System.err.println("Ship array has a null reference");
				}
			}
			System.out.println("===============================================================================================");
		}

	}

}
