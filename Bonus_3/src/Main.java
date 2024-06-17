import java.io.File;
import java.util.ArrayList;

/**
 * @author Mr D Ogwok
 * @version Bonus Practical Three - Binary File IO
 */
public class Main {
	/* TODO: JavaDoc */
	public static void main(String[] args) {
		
		String fileName = "data/transmitters.dat";
		
		ArrayList<Transmitter> transmitters = FileIO.readFile(new File(fileName));
		
		for(Transmitter transmitter: transmitters) {
			System.out.println(transmitter.getID() + " " + transmitter.getName() + " " + transmitter.getFrequency() + 
			" " + transmitter.getWeight());
		}

	}

}
