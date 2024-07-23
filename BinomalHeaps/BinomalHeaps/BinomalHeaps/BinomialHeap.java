/**  Jonathan King
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
	
	public BinomialHeap() {
		this.size = 0;
		this.last = null;
		this.min = null;
	}
	public BinomialHeap(HeapNode last, HeapNode min, int size) {
		this.size = size;
		this.last = last;
		this.min = min;
	}
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
		HeapNode new_node = new HeapNode(new_item, null, null, null, 0);
		if (this.empty()) {
			new_item.setNode(new_node);
			this.size++;
			return new_item;
		}

		if (this.size%2 == 0) {
			new_node = new HeapNode(new_item, null, this.last.next, null, 0);
			this.last.next = new_node;
			new_item.setNode(new_node);
			this.size ++;
			return new_item;
		}

		new_node= new HeapNode(new_item, null, this.last.next, null, 0); // node.next will be the lonely subtree
		new_item.setNode(new_node);
		
		BinomialHeap newBinomialHeap = new BinomialHeap(new_node, new_node, 1);
		this.meld(newBinomialHeap);
		
		this.size++;
		return new_item; 
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
		public String toString() {
			return "<" + String.valueOf(item.key) + ">";
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
		
		public HeapItem(int key, String info) {
			this.node = null;
			this.key = key;
			this.info = info;
		}
		public void setNode(HeapNode node){
			this.node = node;
		}
		public String toString() {
			return "Item {key:" + String.valueOf(key) + ", info:" + info 
					+ ", child:" + ((node.child == null) ? "null" : node.child) 
					+ ", next:" + ((node.next == null) ? "null" : node.next) 
					+ ", parent:" + ((node.parent == null) ? "null" : node.parent)
					+ ", rank:" + node.rank + "}";
		}
	}
	
	public static void main(String[] args) {
		BinomialHeap b = new BinomialHeap();
		///HeapItem i = new HeapItem(3, "hello");
		///HeapNode g = new HeapNode(i, null, null, null, 0);
		///HeapNode n = new HeapNode(i, null, g, null, 0);
		///i.setNode(n);
		///System.out.println(i);
	}
}