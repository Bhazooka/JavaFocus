package accse.csc2a.model;
import acsse.csc2a.model.*;

public class SOSMessage extends Message {

	RECIPIENT_TYPE recipient;
	
	public SOSMessage(String iD, String contents, PLANET_TYPE planet_source, PLANET_TYPE planet_destination,
			MESSAGE_TYPE message_type, RECIPIENT_TYPE recipient) {
		super(iD, contents, planet_source, planet_destination, message_type);
		this.recipient = recipient;
	}
	
	

	public RECIPIENT_TYPE getRecipient() {
		return recipient;
	}
	
	
	public void setRecipient(RECIPIENT_TYPE recipient) {
		this.recipient = recipient;
	}



	@Override
	public boolean validate() {
		if(recipient.equals(RECIPIENT_TYPE.GOVERNMENT) || recipient.equals(RECIPIENT_TYPE.PUBLIC))
		{
			return true;
		}
		else
			return false;
	}
	
	

}
