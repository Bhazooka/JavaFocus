package acsse.csc03a3;
//************ Total Marks for correctness: 10 marks
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {	
	/*
	 * Reads all the current restaurants from the local database (binary file of a collection of restaurants)
	 * @param The path to the database 
	 * 
	 */
	public static ArrayList<Restaurant> readRestaurantsFromDB(String path){
		ArrayList<Restaurant> list = new ArrayList<Restaurant>(1);
		File f = new File(path);
		ObjectInputStream ois = null;
		Integer counter = 0;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			Restaurant r = null;
			while(true){
				try
				{
					r = (Restaurant)ois.readObject();
					list.add(counter, r);					
				}
				catch(EOFException e){
					break;
				}
			}			
		} catch (FileNotFoundException e) {
			System.err.println("File not found, please check path");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An error occured during reading from database");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Restaurant not recognized");
			e.printStackTrace();
		}
		finally{
			try {
				ois.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
		return list;
	}	
		
	/*
	 * Calculates the closest restaurant to your current location
	 * @param the list of restaurants and your current location
	 * @return the Restaurant that is closest
	 */
	public static Restaurant calculateClosestRestaurant(ArrayList<Restaurant> list, GPSPoint currentLocation ){
		Restaurant closestRestaurant = list.get(0);		
		double closestDistance = currentLocation.calculateDistance(closestRestaurant.getLocation());
		
		for(Restaurant r: list){			
			double distance = currentLocation.calculateDistance(r.getLocation());			
			if(distance < closestDistance){
				closestRestaurant = r;
				closestDistance = distance;								
			}
		}
		return closestRestaurant;
	}
	
	
	public static void main(String[] args) {
		// Step 1: Read Restaurant objects from binary file
        ArrayList<Restaurant> restaurants = readRestaurantsFromDB("restaurant.db");
        System.out.println(restaurants.size()+" restaurants loaded into ArrayList");

        // Step 2: Build the Binary Search Tree (BST)
        BinaryTree<String> restaurantTree = new BinaryTree<>();
        for (Restaurant restaurant : restaurants) {
        	if(restaurant!=null && restaurant.getName()!=null)
        		restaurantTree.insert(restaurant.getName());
        }
        System.out.println(restaurantTree.size()+" restaurants loaded into BinaryTree");
        // Step 3: Tree traversal tests
        
        System.out.println("Pre-order traversal test:");
		PositionList<String> out = new PositionList<String>();
		restaurantTree.PreorderElementTraversal(out, restaurantTree.root());		
		System.out.println(out);
		System.out.println("");
		
		System.out.println("In-order traversal test:");
		out = new PositionList<String>();
		restaurantTree.InorderElementTraversal(out, restaurantTree.root());		
		System.out.println(out);
		System.out.println("");
		
		System.out.println("Post-order traversal test:");
		out = new PositionList<String>();
		restaurantTree.PostOrderElementTraversal(out, restaurantTree.root());		
		System.out.println(out);
		System.out.println("");

        // Step 4: Search query from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter restaurant name to search:");
        String query = scanner.nextLine();
        
        Boolean result = restaurantTree.binarySearch(query);
        if (result) {
            System.out.println("Restaurant Found ");
        } else {
            System.out.println("Restaurant not found.");
        }
        
        //Step 5: ArrayList Insertion test
		
		Restaurant r1 = new Restaurant("Student Center","Your go to place.", new GPSPoint(27.998614,-26.183000));
		restaurants.add(0, r1);
		
		Restaurant r2 = new Restaurant("Chaf Pozi","A good old shisanyama place.", new GPSPoint(28.002175,-26.181158));
		restaurants.add(0, r2);
		
		System.out.println("Total restaurants loaded into ArrayList are now "+restaurants.size());
				
		//Step 6: GPS-based Find Test
		Scanner sc = new Scanner(System.in);
		String gps = "28.002175:-26.181158";
		while(!gps.toLowerCase().equals("q")){
			System.out.println("Enter your GPS coordinates in the long:lat format ('q' to quit): ");
			gps = sc.nextLine();
			if(gps.toLowerCase().equals("q"))
				break;
			Double longitude = Double.parseDouble(gps.split(":")[0]);
			Double latitude = Double.parseDouble(gps.split(":")[1]);
			GPSPoint currentLocation = new GPSPoint(longitude,latitude);		
			System.out.println("The closest restaurant to "+currentLocation+" is: \n "+calculateClosestRestaurant(restaurants, currentLocation));		
		}
		sc.close();
	}

}
