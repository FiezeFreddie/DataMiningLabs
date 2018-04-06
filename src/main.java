
public class main {
	
	private static void hierarchical() {
		HierarchicalClusteringPlotter hcp = new HierarchicalClusteringPlotter(3, "data/cluster.txt");
	}
	
	private static void hierarchicalDigits() {
		HierarchicalClustering hc = new HierarchicalClustering(10, "data/train_digits.txt");
		while (hc.getClusterSize() > 10){
		    hc.update();
        }
        System.out.println(hc.getClusterSize());
		for (int i = 0; i < 10; i++){
		    DigitFrame dg = new DigitFrame("something", hc.getCluster(i).centroid(), 8, 8);
        }
	}
	
	private static void kmeans() {
		KMeansPlotter kMeansPlotter = new KMeansPlotter(3,"data/cluster.txt");
	}
	
	private static void kmeansTuneK() {
		for (int i = 1; i <= 10; i++){
			KMeans kMeans = new KMeans(i, "data/cluster.txt");
            for (int j = 0; j < 10; j++){
                kMeans.update();
            }
            double avrRss = 0d;

            for (Cluster c: kMeans.getClusters()){
                avrRss += c.calculateAverageRSS();
            }
            System.out.println("K = " + i + " Average RSS: " + avrRss/kMeans.getClusters().size());
		}
	}
	
	private static void kmeansDigits() {
		KMeans kMeans = new KMeans(10, "data/train_digits.txt");
		for (int i = 0; i < 10; i++){
		    kMeans.update();
        }

        for (int i = 0; i < 10; i++){
		    DigitFrame df = new DigitFrame("something", kMeans.getCluster(i).centroid(), 8, 8);
        }
	}

	public static void main(String[] args) {
		//hierarchical();
		//hierarchicalDigits();
		//kmeans();
		//kmeansTuneK();
		kmeansDigits();
	}

}
