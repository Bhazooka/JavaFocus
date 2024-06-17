package csc2a.models.rover;

import csc2a.models.E_PLANET;

//Abstract Rover class implementing RoverVehicle interface to implement the Abstract Factory Design Pattern
public abstract class Rover implements RoverVehicle{
    /* 
    protected boolean hasWeaponMounts;
    protected boolean hasArmourPlating;
    protected E_PLANET Planet;
    */

    //variables
    boolean hasWeaponMounts;
    boolean hasArmourPlating;
    E_PLANET Planet;

    //constructor
    public Rover(E_PLANET Planet, boolean hasArmourPlating, boolean hasWeaponMounts){
        this.Planet = Planet;
        this.hasArmourPlating = hasArmourPlating;
        this.hasWeaponMounts = hasWeaponMounts;
    }

    //getters methods
    public boolean getHasWeaponMounts(){
        return this.hasWeaponMounts;
    }

    public boolean getHasArmourPlating(){
        return this.hasArmourPlating;
    }

    public E_PLANET getPlanet(){
        return this.Planet;
    }
}
