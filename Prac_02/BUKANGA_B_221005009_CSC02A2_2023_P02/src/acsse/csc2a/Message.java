package acsse.csc2a;
import java.util.Random;

public class Message{

    String ID; //Starts wigth MSG then 6 additional digits
    String Language;    //Language of the text being transmitted
    String Contents;    //The text being transmitted
    String SourcePlanet;    //The Planet from which the Message is being sent
    String DestinationPlanet;   //The Planet to which the Message is being sent

    final String DEFAULT_ID = "UO";
    final String DEFAULT_LANGUAGE = "ENGLISH"; 
    final String DEFAULT_CONTENT = "N/A";
    final String DEFAULT_SOURCE_PLANET = "EARTH";
    final String DEFAULT_DESTINATION_PLANET = "MARS";

    //PLANET ENUM
    public enum PLANETS
    {
        MERCURY, 
        VENUS, 
        EARTH, 
        MARS, 
        JUPITER, 
        SATURN, 
        URANUS, 
        NEPTUNE, 
        PLUTO
    }

    //Create Instance of planet
    PLANETS planet;


    //Default constructor
    public Message()
    {
        this.ID = DEFAULT_ID;
        this.Language = DEFAULT_LANGUAGE;
        this.Contents = DEFAULT_CONTENT;
        this.SourcePlanet = DEFAULT_SOURCE_PLANET;  
        this.DestinationPlanet = DEFAULT_DESTINATION_PLANET;
    }

    //constructors
    public Message(String ID, String Language, String Contents, String SourcePlanet, String DestinationPlanet)
    {
        this.ID = ID;
        this.Language = Language;
        this.Contents = Contents;
        this. SourcePlanet = SourcePlanet;
        this.DestinationPlanet = DestinationPlanet;
    }

    /**
	 * @return Message ID.
	 */
    public String getID(){
        return ID;
    }

    /**
	 * @return Message Language.
	 */
    public String getLanguage(){
        return Language;
    }

    /**
	 * @return Message Content.
	 */
    public String getContents(){
        return Contents;
    }

    /**
	 * @return Message SourcePlanet.
	 */
    public String getSourcePlanet(){
        return SourcePlanet;
    }

    /**
	 * @return Message DestinationPlanet.
	 */
    public String getDestinationPlanet(){
        return DestinationPlanet;
    }


    /**
	 * @param generateRandomNumbers
	 * Function to generate random number.
	 */
    public static char[] generateRandomNumbers() 
    {
        char[] ANS = new char[4];    //an array of 4 random characters
        Random random = new Random();   //used with the java.util.Random, to generate random numbers
        for (int i = 0; i < 4; i++) {
            int rand_Num = random.nextInt(10);  //Random number from bounded to 10
            char rand_Char = (char) (rand_Num + '0'); //Casting to char and adding from 0 on the ascii table, adding it to reach our intended character(number)
            ANS[i] = rand_Char; //save the random characters and add the results to the char array
        }
        return ANS;
    }

    /**
	 * @param generateRandomNumbers
	 * Function to generate random number.
	 */
    public static char[] generateRandomMessage() 
    {
        char[] ANS = new char[6];    
        Random random = new Random();   
        for (int i = 0; i < 4; i++) {
            int rand_Msg = random.nextInt(10);  
            char rand_Char = (char) (rand_Msg + 'A'); //Casting to char and adding from 'A' on the ascii table, adding it to reach our intended character
            ANS[i] = rand_Char; 
        }
        return ANS;
    }

}

