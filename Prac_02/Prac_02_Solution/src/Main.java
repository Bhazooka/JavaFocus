import acsse.csc2a.Message;
import acsse.csc2a.EPLANET_TYPE;
import acsse.csc2a.Ship;

/**
 * Main class will create three Ship instances, and three Message instances for each ship
 * 
 * @author Mr D. Ogwok
 * @author Mr A. Maganlal
 * @version P02
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// These values could be processed via command line arguments
		final int numShips = 3;
		final int numMessages = 3;
		final int planetCount = EPLANET_TYPE.values().length;
		final EPLANET_TYPE[] planets = EPLANET_TYPE.values();

		Ship[] ships = new Ship[numShips];

		for (int i = 0; i < ships.length; i++)
		{
			Ship newShip = new Ship("SH000" + i, "Ship number " + i);
			for (int j = 0; j < numMessages; j++)
			{
				String IDFormat = (i + j > 9) ? "MSG0000%d" : "MSG00000%d";
				String ID = String.format(IDFormat, i + j);
				String contents = String.format("This is a test message: %d", i + j);
				int dindex = (i + j) % planetCount;
				Message newMessage =
						new Message(ID, "English", contents, EPLANET_TYPE.Earth, planets[dindex]);
				newShip.addMessage(newMessage);
			}
			ships[i] = newShip;
		}

		for (Ship ship : ships)
		{
			ship.printMessages();
		}

	}

}
