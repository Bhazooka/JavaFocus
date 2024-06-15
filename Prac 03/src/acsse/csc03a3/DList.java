package acsse.csc03a3;
//************ Total Marks for DList class: 40 marks
public class DList<T extends Comparable<T>> implements IList<T>, Cloneable{

	private Node<T> header = null;
	private Node<T> trailer = null;
	private Integer size = 0;
	
	/**
	 * Default constructor
	 */
	public DList() {
		trailer = new Node<T>(null, null, null);
		header = new Node<T>(trailer, null, null);
		trailer.setPrev(header);
		size = 0;
	}
	/* Clone method 
     * (5 marks)********************************
     */

//	public DList<T> clone() {
//		//TODO: Complete
//		
//		DList<T> newList = new DList();
//		Node<T> current = this.header;
//		
//		while(current!=null) {
//			newList.addLast(current.element);
//			current = current.next;
//		}
//				
//	}
	
	/**
	 * Add an element after a given node in the list
     * 
	 */
	@Override
	public Node<T> addAfter(Node<T> elem, T item) {
		Node<T> newNode = new Node<T>(null, null, item);
		newNode.setPrev(elem);
		newNode.setNext(elem.getNext());
		elem.getNext().setPrev(newNode);
		elem.setNext(newNode);
		size++;
		return newNode;
	}

	/**
	 * Add an element before a given node in a list
     * (5 marks)********************************************
	 */
	@Override
	public Node<T> addBefore(Node<T> elem, T item) {
		//TODO: Complete
		
		if (elem == null || item == null) {
			throw new IllegalArgumentException("One of the args is null");
		}
		
		Node<T> newNode = new Node<>(null, null, item);
		
		if (elem.getPrev() == null) {
			newNode.setNext(elem);
			elem.setPrev(newNode);
			header = newNode;
		} else {
			newNode.setPrev(elem.getPrev());
			newNode.setNext(elem);
			elem.getPrev().setNext(newNode);
			elem.setPrev(newNode);
		}
		return newNode;
	}

	/**
	 * Add an element to the start of the list
	 */
	public Node<T> addFirst(T item) {
		return addAfter(header, item);
	}
	
	/**
	 * Add an element to the end of the list
	 */
	public Node<T> addLast(T item) {
		return addBefore(trailer, item);
	}
	
	/**
	 * Remove a specified node from the list. The removed element is returned
     * (5 marks) ********************************************
	 */
	@Override
	public T remove(Node<T> elem) {
		//TODO: Complete
	    if (elem == null) {
	        return null;
	    }

	    // If the node to be removed is the head
	    if (elem.getPrev() == null) {
	        header = elem.getNext(); //letting the head be the next element/ 2nd element in the list
	        if (header != null) {
	            header.setPrev(null);
	        }
	        elem.setNext(null);
	        return (T) elem;
	    }

	    // If the node to be removed is not the head
	    Node<T> prevNode = elem.getPrev();
	    Node<T> nextNode = elem.getNext();

	    if (prevNode != null) {
	        prevNode.setNext(nextNode);
	    }
	    if (nextNode != null) {
	        nextNode.setPrev(prevNode);
	    }

	    elem.setPrev(null);
	    elem.setNext(null);

	    return (T) elem;
	}

	/**
	 * Returns the node that contains the element that is specified as a parameter
	 */
	@Override
	public Node<T> search(T elem) {
		
		if(this.header.getElement() == elem) {
			return this.header;
		}
		
		Node<T> currentNode = header.getNext();
		while (currentNode != trailer) {
			if (currentNode.getElement().equals(elem)) {
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}

	/**
	 * Returns true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return (header.getNext() == trailer);
	}

	/**
	 * Return the size of the list
	 */
	@Override
	public Integer size() {
		return size;
	}

	/**
	 * Return the first element in the list
	 */
	public Node<T> first() {
		return header.getNext();
	}
	
	/**
	 * Returns the last element in the list
	 */
	public Node<T> last() {
		return trailer.getPrev();
	}
	/*
	 * The overridden toString method that displays all the elements in 
	 * the list (as well as the header and trailer, as empty nodes).
	 */
	@Override
	public String toString() {
		String result = header.toString() + " <-> ";
		Node<T> currentNode = header.getNext();
		
		while (currentNode != trailer) {
			result += currentNode.toString() + " <-> ";
			currentNode = currentNode.getNext();
		}
		
		result += trailer.toString();
		return result;
	}	
}
