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
    public void setNumPassengers(int newNumberOfPassengers){
        this.numOfPassengers = newNumberOfPassengers;
    }
*/
    
    public void setNumPassengers(int NumberOfPassengers){
        this.numOfPassengers = NumberOfPassengers;
    }
    
    
    @Override
    public void fly() {
        System.out.printf("""
                Passenger Spaceship
                -------------------
                Manned \t\t: %s
                Passengers : %d
                """, this.manned, this.numOfPassengers);
    }
      
   /* 
    public void fly() {
    	System.out.println("Ship type: Atmospheric Spaceship");
    	System.out.println("Manned: " + this.manned);
    	System.out.println("Number of Passengers: " + this.numOfPassengers);
    	}
    	*/
}
