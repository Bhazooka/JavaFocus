import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class GUI implements ActionListener{
	JFrame frame;
	JTextArea textfield;
	
	JButton Execute;
	
	
	GUI(){
		
		frame = new JFrame("Port Loop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.setLayout(null);
		
		textfield = new JTextArea();
		textfield.setBounds(100, 100, 600, 300);
		textfield.setEditable(true);
		
		//Adding button
		Execute = new JButton("Run Code");
		Execute.setBounds(250, 450, 300, 50);
		
		//Theres no need to add panel
		frame.add(textfield);
		frame.add(Execute);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent a) {
		StringBuilder output = new StringBuilder();
		
		if(a.getSource() == Execute ) {
	        for (int port = 1; port <= 3000; port++) {
	        	
	            try (Socket s = new Socket("localhost", port)) {
					//System.out.println("Program connected to localhost: " + s.getPort());
					//System.out.println("Local Port of the connection: " + s.getLocalPort());
					//System.out.println("Remote Host of the connection: " + s.getPort());
	            	output.append("\nProgram connected to localhost: " + s.getPort());
	            	output.append("\nLocal Port of the connection: " + s.getLocalPort());
	            	output.append("\nRemote Host of the connection: " + s.getPort());
	                
	            } catch (IOException e) {
	                //System.err.println("Couldn't connect to port " + port);
	            	output.append("\nCouldn't connect to port " + port);
	            }
	        }
	        
	    	
	    	try {
				InetAddress IP = InetAddress.getLocalHost();
				//System.out.println("The computer IP Address: " + IP.getHostAddress());
				output.append("\nThe computer IP Address: " + IP.getHostAddress());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
		
		textfield.setText(output.toString());
		
	}
		
	
	
	public static void main(String[] args) {
		GUI app = new GUI();
	}
}
