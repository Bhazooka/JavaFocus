package acsse.csc03a3;
//************ Total Marks for ArrayListIterator class: 5 marks
import java.util.Iterator;

/**
 * An iterator over a ArrayList. This will return the Item that is
 * contained in the list 
  * 
 */
public class ArrayListIterator<T> implements Iterator<T> {
	private ArrayList<T> list;
	private int cursor;
	
	/**
	 * The constructor
	 * @param list the list to iterate over
	 */
	public ArrayListIterator(ArrayList<T> list) {
		this.list = list;
		if (!list.isEmpty()) {
			this.cursor = 0;
		}
	}
	
	@Override
	/**
	 * Returns true if there next() will return an element
	 * ********** 2 marks ****************************
	 */
	public boolean hasNext() {
		return !list.isEmpty() && (cursor<list.size());
	}

	@Override
	/**
	 * Return the "next" item in the list and then advance the cursor.
	 * ********** 3 marks ****************************
	 */
	public T next() {
		T element = list.get(cursor);
		cursor++;
		return element;
	}

	@Override
	/**
	 * Should be used to remove the item from the list, but for now
	 * we do not include an implementation.
	 */
	public void remove() {
		//do nothing - keep java happy
	}

}
