package acsse.csc2a.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import acsse.csc2a.model.EncryptedMessage;
import acsse.csc2a.model.MESSAGE_TYPE;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.NormalMessage;
import acsse.csc2a.model.PLANET_TYPE;
import acsse.csc2a.model.RECIPIENT_TYPE;
import acsse.csc2a.model.SOSMessage;
import acsse.csc2a.model.Ship;

/**
 * DataReader class
 * @author Daniel Ogwok
 * @version P04
 */
public class DataReader {
	
	// Array Initial Size
	private static int ITEM_ARRAY_SIZE = 1;
	
	/**
	 * Method to read the Ship Information from file
	 * @param shipFileName
	 * 			Name of the Ship file
	 * @param messagesFileName
	 *			Name of the Messages file
	 * @return A ship instance
	 */
	public static Ship readShip(File shipFileName, File messagesFileName){
		Ship newShip = null;
		DataInputStream input = null;
		try {
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(shipFileName)));
			String shipID = input.readUTF();
			String shipName = input.readUTF();
			newShip = new Ship(shipID, shipName);
			// Read Messages
			Message[] messages = readMessages(messagesFileName);
			// Add Messages to Ship
			newShip.addMessages(messages);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return newShip;	
	}
	
	/**
	 * Method to read Messages from the message file
	 * @param fileName
	 * 		Name of the file
	 * @return An array of Messages
	 */
	private static Message[] readMessages(File fileName) {
		Message[] messages = new Message[ITEM_ARRAY_SIZE];
		DataInputStream input = null;
		try {
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			// Loop through messages
			int intCounter = 0;
			while(input.available() > 0) {
				intCounter++;
				// Messages
				String messageID = input.readUTF();
				String messageContent = input.readUTF();
				PLANET_TYPE sourcePlanet = PLANET_TYPE.valueOf(input.readUTF());
				PLANET_TYPE destinationPlanet = PLANET_TYPE.valueOf(input.readUTF());
				MESSAGE_TYPE messageType = MESSAGE_TYPE.valueOf(input.readUTF());
				// Create Instances of Message
				Message message = null;
				if(messageType.equals(MESSAGE_TYPE.SOSMessage)) {
					RECIPIENT_TYPE recipient = RECIPIENT_TYPE.valueOf(input.readUTF());
					message = new SOSMessage(messageID, messageContent, sourcePlanet, destinationPlanet, messageType, recipient);
				}else if(messageType.equals(MESSAGE_TYPE.EncryptedMessage)) {
					String publicKey = input.readUTF();
					message = new EncryptedMessage(messageID, messageContent, sourcePlanet, destinationPlanet, messageType, publicKey);
				}else if(messageType.equals(MESSAGE_TYPE.NormalMessage)) {
					int messageLength = input.readInt();
					message = new NormalMessage(messageID, messageContent, sourcePlanet, destinationPlanet, messageType, messageLength);
				}
				// Add to messages array
				if(intCounter > ITEM_ARRAY_SIZE) {
					// Resize the array
					messages = resizeArray(messages);
					// Add message to last index
					messages[intCounter - 1] = message;
					// Update Size
					ITEM_ARRAY_SIZE = intCounter;
				}else {
					messages[0] = message; 
				}
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return messages;
	}
	
	/**
	 * Method for increasing the size of an array of Items by 1.
	 * @param items 
	 * 			The array of Items to be resized.
	 * @return The new array
	 */
	private static Message[] resizeArray(Message[] items) {
		int newArraySize = items.length + 1;
		Message[] temp = new Message[newArraySize]; // Create a bigger array.
		for (int i = 0; i < items.length; i++) {
			temp[i] = items[i]; // Copy the data across.
		}
		return temp;
	}
	
}
