/*
 * 
 */
package csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;
import java.io.FileOutputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
				

public class BUKAClientPane extends VBox // You may change the JavaFX pane layout
{
	private Button authBtn = new Button("Log in");
	private TextArea usernameInput = new TextArea();
	private TextArea password = new TextArea();
	private TextArea fileIdInput = new TextArea();
	private Button listBtn = new Button("List Files");
	private Button retreiveBtn = new Button("Retrieve file");
	private ScrollPane scrollOut = new ScrollPane();
	private ScrollPane scrollFileList = new ScrollPane();
	private VBox outPutContainer = new VBox();
	private VBox fileListContainer = new VBox();
	private Text heading = new Text("File list");

	DataInputStream din;
	PrintWriter pw;
	BufferedReader reader;

	private Socket socket;

	/**
	 * Instantiates a new BUKA client pane.
	 */
	public BUKAClientPane() {
		// setting up basic ui elements on the screen
		UIsetup();
		scrollOut.setContent(outPutContainer);
		scrollOut.setPrefHeight(200);

		scrollFileList.setContent(fileListContainer);
		scrollFileList.setPrefHeight(200);

		getChildren().add(scrollOut);

		setSpacing(10);
		setPadding(new Insets(10));

		try {
			socket = new Socket("localhost", 2018);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
			outPutContainer.getChildren().add(new Text("Connected sucessfully"));

		} catch (UnknownHostException e) {
			outPutContainer.getChildren().add(new Text(
					"Could not find host to connect to. make sure server is running before starting the client"));
		} catch (IOException e) {
			outPutContainer.getChildren().add(new Text(
					"Could not connect to the server. make sure server is running before starting the client"));
		}

		authBtn.setOnAction(e -> {
			String name = usernameInput.getText();
			String pass = password.getText();
			if (name.length() > 0 && pass.length() > 0) {
				pw.println("AUTH " + name + " " + pass);
				pw.flush();
				try {
					String resp = reader.readLine();
					outPutContainer.getChildren().add(new Text(resp)); // adding the response to the output container
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				outPutContainer.getChildren().add(new Text("Invalid inputs"));
			}

		});

		listBtn.setOnAction(e -> {
			pw.println("LIST");
			pw.flush();
			try {
				String resp = reader.readLine();
				if (resp.startsWith("200")) {
					String line = reader.readLine();
					fileListContainer.getChildren().clear();// removing old texts
					while (!line.equals("***")) {
						fileListContainer.getChildren().add(new Text(line));// getting new files names
						line = reader.readLine(); // reads a new line
					}

				}
				outPutContainer.getChildren().add(new Text(resp));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		
		retreiveBtn.setOnAction(e -> {
			String id = fileIdInput.getText();
			if(id.length() > 0) {
				getFile(id);
			}
		});

	}

	/**
	 * Gets the file.
	 *
	 * @param id the id
	 * @return the file
	 */
	private void getFile(String id) {
		pw.println("PDFRET " + id);
		pw.flush();
		try {
			String resp = reader.readLine();
			
			if(resp.startsWith("200")) {
				String[] tokens = resp.split("\s");
				int fileSize  = Integer.parseInt(tokens[1]);
				String fileName = tokens[2];
				
				File file = new File("data/client/" + fileName);
				
				
				FileOutputStream fout = new FileOutputStream(file);
				
			 byte[] buffer = new byte[1024];
			 int n = 0;
			 while (fileSize > 0) {
				 n = din.read(buffer);
				 fout.write(buffer, 0, n);
				 fileSize -= n;
			 }
			}
			
			outPutContainer.getChildren().add(new Text(resp));
		} catch (IOException e) {
			outPutContainer.getChildren().add(new Text(e.getMessage()));
		}
		
		
	}

	/**
	 * U isetup.
	 */
	private void UIsetup() {
		usernameInput.setPrefHeight(50);
		password.setPrefHeight(50);
		fileIdInput.setPrefHeight(50);

		usernameInput.setPromptText("Enter user name");
		password.setPromptText("Enter user password");
		fileIdInput.setPromptText("Enter file id");

		getChildren().addAll(usernameInput, password, authBtn, heading, scrollFileList, listBtn, fileIdInput,
				retreiveBtn);

	}

}
