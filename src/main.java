import java.util.ArrayList;
import java.util.List;

public class main {

    public static void perceptron() {
        Dataset dataset = new Dataset("data/gaussian.txt", true);

        Perceptron perceptron = new Perceptron(1);

        perceptron.updateWeights(dataset);

        PerceptronPlotter perceptronPlotter = new PerceptronPlotter("first", "second");

        perceptronPlotter.plotData(dataset, perceptron);
    }

    public static void perceptronDigits() {
        Dataset dataset = new Dataset("data/train_digits.txt", true);

        // Show some grayscale images
        for (int i = 0; i < 2; i++) {
            DigitFrame digitFrame = new DigitFrame("title -1: " + i, dataset.get(i), 8, 8);
        }

        for (int i = dataset.size() - 2; i < dataset.size(); i++) {
            DigitFrame digitFrame = new DigitFrame("title 1: " + i, dataset.get(i), 8, 8);
        }

        Perceptron perceptron = new Perceptron(1);

        perceptron.updateWeights(dataset);
        perceptron.updateWeights(dataset);

        DigitFrame digitFrame = new DigitFrame("title weights", perceptron.getWeights(), 8, 8);

        Dataset datasetTest = new Dataset("data/test_digits.txt", true);

        double misclassified = 0;
        List<FeatureVector> misclassifiedFeatureVectors = new ArrayList<FeatureVector>();
        for (FeatureVector featureVector : datasetTest) {
            if (perceptron.predict(featureVector) != featureVector.getLabel()) {
                misclassifiedFeatureVectors.add(featureVector);
                misclassified++;
            }
        }

        double errorrate = misclassified / (double) datasetTest.size();

        System.out.println("Error rate: " + errorrate + "% -> actual errors: " + misclassified + " -> testset size: " + datasetTest.size());

        for (FeatureVector featureVector : misclassifiedFeatureVectors) {
            DigitFrame misclassifiedDigitFrame = new DigitFrame("title misclassified", featureVector, 8, 8);
        }
    }

    public static void nearestNeighbour() {
        NearestNeighbour nearestNeighbour = new NearestNeighbour();
        NearestNeighbourPlotter nearestNeighbourPlotter = new NearestNeighbourPlotter(3);

        nearestNeighbour.readData("data/banana.txt");
        nearestNeighbourPlotter.plotData(nearestNeighbour);
    }

    public static void nearestNeighbourDigits() {
        NearestNeighbour nearestNeighbour = new NearestNeighbour();
        nearestNeighbour.readData("data/train_digits.txt");

        Dataset datasetTest = new Dataset("data/test_digits.txt", false);

        double misclassified = 0;
        List<FeatureVector> misclassifiedFeatureVectors = new ArrayList<FeatureVector>();
        for (FeatureVector featureVector : datasetTest) {
            if (nearestNeighbour.predict(featureVector, 3) != featureVector.getLabel()) {
                misclassifiedFeatureVectors.add(featureVector);
                misclassified++;
            }
        }

        double errorrate = misclassified / (double) datasetTest.size();

        System.out.println("Error rate: " + errorrate + "% -> actual errors: " + misclassified + " -> testset size: " + datasetTest.size());

        for (FeatureVector featureVector : misclassifiedFeatureVectors) {
            DigitFrame misclassifiedDigitFrame = new DigitFrame("title misclassified", featureVector, 8, 8);
        }
    }

    public static void main(String[] args) {
        //                perceptron();

        //        long startTime = System.currentTimeMillis();
        //        perceptronDigits();
        //        long endTime = System.currentTimeMillis();
        //        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        //        nearestNeighbour();

        //        startTime = System.currentTimeMillis();
        //        nearestNeighbourDigits();
        //        endTime = System.currentTimeMillis();
        //        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

    }
}
