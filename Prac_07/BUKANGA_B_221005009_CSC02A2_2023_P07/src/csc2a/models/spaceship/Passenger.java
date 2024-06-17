package csc2a.models.spaceship;

/**
 * @author barbu
 *
 */
public class Passenger extends Spaceship implements SpaceshipVehicle{
    private int numOfPassengers;

    /**
     * @param manned
     */
    public Passenger(boolean manned) {
        super(manned);
        this.numOfPassengers = 0;
    }


    public int getNumPassengers(){
        return this.numOfPassengers;
    }
    
    public void setNumPassengers(int NumberOfPassengers){
        this.numOfPassengers = NumberOfPassengers;
    }
    
    
    /**
     * @Override
     *
     */
    public void fly() {
        System.out.printf("""
        		.......................
                :Passenger Spaceship  :
                .......................
                Manned: %s
                Passengers: %d
                """, 
                this.manned, this.numOfPassengers);
    }

}
