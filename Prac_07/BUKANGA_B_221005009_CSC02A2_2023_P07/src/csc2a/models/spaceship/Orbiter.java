package csc2a.models.spaceship;

import csc2a.models.E_PLANET;

/**
 * @author barbu
 *
 */
public class Orbiter extends Spaceship implements SpaceshipVehicle{
    private E_PLANET Planet;

    /**
     * @param manned
     */
    public Orbiter(boolean manned)  {
        super(manned);
        this.Planet = E_PLANET.Earth;
    }


    public E_PLANET getPlanet(){
        return this.Planet;
    }

    public void setPlanet(E_PLANET Planet){
        this.Planet = Planet;
    }
  

    /**
     *@Override
     *Overriden Fly Method
     */
    
    public void fly() {
        System.out.printf("""
        		.......................
                :Orbiter Spaceship	  :
                .......................
                Manned: %s
                Planet: %s
                """, this.manned, this.Planet);
    }

}
