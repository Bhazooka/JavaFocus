package acsse.csc2a.model;

/**
 * NormalMessage class that is a type of Message Class
 * @author Daniel Ogwok
 * @version P04
 */
public class NormalMessage extends Message{
	
	private int MESSAGE_LENGTH; // Not greater than 40

	/**
	 * Constructor for NormalMessage
	 * @param iD
	 * 		Unique Identifier of the NormalMessage
	 * @param contents
	 * 		Contents of the NormalMessage
	 * @param planet_source
	 * 		Source Planet of the NormalMessage
	 * @param planet_destination
	 * 		Destination Planet of the NormalMessage
	 * @param message_type
	 * 		Message type of the NormalMessage
	 * @param MESSAGE_LENGTH
	 *		Message Length property for NormalMessage
	 */
	public NormalMessage(String iD, String contents, PLANET_TYPE planet_source, PLANET_TYPE planet_destination, MESSAGE_TYPE message_type
			, int MESSAGE_LENGTH) {
		super(iD, contents, planet_source, planet_destination, message_type);
		this.MESSAGE_LENGTH = MESSAGE_LENGTH;
	}

	/**
	 * Method to get the length of the Contents in NormalMessage
	 * @return the length of the NormalMessage contents
	 */
	public int getMessageLength() {
		return contents.length();
	}

	/**
	 * Method to validate the length of the contents of the NormalMessage
	 * @return False if the length of the contents is less than zero, or greater than the defined length of 40, else False
	 */
	@Override
	public boolean validate() {
		if(contents.length() < 0 || contents.length() > MESSAGE_LENGTH) {
			return false;
		}
		return true;
	}
	
}
