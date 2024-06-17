package acsse.csc2a.model;

/**
 * EncryptedMessage class that is a type of Message Class
 * @author Daniel Ogwok
 * @version P04
 */
public class EncryptedMessage extends Message{
	
	// Public Key for Encrypted Message, must be longer than 10 characters
	private String publicKey;

	/**
	 * Constructor for EncryptedMessage
	 * @param iD Unique Identifier of the EncryptedMessage
	 * @param contents
	 * 		Contents of the EncryptedMessage
	 * @param planet_source
	 * 		Source Planet of the EncryptedMessage
	 * @param planet_destination
	 * 		Destination Planet of the EncryptedMessage
	 * @param message_type
	 * 		Message type of the EncryptedMessage
	 * @param publicKey
	 *		Public key property for encryptedMessage
	 */
	public EncryptedMessage(String iD, String contents, PLANET_TYPE planet_source, PLANET_TYPE planet_destination, MESSAGE_TYPE message_type
			, String publicKey) {
		super(iD, contents, planet_source, planet_destination, message_type);
		this.publicKey = publicKey;
	}
	
	/**
	 * Method to get the public Key of the Encrypted Message
	 * @return the public Key property of the EncryptedMessage 
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * Set the public Key of the EncryptedMessage
	 * @param publicKey 
	 * 			the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Method to validate the public Key 
	 * @return True if the public key length is greater than 10, else False
	 */
	@Override
	public boolean validate() {
		if(publicKey.length() > 10) {
			return true;
		}
		return false;
	}

}
