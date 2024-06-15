import java.util.Iterator;
import java.util.Random;
//Correctness 10 marks ***********************************************
public class Main {
	public static Random rand;
	
	//create a random string
	public static String randomString(int length) {
		char[] charArray = new char[length];
		for (int i = 0; i < length; i++) {
			charArray[i] = (char)('a' + rand.nextInt(25));
			if (rand.nextBoolean()) {
				charArray[i] -= 30;
			}
		}
		return new String(charArray);
	}
	
	public static void main(String[] args) {
		rand = new Random();
		HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
		
		//Insert the items
		for (int i = 0; i < 10; i++) {
			hashTable.put(rand.nextInt(1000), randomString(10));
		}
		
		//get the list of keys
		Iterator<Integer> keys = hashTable.keys();
		while (keys.hasNext()) {
			Integer key = keys.next();
			String value = hashTable.get(key);
			System.out.println(key + " -> " + value);
			
		}
		System.out.println("");
		
		//get the list of values
		System.out.println("Values:");
		Iterator<String> values = hashTable.values();
		while (values.hasNext()) {
			System.out.println(values.next());
			
		}
		System.out.println("");
		
		//get the list of keys
		System.out.println("Removing items:");
		keys = hashTable.keys();
		while (keys.hasNext()) {
			System.out.println("Removed: " + hashTable.remove(keys.next()));
			
		}
		System.out.println("");
		
		//get the list of values
		System.out.println("Showing values again:");
		values = hashTable.values();
		while (values.hasNext()) {
			System.out.println(values.next());
			
		}
		System.out.println("");
		
		System.out.println("String test:");
		HashTable<String, Integer> hashTableString = new HashTable<String, Integer>();
		//Insert the items
		for (int i = 0; i < 10; i++) {
			hashTableString.put(randomString(10), rand.nextInt(1000));
		}
		
		//get the list of keys
		Iterator<String> strKeys = hashTableString.keys();
		while (strKeys.hasNext()) {
			String key = strKeys.next();
			Integer value = hashTableString.get(key);
			System.out.println(key + " -> " + value);
			
		}
		System.out.println("");
		
		//get the list of values
		System.out.println("Values:");
		Iterator<Integer> strValues = hashTableString.values();
		while (strValues.hasNext()) {
			System.out.println(strValues.next());
			
		}
		System.out.println("");
		
		//get the list of keys
		System.out.println("Removing items:");
		strKeys = hashTableString.keys();
		while (strKeys.hasNext()) {
			System.out.println("Removed: " + hashTableString.remove(strKeys.next()));
			
		}
		System.out.println("");
		
		//get the list of values
		System.out.println("Showing values again:");
		strValues = hashTableString.values();
		while (strValues.hasNext()) {
			System.out.println(strValues.next());
			
		}
		System.out.println("");
		
	}
}
