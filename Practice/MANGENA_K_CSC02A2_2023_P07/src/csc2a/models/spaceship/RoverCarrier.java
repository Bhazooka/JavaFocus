package csc2a.models.spaceship;

import csc2a.models.rover.Rover;
import java.util.ArrayList;

public class RoverCarrier extends Spaceship implements SpaceshipVehicle{

    //Array List of type rovers to hold rover object
    private ArrayList<Rover> Rovers;

    public RoverCarrier(boolean manned) {
        super(manned);
    }

    //getters and setters    
    public ArrayList<Rover> getRovers(){
        return this.Rovers;
    }

    /*     
    public void setRovers(ArrayList<Rover> newRovers){
        this.Rovers = newRovers;
    }
    */    

    public void setRovers(ArrayList<Rover> Rovers){
        this.Rovers = Rovers;
    }

    //Overriden fly Method
    @Override
    public void fly() {
    	
        System.out.printf("""
                Ship Type: Rover Carrier
                Manned \t:%s
                """, 
                this.manned);
         
    	//System.out.println("Ship type: Atmospheric Spaceship");
    	//System.out.println("Manned: " + this.manned);
    
    	
        //for each loop to perform drive method on each rover object in the array list
        for (Rover R : Rovers){
            R.drive();
        }
    }
    
    

}
