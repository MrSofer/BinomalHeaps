

public class TEST{
	public static void main(String args[]) {
		BinomialHeap b = new BinomialHeap();
		
		b.insert(1, "one");
		b.insert(2, "two");
	    b.insert(3, "three");
		b.insert(4, "four");
		b.insert(5, "five");
		b.insert(6, "six");
		b.insert(7, "seven");
		b.insert(8,  "eight");
		b.insert(9, "one");
		b.insert(10, "two");
	    b.insert(11, "three");
		b.insert(12, "four");
		b.insert(13,"five");
		b.insert(14,"six");
		b.insert(15,"seven");
		System.out.println(b);
		
	}
}