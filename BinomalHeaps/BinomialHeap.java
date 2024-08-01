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
	public HeapItem insert(int key, String info)

	{
		HeapItem new_item = new HeapItem(key, info);
		HeapNode new_node;
		if (this.empty()) {
			new_node = new HeapNode(new_item, null, null, null, 0);
			new_node.next = new_node;
			new_item.setNode(new_node);
			this.size++;
			this.min = new_node;
			this.last = new_node;
			return new_item;
		}
		if (this.size%2 == 0) {
			new_node = new HeapNode(new_item, null, this.last.next, null, 0);
			this.last.next = new_node;
			new_item.setNode(new_node);
			this.size ++;
			if(new_item.key < this.min.item.key) this.min = new_node;
			return new_item;
		}

		new_node = new HeapNode(new_item, null, this.last.next, null, 0);// node.next will be the lonely subtree
		new_item.setNode(new_node);

		BinomialHeap newBinomialHeap = new BinomialHeap(new_node, new_node, 1);
		this.meld(newBinomialHeap);

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
		if (empty()) return null;
		HeapNode tmp = last.next;
		HeapNode minimum = last;
		while (tmp != last) {
			if (tmp.item.key < minimum.item.key) {
				minimum = tmp;
			}
			tmp = tmp.next;
		}
		return minimum.item;
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
	/*public void meld(BinomialHeap heap2)
	take into account:
	size1 can be bigger smaller or equals to size2

	{
		this.size += heap2.size;

		HeapNode this_node = this.last;
		HeapNode heap2_node = heap2.last.next;
		HeapNode im_back = heap2.last.next;
		HeapNode this_next, heap2_next;

		do // remember we may need to meld a single node to another heap, remember the idea of a first entry pass
		{

			if (this_node.next.rank > heap2_node.rank)
			{
				this_next = this_node.next;
				this_node.next = heap2_node;

				while (heap2_node.next.rank < this_next.rank)
				{
					heap2_node = heap2_node.next;
				}

				heap2_next = heap2_node.next;
				heap2_node.next = this_next;
				this_node = heap2_node;
				heap2_node = heap2_next;
			} else if (this_node.next.rank == heap2_node.rank)
			{
				heap2_next = heap2_node.next;
				if (this_node.next.item.key <= heap2_node.item.key)
				{
					this_node.next.concatenate(heap2_node);
					check_remainder(this_node, heap2_next);
                }
				else
				{
					this_next = this_node.next;
					this_node.next = heap2_node;
					heap2_node.next = this_next.next;
					heap2_node.concatenate(this_next);
					check_remainder(this_node, heap2_next);
                }
				this_node = this_node.next;
                heap2_node = heap2_next;


            }
			else{this_node = this_node.next;}

		}while (this_node != this.last && this_node.child != this.last
				&& heap2_node != im_back && heap2_node.child != im_back);

		if (this_node.child == this.last){this.last = this_node;}

		HeapItem min_item = this.findMin();
		this.min = min_item.node;

	}*/

	public void meld(BinomialHeap heap2)
	{
		//melding 1 and 1 doesn't work
		this.size += heap2.size;
		BinomialHeap bigger_heap, smaller_heap;
		HeapNode b_node,s_node,b_next,s_next, remainder = null;
		int exp = 0;

		if (this.last.rank >= heap2.last.rank)
		{
			bigger_heap = this;
			smaller_heap = heap2;
		}
		else
		{
			smaller_heap = this;
			bigger_heap = heap2;
		}

		if(bigger_heap.numTrees() == 1 && smaller_heap.numTrees() == 1 && bigger_heap.last.rank == smaller_heap.last.rank)
		{
			HeapNode ret = null;
			ret = two_nodes(bigger_heap.last,smaller_heap.last);
			this.last = ret;
		}
		else {
			HeapNode ret;
			HeapNode[] ret_arr = new HeapNode[bigger_heap.last.rank + 1];
			HeapNode[] iii_nodes;


			b_node = bigger_heap.last.next;
			s_node = smaller_heap.last.next;

			do {
				b_next = b_node.next;
				s_next = s_node.next;
				switch (which_case(b_node, s_node, remainder, exp)) {
					case 1:
						break;
					case 2:
						ret_arr[exp] = b_node;
						break;
					case 3:
						ret_arr[exp] = s_node;
						break;
					case 4:
						ret_arr[exp] = remainder;
						remainder = null;
						break;
					case 5:
						remainder = two_nodes(b_node, s_node);
						b_node = b_next;
						s_node = s_next;
						break;
					case 6:
						remainder = two_nodes(b_node, remainder);
						b_node = b_next;
						break;
					case 7:
						remainder = two_nodes(s_node, remainder);
						s_node = s_next;
						break;
					case 8:
						iii_nodes = three_nodes(b_node, s_node, remainder);
						ret_arr[exp] = iii_nodes[0];
						remainder = iii_nodes[1];
						b_node = b_next;
						s_node = s_next;
						break;

				}

				exp++;
			} while (s_node.rank >= exp && s_node.next != s_node);

			if (remainder != null) {
				System.out.println(remainder.item.key);
				while (remainder.rank == b_node.rank) {
					b_next = b_node.next;
					remainder = two_nodes(b_node, remainder);
					b_node = b_next;
					exp++;
				}
				ret_arr[exp] = remainder;
			}

			while(exp < ret_arr.length)
			{
				if (b_node.rank == exp){
					ret_arr[exp] = b_node;
					b_node = b_node.next;
				}
			}

			while (ret_arr[exp] == null) {exp--;}
			ret = ret_arr[exp];
			exp = 0;
			while(exp < ret_arr.length)
			{
				while (ret_arr[exp] == null && exp < ret_arr.length) {exp++;}
				if (exp < ret_arr.length)
				{
					ret.next = ret_arr[exp];
					ret = ret.next;
				}
			}
			this.last = ret;

		}
		this.min = this.findMin().node;
	}

	private int which_case(HeapNode b_node, HeapNode s_node, HeapNode remainder , int exp) {
		if (b_node.rank == exp) {
			if (s_node.rank == exp) {
				if (remainder != null) {
					return 8;
				} else {
					return 5;
				}
			} else {
				if (remainder != null) {
					return 6;
				} else {
					return 2;
				}
			}
		} else {
			if (s_node.rank == exp) {
				if (remainder != null) {
					return 7;
				} else {
					return 3;
				}
			} else {
				if (remainder != null) {
					return 4;
				} else {
					return 1;
				}
			}
		}
	}

	private HeapNode[] check_which_nodes_to_concatenate(HeapNode b_node, HeapNode s_node, HeapNode remainder){
		HeapNode[] ret = {b_node,s_node,remainder};
		if (s_node.item.key < b_node.item.key && s_node.item.key < remainder.item.key)
		{
			ret[0] = s_node;
			ret[1] = b_node;
		}
		if (s_node.item.key < b_node.item.key && s_node.item.key < remainder.item.key)
		{
			ret[0] = remainder;
			ret[2] = b_node;
		}

		return ret;
	}


	private void one_node(HeapNode node, HeapNode ret_node)
	{
			ret_node.next = node;
			ret_node = ret_node.next;
			node = node.next;
	}

	private HeapNode two_nodes(HeapNode node1,HeapNode node2)
	{
		if (node1.item.key <= node2.item.key)
		{
			node1.rank++;
			node1.concatenate(node2);
			return node1;
		}
		else
		{
			node2.rank++;
			node2.concatenate(node1);
			return node2;
		}
	}

	private HeapNode[] three_nodes(HeapNode b_node, HeapNode s_node, HeapNode remainder)
	{
		HeapNode[] cwntc = check_which_nodes_to_concatenate(b_node, s_node, remainder);
		HeapNode[] ret = new HeapNode[2];
		ret[0] = cwntc[0];
		ret[1] = two_nodes(cwntc[1],cwntc[2]);
		return ret;
	}


//	private void check_remainder(HeapNode this_node, HeapNode heap2_next) {
//
//		HeapNode this_next = this_node.next;
//
//		while ((this_node.next.rank == this_node.next.next.rank &&
//				this_node.next.rank != heap2_next.rank)
//				|| (this_node.next.rank != this_node.next.next.rank &&
//				this_node.next.rank == heap2_next.rank)) {
//
//			if (this_node.next.rank == this_node.next.next.rank)
//			{
//				if (this_node == this_node.next){break;}
//				merge_remainder(this_node, this_node.next, false);
//			}
//			else
//			{
//				merge_remainder(this_node, heap2_next, true);
//			}
//		}
//	}

	private void merge_remainder(HeapNode this_node, HeapNode other_node, boolean are_we_on_heap_2)
	{
		HeapNode other_next = other_node.next;

		if (this_node.next.item.key <= other_node.item.key)
		{
			this_node.next.concatenate(other_node);
		}
		else
		{
			HeapNode this_next = this_node.next;
			if (are_we_on_heap_2)
			{other_node.next = this_node.next.next;} // problematic
			other_node.concatenate(this_next);
			this_node.next = other_node;
		}
		other_node = other_next;
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
		protected void concatenate(HeapNode node2)
		{
			if (this.rank != 0)
			{
				node2.next = this.child.next;
				this.child.next = node2;
			}
			this.child = node2;
			node2.parent = this;
			this.rank++;
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

	public static void main(String[] args)
	{
		BinomialHeap b = new BinomialHeap();
		HeapItem i = new HeapItem(3,"3");
		b.last = new HeapNode(i,null,null,null,0);
		b.last.item = i;
		b.last.next = b.last;
		b.size++;
		b.insert(4,"4");
		b.insert(5,"5");
		System.out.println(b.last.item.key);
		System.out.print(b.last.child.item.key);
		System.out.print(b.last.next.item.key);
	}
}
