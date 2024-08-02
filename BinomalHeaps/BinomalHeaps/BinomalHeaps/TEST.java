

public class TEST{
	public static void main(String args[]) {
		System.out.println("dsfdbfdj");
		BinomialHeap b = new BinomialHeap();
		
		b.insert(1, "one");
		System.out.println("min is " + b.min);
		b.insert(2, "two");
		b.insert(3,"three");
		b.insert(4, "four");

		System.out.println(b);
		System.out.println("min is " + b.min);
		System.out.println("last is " + b.last);
		System.out.println("last next is " + b.last.next);
		System.out.println("heap size is " + b.size);
		
	}
}