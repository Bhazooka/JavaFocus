//Correctness 10 marks ***********************************************
import java.util.Random;


public class Main {	
	private static final int PROCESS_NO = 10;
	
	/**
	 * Method that allocates tasks based on priority
	 * @param tasks
	 * @param interns
	 * 10 marks ***********************************************
	 */
	public static void allocateInternsWithPriorityQueue(PriorityQueue<Integer, Task> tasks, Intern[] interns){
	//TODO:Complete code
		while (!tasks.isEmpty()) {
			Entry<Integer, Task> newEntry = tasks.removeMin();
			
			for (Intern intern : interns) {
				newEntry.getValue().setWorkCompleted(newEntry.getValue().getWorkCompleted() + intern.getWorkRate());
				intern.setEnergy(intern.getEnergy()- intern.getWorkRate());
				if (newEntry.getValue().getWorkCompleted()/newEntry.getValue().getWorkTotal() == 1) {
					System.out.println("Task " + newEntry.getValue().getName() + " completed");
					
				} if (intern.getEnergy() > 0) {
					System.out.println("Energy level " + intern.getEnergy());
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Heap-Based Priority Queue Intern Scheduler");
		System.out.println("==============================================");
		Random rand = new Random(System.currentTimeMillis());
		
		PriorityQueue<Integer, Task> taskQ = new PriorityQueue<Integer, Task>();		 
		Intern[] interns = new Intern[10]; 
		
		for(int i=0;i< 10;i++){
			interns[i] = new Intern();
			interns[i].setName("Intern "+i);
			System.out.println(interns[i]);
		}
		
		for(int j=0;j<10;j++){
			Task task = new Task();
			task.setName("Task "+j);
			System.out.println(task);
			taskQ.insert(new Entry<Integer, Task>(rand.nextInt(10),task));
		}
				
		allocateInternsWithPriorityQueue(taskQ, interns);			
	}
}