package csc2a.models.rover;

import csc2a.models.E_PLANET;

/**
 * @author barbu
 *
 */
public class EarthTraveller extends Rover implements RoverVehicle {
    private int ATVClass;

    /**
     * @param Planet
     * @param hasArmourPlating
     * @param hasWeaponMounts
     */
    public EarthTraveller(E_PLANET Planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
        super(Planet, hasArmourPlating, hasWeaponMounts);
        this.ATVClass = 1;
    }
    
    public int getATVClass(){
        return this.ATVClass;
    }

    public void setATVClass(int ATVClass){
        this.ATVClass = ATVClass;
    }

    
    
    /**Implement and Override Drive method from RoverVehicle
     *@Override
     */
    @Override
    public void drive() {
        System.out.printf("""
        				****************************
                        |Earth Traveler			   |
                        ****************************
                        Class:%d
                        Armour Plating: %s 
                        Weapon Mounts: %s
                        """,
                this.hasArmourPlating, this.hasWeaponMounts, this.ATVClass);
    }

    
}