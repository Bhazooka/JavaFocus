package csc2a.factory;

import csc2a.models.E_PLANET;
import csc2a.models.spaceship.SpaceshipVehicle;
import csc2a.models.rover.RoverVehicle;

/**
 * @author barbu
 *Interface class to implement Design Pattern
 */
public interface VehicleFactory {
    /**
     * @param planet
     * @param hasArmourPlating
     * @param hasWeaponMounts
     * @return
     */
    RoverVehicle createRover(E_PLANET planet, boolean hasArmourPlating, boolean hasWeaponMounts);
    SpaceshipVehicle createSpaceship(String type, boolean manned);
}
