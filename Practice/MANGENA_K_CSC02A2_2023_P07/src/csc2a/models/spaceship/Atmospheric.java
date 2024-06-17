package csc2a.models.spaceship;

import csc2a.models.E_PLANET;

public class Atmospheric extends Spaceship implements SpaceshipVehicle{
    private E_PLANET Planet;
    private int numSensors;

    public Atmospheric(boolean manned) {
        super(manned);
        this.Planet = E_PLANET.Earth;
        this.numSensors = 0;
    }

    
    public E_PLANET getPlanet() {
        return Planet;
    }

    public int getNumSensors() {
        return numSensors;
    }

    public void setPlanet(E_PLANET Planet){
        this.Planet = Planet;
    }

    /*
    public void setNumSensors(int NewNumSensors){
        this.numSensors = NewNumSensors;
    }
    */

    public void setNumSensors(int NumSensors){
        this.numSensors = NumSensors;
    }



    //Override fly Method
   
    @Override
    public void fly() {
        System.out.printf("""
                Ship type: Atmospheric Spaceship \n
                Manned \t: %s
                Planet \t: %s
                Sensors : %d
                """, 
                this.manned, this.Planet, this.numSensors);
    }
    
    /*
    @Override
    public void fly() {
    	System.out.println("Ship type: Atmospheric Spaceship");
    	System.out.println("Manned: " + this.manned);
    	System.out.println("Sensors: " + this.numSensors);
    	System.out.println("Planet: " + this.Planet);
    	}
    	*/
}
