import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author hansgaiser
 *
 */
public class LSH {
	
	/**
	 * Computes the candidate pairs using the LSH technique.
	 * @param mhs The minhash signature object.
	 * @param bs The number of buckets to be used in the LSH table.
	 * @param r The number of rows per band to be used.
	 * @return Returns a set of indices pairs that are candidate to being similar.
	 */
	public static Set<List<Integer>> computeCandidates(MinHashSignature mhs, int bs, int r) {
		// assert that the number of rows can be evenly divided by r
		assert(mhs.rows() % r == 0);
		
		// the number of bands
		int b = mhs.rows() / r;
		
		// the result
		Set<List<Integer>> candidates = new HashSet<List<Integer>>();

        List<List<List<Integer>>> buckets = new ArrayList<>();

        for (int i = 0; i < b; i++){
            List<List<Integer>> theBand = new ArrayList<>();
            buckets.add(theBand);
            for (int j = 0; j < bs; j++) {
                List<Integer> theBucket = new ArrayList<>();
                buckets.get(i).add(theBucket);
            }
        }

		for (int i = 0; i < b; i++){
		    for (int j = 0; j < mhs.cols(); j++){
		        String s = mhs.colSegment(j, i*r, (i+1)*r);
		        buckets.get(i).get(s.hashCode() % bs).add(j);
            }
		}

		for (int i = 0; i < b; i++) {
		    for(int j = 0; j < mhs.cols(); j++){
		        String s = mhs.colSegment(j, i*r, (i+1)*r);
		        for (int doc : buckets.get(i).get(s.hashCode() % bs)){
		            if (doc != j) {
                        List<Integer> candidatePair = new ArrayList<>();
                        List<Integer> candidatePairReverse = new ArrayList<>();
                        candidatePair.add(doc);
                        candidatePair.add(j);
                        candidatePairReverse.add(j);
                        candidatePairReverse.add(doc);
                        if (!candidates.contains(candidatePair) && !candidates.contains(candidatePairReverse)) {
                            candidates.add(candidatePair);
                        }
                    }
		        }
            }
        }

		return candidates;
	}
	
}
