package acsse.csc03a3;
//************ Total Marks for Stack class: 10 marks
public class Stack<T extends Comparable<T>> {
	//TODO: COMPLETE CODE HERE
	/*
	 * Data member (1 mark)
	 */
    private Node<T> top;
    private int size;
	
	/*
	 * Default constructor (1 mark)
	 */
    public Stack() {
        top = null;
        size = 0;
    }
	
	
	/*
	 * Add an element to the stack, by adding at the beginning of the list (2 marks)
	 */
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

	
	
	
	/*
	 * Removes an element from the stack, by removing the first element in the list (2 marks)
	 */
    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            T data = top.data;
            top = top.next;
            size--;
            return data;
        }
    }
	
	
	/*
	 * Takes a look at the first element in the stack without removing it (2 marks)
	 */
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return top.data;
        }
    }
	
	
	/*
	 * Returns the size of the stack (1 mark)
	 */
    public int size() {
        return size;
    }
	
	
	/*
	 * Returns whether the stack is empty or not (1 mark)
	 */
    public boolean isEmpty() {
        return size == 0;
    }
    
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
	
}
