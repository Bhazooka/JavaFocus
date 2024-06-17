package acsse.csc2a;

public class Ships{

    String ID; //starts with SH and then an additional 4 digits
    String Name; 
    String[] Messages;  //an array of messages

    final String DEFAULT_ID = "UO";
    final String DEFAULT_NAME = "UO";
    final String DEFAULT_MSG = "COOL";

    /**
	 * Creates a Ship with a unique ID and type.
	 * @param Ships
	 * 			The unique ID of the telescope.
	 * @param Name
	 *        	The Ship Name.
	 */
    public Ships(String ID, String Name, String Message1, String Message2, String Message3)
    {
        this.ID = ID;
        this.Name = Name;
        this.Messages = new String[]{Message1, Message2, Message3};
    }

    /**
	 * @return The Ship ID.
	 */
    public String getID(){
        return ID;
    }

    /**
	 * @return The Ship NAME.
	 */
    public String getName(){
        return Name;
    }    

    /**
	 * @param printEverything
	 * 			The printEverything function Everthing else.
	 */
    public void printEverything(String ID, String Language, String Messages, String Content, String Source_Planet, String Destination_Planet)
    {
        
        System.out.print(
                            "SHIP_ID: " + ID + "\n" + 
                            "LANGUAGE: "+ Language + "\n" + 
                            "CONTENT: "+ Content + "\n\n" +
                            "SOURCE PLANET: \t" + "\t\tDESTINATION PLANET: " + "\n" +
                            Source_Planet + "\t\t ------> \t" + Destination_Planet + "\n\n"
                        );             
    }   

    /**
	 * @param printMessage
	 * 			The printMessage function to deisplay 3 messages.
	 */
    public void printMessages(String[] Mesaages)
    {   
        for(int z = 0; z <= 2; z++)
        {
            System.out.print("MESSAGES"+ (z+1) + ": \t" + Messages[z] + z + "\n");             
        }
    }

}