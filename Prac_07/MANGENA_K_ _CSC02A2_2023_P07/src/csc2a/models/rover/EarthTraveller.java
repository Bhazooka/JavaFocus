package csc2a.models.rover;

import csc2a.models.E_PLANET;

public class EarthTraveller extends Rover implements RoverVehicle {
    private int ATVClass;
    int AVTCLASS = 1;

    public EarthTraveller(E_PLANET Planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
        super(Planet, hasArmourPlating, hasWeaponMounts);
        this.ATVClass = AVTCLASS;
    }
    
    public int getATVClass(){
        return this.ATVClass;
    }

/* 
    public void setATVClass(int newATVClass){
        this.ATVClass = newATVClass;
    }
*/

    public void setATVClass(int ATVClass){
        this.ATVClass = ATVClass;
    }

            
    //@Override
    public void drive() {
		System.out.println("Rover type: Earth Traveler");
		System.out.println("Has Armour Plating: " + this.hasArmourPlating);
		System.out.println("Has Weapon Mounts: " + this.hasWeaponMounts);
		System.out.println("ATVClass: " + this.ATVClass);
	}

    
}