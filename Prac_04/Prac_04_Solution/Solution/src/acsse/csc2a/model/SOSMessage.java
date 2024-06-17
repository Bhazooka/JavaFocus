/**
 * 
 */
package acsse.csc2a.model;

/**
 * SOSMessage class that is a type of Message Class
 * @author Daniel Ogwok
 * @version P04
 */
public class SOSMessage extends Message{
	
	// Category of Recipient for the SOSMessage
	private RECIPIENT_TYPE recipient;
	
	/**
	 * Constructor for SOSMessage
	 * @param iD
	 * 		Unique Identifier of the SOSMessage
	 * @param contents
	 * 		Contents of the SOSMessage
	 * @param planet_source
	 * 		Source Planet of the SOSMessage
	 * @param planet_destination
	 * 		Destination Planet of the SOSMessage
	 * @param message_type
	 * 		Message type of the SOSMessage
	 * @param recipient
	 * 		Recipient of the SOSMessage
	 */
	public SOSMessage(String iD, String contents, PLANET_TYPE planet_source, PLANET_TYPE planet_destination, MESSAGE_TYPE message_type, RECIPIENT_TYPE recipient) {
		super(iD, contents, planet_source, planet_destination, message_type);
		this.recipient = recipient;
	}

	/**
	 * Method to get the Recipient of the SOSMessage
	 * @return the recipient of the SOSMessage of type RECIPIENT_TYPE, either Government or Public
	 */
	public RECIPIENT_TYPE getRecipient() {
		return recipient;
	}
	
	/**
	 * Method to set the recipient of the SOSMessage
	 * @param recipient 
	 * 			the recipient of the SOSMessage, of type RECIPIENT_TYPE either Government or Public
	 */
	public void setRecipient(RECIPIENT_TYPE recipient) {
		this.recipient = recipient;
	}

	/**
	 * Method to validate the SOSMessage
	 * @return True, if the Message Recipient is either Government or Public, else False
	 */
	@Override
	public boolean validate() {
		if(recipient.equals(RECIPIENT_TYPE.GOVERNMENT) || recipient.equals(RECIPIENT_TYPE.PUBLIC)) {
			return true;
		}
		return false;
	}

}
