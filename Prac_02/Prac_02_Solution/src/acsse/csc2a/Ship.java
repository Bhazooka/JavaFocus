/**
 * 
 */
package acsse.csc2a;

/**
 * Class to store the Ship
 * 
 * @author Mr D. Ogwok
 * @author Mr A. Maganlal
 * @version P02
 */
public class Ship
{
	public final static int	DEFAULT_MESSAGE_COUNT	= 2;
	private final String		ID;
	private String					name;
	private Message[]				messages;
	private int							messageIndex;

	/**
	 * Constructor for Ship class
	 * 
	 * @param iD
	 *          Unique identifier of the ship, starts with SH, then 4 additional digits eg. SH0076
	 * @param name
	 *          Name of the Ship
	 */
	public Ship(String iD, String name)
	{
		this.ID = iD;
		this.name = name;
		this.messages = new Message[DEFAULT_MESSAGE_COUNT];
		this.messageIndex = 0;
	}

	/**
	 * Add message to the ship. If the internal array is too small it will be resized.
	 * 
	 * @param newMessage
	 *          The message instances that needs to be added
	 */
	public void addMessage(Message newMessage)
	{
		if (this.messageIndex >= this.messages.length)
		{
			// Need to resize the array
			Message[] resizedMessageArray = new Message[messages.length * 2];
			System.arraycopy(messages, 0, resizedMessageArray, 0, messages.length);
			this.messages = resizedMessageArray;
		}
		this.messages[this.messageIndex++] = newMessage;
	}

	@Override
	public String toString()
	{
		// Override toString to return basic Ship details
		return String.format("%s\t%s", this.ID, this.name);
	}

	/**
	 * Print details of the Ship as well as any contained messages. Utilised the
	 * {@link Ship#toString()} and {@link Message#toString()} methods for customised output
	 */
	public void printMessages()
	{
		// Create StringBuilder to build a "dynamic" String
		StringBuilder sb = new StringBuilder();
		// Print Ship details
		sb.append(String.format("%s%n", this));
		for (Message message : this.messages)
		{
			if (message == null)
			{
				continue;
			}
			// Print message details
			sb.append(String.format("%s%n", message));
		}
		// Print Message String to System out
		System.out.println(sb.toString());
	}

	/**
	 * @return The name of the ship
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *          The new Ship name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return The ID of the Ship
	 */
	public String getID()
	{
		return ID;
	}
}
