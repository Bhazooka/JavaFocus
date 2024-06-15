//Overall PriorityQueue class: 5 marks ***********************************************

public class PriorityQueue<K extends Comparable <? super K>,V>{
	private PositionList<Entry<K,V>> list = null;
	
	/*
	 * Default constructor
	 */
	public PriorityQueue(){
		this.list = new PositionList<Entry<K,V>>();
	}
	
	/*
	 * Insert method that store the entry in an unordered manner (you can add it at the end)	 
	 */
	public void insert(Entry<K,V> entry){
		list.addLast(entry);		
	}
	
	/*
	 * Remove the entry with the highest priority (typically 0 as an integer)
	 * by performing a Heap sort and returning the root entry
	 * 5 marks ***********************************************
	 */
	public Entry<K,V> removeMin(){
		//TODO:Complete code
		if (isEmpty()) return null;
		Heap<K,Entry<K,V>> replacementHeap = new Heap<>();
		for (Entry<K,V> e : list) {
			replacementHeap.insert(e.getKey(), e);
		}
		
		Entry<K,V> firstPriority = replacementHeap.removeMin().getValue();
		
		Position<Entry<K,V>> eleRemove = list.search(firstPriority);
		if (eleRemove != null) {
			list.remove(eleRemove);
		}
		
		return firstPriority;
		
	}
	
	/*
	 * Returns the entry with the highest priority (typically 0 as an integer) 
	 */
	public Entry<K,V> min(){
		Heap<K,V> heap = new Heap<>();
		for(Entry<K,V> e: list){
			heap.insert(e.getKey(),e.getValue());
		}
		return heap.removeMin();
	}	
	
	/*
	 * Returns the size of the Priority Queue
	 */
	public Integer size(){
		return this.list.size();
	}
	
	/*
	 * Returns whether the Priority Queue is empty or not.
	 */
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
	/*
	 * The overridden toString method
	 */
	public String toString(){
		return list.toString();
	}
	/**
	 * Convert a Position<T> into a Entry<K,V>
	 * @param p a Position<T> to convert
	 * @return the corresponding Entry<K,V>
	 */
	@SuppressWarnings("unchecked")
	private Entry<K,V> checkPosition(Position<Entry<K,V>> p) {
		if (!(p instanceof Entry<?,?>)) {
			throw new PositionException("Invalid Position");
		}
		
		return (Entry<K,V>)p;
	}
}