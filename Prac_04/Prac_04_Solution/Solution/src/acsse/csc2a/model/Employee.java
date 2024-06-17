package acsse.csc2a.model;

/**
 * Employee class
 * @author Daniel Ogwok
 * @version P04
 */
public class Employee implements IValidation{
	
	private String EmployeeID;
	private String FirstName;
	private String LastName;
	private Ship shipData;
	
	/**
	 * Constructor for Employee
	 * @param employeeID
	 * 			Unique Identifier of employee with atleast 6 characters
	 * @param firstName
	 * 			First Name of the Employee
	 * @param lastName
	 * 			Last Name of the Employee
	 */
	public Employee(String employeeID, String firstName, String lastName) {
		EmployeeID = employeeID;
		FirstName = firstName;
		LastName = lastName;
	}

	/**
	 * Get Employee ID
	 * @return the employeeID
	 */
	public String getEmployeeID() {
		return EmployeeID;
	}

	/**
	 * Set EmployeeID of the Employee
	 * @param employeeID 
	 * 			the employeeID to set
	 */
	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	/**
	 * Get First Name of the Employee
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * Set First Name of the Employee
	 * @param firstName 
	 * 				the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * Get the last Name of the Employee
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @param shipData the shipData to set
	 */
	public void setShipData(Ship shipData) {
		this.shipData = shipData;
	}

	/**
	 * Validate the Employee Details, ID must be longer than 6 characters
	 * @return True if user ID is valid, False if invalid
	 */
	@Override
	public boolean validate() {
		if(EmployeeID.length() > 6) {
			return true;
		}
		return false;
	}
	
	/**
	 * Validate messages using the validate Method
	 * @return True if all messages valid, or False if any invalid message found
	 */
	public boolean sendMessages() {
		for(Message message : this.shipData.getMessages())
		{
			// Validate Messages
			if(message instanceof SOSMessage) {	//instanceof checks if the current object is an instance of the class being compared
				SOSMessage newMessage = (SOSMessage) message;
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
	
	/**
	 * Print messages being transported by Ship
	 * @return A string representing the Ship and messages
	 */
	public String printMessages() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.shipData.getID());
		sb.append("\t"+this.shipData.getName());
		sb.append("\r\n");	//the "\r" is a control character that causes the cursor to move to the beginning of the current line in a console or terminal window
		sb.append("Messages \n");
		for(Message message : this.shipData.getMessages())
		{
			String messageString = "";
			if(message instanceof SOSMessage) {
				SOSMessage newMessage = (SOSMessage) message;
				messageString = String.format("ID: %s %s: \t\t (%s => %s ) \t \tContents: %s \t\t\tReceiver: %s", 
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
	
	
}
