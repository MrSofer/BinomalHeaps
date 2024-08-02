

public class TEST{
	public static void main(String args[]) {
		BinomialHeap b = new BinomialHeap();
		
		for (int i = 1; i < 16; i ++) {
			b.insert(i, "<3");
		}
		System.out.println(b);
		
		b.deleteMin();
		
		System.out.println(b);
		
	}
}