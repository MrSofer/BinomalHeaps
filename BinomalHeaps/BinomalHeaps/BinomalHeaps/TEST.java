

public class TEST{
	public static void main(String args[]) {
		BinomialHeap b = new BinomialHeap();
		b.insert(1, "one");
		b.insert(2, "two");
		System.out.println(b);
		System.out.println("min is " + b.min);
		System.out.println("last is " + b.last);
		System.out.println("last next is " + b.last.next);
		System.out.println("heap size is " + b.size);
		
		System.out.println(b);
	}
}