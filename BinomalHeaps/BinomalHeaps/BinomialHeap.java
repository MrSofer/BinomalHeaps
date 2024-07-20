/**
 * BinomialHeap
 *
 * An implementation of binomial heap over positive integers.
 *
 */
public class BinomialHeap
{
	public int size;
	public HeapNode last;
	public HeapNode min;

	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapItem.
	 *
	 */
	public HeapItem insert(int key, String info)

	{
		HeapItem new_item = new HeapItem(key, info);
		HeapNode new_node = new HeapNode(new_item,null,null,null,1);
		if (empty()) {

			new_item.setNode(new_node);
			return new_item;
		}

		int i = this.size;
		if(i%2 == 0) {
			HeapItem item = new HeapItem(key, info);
			HeapNode node = new HeapNode(item, null, last.next, null, 1);
			last.next = node;
			item.setNode(node);
			this.size ++;
			return item;
		}

		HeapItem item = new HeapItem(key, info);
		HeapNode node = new HeapNode(item, null, last.next, null, 1); // node.next will be the lonely subtree
		item.setNode(node);

		int s = this.size;
		int exp = 0;
		while(s%2 == 1) {
			s /= 2;
			if (node.next.getAmountOfChildren() == Math.pow(2, exp)) {

			}
			exp ++;

		}

		this.size++;
		return item; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin()
	{
		return; // should be replaced by student code

	}

	/**
	 * 
	 * Return the minimal HeapItem, null if empty.
	 *
	 */
	public HeapItem findMin()
	{
		return null; // should be replaced by student code
	} 

	/**
	 * 
	 * pre: 0<diff<item.key
	 * 
	 * Decrease the key of item by diff and fix the heap. 
	 * 
	 */
	public void decreaseKey(HeapItem item, int diff) 
	{    
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) 
	{    
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2)
	{
		return; // should be replaced by student code   		
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return size; // should be replaced by student code
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty()
	{
		return size == 0;
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees()
	{
		int size = this.size;
		int ret = 0;
		while (size>0)
		{
			if (size % 2 == 1){ret++;}
			size/=2;
		}
		return ret;
	}

	/**
	 * Class implementing a node in a Binomial Heap.
	 *  
	 */
	public static class HeapNode{
		public HeapItem item;
		public HeapNode child;
		public HeapNode next;
		public HeapNode parent;
		public int rank;
		
		public HeapNode(HeapItem item, HeapNode child, HeapNode next, HeapNode parent, int rank)
		{
			this.item = item;
			this.child = child;
			this.next = next;
			this.parent = parent;
			this.rank = rank;
		}

	}

	/**
	 * Class implementing an item in a Binomial Heap.
	 *  
	 */
	public static class HeapItem{
		public HeapNode node;
		public int key;
		public String info;
		
		public HeapItem(int key,String info) {
			this.node = null;
			this.key = key;
			this.info = info;
		}
		public void setNode(HeapNode node){
			this.node = node;
		}
	}
}
