

public class TEST{
	public static void main(String args[]) {
		BinomialHeap b = new BinomialHeap();
		
		BinomialHeap.HeapItem Ifour = new BinomialHeap.HeapItem(4, "4");
		BinomialHeap.HeapItem Ifive = new BinomialHeap.HeapItem(5, "5");
		BinomialHeap.HeapItem Isix = new BinomialHeap.HeapItem(6, "6");
		BinomialHeap.HeapItem Iseven = new BinomialHeap.HeapItem(7, "7");
		BinomialHeap.HeapNode four = new BinomialHeap.HeapNode(Ifour, null, null, null, 0);//item child next parent rank
		BinomialHeap.HeapNode five = new BinomialHeap.HeapNode(Ifive, null, null, null, 1);
		BinomialHeap.HeapNode six = new BinomialHeap.HeapNode(Isix, null, null, null, 2);
		BinomialHeap.HeapNode seven = new BinomialHeap.HeapNode(Iseven, null, null, null, 2);
		
		BinomialHeap.HeapItem Ione = new BinomialHeap.HeapItem(1, "1");
		BinomialHeap.HeapItem Itwo = new BinomialHeap.HeapItem(2, "2");
		BinomialHeap.HeapItem Ithree = new BinomialHeap.HeapItem(3, "3");
		BinomialHeap.HeapNode one = new BinomialHeap.HeapNode(Ione, null, null, null, 0);//item child next parent rank
		BinomialHeap.HeapNode two = new BinomialHeap.HeapNode(Itwo, four, null, null, 1);
		BinomialHeap.HeapNode three = new BinomialHeap.HeapNode(Ithree, five, one, null, 2);
		one.next = two;
		two.next = three;
		b.min = one;
		b.last = three;
		four.parent = two;
		five.parent = three;
		six.parent = three;
		seven.parent = five;
		five.next = six;
		six.next = five;
		four.next = four;
		five.child = seven;
		seven.next = seven;
		
		
		
		b.size = 7;
		System.out.println(b);
	}
}