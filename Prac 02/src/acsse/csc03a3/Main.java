package acsse.csc03a3;

import java.util.Scanner;

//[15 marks correctness]
public class Main {
		
	
	/*
	 * a function that removes the first occurrence of an operator in the operatorList
	 */
	public List<String> removeOperatorFromList(String operator, List<String> operatorList){
		//TODO: Complete code [5 marks]
		Node <String> nodeToRemove = operatorList.search(operator);
		if(nodeToRemove != null) {
		operatorList.remove(nodeToRemove);
		}
		return operatorList;
	}
		
	
	
	/*
	 * a function that adds an operator to the end of the current operator list (duplicates are allowed)
	 */
	public static List<String> addOperatorToList(String operator, List<String> operatorList){
		//TODO: Complete code [5 marks]
		operatorList.addLast( operator );
		return operatorList;
	}
	/*
	 * A procedure for reading a Bitmap, apply all the operators in the operator List and 
	 * write the final bitmap to disk as output.bmp   
	 */
	public static void processBitmapProcessList(String img, List<String> operatorList) {
		//TODO: Complete code [10 marks]
		BitmapProcessor<Integer> bit = new BitmapProcessor<>();
		Integer[][] arr = bit.readBitmap(img, Integer.class);
        
		
        Node<String> currentNode = operatorList.first();
        
        while(currentNode != null) {
        	String operator = currentNode.getElement();
        	
        	switch(operator) {
        	
        	case "ApplyGrayscaleFilter": {
        		arr = bit.applyGrayscaleFilter(arr, Integer.class);
        		break;
        	}
        	
        	case "Dilation" :
        		arr = bit.applyDilation(arr, Integer.class, null);
        		break ;
        		
        	case "Erosion":
        		arr = bit.applyErosion(arr, Integer.class, null);
        	    break ;
        	    
        	case "Grayscale" :
        		arr = bit.applyGrayscaleFilter(arr, Integer.class);
        		break;
        		
        	case "FlipHorizontally" :
        		arr = bit.flipHorizontally(arr, Integer.class);
        		break ;
        	}
        	currentNode = currentNode.getNext();
        }
        bit.writeBitmap("tux2.bmp", arr);
	}

	
    public static void main(String[] args) {
    	
    	List<String> operatorList = new List<>();
        boolean done = false;
        
        
        while (!done) {
            System.out.println("1. View List...");
            System.out.println("2. Add Operator to List");
            System.out.println("3. Remove Operator from List");
            System.out.println("4. Process List");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            
            switch(choice) {
            case "1":
            	if (operatorList.isEmpty()) {
            		System.out.println("List is empty. ");
            	}else { 
            		System.out.println(operatorList.toString());
            	}
            	
            	break;
            
            case "2": 
            	System.out.println("Enter operation to add");
            	operatorList = addOperatorToList(scanner.next(), operatorList);
            	break;
            	
            case "3":
            	System.out.println("Enter operation to remove ");
            	operatorList.remove(operatorList.search(scanner.next()));
            	break;
            
            case "4":
            	processBitmapProcessList("tux.bmp", operatorList);
            	break;
            
            
            case "5": {
            	done = true;
            }
        }
        
    }
	
}
}