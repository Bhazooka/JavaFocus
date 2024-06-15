import java.util.Comparator;
import java.util.Iterator;


public class AVLTree<K, V> extends BinarySearchTree<K, V> implements Dictionary<K, V> {

    public AVLTree(Comparator<K> c) {
        super(c);
    }

    public AVLTree() {
        super();
    }

    /**
     * A node in the AVL Tree
     * @param <K> The key that will be stored
     * @param <V> The value that will be stored
     */
    protected static class AVLNode<K, V> extends BTNode<Entry<K, V>> {
        protected int height;

        AVLNode() {
        }

        AVLNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent, BTPosition<Entry<K, V>> left, BTPosition<Entry<K, V>> right) {
            super(element, parent, left, right);
            height = 0;
            if (left != null)
                height = Math.max(height, 1 + ((AVLNode<K, V>) left).getHeight());
            if (right != null)
                height = Math.max(height, 1 + ((AVLNode<K, V>) right).getHeight());
        }

        public void setHeight(int h) {
            height = h;
        }

        public int getHeight() {
            return height;
        }
    }

    /**
     * Creates a new node that can be inserted into the tree
     * @param element The element that the node contains
     * @param parent A reference to the parent
     * @param left A reference to the left child
     * @param right A reference to the right child
     * @return
     */
    protected BTPosition<Entry<K, V>> createNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent, BTPosition<Entry<K, V>> left, BTPosition<Entry<K, V>> right) {
        return new AVLNode<K, V>(element, parent, left, right);
    }

    /**
     * Return the height for a position in the AVL tree
     * @param p The position
     * @return returns the height of the node
     */
    protected int height(Position<Entry<K, V>> p) {
        return ((AVLNode<K, V>) p).getHeight();
    }

    /**
     * Set the height for a node in the tree
     * @param p The position of the node in the tree
     */
    protected void setHeight(Position<Entry<K, V>> p) {
        ((AVLNode<K, V>) p).setHeight(1 + Math.max(height(left(p)), height(right(p))));
    }

    /**
     * Return if the position is balanced, if the difference of the heights of the children are less than or equal to 1
     * @param p The position (root) of two children in the tree
     * @return true if the tree is balanced at this point
     */
    protected boolean isBalanced(Position<Entry<K, V>> p) {

        int bf = height(left(p)) - height(right(p));
        return ((-1 <= bf) && (bf <= 1));
    }

    /**
     * Return the taller of the two children for a position
     * @param p The parent of two children in the tree
     * @return The taller of the two children
     */
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        /** COMPLETE CODE HERE  - 5 MARKS **/ 
    	Position<Entry<K, V>> leftChild = left(p);
        Position<Entry<K, V>> rightChild = right(p);
        int leftHeight = height(leftChild);
        int rightHeight = height(rightChild);
        if (leftHeight > rightHeight) return leftChild;
        if (rightHeight > leftHeight) return rightChild;
        // If heights are the same, return the child on the same side as its parent's taller child
        if (isRoot(p)) return leftChild; // If p is root, just default to leftChild
        return (p == left(parent(p))) ? leftChild : rightChild;
    	
    }

    /**
     * Rebalance the tree from a starting z position
     * @param zPos A z position in the tree
     */
    protected void rebalance(Position<Entry<K, V>> zPos) {

        if (isInternal(zPos))
            setHeight(zPos);
        while (!isRoot(zPos)) {
            zPos = parent(zPos);
            setHeight(zPos);
            if (!isBalanced(zPos)) {
                Position<Entry<K, V>> xPos = tallerChild(tallerChild(zPos));
                zPos = restructure(xPos);
                setHeight(left(zPos));
                setHeight(right(zPos));
                setHeight(zPos);
            }
        }
    }

    /**
     * Restructure the tree given an x position in the tree. The Z and Y position must be calculated
     * @param x a X position in the tree.
     * @return The root of the restructured tree
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        BTPosition<Entry<K, V>> a = null, b = null, c = null, t1 = null, t2 = null, t3 = null, t4 = null;

        Position<Entry<K, V>> y = parent(x);
        Position<Entry<K, V>> z = parent(y);
        boolean xLeft = (x == left(y));
        boolean yLeft = (y == left(z));

        if (xLeft && yLeft) {  // Left-Left Case
            a = (BTPosition<Entry<K, V>>) x;
            b = (BTPosition<Entry<K, V>>) y;
            c = (BTPosition<Entry<K, V>>) z;
            t1 = (BTPosition<Entry<K, V>>) left(a);
            t2 = (BTPosition<Entry<K, V>>) right(a);
            t3 = (BTPosition<Entry<K, V>>) right(b);
            t4 = (BTPosition<Entry<K, V>>) right(c);
        } else if (!xLeft && !yLeft) {  // Right-Right Case
            a = (BTPosition<Entry<K, V>>) z;
            b = (BTPosition<Entry<K, V>>) y;
            c = (BTPosition<Entry<K, V>>) x;
            t1 = (BTPosition<Entry<K, V>>) left(a);
            t2 = (BTPosition<Entry<K, V>>) left(b);
            t3 = (BTPosition<Entry<K, V>>) left(c);
            t4 = (BTPosition<Entry<K, V>>) right(c);
        } else if (xLeft && !yLeft) {  // Left-Right Case
            a = (BTPosition<Entry<K, V>>) y;
            b = (BTPosition<Entry<K, V>>) x;
            c = (BTPosition<Entry<K, V>>) z;
            t1 = (BTPosition<Entry<K, V>>) left(a);
            t2 = (BTPosition<Entry<K, V>>) left(b);
            t3 = (BTPosition<Entry<K, V>>) right(b);
            t4 = (BTPosition<Entry<K, V>>) right(c);
        } else {  // Right-Left Case
            a = (BTPosition<Entry<K, V>>) z;
            b = (BTPosition<Entry<K, V>>) x;
            c = (BTPosition<Entry<K, V>>) y;
            t1 = (BTPosition<Entry<K, V>>) left(a);
            t2 = (BTPosition<Entry<K, V>>) left(b);
            t3 = (BTPosition<Entry<K, V>>) right(b);
            t4 = (BTPosition<Entry<K, V>>) right(c);
        }

        // Set the children of newly restructured subtree
        
        b.setLeft(a);
        b.setRight(c);
        a.setLeft(t1);
        a.setRight(t2);
        c.setLeft(t3);
        c.setRight(t4);

        // Fix the parent pointers
        if (t1 != null) t1.setParent(a);
        if (t2 != null) t2.setParent(a);
        if (t3 != null) t3.setParent( c);
        if (t4 != null) t4.setParent(c);
        a.setParent( b);
        c.setParent(b);

        // Adjust the root of the tree if necessary
        if (isRoot(z)) {
            root=(b);
            b.setParent(null);
        } else {
            Position<Entry<K, V>> zParent = parent(z);
            if (z == left(zParent)) {
                insertLeft(zParent, (Entry<K, V>) b);
            } else {
                insertRight(zParent, (Entry<K, V>) b);
            }
            b.setParent((BTPosition<Entry<K, V>>) zParent);
        }

        // Update heights
        setHeight(a);
        setHeight(c);
        setHeight(b);

        return b;  // Return new root of the subtree
    }

    


	/**
     * Insert an item into the tree and rebalance
     * @param k The key
     * @param v The value
     * @return an Entry in the tree
     * @throws InvalidKeyException if the key is not valid
     */
    public Entry<K, V> insert(K k, V v) throws InvalidKeyException {
        Entry<K, V> toReturn = super.insert(k, v);
        rebalance(actionPos);
        return toReturn;
    }

    /**
     * Remove an item from the AVL tree and rebalance
     * @param ent the entry to remove
     * @return the removed entry
     * @throws InvalidEntryException if the key is not valid
     */
    public Entry<K, V> remove(Entry<K, V> ent) throws InvalidEntryException {
        Entry<K, V> toReturn = super.remove(ent);
        if (toReturn != null)
            rebalance(actionPos);
        return toReturn;
    }

    /**
     * Return a string representation of the tree
     * @return A string representation of the tree
     */
    public String toString() {

        String str = "";
        Iterator<Entry<K, V>> elementIter = iterator();
        while (elementIter.hasNext()) {
            Entry<K, V> elem = elementIter.next();
            if (elem != null) {
                str += elem.getKey().toString() + "\t" + elem.getValue().toString() + "\n";
            }
        }
        str += "\n";
        return str;
    }
}
