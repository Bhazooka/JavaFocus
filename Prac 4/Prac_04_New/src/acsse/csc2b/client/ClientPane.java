package acsse.csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClientPane extends GridPane {

    private Socket clientSocket; // Socket

    // Streams
    private InputStream inputStream;
    private OutputStream outputStream;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private String[] dataList;

    private Button connectButton = new Button();
    private Button pullButton = new Button();
    private Button downloadButton = new Button();
    private Button uploadButton  = new Button();
    private Button displayButton  = new Button();

    private Label listLabel = new Label() ;
    private Label responseLabel = new Label();
    private Label idLabel = new Label();

    TextArea listTextArea;
    private TextArea responseTextArea = new TextArea();
    private TextField imageIDField = new TextField();
    
    private String fileName = "";
    
    /**
     * The function sets the Dimensions of the GUI
     * The function handles the objects and places 
     * 	everything in the GUI and sets the 
     * 	vertical and horizontal gaps
     */
    private void GUI() {
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        connectButton.setText("CONNECT");
        pullButton.setText("PULL");
        downloadButton.setText("DOWNLOAD");
        uploadButton.setText("UPLOAD");
        displayButton.setText("DISPLAY DOWNLOAD");
        responseLabel.setText("Response: ");
        idLabel.setText("Enter Image ID: ");

        listTextArea = new TextArea();
        responseTextArea.setPrefHeight(100);

        listTextArea.setPrefHeight(100);

        VBox leftColumn = new VBox(10,
            connectButton,
            pullButton,
            uploadButton,
            listLabel
            
        );
        leftColumn.setAlignment(Pos.CENTER_LEFT);

        VBox rightColumn = new VBox(10,
            
            new HBox(10, responseLabel),
            listTextArea,
            new HBox(10,idLabel, imageIDField, downloadButton),
            responseTextArea,
            displayButton
        );
        rightColumn.setAlignment(Pos.CENTER_LEFT);

        HBox contentContainer = new HBox(20, leftColumn, rightColumn);
        contentContainer.setAlignment(Pos.CENTER);

        add(contentContainer, 0, 0, 4, 1);
    }

    

    /**
     * Constructor to Initialize all objects
     * 	Here we handle the button click events
     * 	as well as files. 
     * The function contains the 5 buttons
     * 	needed to process the: 
     * 		-List PULL request, 
     * 		-Image DOWNLOAD command
     * 		-Image UPLOAD command
     * 		-Socket CONNECT request
     * The function handles the GUI
     * @param stage
     */
    public ClientPane(Stage stage) {
        GUI();

        connectButton.setOnAction((e) -> {
            try {
                clientSocket = new Socket("localhost", 9876);
                outputStream = clientSocket.getOutputStream();
                inputStream = clientSocket.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                printWriter = new PrintWriter(outputStream);
                dataInputStream = new DataInputStream(inputStream);
                dataOutputStream = new DataOutputStream(outputStream);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        
        uploadButton.setOnAction((e) -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);
            String fileName = selectedFile.getName();
            int fileID = dataList.length + 1;
            printWriter.println("UPLOAD " + fileID + " " + fileName + " " + selectedFile.length());
            printWriter.flush();
            System.out.println("Upload command sent from the client");

            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(selectedFile);
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                    dataOutputStream.write(buffer, 0, bytesRead);
                    dataOutputStream.flush();
                }
                fileInputStream.close();
                System.out.println("File sent for Upload to server");
                String response = bufferedReader.readLine();
                responseTextArea.appendText("Status of uploaded file: " + response);
            } catch (FileNotFoundException fnfe2) {
                fnfe2.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });

        downloadButton.setOnAction((e) -> {
            int imageID = Integer.parseInt(imageIDField.getText());
            printWriter.println("DOWNLOAD " + imageID);
            printWriter.flush();
            String response = "";

            try {
                response = bufferedReader.readLine();
                int fileSize = Integer.parseInt(response);
                responseTextArea.appendText("File Received Size: " + response);

                for (String data : dataList) {
                    StringTokenizer tokenizer = new StringTokenizer(data);
                    String id = tokenizer.nextToken();
                    String name = tokenizer.nextToken();
                    if (id.equals(imageIDField.getText())) {
                        fileName = name;
                    }
                }

                File downloadedFile = new File("data/client/" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(downloadedFile);

                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                int totalBytes = 0;
                while (totalBytes != fileSize) {
                    bytesRead = dataInputStream.read(buffer, 0, buffer.length);
                    fileOutputStream.write(buffer, 0, bytesRead);
                    fileOutputStream.flush();
                    totalBytes += bytesRead;
                }
                fileOutputStream.close();
                System.out.println("File saved");

            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });
        
        pullButton.setOnAction((e) -> {
            input(printWriter, "PULL");
            String response = "";
            response = reply(bufferedReader);
            System.out.println(response);
            dataList = response.split("#");
            for (String data : dataList) {
                listTextArea.appendText(data + "\n");
            }
        });

        displayButton.setOnAction((e) -> {
            Image image = new Image("file:data/client/" + fileName);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            add(imageView, 0, 0, 10, 10);
        });
    }

    /**
     * Function to print the Server response
     * 
     * @param bufferedReader
     * @return
     */
    private String reply(BufferedReader bufferedReader) {
        String response = "";
        try {
            response = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Function to capture user input
     * @param printWriter
     * @param message
     */
    private void input(PrintWriter printWriter, String message) {
        printWriter.println(message);
        printWriter.flush();
    }
}
