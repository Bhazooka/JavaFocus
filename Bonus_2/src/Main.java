
import model.Message;
import model.OriginalTransmission;
import model.Ship;


/**
 * @author Mr D Ogwok
 * @version Bonus Practical Two - Adapter Design Pattern
 */
public class Main {
	
	/* TODO: JavaDoc */
	public static void main(String[] args) {
		// Get original transmission format
		OriginalTransmission transmission = new OriginalTransmission();
		String shipData = transmission.getShipData();
		
		// Adapt to Destination Planet format using Adapter Design Pattern (Get Adapted Instances)
		TransmissionAdapter adaptedTransmission = new TransmissionAdapter();
		Ship newShip = adaptedTransmission.getShip(shipData);
		
		// Display Adapted Ship and Messages
		printAdaptedInfo(newShip);
	}
	
	/* TODO: JavaDoc */
	private static void printAdaptedInfo(Ship shipData) {
		System.out.println(shipData.getUniqueID() + " " + shipData.getShipName() + " "
				+ shipData.getShipCaptain() + " " + shipData.getDestination());
		for(Message message : shipData.getMessages()) {
			System.out.println(message.getUniqueID() + " - " + message.getReceiverID() + " - " + message.getMessageContent());
		}
	}

}
