package csc2a.models.spaceship;

public class Passenger extends Spaceship implements SpaceshipVehicle{
    private int numOfPassengers;

    public Passenger(boolean manned) {
        super(manned);
        this.numOfPassengers = 0;
    }


    public int getNumPassengers(){
        return this.numOfPassengers;
    }

    /*
    public void setNumPassengers(){
        this.numOfPassengers = newNumberOfPassengers;
    }
*/
    
    public void setNumPassengers(int NumberOfPassengers){
        this.numOfPassengers = NumberOfPassengers;
    }
    
    /*

    public void fly() {
        System.out.printf("""
                Manned: %s Passengers: %s
    }
    */    
    
    @Override
    public void fly() {
    	System.out.println("Ship type: Atmospheric Spaceship");
    	System.out.println("Manned: " + this.manned);
    	System.out.println("Number of Passengers: " + this.numOfPassengers);
    	}
}
