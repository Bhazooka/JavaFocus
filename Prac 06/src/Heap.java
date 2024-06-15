//Overall Heap class: 25 marks ***********************************************
/**
 * An implementation of a heap structure implemented as a binary tree 
 *
 * @param <T> The type that will be stored in the heap - this class extends the
 * BinaryTree super class
 */
public class Heap<K extends Comparable<? super K>,V> extends BinaryTree<Entry<K,V>> {
	protected BTNode<Entry<K,V>> lastNode = null;
	
	public Heap() {
		this.size = 0;
		this.lastNode = null;
	}
	
	/**
	 * Insert an item in to the heap in heap order
	 * @param key the key of the entry
	 * @param value the value of the entry
	 */
	public void insert(K key, V value) {
		//if this heap is empty, create a new root and then add it to the create it
		BTNode<Entry<K,V>> insertNode = null;
		if (root == null) {
			root = new BTNode<Entry<K,V>>(null, new BTNode<Entry<K,V>>(null, null, null, null), 
					new BTNode<Entry<K,V>>(null, null, null, null),
					new Entry<K,V>(key, value));
			root.left().setParent(root);
			root.right().setParent(root);
			lastNode = root.left();
			insertNode = root;
			size++;
		} else {
			//add the node to the last position
			lastNode.setElement(new Entry<K,V>(key, value));
			lastNode.setLeft(new BTNode<Entry<K,V>>(lastNode, null, null, null));
			lastNode.setRight(new BTNode<Entry<K,V>>(lastNode, null, null, null));
			insertNode = lastNode;
			lastNode = getLastNodeInsert(lastNode);
			size++;
		}
		
		//perform an upheap on the last node to ensure that heap order has been maintained
		upheap(insertNode);
	}

	/**
	 * Remove the "smallest" element from the heap. This can be the element
	 * with the largest or the smallest key. It depends on the ordering
	 * @return
	 */
	public Entry<K,V> removeMin() {
		Entry<K,V> itemToRemove = root().element();
		
		/**
		 * If there is only one element, just return it and be done with it.
		 */
		if (size == 1) {
			root = null;
			size--;
			return itemToRemove;
		} else {
			lastNode = getLastNodeRemove(lastNode);
			root.setElement(lastNode.element());
			lastNode.setElement(null);
			
			//if this is an internal node, remove it
			if (!isExternal(lastNode)) {
				lastNode.setLeft(null);
				lastNode.setRight(null);
			}
			
			size--;
			downheap(root);
			return itemToRemove;
		}
	}
	
	/**
	 * Perform an upheap operation on the heap. This will restore heap order. This
	 * function is called recursively
	 * @param node the current node that you are operating on
	 * 10 marks ***********************************************
	 */
	private void upheap(BTNode<Entry<K,V>> node) {
		//TODO:Complete code
		if (node == root) {
			return;
		}
		if (node.element().getKey().compareTo(node.parent().element().getKey()) > 0 ) {
			return;
		} else {
			swap(node, node.parent());
			upheap(node.parent());
		}
	}
	
	/**
	 * Perform a downheap operation on the heap. This will restore heap order. This
	 * function is called recursivly
	 * @param node the current node that you are operating on
	 * 10 marks ***********************************************
	 */
	private void downheap(BTNode<Entry<K,V>> node) {
		//TODO:Complete code
		if (isExternal(node)) {
			return;
		}
		BTNode<Entry<K,V>> lessThan;
		if (node.left().element() != null || node.right().element() != null) {
			return;
		}
		
		if (node.left().element().getKey().compareTo(node.right().element().getKey()) > 0) {
			lessThan = node.left();
		} else {
			lessThan = node.right();
		}
		
		if (node.element().getKey().compareTo(lessThan.element().getKey()) <= 0) {
			return;
		} else {
			swap(node, lessThan);
			downheap(lessThan);
		}
		
	}
	
	/**
	 * Locate and return the last node in the tree once you have
	 * removed an item. This is the opposite to locating the
	 * last node on insert.
	 * @param node the current last node in the tree
	 * @return The new last node in the tree
	 */
	private BTNode<Entry<K,V>> getLastNodeRemove(BTNode<Entry<K,V>> node) {
		//1. if you are a right child, then the next node will be your sibling
		if (isRightChild(node)) {
			return sibling(node);
		}
		
		//2. go up until a right child or the root is reached
		BTNode<Entry<K,V>> currentNode = node.parent();
		while (!isRoot(currentNode) && !isRightChild(currentNode)) {
			currentNode = currentNode.parent();
		}
		
		//3. if right child then go to the left child
		if (!isRoot(currentNode) && isRightChild(currentNode)) {
			currentNode = sibling(currentNode);
		}
		
		//4. go as right as possible until a right node is reached
		while (currentNode.left() != null || currentNode.right() != null) {
			currentNode = currentNode.right();
		}
		
		//5. this should be the external node
		return currentNode;
	}
	
	/**
	 * Locate the new last node in the tree once a value has been inserted. This
	 * is the opposite of locating a node on removal
	 * @param node the current last node in the tree
	 * @return the new last node in the tree.
	 * 5 marks ***********************************************
	 */
	private BTNode<Entry<K,V>> getLastNodeInsert(BTNode<Entry<K,V>> node) {
		//TODO:Complete code
		//1. if you are a right child, then the next node will be your sibling
				if (isRightChild(node)) {
					return sibling(node);
				}
				
				//2. go up until a right child or the root is reached
				BTNode<Entry<K,V>> currentNode = node.parent();
				while (!isRoot(currentNode) && !isRightChild(currentNode)) {
					currentNode = currentNode.parent();
				}
				
				//3. if right child then go to the left child
				if (!isRoot(currentNode) && isRightChild(currentNode)) {
					currentNode = sibling(currentNode);
				}
				
				//4. go as right as possible until a right node is reached
				while (currentNode.left().element() != null || currentNode.right().element() != null) {
					currentNode = currentNode.left();
				}
				
				//5. this should be the external node
				return currentNode;
	}
	
	private void swap(BTNode<Entry<K,V>> node1, BTNode<Entry<K,V>> node2) {
		BTNode<Entry<K,V>> temp = node1;
		node1.setElement(node2.element());
		node2.setElement(temp.element());
	}
}

