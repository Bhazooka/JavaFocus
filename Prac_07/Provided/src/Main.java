import java.util.ArrayList;
import java.util.Scanner;

import csc2a.factory.CivilianFactory;
import csc2a.factory.MilitaryFactory;
import csc2a.factory.VehicleFactory;
import csc2a.models.rover.E_PLANET;
import csc2a.models.rover.EarthTraveller;
import csc2a.models.rover.MarsRover;
import csc2a.models.rover.MercuryExplorationRover;
import csc2a.models.rover.Rover;
import csc2a.models.rover.RoverVehicle;
import csc2a.models.rover.VenusPathfinder;
import csc2a.models.spaceship.Atmospheric;
import csc2a.models.spaceship.Orbiter;
import csc2a.models.spaceship.Passenger;
import csc2a.models.spaceship.RoverCarrier;
import csc2a.models.spaceship.SpaceshipVehicle;

/**
 * @author Mr D Ogwok
 * @version P07
 */
public class Main{
	
	/* TODO: JavaDoc */
	private static Scanner sc = new Scanner(System.in);
	
	/* TODO: JavaDoc */
	public static void main(String[] args) {
		
		System.out.println("Hello Client, I am your contractor from Cyber Construction.");
		System.out.println("What sort of Vehicles would you like us to construct? (Enter a number) \n"
				+ "1. Civilian 2. Military");
		
		int category = sc.nextInt();	/* NOTE: Always check to make sure you are getting only valid values */
		
		boolean hasArmourPlating = false;
		boolean hasWeaponMounts = false;
		boolean manned = false;	// Unmanned
		
		if(category == 2) {
			hasArmourPlating = true;
			hasWeaponMounts = true;
			manned = true;	// Manned
			
			// Military Vehicles Factory
			MilitaryFactory mf = new MilitaryFactory();
			manufactureVehicle(mf, hasArmourPlating, hasWeaponMounts, manned);
		}else if(category == 1) {
			
			// Civilian Vehicles Factory
			CivilianFactory cf = new CivilianFactory();
			manufactureVehicle(cf, hasArmourPlating, hasWeaponMounts, manned);
		}else {
			System.err.println("Invalid value");
		}
	}
	
	/* TODO: JavaDoc */
	private static void manufactureVehicle(VehicleFactory vf, boolean hasArmourPlating, boolean hasWeaponMounts, boolean manned) {
		System.out.println("1. Rover 2. Spaceship");
		int type = sc.nextInt();	/* NOTE: Always check to make sure you are getting only valid values */
		switch(type) {
			case 1:
			{
				System.out.println("Which Planet is the Rover for? (Earth, Mars, Mercury, Venus)");
				E_PLANET planet = E_PLANET.valueOf(sc.next());
				// Create Rover
				RoverVehicle rover = null;
				if(planet.equals(E_PLANET.Earth)) {
					rover = vf.createRover(E_PLANET.Earth, hasArmourPlating, hasWeaponMounts);
					System.out.println("Created Earth Traveller Rover");
					EarthTraveller et = (EarthTraveller) rover;
					System.out.println("Enter Earth Traveller Rover Class ");
					int intClass = sc.nextInt();
					et.setATVClass(intClass);
				}else if(planet.equals(E_PLANET.Mars)) {
					rover = vf.createRover(E_PLANET.Mars, hasArmourPlating, hasWeaponMounts);
					System.out.println("Created Mars Rover");
					MarsRover mr = (MarsRover) rover;
					System.out.println("Enter number of wheels");
					int intWheels = sc.nextInt();
					mr.setNumWheels(intWheels);
					System.out.println("Enter number of arms");
					int intArms = sc.nextInt();
					mr.setNumArms(intArms);
				}else if(planet.equals(E_PLANET.Mercury)) {
					rover = vf.createRover(E_PLANET.Mercury, hasArmourPlating, hasWeaponMounts);
					System.out.println("Created Mercury Exploration Rover");
					MercuryExplorationRover mer = (MercuryExplorationRover) rover;
					System.out.println("Enter current temperature (integer)");
					int temp = sc.nextInt();
					mer.setTemp(temp);
					System.out.println("Enter number of minerals to record");
					int intMinerals = sc.nextInt();
					mer.setNumMinerals(intMinerals);
				}else if(planet.equals(E_PLANET.Venus)) {
					rover = vf.createRover(E_PLANET.Venus, hasArmourPlating, hasWeaponMounts);
					System.out.println("Created Venus Pathfinder Rover");
					VenusPathfinder vp = (VenusPathfinder) rover;
					System.out.println("Enter current temperature (integer)");
					int temp = sc.nextInt();
					vp.setTemp(temp);
					System.out.println("Enter current atmospheric pressure (integer)");
					double atmosphericPressure = sc.nextDouble();
					vp.setAtmosphericPressure(atmosphericPressure);
				}else {
					System.err.println("Not a valid planet");
				}
			
				// Drive Rover
				rover.drive();
				
				break;
			}
			case 2:
			{
				// Create Spaceship
				SpaceshipVehicle spaceship = null;
				System.out.println("What type of Spaceship should we create? (Atmospheric, Orbiter, Passenger, RoverCarrier)");
				String spaceshipType = sc.next(); /* NOTE: Always check to make sure you are getting only valid values */
				if(spaceshipType.equals("Atmospheric")) {
					spaceship = vf.createSpaceship(spaceshipType, manned);
					System.out.println("Created Atmospheric Spaceship");
					Atmospheric at = (Atmospheric) spaceship;
					System.out.println("Enter Planet to visit ");
					E_PLANET planet = E_PLANET.valueOf(sc.next()); /* NOTE: Always check to make sure you are getting only valid values */
					at.setPlanet(planet);
					System.out.println("Enter number of sensors ");
					int intSensors = sc.nextInt();
					at.setNumSensors(intSensors);
				}else if(spaceshipType.equals("Orbiter")) {
					spaceship = vf.createSpaceship(spaceshipType, manned);
					System.out.println("Created Orbiter Spaceship");
					Orbiter ob = (Orbiter) spaceship;
					System.out.println("Enter Planet to orbit ");
					E_PLANET planet = E_PLANET.valueOf(sc.next()); /* NOTE: Always check to make sure you are getting only valid values */
					ob.setPlanet(planet);
				}else if(spaceshipType.equals("Passenger")) {
					spaceship = vf.createSpaceship(spaceshipType, manned);
					System.out.println("Created Passenger Spaceship");
					Passenger ps = (Passenger) spaceship;
					System.out.println("Enter number of passengers ");
					int intPassengers = sc.nextInt();
					ps.setNumPassengers(intPassengers);
				}else if(spaceshipType.equals("RoverCarrier")) {
					spaceship = vf.createSpaceship(spaceshipType, manned);
					System.out.println("Created Rover Carrier Spaceship");
					RoverCarrier rc = (RoverCarrier) spaceship;
					// We shall carry a Mars Rover & Earth Traveller
					// System.out.println("We shall carry a Mars Rover & Earth Traveller");
					Rover marsRover = new MarsRover(E_PLANET.Mars, hasArmourPlating, hasWeaponMounts); 
					Rover earthTraveller = new EarthTraveller(E_PLANET.Earth, hasArmourPlating, hasWeaponMounts);
					ArrayList<Rover> rovers = new ArrayList<Rover>();
					rovers.add(marsRover);
					rovers.add(earthTraveller);
					rc.setRovers(rovers);
				}
				
				// Fly Spaceship
				spaceship.fly();
				break;
			}
			default:
			{
				System.err.println("Wrong Value Entered");
				
				break;
			}
		}
	}

}
