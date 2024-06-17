package csc2a.models.spaceship;

import csc2a.models.E_PLANET;

public class Orbiter extends Spaceship implements SpaceshipVehicle{
    private E_PLANET Planet;

    public Orbiter(boolean manned)  {
        super(manned);
        this.Planet = E_PLANET.Earth;
    }


    public E_PLANET getPlanet(){
        return this.Planet;
    }

    /*
    public void setPlanet(E_PLANET newPlanet){
        this.Planet = newPlanet;
    }
    */
    
    public void setPlanet(E_PLANET Planet){
        this.Planet = Planet;
    }
  

    @Override
    public void fly() {
        System.out.printf("""
                Orbiter Spaceship
                -----------------
                Manned \t:%s
                Planet \t:%s
                """, this.manned, this.Planet);
    }

/*    
    @Override
    public void fly() {
    	System.out.println("Ship type: Atmospheric Spaceship");
    	System.out.println("Manned: " + this.manned);
    	System.out.println("Planet: " + this.Planet);
    	}
    	*/
}
