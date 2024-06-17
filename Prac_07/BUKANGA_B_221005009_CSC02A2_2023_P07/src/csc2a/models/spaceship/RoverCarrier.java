package csc2a.models.spaceship;

import csc2a.models.rover.Rover;
import java.util.ArrayList;

/**
 * @author barbu
 *
 */
public class RoverCarrier extends Spaceship implements SpaceshipVehicle{

    //Array List of type rovers to hold rover object
    private ArrayList<Rover> Rovers;

    /**
     * @param manned
     */
    public RoverCarrier(boolean manned) {
        super(manned);
    }

    //getters and setters    
    public ArrayList<Rover> getRovers(){
        return this.Rovers;
    }
  

    public void setRovers(ArrayList<Rover> Rovers){
        this.Rovers = Rovers;
    }

    //
    /**
     * @Override
     * Overriden fly Method
     *		for each loop to perform drive method on each rover object in the array list
     */
    
    public void fly() {
    	
        System.out.printf("""
        		.......................
                : Rover Carrier		  :
                .......................
                Manned:%s
                """, 
                this.manned);

        for (Rover roversList : Rovers){
            roversList.drive();
        }
    }
    
    

}
