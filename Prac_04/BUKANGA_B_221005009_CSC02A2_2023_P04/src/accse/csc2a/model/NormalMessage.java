package accse.csc2a.model;

import acsse.csc2a.model.*;

public class NormalMessage extends Message{	
	
	private int MESSAGE_LENGTH;
	
	public NormalMessage(String iD, String contents, PLANET_TYPE planet_source, PLANET_TYPE planet_destination,
			MESSAGE_TYPE message_type, int MESSAGE_LENGTH) {
		super(iD, contents, planet_source, planet_destination, message_type);
		
		this.MESSAGE_LENGTH = MESSAGE_LENGTH;
	}
	
	
	//Getters and setters
	public int getMessageLength() {
		return MESSAGE_LENGTH;
	}

	public void setMessageLength(int MESSAGE_LENGTH) {
		this.MESSAGE_LENGTH = MESSAGE_LENGTH;
	}




	@Override
	public boolean validate() {
		
		if(contents.length() <= MESSAGE_LENGTH)
		{
			return true;
		}
		else
			return false;
	}
	
	

}
