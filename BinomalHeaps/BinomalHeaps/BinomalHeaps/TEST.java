
import java.util.Random;


//Java code to demonstrate the working 
//of nextInt() 
import java.util.*;

public class TEST{
	public static void main(String args[]) {
		BinomialHeap b = new BinomialHeap();
		

		 // TEST 1
		   /*for (int i = 1; i < 6; i ++) {
			long start = System.currentTimeMillis();
			for(int j = 0; j < Math.pow(3, i+7); j++) {
				b.insert(j, "");
			}
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			System.out.println( "----- i = " + i + " ----- \n"
				+ timeElapsed +  " = time \n"
				+ b.numTrees() + " = num trees\n"
				+ b.count + " = links");
			b.resetCount();
		}*/
		
		
		// TEST 2
	    for (int i = 1; i < 6; i ++) {
			int[] array = new int[(int)Math.pow(3, i+7)];
			Random random = new Random();
			for (int j = 0; j < array.length; j++) {
				array[j] = j+1;
			}
			for (int k = array.length - 1; k > 0; k--) {
				int j = random.nextInt(k + 1);
	            int temp = array[k];
	            array[k] = array[j];
	            array[j] = temp;
	        }
			
			long start = System.currentTimeMillis();
			for(int j = 0; j < Math.pow(3, i+7); j++) {
				b.insert(array[j], "");
			}
			int sum_ranks = 0;
			int count = 0;
			System.out.println(b.size);
			System.out.println(Math.pow(3, i+7)/2);
			for(int c = 0; c < Math.pow(3, i+7)/2; c++) {
				sum_ranks += b.min.rank;
				System.out.println(b.min);
				b.deleteMin();			
			}
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			System.out.println( "----- i = " + i + " ----- \n"
				+ timeElapsed +  " = time\n"
				+ b.numTrees() + " = num trees\n"
				+ b.count + " = links\n"
				+ sum_ranks + " = sum of deleted ranks");
			b.resetCount();
		}

		System.out.println("DONE");
		
		
		/*BinomialHeap c = new BinomialHeap();

		c.insert(4, "a");
		c.insert(8, null);
		c.insert(1, null);
		c.insert(5, null);
		c.insert(6, null);
		c.insert(2,null);
		c.insert(7, null);
		c.insert(3, null);
			
		System.out.println(c.last);
		System.out.println(c.min);
		System.out.println(c.last.parent);
		System.out.println(c.min.parent);
		System.out.println(c.last.rank);
		System.out.println(c.min.rank);
		System.out.println(c.last.next);
		System.out.println(c.min.next);
		System.out.println("--------------");
		System.out.println(c.last.child);	
		System.out.println(c.last.child.parent);
		System.out.println(c.last.child.rank);
		System.out.println(c.last.child.child);
		System.out.println("-------------");
		System.out.println(c.last.child.next);
		System.out.println(c.last.child.next.child);
		System.out.println(c.last.child.next.parent);
		System.out.println(c.last.child.next.rank);
		System.out.println("------------------");
		System.out.println(c.min.child);
		System.out.println(c.min.child.next);
		System.out.println(c.min.child.parent);
		System.out.println(c.min.child.rank);
		System.out.println(c.min.child.child);
		BinomialHeap v = new BinomialHeap();
		for(int o = 0; o< 8; o++) {
			v.insert(o, null);
		}
		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();	
		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();
		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();
	
		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();

		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();

		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();
	
		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();
	
		System.out.println(c);
		System.out.println(c.min.rank);
		c.deleteMin();
		System.out.println(c); 
		System.out.println(c.min.rank);*/
		
		
		
	}
}