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

	public String toString() {
		if (this.empty()) {
			return "Heap is empty";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("BinomialHeap\n");

		BinomialHeap.HeapNode current = this.last;
		do {
			if (current != null) {
				sb.append("Tree with root: ").append(current.toString()).append("\n");
				appendTree(sb, current, "", true);
				current = current.next;
			}
		} while (current != this.last);

		return sb.toString();
	}

	private void appendTree(StringBuilder sb, BinomialHeap.HeapNode node, String indent, boolean last) {
		if (node == null) return;

		sb.append(indent);
		if (last) {
			sb.append("└── ");
			indent += "    ";
		} else {
			sb.append("├── ");
			indent += "│   ";
		}
		sb.append(node.toString()).append("\n");

		if (node.child != null) {
			BinomialHeap.HeapNode child = node.child;
			do {
				appendTree(sb, child, indent, child.next == node.child);
				child = child.next;
			} while (child != node.child);
		}
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

		new_node = new HeapNode(new_item, null, null, null, 0);
		new_node.next = new_node;// node.next will be the lonely subtree
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
		if (min == null) return;

		HeapNode old_minimum = this.min;

		/// deleting the minimum
		if(this.min.next == this.min) {
			if(min.child == null){
				this.min = null;
				this.last = null;
				this.size--;
			}
			else {
				this.last = this.min.child;
				this.min = findMin().node;
			}
			this.size--;
			return;
		}
		else {
			/// point to the node before this.min
			HeapNode tmp = this.min;
			while(tmp.next != this.min) {
				tmp = tmp.next;
			}
			tmp.next = tmp.next.next;
			while (tmp.rank < tmp.next.rank) {
				tmp = tmp.next;
			}
			this.last = tmp;
			this.min = findMin().node;
			this.size --;
			if(old_minimum.child != null) meldChildrenWithMainHeap(old_minimum);
		}
		return;
	}
	/**
	 *
	 * meld's the chosen node's children with the original heap. takes care of every exception
	 *
	 */
	private void meldChildrenWithMainHeap(HeapNode n) {
		if (n == null) return;
		if (n.child.next != n.child) {
			// finding the children's minimum
			HeapNode kid = n.child.next;
			HeapNode minimum = n.child;
			n.child.parent = null;
			while (kid != n.child) {
				kid.parent = null;
				if (kid.item.key < minimum.item.key) {
					minimum = kid;
				}
				kid = kid.next;
			}
			this.size -= (int)(Math.pow(2,n.child.rank + 1) - 1);
			BinomialHeap b = new BinomialHeap(n.child, minimum, (int)(Math.pow(2,n.child.rank + 1) - 1));
			this.meld(b);
		}
		else {
			n.child.parent = null;
			BinomialHeap b = new BinomialHeap(n.child, n.child, 1);
			this.meld(b);
		}
		return;
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
		minimum.item.node = minimum;
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

	public void meld(BinomialHeap heap2)
	{
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
		this.size += heap2.size;

		if(bigger_heap.numTrees() == 1 && smaller_heap.numTrees() == 1 && bigger_heap.last.rank == smaller_heap.last.rank)
		{
			HeapNode ret = null;
			ret = two_nodes(bigger_heap.last,smaller_heap.last);
			this.last = ret;
		}
		else {
			HeapNode ret;
			HeapNode[] ret_arr = new HeapNode[bigger_heap.last.rank + 2];
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
				while (remainder.rank == b_node.rank) {
					b_next = b_node.next;
					remainder = two_nodes(b_node, remainder);
					//remainder.next = remainder;
					b_node = b_next;
					exp++;
				}
				ret_arr[exp] = remainder;
			}

			while(exp < ret_arr.length-1)
			{
				if (b_node.rank == exp){
					ret_arr[exp] = b_node;
					b_node = b_node.next;
				}
				exp++;
			}

			while (ret_arr[exp] == null) {exp--;}
			ret = ret_arr[exp];
			exp = 0;
			while(exp < ret_arr.length-1)
			{
				while (ret_arr[exp] == null && exp < ret_arr.length-1) {exp++;}
				if (exp < ret_arr.length-1)
				{
					ret.next = ret_arr[exp];
					ret = ret.next;
					exp++;
				}
			}
			this.last = ret;

		}
		HeapItem f_min = this.findMin();
		this.min = f_min.node;
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
		if (remainder.item.key < b_node.item.key && remainder.item.key < s_node.item.key)
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
		HeapNode new_node1, new_node2;
		new_node1 = new HeapNode(node1.item,node1.child,node1.next,node1.parent,node1.rank);
		new_node2 = new HeapNode(node2.item,node2.child,node2.next,node2.parent,node2.rank);
		new_node1.next = new_node1;
		new_node1.item.node = new_node1;
		new_node2.next = new_node2;
		new_node2.item.node = new_node2;
		if (node1.item.key <= node2.item.key)
		{
			new_node1.concatenate(new_node2);
			return new_node1;
		}
		else
		{
			new_node2.concatenate(new_node1);
			return new_node2;
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


	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return size;
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
			this.child.parent = this;
			this.rank++;
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
		
		public HeapItem(int key,String info) {
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

	public static void main(String[] args)
	{
		BinomialHeap b = new BinomialHeap();
		HeapItem i = new HeapItem(16,"16");
		b.last = new HeapNode(i,null,null,null,0);
		b.last.item = i;
		b.last.next = b.last;
		b.size++;
		for (int j = 15; j >=1; j--) {
			System.out.println("inserting " + j);
			b.insert(j, Integer.toString(j));
		}
		b.deleteMin();
		System.out.println(b);

	}
}
