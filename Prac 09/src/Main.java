import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Compilation and Correct execution. - 10 MARKS

public class Main {

    public static String stripWord(String word) {
        return word.replaceAll("[^a-z]", "");
    }

    public static PositionList<IndexItem> lookup(String item, AVLTree<String, PositionList<IndexItem>> tree) {
        Entry<String, PositionList<IndexItem>> found = tree.find(item);
        if (found != null) {
            return found.getValue();
        }
        return null;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: " + args[0] + "<filename>");
        }

        //construct an AVL tree to act as an index
        AVLTree<String, PositionList<IndexItem>> indexTree = new AVLTree<>();

        try {
            Scanner sc = new Scanner(new File(args[0]));

            int lineNumber = 0;
            while (sc.hasNextLine()) {
                /** COMPLETE CODE HERE - 10 MARKS **/
            	 String line = sc.nextLine();
            	    lineNumber++;

            	    // Split the line into words using spaces and punctuation marks as delimiters.
            	    String[] words = line.toLowerCase().split("\\W+"); // Split on non-word characters

            	    for (String rawWord : words) {
            	        String word = stripWord(rawWord); // Strip unwanted characters (if any left)
            	        if (!word.isEmpty()) { // Check if the word is not empty after stripping
            	            PositionList<IndexItem> itemList = lookup(word, indexTree);

            	            if (itemList == null) {
            	                // Word not found, create a new list and index entry
            	                itemList = new NodePositionList<>();
            	                indexTree.insert(word, itemList);
            	            }

            	            // Add the current line number to the position list of the word
            	            itemList.addLast(new IndexItem(lineNumber, lineNumber));
            	        }
            	    }
	    }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + args[0] + " is not found");
        }

        System.out.println("There are " + indexTree.size() + " unique items in the index.");

        System.out.println("Looking up 'computer'");
        System.out.println("\t" + lookup("computer", indexTree));
        System.out.println("Looking up 'bob'");
        System.out.println("\t" + lookup("bob", indexTree));
        System.out.println("Looking up 'wikipedia'");
        System.out.println("\t" + lookup("wikipedia", indexTree));
        System.out.println("Looking up 'turing'");
        System.out.println("\t" + lookup("turing", indexTree));

    }

}

