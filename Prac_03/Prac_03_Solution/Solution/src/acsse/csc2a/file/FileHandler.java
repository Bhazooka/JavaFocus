package acsse.csc2a.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc2a.model.EPLANET_TYPE;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.Ship;

/**
 * Class to manage the reading of the files and creation of Ship and Message class instances
 * @author Mr D Ogwok
 * @version P03
 */
public class FileHandler {

	/**
	 * Method to create an instance of Ship from tokens
	 * @param tokens
	 * 		The string of tokens to be used as parameters for creating a Ship instance
	 * @return An instance of Ship
	 */		
	private static Ship parseShip(String tokens) {
		StringTokenizer shipTokens = new StringTokenizer(tokens, " ");
		String shipId = shipTokens.nextToken();
		String shipName = "";
		while(shipTokens.hasMoreTokens()) {
			shipName += shipTokens.nextToken() + " ";
		}
		return new Ship(shipId, shipName);
	}
	
	/**
	 * Method to create an instance of Message from tokens
	 * @param tokens
	 * 		The string of tokens to be used as paramaters to create a Message instance
	 * @return An instance of Message
	 */
	private static Message parseMessage(String tokens) {
		StringTokenizer messageTokens = new StringTokenizer(tokens, " ");
		String ID = messageTokens.nextToken();
		String language = messageTokens.nextToken();
		String contents = messageTokens.nextToken();
		EPLANET_TYPE source = EPLANET_TYPE.valueOf(messageTokens.nextToken());
		EPLANET_TYPE destination = EPLANET_TYPE.valueOf(messageTokens.nextToken());
		return new Message(ID, language, contents, source, destination);
	}
	
		
	/**
	 * Create a new Array of Ship from old array with the new Ship
	 * @param oldArray
	 * 		The original Ship array
	 * @param newShip
	 * 		The Ship instance to be added to the new Array
	 * @return The new Ship array
	 */
	private static Ship[] addShip(Ship[] oldArray, Ship newShip) {
		Ship[] newArray = new Ship[oldArray.length + 1];
		for(int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
		newArray[oldArray.length] = newShip;
		return newArray;
	}
	
	/**
	 * Create a new Array of Message from old array with a new Message at the last Inde
	 * @param oldArray
	 * 			The original Message array
	 * @param newMessage
	 * 			The message instance to be added to the array
	 * @return The new Message array
	 */
	private static Message[] addMessage(Message[] oldArray, Message newMessage) {
		Message[] newArray = new Message[oldArray.length + 1];
		for(int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
		newArray[oldArray.length] = newMessage;
		return newArray;
	}
	
	/**
	 * Read File that contains ships and messages
	 * @param fileName
	 * 		Name of the File to be read
	 * @return An Array of Ships and their respective Messages
	 */
	public static Ship[] readFile(String fileName) {
		// File
		File file = new File(fileName);
		// Array of Ship
		Ship[] shipList = new Ship[0];
		// Array of Message
		Message[] messageList = new Message[0];
		// Use Scanner Class
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
			
			// Pattern for Ships (SH)\d{3}\s[A-Za-z ]*
			// (SH)\d{4}\s([A-Za-z]\s?)+
			final Pattern shipPattern = Pattern.compile("(SH)\\d{4}\\s[A-Za-z ]*");
			
			// Pattern for Messages (MSG)\d*\s[A-Z][a-z]*\s[A-Za-z]*\s[A-Z][a-z]*\s[A-Z][a-z]*
			final Pattern messagePattern = Pattern.compile("(MSG)\\d*\\s[A-Z][a-z]*\\s[A-Za-z]*\\s[A-Z][a-z]*\\s[A-Z][a-z]*");
			
			// Ship to be processed/read from file 
			Ship currentShip = null;
			// Read file
			while (fileScanner.hasNextLine())
			{
				// Read the line
				String currentLine = fileScanner.nextLine();
				System.out.println(currentLine);
				// Create matchers from patterns to compare with
				Matcher shipMatcher = shipPattern.matcher(currentLine);
				Matcher messageMatcher = messagePattern.matcher(currentLine);
				// Test if pattern matches with matcher
				if(shipMatcher.matches()) {
					System.out.println("Ship");
					if(currentShip != null) {
						//shipList.add(currentShip); // Add current Ship to List of ships
						shipList = addShip(shipList, currentShip);
					}
					// Process new Ship, and wait to add messages to it
					currentShip = parseShip(currentLine);
				}else if(messageMatcher.matches()) {
					Message currentMessage = parseMessage(currentLine);
					if (currentShip != null)
					{
						currentShip.addMessage(currentMessage);
					}
					else
					{
						System.err.println("No Ship found yet");
						// handle special case where the first Ship has not been read yet
						messageList = addMessage(messageList, currentMessage);
					}
				}else {
					System.err.println("Not a valid Line");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shipList;
	}
	
}
