import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NearestNeighbour {
    /**
     * List of training vectors.
     */
    Dataset dataset;

    /**
     * Constructor.
     */
    public NearestNeighbour() {
        dataset = new Dataset();
    }

    /**
     * Reads in the data from a text file.
     *
     * @param fileName
     */
    public void readData(String fileName) {
        dataset.readData(fileName, false);
    }

    /**
     * @return
     */
    public Dataset getDataset() {
        return dataset;
    }

    /**
     * Classifies a query. Finds the k nearest neighbours and scales them if necessary.
     *
     * @param features The query features.
     * @param k        The number of neighbours to select.
     * @return Returns the label assigned to the query.
     */
    public int predict(List<Double> features, int k) {
        // the result

        List<Measurement> measurements = new ArrayList<>(dataset.size());

        for (FeatureVector featureVector : dataset) {
            measurements.add(new Measurement(featureVector, featureVector.distance(features)));
        }

        Collections.sort(measurements);

        List<Integer> labelVote = new ArrayList<>();

        for (Measurement measurement : measurements.subList(0, k)) {
            labelVote.add(measurement.getFeatureVector().getLabel());
        }

        return mostCommon(labelVote);
    }

    public static Integer mostCommon(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();

        for (Integer t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Entry<Integer, Integer> max = null;

        for (Entry<Integer, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue()) {
                max = e;
            }
        }

        return max.getKey();
    }
}

