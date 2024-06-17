package accse.csc2a.model;
import acsse.csc2a.model.*;

public class EncryptedMessage extends Message{
	
	String publicKey;

	public EncryptedMessage(String iD, String contents, PLANET_TYPE planet_source, PLANET_TYPE planet_destination,
			MESSAGE_TYPE message_type, String publicKey) {
		super(iD, contents, planet_source, planet_destination, message_type);
		
		this.publicKey = publicKey;
	}
	
	
	
	public String getPublicKey() {
		return publicKey;
	}

	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}



	@Override
	public boolean validate() {
		if(publicKey.length() > 10)
		{
			return true;
		}
		else
			return false;
	}

}
