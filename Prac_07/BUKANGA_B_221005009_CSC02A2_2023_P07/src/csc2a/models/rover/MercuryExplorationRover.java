package csc2a.models.rover;

import csc2a.models.E_PLANET;

public class MercuryExplorationRover extends Rover implements RoverVehicle {
    private int temp; 
    private int numMinerals;

    /**
     * @param Planet
     * @param hasArmourPlating
     * @param hasWeaponMounts
     */
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

    public void setTemp(int Temp){
        this.temp = Temp;
    }

    public void setNumMinerals(int NumMinerals){
        this.numMinerals = NumMinerals;
    }

    

    /**
     *@Override
     *    Implement and Override Drive method from RoverVehicle
     */
    public void drive() {
        System.out.printf("""
    				    ****************************
                        |Mercury Exploration Rover |
                        ****************************
                        Rover Type:  \n
                        Armour Plating: %s
                        Weapon Mounts: %s
                        Temperature: %d
                        Number of minerals: %d
                        """,
                this.hasArmourPlating, this.hasWeaponMounts, this.temp, this.numMinerals);
    } 

}
