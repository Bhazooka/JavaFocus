package csc2a.models.spaceship;

import csc2a.models.E_PLANET;

/**
 * @author barbu
 *
 */
public class Atmospheric extends Spaceship implements SpaceshipVehicle{
    private E_PLANET Planet;
    private int numSensors;

    /**
     * @param manned
     */
    public Atmospheric(boolean manned) {
        super(manned);
        this.Planet = E_PLANET.Earth;
        this.numSensors = 0;
    }

    //getters and setters
    public E_PLANET getPlanet() {
        return Planet;
    }

    public int getNumSensors() {
        return numSensors;
    }

    public void setPlanet(E_PLANET Planet){
        this.Planet = Planet;
    }


    public void setNumSensors(int NumSensors){
        this.numSensors = NumSensors;
    }

   
    /**
     *@Override
     *Override fly Method
     */
    public void fly() {
        System.out.printf("""
        		.......................
                :Atmospheric Spaceship:
                .......................
                Manned: %s
                Planet: %s
                Sensors: %d
                """, 
                this.manned, this.Planet, this.numSensors);
    }
    
}
