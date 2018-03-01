import java.util.List;
import java.util.Set;


public class main {

    private static MinHash hash;

	public static void exercise1_1() {
		for(int i = 0; i < 6; i++) {
			System.out.println("For k = " + i);
			ShingleSet set = new ShingleSet(i);
			set.shingleStrippedString("The plane was ready for touch down");
			ShingleSet set2 = new ShingleSet(i);
			set2.shingleStrippedString("The quarterback scored a touchdown");
			System.out.println(set.jaccardDistance(set2));
		}
		for(int i = 0; i < 6; i++) {
			System.out.println("For k = " + i);
			ShingleSet set = new ShingleSet(i);
			set.shingleString("The plane was ready for touch down");
			ShingleSet set2 = new ShingleSet(i);
			set2.shingleString("The quarterback scored a touchdown");
			System.out.println(set.jaccardDistance(set2));
		}
	}

	public static void exercise1_2() {
	    hash = new MinHash();
		HashFunction func1 = new HashFunction(1,1);
		HashFunction func2 = new HashFunction(3,1);
		hash.addHashFunction(func1);
		hash.addHashFunction(func2);


	}
	
	public static void exercise1_3(MinHash mh) {
	    mh = new MinHash();
		mh.addRandomHashFunctions(100);
        MinHashSignature mhs = mh.computeSignature();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// exercise 1.1
		exercise1_1();
		
		// exercise 1.2
		exercise1_2();

		// exercise 1.3
	}

}
