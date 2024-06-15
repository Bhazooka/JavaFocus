package acsse.csc03a3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {	
	
	/**
	 * A method for recursively search for a String in an array of Strings
	 * @param word - The word of type String that we are looking for.
	 * @param words - The String array containing all the words
	 * @param lo - The lower bound for the search
	 * @param hi - The upper bound for the search
	 * @return the index of the found word in the words array
	 * (10 marks) ********************************************
	 */
	public static int recursiveBinarySearch(String word, String[] words, int lo, int hi){		
		//TODO: Complete
		if (lo > hi)
			return -1;	//If the lower bound is greater than upper bound, then word not found
		
		int mid = lo + (hi - lo)/2;
		int wordToCompare = word.compareTo(words[mid]);	
		
		if (wordToCompare == 0) {
			return mid;
		}
		else if(wordToCompare < 0) {
			return recursiveBinarySearch(word, words, lo, mid - 1);
		}
		else { 
			return recursiveBinarySearch(word, words, mid + 1, hi);
		}
				
	}
	
	/**
	 * @param word - The String which its characters will be shuffled around
	 * @return The shuffled String
	 * (5 marks) ********************************************
	 */
	public static String mixCharacterOrder(String word){	
		//TODO: Complete	
		char[] charArray = word.toCharArray();	//convert string to array of characters
		Random random = new Random(); //random function to shuffle the characters
		
		for (int i = 1; i < charArray.length; i++) {
			int randomIndex = random.nextInt(i + 1);
			
			char temp = charArray[i];
			charArray[i] = charArray[randomIndex];
			charArray[randomIndex] = temp;
		}
		return new String(charArray);
	}
	/**
	 * The conundrum solver that uses the array dictionary, mixCharacterOrder 
	 * and recursive binary search.
	 * @param conundrum - The current conundrum that needs to be solved.
	 * @param words - The String array containing all the dictionary words
	 * @return A valid word that can be found in the conundrum
	 * (5 marks) ********************************************
	 */
	public static String solveConundrum1(String conundrum, String[] words){				
		//TODO: Complete
		String shuffleConundrum = mixCharacterOrder(conundrum);
		
		Arrays.sort(words);
		
		int index = recursiveBinarySearch(shuffleConundrum, words, 0, words.length - 1);
		
		if (index == -1 )
		{
			return null;
		} else {
			return words[index];
		}
	}
	
	/**
	 * The conundrum solver that uses the DList dictionary, mixCharacterOrder 
	 * and the DList search.
	 * @param conundrum - The current conundrum that needs to be solved.
	 * @param words - The String array containing all the dictionary words
	 * @return A valid word that can be found in the conundrum
	 */
	public static String solveConundrum2(String conundrum, DList<String> words){		
		while(words.search(conundrum) == null){
			conundrum = mixCharacterOrder(conundrum);
		}
		return conundrum;				
	}
	/**
	 * @param path - The location of the dictionary
	 * @return The String array of words  containing all the dictionary words
	 * (5 marks) ********************************************
	 */
	public static String[] loadPotentialDicitonary1(String path){
		//TODO: Complete	
		
		ArrayList<String> wordList = new ArrayList<>();
		
		try {
			File file = new File(path);
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNext()) {
				String word = scanner.nextLine().trim();
				wordList.add(word);
			}
			
		}catch (FileNotFoundException fne) {
			fne.printStackTrace();
			return new String[0];
		}
	
		
		return wordList.toArray(new String[0]);
		
		
	}	
	
	/**
	 * @param path - The location of the dictionary
	 * @return The String array of words  containing all the dictionary words
	 */
	public static DList<String> loadPotentialDictionary2(String path){		
		DList<String> wordsList = new DList<String>();
		File f = new File(path);		
		 
		try {
			Scanner s = new Scanner(f);
			
			while(s.hasNextLine()){
				wordsList.addLast(s.nextLine());					
			}			
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		System.out.println(wordsList.size()+ " entries loaded");
		return wordsList;
	}
	/*
	 * The main method
	 * //(10 marks for execution) ********************************************
	 */
	public static void main(String[] args) {
		final String PATH = "test.dict"; 
		String conundrum = "ionlitabo";
		
		System.out.println("Dictionary Load 1 begin");
		long startTime = System.currentTimeMillis();
		String[] words = loadPotentialDicitonary1(PATH);
		long endTime = System.currentTimeMillis();
		double totalTime = (endTime - startTime)/1000.0;
		System.out.println("Dictionary Load 1 completed in "+ totalTime+" seconds");
		
		System.out.println("Dictionary Load 2 begin");
		startTime = System.currentTimeMillis();
		DList<String> wordList = loadPotentialDictionary2(PATH);
		endTime = System.currentTimeMillis();
		totalTime = (endTime - startTime)/1000.0;
		System.out.println("Dictionary Load 2 completed in "+ totalTime+" seconds");
		
		System.out.println("Algorithm 1 Test begin");
		startTime = System.currentTimeMillis();
		//System.out.println("The found word is: "+solveConundrum1(conundrum, words));
		endTime = System.currentTimeMillis();
		totalTime = (endTime - startTime)/1000.0;
		System.out.println("Algorithm 1 Test completed in "+ totalTime+" seconds");
		
		System.out.println("Algorithm 2 Test begin");
		startTime = System.currentTimeMillis();
		System.out.println("The found word is: "+solveConundrum2(conundrum, wordList));
		endTime = System.currentTimeMillis();
		totalTime = (endTime - startTime)/1000.0;
		System.out.println("Algorithm 2 Test completed in "+ totalTime+" seconds");
	}

}
