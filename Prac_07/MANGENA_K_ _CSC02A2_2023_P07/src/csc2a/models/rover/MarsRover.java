package csc2a.models.rover;

import csc2a.models.E_PLANET;


public class MarsRover extends Rover implements RoverVehicle{

    private int numWheels;
    private int numArms;

    //constructor
    public MarsRover(E_PLANET Planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
        super(Planet, hasArmourPlating, hasWeaponMounts);
        this.numWheels = 4; //default number of wheels
        this.numArms = 2; //default number of arms
    }

    //Getters and Setters
    public int getNumWheels(){
        return this.numWheels;
    }

    public int getNumArms(){
        return this.numArms;
    }

    /*
    public void setNumWheels(int newNumWheels){
        this.numWheels = newNumWheels;
    }

    public void setNumArms(int newNumArms){
        this.numArms = newNumArms;
    }
     */

    public void setNumWheels(int NumWheels){
        this.numWheels = NumWheels;
    }

    public void setNumArms(int NumArms){
        this.numArms = NumArms;
    }

    /*
    public void drive() {
        System.out.printf("""
                        Rover Type: Mars Rover \n
                        """
    }
    */
    
    public void drive() {
		System.out.println("Rover type: Earth Traveler");
        System.out.println("Has Armour Plating: " + this.hasArmourPlating);
		System.out.println("Has Weapon Mounts: " + this.hasWeaponMounts);
		System.out.println("Number of Arms: " + this.numArms);
		System.out.println("Number of Wheels: " + this.numWheels);
	}


}
