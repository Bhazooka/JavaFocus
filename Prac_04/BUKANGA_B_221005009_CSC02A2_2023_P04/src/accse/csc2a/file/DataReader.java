package accse.csc2a.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ThreadMXBean;

import accse.csc2a.model.EncryptedMessage;
import accse.csc2a.model.NormalMessage;
import accse.csc2a.model.SOSMessage;
import acsse.csc2a.model.*;

public class DataReader {
	
	private static int ITEM_ARRAY_SIZE = 1;
	
	public Ship readShip(File shipFile, File messageFile)
	{
		DataInputStream input = null;
		Ship ship = null;
		
		try {
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(shipFile)));
			
			String SHIP_ID = input.readUTF();
			String SHIP_NAME = input.readUTF();
			ship = new Ship(SHIP_ID, SHIP_NAME);
			
			Message[] messages = readMessages(messageFile);
			
			ship.addMessages(messages);
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally {
			if(input != null)
			{
				try {
					input.close();
				}
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
		return ship;
	}
	
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
	
	private static Message[] resizeArray(Message[] items) {
		int newArraySize = items.length + 1;
		Message[] temp = new Message[newArraySize]; // Create a bigger array.
		for (int i = 0; i < items.length; i++) {
			temp[i] = items[i]; // Copy the data across.
		}
		return temp;
	}

}
