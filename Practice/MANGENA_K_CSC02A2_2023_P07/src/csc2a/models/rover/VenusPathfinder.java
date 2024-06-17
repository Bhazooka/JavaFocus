package csc2a.models.rover;

import csc2a.models.E_PLANET;


public class VenusPathfinder extends Rover implements RoverVehicle {
    private int temp;
    private double atmosphericPressure;

    public VenusPathfinder(E_PLANET Planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
        super(Planet, hasArmourPlating, hasWeaponMounts);
        this.temp = 0;
        this.atmosphericPressure = 0;
    }

    //Getters and setters
    public int getTemp() {
        return temp;
    }

    public double getAtmosphericPressure() {
        return atmosphericPressure;
    }

    
/* 
    public void setTemp(int newTemp){
        this.temp = newTemp;
    }

    public void setAtmosphericPressure(double newAtmosphericPressure){
        this.atmosphericPressure = newAtmosphericPressure;
    }
*/

    public void setTemp(int Temp){
        this.temp = Temp;
    }

    public void setAtmosphericPressure(double AtmosphericPressure){
        this.atmosphericPressure = AtmosphericPressure;
    }

    
    //Overriden drive Method
    @Override
    public void drive() {
        System.out.printf("""
                        Rover Type: Venus Path Finder \n
                        Has Armour Plating \t: %s
                        Has Weapon Mounts \t: %s
                        Temperature \t\t: %d
                        Atmospheric Pressure : %f
                        """,
                this.hasArmourPlating, this.hasWeaponMounts, this.temp, this.atmosphericPressure);
    }
    
    /*   
    public void drive() {
		System.out.println("Rover type: Earth Traveler");
		System.out.println("Has Armour Plating: " + this.hasArmourPlating);
		System.out.println("Has Weapon Mounts: " + this.hasWeaponMounts);
		System.out.println("Pressure: " + this.atmosphericPressure);
	}
*/
}
