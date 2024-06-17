import java.io.File;
import java.util.Scanner;

import accse.csc2a.model.*;
import accse.csc2a.file.*;
import acsse.csc2a.model.Ship;

/**
 * Main class used to instantiate the various objects, make use of respective methods and output the results
 * @author Daniel Ogwok
 * @version P04
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee newEmployee = null;
		try (// Validate Employee/User ID
		Scanner scInput = new Scanner(System.in)) {
			System.out.println("Employee ID: ");
			String employeeID = scInput.next();	//the next function searches for enter character to seperate tokens. It creates a string after the enter is pressed. In this case if we have a full line, then the next line will activate the next() function.
			System.out.println("First Name: ");
			String employeeFirstName = scInput.next();
			System.out.println("Last Name: ");
			String employeeLastName = scInput.next();
			newEmployee = new Employee(employeeID, employeeFirstName, employeeLastName);
			if(newEmployee.validate()) {
				System.out.println("Valid Employee");
			}else {
				System.out.println("NOT Valid Employee");
			}
		}
		
		// Read the Binary file
		String shipFileName = "data/ship.dat";
		String messagesFileName = "data/messages.dat";
		File shipFile = new File(shipFileName);
		File messagesFile = new File(messagesFileName);
		Ship newShip = DataReader.readShip(shipFile, messagesFile);
		
		newEmployee.setShipData(newShip);
		// Print Messages
		System.out.println(newEmployee.printMessages());
		
		// Send Messages
		if(newEmployee.sendMessages()) {
			System.out.println("Messages Validated and Sent Succesfully");
		}else {
			System.out.println("Error Sending Messages");
		}
	}

}
