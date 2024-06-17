package csc2b.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

	

public class BUKAHandler implements Runnable {
	private Socket client;
	private DataInputStream din;
	private DataOutputStream dout;
	private PrintWriter pw;
	private BufferedReader reader;
	private boolean isLoggedIn = false;

	/**
	 * Instantiates a new BUKA handler.
	 *
	 * @param newConnectionToClient the new connection to client
	 */
	public BUKAHandler(Socket newConnectionToClient) {
		client = newConnectionToClient;
	}

	/**
	 * Run.
	 */
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(client.getOutputStream());
			dout = new DataOutputStream(client.getOutputStream());

			String line = reader.readLine();
			String[] commandTokens = line.split("\s");
			String command = commandTokens[0];
			while (!command.equals("LOGOUT")) {
				switch (command) {
				case "AUTH": {
					if (!matchUser(commandTokens[1], commandTokens[2])) {
						pw.println("500 could not authenticate");
						pw.flush();
					} else {
						isLoggedIn = true;
						pw.println("200 Logged in");
						pw.flush();

					}

					break;
				}
				case "LIST": {
					if (!isLoggedIn) {
						pw.println("500 authenticate yourself first");
						pw.flush();
					} else {
						sendFileList();
					}
					break;
				}

				case "PDFRET":
					if (!isLoggedIn) {
						pw.println("500 authenticate yourself first");
						pw.flush();

					} else {
						String id = commandTokens[1]; // possible id for the pdf file
						pdfRet(id);

					}
					break;
				}

				// reading in next command
				line = reader.readLine();
				System.out.println("line: " + line);
				commandTokens = line.split("\s");
				command = commandTokens[0];

			}

			// logout the client if the command was a logout command

			pw.println("200 you are logged out");
			pw.flush();

			// release resources
			client.close();
			din.close();
			dout.close();
			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Send file list.
	 */
	private void sendFileList() {
		ArrayList<String> fileList = getFileList();

		pw.println("200 okay file names coming");
		pw.flush();
		fileList.forEach(file -> {
			System.out.println(file);
			pw.println(file); // send each file name to the client
			pw.flush();
		});

		pw.println("***");
		pw.flush();
	}

	/**
	 * Pdf ret.
	 *
	 * @param id the id
	 */
	private void pdfRet(String id) {
		String fileName = idToFile(id);
		File file = new File("data/server/" + fileName);

		if (fileName.length() > 0 && file.exists()) {

			try {
				FileInputStream fin = new FileInputStream(file);

				pw.println("200 " + file.length() + " " + fileName); // sending a confirmation and the file size including the filename to the client
                pw.flush();
				byte[] buffer = new byte[1024];
				int n = 0;

				while ((n = fin.read(buffer)) != -1) {
					dout.write(buffer, 0, n); // sending the byte read in the buffer
					dout.flush(); // flush the buffer
				}

			} catch (IOException e) {
				pw.println("500 could not send file with ID " + id);
				pw.flush();
			}

		} else {
			pw.println("500 could not send file with ID " + id);
			pw.flush();
		}

	}

	/**
	 * Match user.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	private boolean matchUser(String username, String password) {
		boolean found = false;
		File userFile = new File("data/server/users.txt");
		try {
			// Code to search users.txt file for match with username and password
			Scanner scan = new Scanner(userFile);
			while (scan.hasNextLine() && !found) {
				String line = scan.nextLine();
				String lineSec[] = line.split("\\s");
				String name = lineSec[0];
				String pass = lineSec[1];

				if (name.equals(username) && pass.equals(password))
					return true;

			}
			scan.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return found;
	}

	/**
	 * Gets the file list.
	 *
	 * @return the file list
	 */
	private ArrayList<String> getFileList() {
		ArrayList<String> result = new ArrayList<String>();
		// Code to add list text file contents to the arraylist.
		File lstFile = new File("data/server/PdfList.txt");
		try {
			Scanner scan = new Scanner(lstFile);

			while (scan.hasNextLine()) {
				result.add(scan.nextLine());
			}

			scan.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * Id to file.
	 *
	 * @param ID the id
	 * @return the string
	 */
	private String idToFile(String ID) {
		String result = "";
		// Code to find the file name that matches strID
		File lstFile = new File("data/server/PdfList.txt");
		try {

			Scanner scan = new Scanner(lstFile);
			String line = "";
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				String[] tokens = line.split("\s");
				String id = tokens[0];

				if (id.equals(ID)) {
					return tokens[1];
				}

			}

			scan.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
