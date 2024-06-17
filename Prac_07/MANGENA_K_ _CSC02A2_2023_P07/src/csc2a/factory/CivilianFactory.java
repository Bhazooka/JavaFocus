/**
 * 
 */
package csc2a.factory;

import csc2a.models.E_PLANET;
import csc2a.models.rover.*;
import csc2a.models.spaceship.*;

public class CivilianFactory implements VehicleFactory{
	public RoverVehicle createRover(E_PLANET planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
	    RoverVehicle rover;
	    
	    switch (planet) {
	        case Earth:
	            rover = new EarthTraveller(planet, hasArmourPlating, hasWeaponMounts);
	            break;
	        case Mars:
	            rover = new MarsRover(planet, hasArmourPlating, hasWeaponMounts);
	            break;
	        case Mercury:
	            rover = new MercuryExplorationRover(planet, hasArmourPlating, hasWeaponMounts);
	            break;
	        case Venus:
	            rover = new VenusPathfinder(planet, hasArmourPlating, hasWeaponMounts);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid planet: " + planet);
	    }
	    return rover;
	}
	
/*	
    public RoverVehicle createRover(E_PLANET planet, boolean hasArmourPlating, boolean hasWeaponMounts) {
        return 
        if(planet){
            if 
                Earth -> new EarthTraveller(planet, hasArmourPlating, hasWeaponMounts);
            if else
                Mars -> new MarsRover(planet, hasArmourPlating, hasWeaponMounts);
            if else
                new MercuryExplorationRover(planet, hasArmourPlating, hasWeaponMounts);
            
        };
    }    
*/


    /**
     *@return spaceship
     */
    @Override
    public SpaceshipVehicle createSpaceship(String type, boolean manned) {
        SpaceshipVehicle spaceship = null;
        switch (type.toLowerCase()) {
            case "atmospheric":
                spaceship = new Atmospheric(manned);
                break;
            case "orbiter":
                spaceship = new Orbiter(manned);
                break;
            case "passenger":
                spaceship = new Passenger(manned);
                break;
            case "rovercarrier":
                spaceship = new RoverCarrier(manned);
                break;
        }
        return spaceship;
    }
    

/*
    public SpaceshipVehicle createSpaceship(String type, boolean manned) {
        return
        if(type.toLowerCase()){
            if 
                "atmospheric" -> new Atmospheric(manned);
            if else 
                "orbiter" -> new Orbiter(manned);
            if else
                "passenger" -> new Passenger(manned);
            if else
                "rovercarrier" -> new RoverCarrier(manned);
        };
        
        */
    
}
	

