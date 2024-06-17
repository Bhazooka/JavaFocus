import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.lang.Math;


/**
 * @author barbu
 *	221005009 Prac-02
 */
public class Main {

	
	/**
	 * @param args
	 * 	Main method to execute program
	 */
	public static void main(String[] args) {
	

		try {			
			ServerSocket serverS = new ServerSocket(8888);
			System.out.println("Ready for incoming connection");
			
			//Socket needs to accept connection
			Socket socketAccept = serverS.accept();
			//Try PrintWriter printing this to the console and see which fits better
			System.out.println("HELLO - you may make 4 requests and I’ll try to detect your language");
			
			//Using buffered reader to read data from client by getting the data using the getInputStream and using InputStreamReader to convert the bytes into characters through.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketAccept.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socketAccept.getOutputStream());
			
			printWriter.println("HELLO - you may make 4 requests and I’ll try to detect your language");
			printWriter.flush();
			
			String input = "";
			
			
			int count = 0;
			
				
			//getting the input from the client
			input = bufferedReader.readLine().toLowerCase();
			if(input.equals("start"));
			{
				printWriter.println("REQUEST or DONE");
				printWriter.flush();
				
				input = bufferedReader.readLine().toLowerCase();
				
				if(input.equals("request"));
				{

					while(count < 5) {
					
					input = bufferedReader.readLine().toLowerCase();
							
					int choice = getRandomNumber();
					
					if(input.contains("ngiyabonga") || input.contains("mina")) {
						REPLY("I detect some Zulu", printWriter, count);
					}
					else if(input.startsWith("is")) {
						
						switch(choice)
						{
						case 1: 
							REPLY("Anglais?", printWriter , count);
							break;
						case 2:
							REPLY("English", printWriter, count);
							break;
						case 3:
							REPLY("Afrikaans?", printWriter, count);
							break;
						}
						
					}
					else if(input.contains("dumela")){

						REPLY("I greet you in Sotho!", printWriter, count);
					}
					else if(input.equals("done")) {
						count = 5;	//one way of terminating the project is by increasing count
					}
					else{
						switch(choice) {
						case 1:
							REPLY("Howzit", printWriter, count);
							break;
						case 2:
							REPLY("I'm still learning", printWriter, count);
							break;
						case 3:
							REPLY("No idea", printWriter, count);
							break;
							}
							
						}
					count++;
					}
					
				}
//				else if(input.equals("done")) {
//					count = 5;
//				}
				

			}

	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @param reply
	 * @param printWriter
	 * @param count
	 * 	Helper method to print out the response
	 */
	public static void REPLY(String reply, PrintWriter printWriter, int count) {
		printWriter.println("0" + count + "# " + reply);
		printWriter.flush();
	}
	
	
	
	/**
	 * @return
	 * 	Helper function to generate a random number between one and three
	 * 	Three represents the maximum number of choices a response can have
	 */
	public static int getRandomNumber() {
    	Random rand = new Random();
    	return rand.nextInt(3) + 1;
    }
	

}
