import java.util.List;
import java.util.Set;


public class main {

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
    	ShingleSet s1 = new ShingleSet(1);
    	s1.shingleString("ab");
    	ShingleSet s2 = new ShingleSet(1);
    	s2.shingleString("c");
    	ShingleSet s3 = new ShingleSet(1);
    	s3.shingleString("bde");
    	ShingleSet s4 = new ShingleSet(1);
    	s4.shingleString("acd");
        MinHash hash = new MinHash();
		HashFunction func1 = new HashFunction(1,1);
		HashFunction func2 = new HashFunction(3,1);
		hash.addHashFunction(func1);
		hash.addHashFunction(func2);


	}
	
	public static void exercise1_3(MinHash mh) {
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
        exercise1_3(new MinHash());
	}

}
