import sun.reflect.generics.tree.Tree;

import java.util.SortedSet;
import java.util.TreeSet;


/**
 * The ShingleSet class contains a sorted set of shingles.
 * @author hansgaiser
 *
 */
public class ShingleSet extends TreeSet<String> implements SortedSet<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = 2143400328259342668L;

	/**
	 * The size of the shingles.
	 */
	private int k;

	/**
	 * Constructor for the ShingleSet.
	 * @param k The size of the shingles.
	 */
	public ShingleSet(int k) {
		this.k = k;
	}

	/**
	 * Add shingles of size k to the set from String s.
	 * @param s The string that is to be transformed to shingles.
	 */
	public void shingleString(String s) {
		for(int i = 0; i < s.length()-k+1; i++){
			this.add(s.substring(i , i+k));
		}
	}

	/**
	 * Add shingles of size k to the set from String s, where all white spaces from s are removed.
	 * @param s The string that is to be transformed to shingles.
	 */

	public void shingleStrippedString(String s) {
		for(int i = 0; i < s.length()-k+1; i++){
			if(!s.substring(i , i+k).contains(" ")){
				this.add(s.substring(i , i+k));
			}
		}
	}

	/**
	 * Calculates the Jaccard distance between this set and the other set.
	 * @param other The other set of shingles that this set will be compared to.
	 * @return The Jaccard distance between this set and the other set.
	 */
	public double jaccardDistance(TreeSet<String> other) {
		TreeSet<String> Union = new TreeSet<>(this);
		TreeSet<String> Intersect = new TreeSet<>(this);
		Union.addAll(other);
		Intersect.retainAll(other);
		double distance = 1- ((double) Intersect.size()/ (double) Union.size());
		return distance;
	}

}
