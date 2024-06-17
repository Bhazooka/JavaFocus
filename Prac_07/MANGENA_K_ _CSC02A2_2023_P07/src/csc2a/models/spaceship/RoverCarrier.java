package csc2a.models.spaceship;

import csc2a.models.rover.Rover;
import java.util.ArrayList;

public class RoverCarrier extends Spaceship implements SpaceshipVehicle{

    private ArrayList<Rover> Rovers;

    public RoverCarrier(boolean manned) {
        super(manned);
    }
  
    public ArrayList<Rover> getRovers(){
        return this.Rovers;
    }

    /*     
    public void setRovers(){
        this.Rovers = newRovers;
    }
    */    

    public void setRovers(ArrayList<Rover> Rovers){
        this.Rovers = Rovers;
    }

    //Overriden fly Method
    @Override
    public void fly() {
    	System.out.println("Ship type: Atmospheric Spaceship");
    	System.out.println("Manned: " + this.manned);
    
        for (Rover rovs : Rovers){
            rovs.drive();
        }
    }
    
    

}
