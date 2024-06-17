import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    //reading information of the ships
    public static ArrayList<SpaceShip> readSpaceShip(File file) {
    	//Ship Array List
        ArrayList<SpaceShip> shipList = new ArrayList<>();
        
        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
            	//collecting data of each ship and seperating the data into seperate information
                String line = sc.nextLine();
                String[] shipInfo = line.split(" ");		//array to store the information
                //The position of the ship
                double x = Double.parseDouble(shipInfo[0]);
                double y = Double.parseDouble(shipInfo[1]);
                String ID = shipInfo[2];
                StringBuilder sname = new StringBuilder(shipInfo[3]);
                for(int i=4; i<shipInfo.length; i++)
                {
                	sname.append(" ").append(shipInfo[i]);
                }
                	
                shipList.add(new SpaceShip(ID, sname.toString(), x, y));
            }
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

        return shipList;
    }

    //reading information of the planets
    public static ArrayList<Planet> readPlanet(File file) {
    	//PLanet Array List
        ArrayList<Planet> planetList = new ArrayList<>();
        
        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
            	//collecting data of each planet and seperating the data into seperate information
                String[] planetInfo = sc.nextLine().split(" ");		//array to store the information
                String pname = planetInfo[0];
                Color color = StringToColor(planetInfo[1]);
                double x = Double.parseDouble(planetInfo[2]);
                double y = Double.parseDouble(planetInfo[3]);
                int radius = Integer.parseInt(planetInfo[4]);

                planetList.add(new Planet(pname, color, radius, x, y));
            }
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        return planetList;
    }

    //setting the colour of the objects and stage
    private static Color StringToColor(String ColorString){
        return switch(ColorString){
            case "Red" -> Color.RED;
            case "Yellow" -> Color.YELLOW;
            case "Blue" -> Color.BLUE;
            case "Brown" -> Color.BROWN;
            case "Pink" -> Color.PINK;
            case "Green" -> Color.GREEN;
            default -> Color.GREY;
        };
    }
}
