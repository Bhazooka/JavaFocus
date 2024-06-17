package accse.csc2a.model;

import acsse.csc2a.model.*;

/**
 * @author barbu
 * SAO2 221005009
 */
public class Employee implements IValidation{
	
	String EmployeeID;
	String FistName;
	String LastName;
	Ship ShipData;

	
	/**Constrictor
	 * @param employeeID
	 * @param fistName
	 * @param lastName
	 * @param shipData
	 */
	public Employee(String employeeID, String fistName, String lastName) {
		super();
		EmployeeID = employeeID;
		FistName = fistName;
		LastName = lastName;
	}
	
	public String getEmployeeID() {
		return EmployeeID;
	}


	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}


	public String getFistName() {
		return FistName;
	}


	public void setFistName(String fistName) {
		FistName = fistName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public Ship getShipData() {
		return ShipData;
	}


	/**
	 *@method
	 */
	@Override
	public boolean validate() {
		if(EmployeeID.length() >= 6)
		{
			return true;
		}
		else
			return false;
	} 
	
	
	
	
	public String printMessages() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.ShipData.getID());
		sb.append("\t" + this.ShipData.getName());
		sb.append("\r\n");
		sb.append("Messages \n");
		for(Message message : this.ShipData.getMessages())
		{
			String messageString = "";
			
			if(message instanceof SOSMessage)
			{
				SOSMessage newMessage = (SOSMessage) message;
				messageString = String.format("ID: %s %s \t\t (%s => %s) \t \tContents: %s \t\tReciever: %s",
						newMessage.getID(), newMessage.getMessage_type(), newMessage.getPlanet_source(), newMessage.getPlanet_destination(), newMessage.getContents(), newMessage.getRecipient());
				sb.append(messageString);
			}
			if(message instanceof EncryptedMessage) {
				EncryptedMessage newMessage = (EncryptedMessage) message;
				messageString = String.format("ID: %s %s: \t (%s => %s ) \t \tContents: %s \t\t\tPublic Key: %s", 
						newMessage.getID(), newMessage.getMessage_type(), newMessage.getPlanet_source(), newMessage.getPlanet_destination(), newMessage.getContents(), newMessage.getPublicKey());
				sb.append(messageString);
			}
			if(message instanceof NormalMessage) {
				NormalMessage newMessage = (NormalMessage) message;
				messageString = String.format("ID: %s %s: \t\t (%s => %s ) \t \tContents: %s \t\t\tMessage Length: %s", 
						newMessage.getID(), newMessage.getMessage_type(), newMessage.getPlanet_source(), newMessage.getPlanet_destination(), newMessage.getContents(), newMessage.getMessageLength());
				sb.append(messageString);
			}
			sb.append("\r\n");
		}
		return sb.toString();
		
	}
	
	
	public boolean sendMessage() {
		for(Message message : this.ShipData.getMessages())
		{
			if(message instanceof SOSMessage)
			{
				SOSMessage newMessage = (SOSMessage)message;
				if(!newMessage.validate()) {
					return false;
				}
			}
			
			if(message instanceof EncryptedMessage) {
				EncryptedMessage newMessage = (EncryptedMessage) message;
				if(!newMessage.validate()) {
					return false;
				}
			}
			
			if(message instanceof NormalMessage) {
				NormalMessage newMessage = (NormalMessage) message;
				if(!newMessage.validate()) {
					return false;
				}
			}
		}
		
		return true;
	}

}
