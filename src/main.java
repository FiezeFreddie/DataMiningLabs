
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
		// add code here
	}
	
	private static void kmeansTuneK() {
		// add code here
	}
	
	private static void kmeansDigits() {
		// add code here
	}

	public static void main(String[] args) {
		//hierarchical();
		hierarchicalDigits();
		//kmeans();
		//kmeansTuneK();
		//kmeansDigits();
	}

}
