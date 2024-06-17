package csc2a.models.rover;

import csc2a.models.E_PLANET;

public class MercuryExplorationRover extends Rover implements RoverVehicle {
    private int temp; 
    private int numMinerals;

    public MercuryExplorationRover(E_PLANET Planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
        super(Planet, hasArmourPlating, hasWeaponMounts);
        this.temp = 0;
        this.numMinerals = 0;
    }
    
    //Getters and setters
    public int getTemp(){
        return this.temp;
    }

    public int getNumMinerals(){
        return this.numMinerals;
    }

    /* 
    public void setTemp(int newTemp){
        this.temp = newTemp;
    }
    */

    public void setTemp(int Temp){
        this.temp = Temp;
    }
    
    /*    
    public void setNumMinerals(int newNumMinerals){
        this.numMinerals = newNumMinerals;
    } 
    */

    public void setNumMinerals(int NumMinerals){
        this.numMinerals = NumMinerals;
    }

    
    //Implement and Override Drive method from RoverVehicle
    @Override
    public void drive() {
        System.out.printf("""
                        Rover Type: Mercury Exploration Rover \n
                        Has Armour Plating \t: %s
                        Has Weapon Mounts \t: %s
                        Temperature \t\t: %d
                        Number of minerals \t: %d
                        """,
                this.hasArmourPlating, this.hasWeaponMounts, this.temp, this.numMinerals);
    } 
    
 /*   
    //Implement and Override Drive method from RoverVehicle
    @Override  
    public void drive() {
		System.out.println("Rover type: Earth Traveler");
		System.out.println("Has Armour Plating: " + this.hasArmourPlating);
		System.out.println("Has Weapon Mounts: " + this.hasWeaponMounts);
		System.out.println("Minerals: " + this.numMinerals);
    }
*/
}