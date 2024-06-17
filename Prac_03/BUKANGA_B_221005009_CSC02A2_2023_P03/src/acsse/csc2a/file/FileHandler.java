package acsse.csc2a.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc2a.model.EPLANET_TYPE;
import acsse.csc2a.model.Ship;
import acsse.csc2a.model.Message;

/**
 * A class responsible for handling file input/output operations.
 * @author Baraka Bukanga
 * @version P03
 */
public class FileHandler {
	// Create a default number of Ships for initialising the ship array.
	private static final int DEF_SHIP = 3;

	/**
	 * @param FileHandle
	 * File handle used to access given files.
	 * @return	An array of Ships.
	 */
	public static Ship[] readShips(File ShipFile) {
		//Initialise the Ships array.
		Ship[] Ships = new Ship[DEF_SHIP];
		//Regular expression patterns for Ships and messages.
		final Pattern shipPattern = Pattern.compile("^SH\\d{4}[\\s].*$");
		final Pattern messagePattern = Pattern.compile("^MSG\\d{6}[\\s].*[\\s](.*)(?!\\2)[\\s](.*)$");
		Scanner fileReader = null;
		int arrayIndex = 0;
		
		
		// Initialise the Scanner object and read the file
		try {
			fileReader = new Scanner(ShipFile);
			Ship currentShip = null;
		
			while (fileReader.hasNextLine()) {	//While theres more lines to read a next line to read from the file
				String line = fileReader.nextLine();
				Matcher shipMatcher = shipPattern.matcher(line); //Create matcher objects for each of the regex patterns.
				Matcher messageMatcher = messagePattern.matcher(line);
				// If the line matches a ship
				if (shipMatcher.matches()) {
					currentShip = convToShip(line);	//Create ship
					// Check if the array is not yet full.
					if (arrayIndex >= Ships.length) {
						Ships = Arrays.copyOf(Ships, Ships.length * 2);
					}
 
					Ships[arrayIndex++] = currentShip; //Add ship to array.
				} 
				else if (messageMatcher.matches()) { //If the line matches a message
					//If there is currently a valid ship to add the message to
					if (currentShip != null) {
						Message m = convToMessage(line); //Create a message object.
						currentShip.addMessage(m); //Add the message to the ship.
					} 
					else {
						System.err.println("No valid ship to which the message can be added.");
					}
				} 
				else {
					System.err.format("%s does not match any pattern. %n", line);
					if (isCorruptedShip(line))
						currentShip = null;
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Resize the array to remove unused memory locations to avoid encountering null references.
		Ships = Arrays.copyOf(Ships, arrayIndex);
		return Ships;
	}
	/**
	 * Determines if a line within file is corrupt ship information.
	 * @param line
	 * line of text to be analysed.
	 * @return A truth value indicating whether the line represents corrupted ship information
	 */
	private static boolean isCorruptedShip(String line) {
		int shipIDLength = 4; //Ships have an ID length of 4
		StringTokenizer tokenizer = new StringTokenizer(line, "\s");
		String ID = tokenizer.nextToken();
		return ID.length() == shipIDLength; // "true" if the length of the IF is 7.
	}

	/**
	 * Creates a ship object from the provided string.
	 * @param shipString
	 * A string representing ship data.
	 * @return	A ship object.
	 */
	private static Ship convToShip(String shipString) {
		//Seperating the string into tokens using the WhiteSpace character \s
		StringTokenizer tokenizer = new StringTokenizer(shipString, "\s");
		String ID = tokenizer.nextToken();
		String Name = tokenizer.nextToken();
		
		return new Ship(ID, Name); //Return a new ship object.
	}

	/**
	 * Creates an message object from the provided string.
	 * @param messageString
	 * A string representing message data.
	 * @return	An message object. 
	 */
	private static Message convToMessage(String messageString) {
		// Divide the string into tokens using the TAB character.
		StringTokenizer tokenizer = new StringTokenizer(messageString, "\s");
		String ID = tokenizer.nextToken();
		String Language = tokenizer.nextToken();
		String Contents = tokenizer.nextToken();
		EPLANET_TYPE Source = EPLANET_TYPE.valueOf(tokenizer.nextToken());
		EPLANET_TYPE Destination = EPLANET_TYPE.valueOf(tokenizer.nextToken());
		return new Message(ID, Language, Contents,Source, Destination); // Return the new message object.
	}
}
