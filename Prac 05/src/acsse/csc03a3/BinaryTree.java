package acsse.csc03a3;
//Overall BinaryTree class: 20 marks *****************************************
import java.util.Iterator;

/**
 * A Binary Tree. There should be a reference to the root of the tree
 * and then all of the nodes contained in the tree are children of the root.
 *
 * @param <T> The type of the objects that will be contained in the tree
 */
public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
	private BTNode<T> root;
	private int size;
	
	/**
	 * Create a new tree, with a null node in the root;
	 */
	public BinaryTree() {
		root = new BTNode<T>(null, null, null, null);
		size = 0;
	}
	
	public BinaryTree(T element) {
		root = new BTNode<T>(null, null, null, element);
		size = 1;
	}
	
	public BinaryTree(BTNode<T> root) {
		this.root = root;
	}
	
	/**
	 * Get the root of the tree
	 * @return return the root of the tree
	 */
	public BTPosition<T> root() {
		return root;
	}
	
	/**
	 * Get an iterator for the children of a node
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 */
	public BTPosition<T> left(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		if (hasLeft(treeNode)) {
			return treeNode.left();
		}
		return null;
	}
	
	/**
	 * Get an iterator for the children of a node
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 */
	public BTPosition<T> right(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		if (hasRight(treeNode)) {
			return treeNode.right();
		}
		return null;
	}
	
	public boolean hasLeft(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		return treeNode.left() != null;
	}
	
	public boolean hasRight(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		return treeNode.right() != null;
	}
	
	/**
	 * Get the parent of a node
	 * @param node a Position<T> which is a node in this tree
	 * @return a Position<T> which is the parent of this node
	 */
	public BTPosition<T> parent(BTPosition<T> node) {
		return node.parent();
	}
	
	/**
	 * Returns a list of all of the Positions in this tree. These could be
	 * converted to a TreeNode<T> in order to operate on the tree
	 * @return A IList<Position<T>> of all the positions in the tree
	 * 
	 */
	public IList<BTPosition<T>> positions() {
		IList<BTPosition<T>> positionList = new PositionList<BTPosition<T>>();
		nodeTraversal(positionList, root);
		return positionList;
	}
	
	/**
	 * Returns an iterator over all of the elements in this tree
	 * @return an Iterator<T> over all of the elements
	 */
	public Iterator<T> elements() {
		PositionList<T> elementList = new PositionList<T>();
		PreorderElementTraversal(elementList, root);
		return elementList.iterator();
	}
	
	/*
	 * Calculates the depth of a Binary tree node position in the overall binary tree
	 * 5 marks ***********************************************************************************
	 */
	public Integer nodeDepth(BTPosition<T> nodePos){
		//TODO: COMPLETE CODE HERE 
		if (nodePos == root || nodePos == null) {
			return 0;
		}
			return 1 + nodeDepth(parent(nodePos));
	}
	
	/**
	 * Return an Iterator over all of the elements in this tree
	 */
	@Override
	public Iterator<T> iterator() {
		return elements();
	}
	
	/**
	 * The number of nodes in this tree
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return true if the tree is empty
	 * @return true if the tree is empty, false if the tree is not empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Perform a preorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 */
	public void nodeTraversal(IList<BTPosition<T>> elements, BTPosition<T> root) {
		BTNode<T> rootNode = checkPosition(root);
		elements.addLast(rootNode);
		
		if (hasLeft(root)) {
			nodeTraversal(elements, left(root));
		}
		
		if (hasRight(root)) {
			nodeTraversal(elements, right(root));
		}
	}
	
	/**
	 * Perform a preorder traversal over all of the elements in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 * 5 marks ***********************************************************************************
	 */
	public void PreorderElementTraversal(PositionList<T> elements, BTPosition<T> root) {
		//TODO: COMPLETE CODE HERE
		if (root == null)
			return;

		elements.addLast(root.element());
		PreorderElementTraversal(elements, left(root));
		PreorderElementTraversal(elements, right(root));
	}
	
	
	/**
	 * Perform a preorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 * 
	 */
	public void PostOrderElementTraversal(PositionList<T> elements, BTPosition<T> root) {
BTNode<T> rootNode = checkPosition(root);
		
		if (hasLeft(root)) {
			PostOrderElementTraversal(elements, left(root));
		}
		
		if (hasRight(root)) {
			PostOrderElementTraversal(elements, right(root));
		}
		
		elements.addLast(rootNode.element());
	}

	/**
	 * Perform an inorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 * 
	 */
	public void InorderElementTraversal(PositionList<T> elements, BTPosition<T> root) {
		
		BTNode<T> rootNode = checkPosition(root);
		
		if (hasLeft(root)) {
			InorderElementTraversal(elements, left(root));
		}
		
		elements.addLast(rootNode.element());
		
		if (hasRight(root)) {
			InorderElementTraversal(elements, right(root));
		}
	}

    /**
     * Inserts a new element into the binary search tree.
     * @param element The element to insert.
     */
    public void insert(T element) {
        root = insertRecursive(root, element);
        size++;
    }
    /**
     * Insert a new node into the Binary Tree preserving order in the tree
     * @param the root of the subtree
     * @param The element to insert 
     * 5 marks ***********************************************************************************
     */

    private BTNode<T> insertRecursive(BTNode<T> node, T element) {
    	//TODO: COMPLETE CODE HERE
		if (node == null) {
			return new BTNode<T>(null, null, null, element);
		}

		if (element == null) {
			return node;
		}

		int check = 0;
		if (element != null && node.element() != null) {
			check = element.compareTo(node.element());
		}

		if (check < 0) {
			node.setLeft(insertRecursive((BTNode<T>) node.left(), element));
		} else if (check > 0) {
			node.setRight(insertRecursive((BTNode<T>) node.right(), element));
		}
		return node;
    	
    }
    /**
     * Searches for an element in the binary search tree.
     * @param element The element to search for.
     * @return True if the element is found, false otherwise.
     */
    public boolean binarySearch(T element) {
        return binarySearchRecursive(root, element);
    }
    
    /**
     * Searches for a node in the Binary Tree 
     * @param the root of the subtree
     * @param The element to insert 
     * 5 marks ***********************************************************************************
     */
    private boolean binarySearchRecursive(BTNode<T> node, T element) {
    	//TODO: COMPLETE CODE HERE
		if (node == null)
			return false;

		int check = 0;
		if (element != null && node.element() != null) {
			check = element.compareTo(node.element());
		}

		if (check < 0)
			return binarySearchRecursive((BTNode<T>) node.left(), element);
		else if (check > 0)
			return binarySearchRecursive((BTNode<T>) node.right(), element);
		else {
			return true;
		}
    }
    

	/**
	 * Convert a Position<T> into a TreeNode<T>
	 * @param p a Position<T> to convert
	 * @return the corresponding TreeNode<T>
	 */
	private BTNode<T> checkPosition(BTPosition<T> p) {
		if (!(p instanceof BTNode<?>)) {
			throw new PositionException("Invalid Position");
		}
		
		return (BTNode<T>)p;
	}

}
