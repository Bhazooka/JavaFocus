package acsse.csc2a;

/**
 * A class that stores a specific text message. Each message has a specific source and destination.
 * 
 * @see EPLANET_TYPE
 * @author Mr. D Ogwok
 * @author Mr. A. Maganlal
 * @version P02
 */
public class Message
{
	private final String	ID;
	private String				language;
	private String				contents;
	private EPLANET_TYPE	source;
	private EPLANET_TYPE	destination;

	/**
	 * Constructor for Message Class
	 * 
	 * @param id
	 *          Unique Identifier of the Message, starts with MSG, then 6 additional digits eg.
	 *          MSG007600
	 * @param language
	 *          Language of the message
	 * @param contents
	 *          Contents of the message
	 * @param source
	 *          Planet from which the message is being sent
	 * @param destination
	 *          Planet to which the message is being sent
	 */
	public Message(String id, String language, String contents, EPLANET_TYPE source,
			EPLANET_TYPE destination)
	{
		this.ID = id;
		this.language = language;
		this.source = source;
		this.destination = destination;
		this.contents = contents;
	}

	@Override
	public String toString()
	{
		// override toString to return custom string of the current instance
		return String.format("%s\t(%s) [%s => %s]: %s", this.ID, this.language, this.source,
				this.destination, this.contents);
	}

	/**
	 * Get ID of Message
	 * 
	 * @return ID of the message
	 */
	public String getID()
	{
		return ID;
	}

	/**
	 * Get Language of the message
	 * 
	 * @return language of the message
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Get Contents of the message
	 * 
	 * @return contents of the message
	 */
	public String getContents()
	{
		return contents;
	}

	/**
	 * Get source planet of the message
	 * 
	 * @return Source planet of the message
	 */
	public EPLANET_TYPE getSource()
	{
		return source;
	}

	/**
	 * Get destination planet of the message
	 * 
	 * @return Destination planet of the message
	 */
	public EPLANET_TYPE getDestination()
	{
		return destination;
	}

	/**
	 * Set Language of the message
	 * 
	 * @param language
	 *          Language in which the message is
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Set Contents of the Message
	 * 
	 * @param contents
	 *          The contents that are to be transmitted
	 */
	public void setContents(String contents)
	{
		this.contents = contents;
	}

	/**
	 * Set Source Planet of the message
	 * 
	 * @param source
	 *          The planet from which the message is being transmitted
	 */
	public void setSource(EPLANET_TYPE source)
	{
		this.source = source;
	}

	/**
	 * Set Destination Planet of the message
	 * 
	 * @param destination
	 *          The planet to which the message is being transmitted
	 */
	public void setDestination(EPLANET_TYPE destination)
	{
		this.destination = destination;
	}

}
