import java.util.StringTokenizer;
import model.Message;
import model.Ship;

/**
 * @author barbu
 * Public Transmitter class, Adapter class to convert the data
 */
public class TransmissionAdapter implements ITransmission {

	/**
	 * getShip Method implemented from ITransmission interface
	 */
	@Override
	public Ship getShip(String shipFile) {
		Ship s = null;
		
		//Start of info String
		StringTokenizer  Tokenizer = new StringTokenizer(shipFile,"_");
		String ShipString = Tokenizer.nextToken();
		//Seperate ship property data
		StringTokenizer shipDATA = new StringTokenizer(ShipString,"-");
		//Ship object to initialize values from token
		s = new Ship(shipDATA.nextToken(), shipDATA.nextToken(), shipDATA.nextToken(), shipDATA.nextToken());
	
		//Perform operations while theres data, return ship upon completion
		while(Tokenizer.hasMoreTokens())
		{
			//Seperate the message data
			StringTokenizer message = new StringTokenizer(Tokenizer.nextToken(), "+");
			//add message respective ship
			s.getMessages().add(new Message(message.nextToken(), message.nextToken(), message.nextToken()));
		}
		return s;	
	}

	

}
