/**
 * A Skiplist Node
 * @param <E> The type of this node
 */
public class SLNode<E> implements Position<E> {

    // Data members: SLNodes for next, prev, above, below
    private SLNode<E> next;
    private SLNode<E> prev;
    private SLNode<E> above;
    private SLNode<E> below;
    private E element;

    /**
     * Constructor for a new SLNode.
     * @param next the next link
     * @param prev the prev link
     * @param above the above link
     * @param below the below link
     * @param element the element
     */
    public SLNode(SLNode<E> next, SLNode<E> prev, SLNode<E> above, SLNode<E> below, E element) {
    	this.element = element;
        this.next = next;
        this.prev = prev;
        this.above = above;
        this.below = below;
    }


    public E getElement() {
        return element;
    }

    public SLNode<E> getNext() {
        return next;
    }

    public SLNode<E> getPrev() {
        return prev;
    }

    public SLNode<E> getAbove() {
        return above;
    }

    public SLNode<E> getBelow() {
        return below;
    }

    public void setNext(SLNode<E> next) {
        this.next = next;
    }

    public void setPrev(SLNode<E> prev) {
        this.prev = prev;
    }

    public void setAbove(SLNode<E> above) {
        this.above = above;
    }

    public void setBelow(SLNode<E> below) {
        this.below = below;
    }

	@Override
	public E element() {
		return element;
	}
}
