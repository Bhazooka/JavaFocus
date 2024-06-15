import java.nio.ByteBuffer;
import java.util.Iterator;
//Overall Hash Table: 40 marks ***********************************************

public class HashTable<K,V> implements IMap<K,V> {
	Object[] table;
	int size;
	int capacity;

	/**
	 * Default constructor
	 */
	public HashTable() {
		this(100);
	}
	
	/**
	 * Constructor - provides the size of the array
	 * @param initialSize the initial size
	 */
	public HashTable(int initialSize) {
		this.capacity = initialSize;
		this.table = createArray(this.capacity);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Create an array that contains the positionslists that act as buckets
	 * @param size the size of the array to create
	 * @return the array that was created
	 * 4 marks ***********************************************
	 */
	private Object[] createArray(int size) {
		//TODO: COMPLETE CODE HERE
		table = new Object[size];
		for (int i = 0; i < size; i++) {
			table[i] = new PositionList<Entry<K,V>>();
		}
		
		return table;
	}
	
	/**
	 * Hash a string input
	 * @param str The input string
	 * @return the hash code for the integer
	 */
	private long hash(String str) {
		return hash(str.getBytes());
	}
	
	/**
	 * A hash an integer input
	 * @param inputInt the input input
	 * @return the hash code for the integer
	 */
	private long hash(int inputInt) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(inputInt).array();
		return hash(bytes);
	}
	
	/**
	 * Calculate a hash code using the djb2 hash function
	 * This hash function was created by Dan Bernstein, however
	 * normally it works with string inputs, this has been modified
	 * to work with byte inputs
	 * @param input the input array of bytes
	 * @return a hash value for the input
	 */
	private long hash(byte[] input) {
		long hash = 5381;
		for (int i = 0; i < input.length; i++) {
			hash = ((hash << 5) + hash) + input[i];
		}
		return hash;
	}
	
	/**
	 * Calculate a hash for either a string or an Integer
	 * @param element the element to hash
	 * @return a compressed hash code for the element
	 */
	private long hash(K element) {
		if (element instanceof Integer) {
			return hash((Integer)element) % capacity;
		}
		
		if (element instanceof String) {
			return hash((String)element) % capacity;
		}
		
		return (long)element.hashCode() % capacity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Remove an element from the hash table
	 * @param key the key of the element to remove
	 * 10 marks ***********************************************
	 */
	public V remove(K key) {
		//TODO: COMPLETE CODE HERE
		long index = hash(key);
		int i = (int)index;
		
		PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>)table[i];
		
		Iterator<Entry<K,V>> iterator = bucket.iterator();
		
		while (iterator.hasNext()) {
			
			Entry<K,V> e = iterator.next();
			
			if (e.getKey().equals(key)) {
				iterator.remove();
				size--;
				
				return e.getValue();
			}
		}
		
		return null;
	}


	@Override
	/**
	 * Get the value for a given key
	 * @param key the key for the element
	 * @returns the value for the associated key
	 * 10 marks ***********************************************
	 */
	public V get(K key) {
		//TODO: COMPLETE CODE HERE
		long index = hash(key);
		int i = (int)index;
		
		PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>)table[i];
		
		Iterator<Entry<K,V>> iterator = bucket.iterator();
		
		while (iterator.hasNext()) {
			Entry<K,V> e = iterator.next();
			if (e.getKey().equals(key)) {
				return e.getValue();
			}
		}
		return null;
	}


	@Override
	/**
	 * Put an element into the hash table
	 * @param key the key for the element (unique)
	 * @param value the value for the element
	 * 8 marks ***********************************************
	 */
	public void put(K key, V value) {
		//TODO: COMPLETE CODE HERE
		long index = hash(key);
		int i = (int)index;
		
		PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>)table[i];
		
		Iterator<Entry<K,V>> iterator = bucket.iterator();
		while (iterator.hasNext()) {
			Entry<K,V> element = iterator.next();
			if (element.getKey().equals(key)) {
				element.setValue(value);
			}
		}
		Entry<K,V> newEntry = new Entry<>(key, value);
		bucket.addLast(newEntry);
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Returns an iterator over the keys of the hash table
	 * 8 marks ***********************************************
	 */
	public Iterator<K> keys() {
		//TODO: COMPLETE CODE HERE
		PositionList<K> key = new PositionList<K>();
		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>)table[i];
			Iterator<Entry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				Entry<K,V> element = bucketIterator.next();
				key.addLast(element.getKey());
			}
		}
		
		return key.iterator();
		
	}

	@Override
	/**
	 * Returns an iterator over the values in the hash table
	 */
	public Iterator<V> values() {
		PositionList<V> val = new PositionList<V>();
		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>)table[i];
			Iterator<Entry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				Entry<K,V> element = bucketIterator.next();
				val.addLast(element.getValue());
			}
		}
		
		return val.iterator();
	}

	@Override
	/**
	 * Returns the size of the hashtable
	 */
	public int size() {
		return this.size;
	}

	@Override
	/**
	 * Returns true if the hashtable is empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}

}
