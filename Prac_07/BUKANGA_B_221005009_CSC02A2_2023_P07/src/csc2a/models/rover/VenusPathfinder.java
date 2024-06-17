package csc2a.models.rover;

import csc2a.models.E_PLANET;


/**
 * @author barbu
 *
 */
public class VenusPathfinder extends Rover implements RoverVehicle {
    private int temp;
    private double atmosphericPressure;

    /**
     * @param Planet
     * @param hasArmourPlating
     * @param hasWeaponMounts
     */
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

    public void setTemp(int Temp){
        this.temp = Temp;
    }

    public void setAtmosphericPressure(double AtmosphericPressure){
        this.atmosphericPressure = AtmosphericPressure;
    }

    
    /**
     *@Override
     *Overriden drive Method
     */
    public void drive() {
        System.out.printf("""
        				****************************
                        |Venus Path Finder		   |
                        ****************************
                        Has Armour Plating: %s
                        Has Weapon Mounts: %s
                        Temperature: %d
                        Atmospheric Pressure: %f
                        """,
                this.hasArmourPlating, this.hasWeaponMounts, this.temp, this.atmosphericPressure);
    }
    
}
