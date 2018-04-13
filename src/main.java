import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
	
	/**
	 * Computes nrVectors eigen vectors of m where e is the
	 * stopping criterion for the norm of the difference for an
	 * eigenvector in between two rounds.
	 * @param m The matrix of which eigenvectors should be computed.
	 * @param nrVectors The number of eigenvectors to compute.
	 * @param e The threshold for the stopping criterion.
	 * @return A list of eigenvectors in m.
	 */
	public static List<Matrix> powerIteration(Matrix m, int nrVectors, double e) {
		assert(m.cols() == m.rows() && m.cols() >= nrVectors);
		
		List<Matrix> eigenvectors = new ArrayList<Matrix>();
		
		// add code here
        Matrix theMatrix = m;
		for (int o = 0; o < nrVectors; o++) {
            Matrix vect = new Matrix(m.rows(), 1);
            for (int i = 0; i < vect.rows(); i++) {
                vect.set(i, 0, 1d);
            }
            boolean keepGoing = true;

            while (keepGoing) {
                Matrix dotProd = theMatrix.dot(vect);
                double theNorm = dotProd.norm();
                Matrix nextVec = dotProd.multiply(1 / Math.abs(theNorm));
                if (Math.abs(vect.add(nextVec.multiply(-1)).norm()) < e) {
                    keepGoing = false;
                    eigenvectors.add(nextVec);
                }
                vect = nextVec;
            }

            Matrix tempMat = theMatrix.dot(vect);
            double eigenVal = vect.transpose().dot(tempMat).get(0,0);
            Matrix otherTempMat = vect.dot(vect.transpose());
            otherTempMat = otherTempMat.multiply(eigenVal);
            theMatrix = theMatrix.add(otherTempMat.multiply(-1d));

        }
		return eigenvectors;
	}
	
	/**
	 * Computes two eigenvectors of a small matrix example.
	 */
	public static void powerIterationTest(){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("data/matrix.txt"));
            Scanner sc = new Scanner(fileInputStream);
            Matrix theMatrix = new Matrix(2, 2);
            for (int i = 0; i < theMatrix.rows(); i++) {
                for (int j = 0; j < theMatrix.cols(); j++) {
                    int nextVal = sc.nextInt();
                    double MatrixVal = (double) nextVal;
                    theMatrix.set(i, j, MatrixVal);
                }
            }
            powerIteration(theMatrix, 2, 10e-5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Computes the principal components from a Gaussian
	 * distributed dataset.
	 */
	public static void pca() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("data/gaussian.txt"));
            Scanner sc = new Scanner(fileInputStream);
            Matrix theMatrix = new Matrix();
            int points = 0;
            while (sc.hasNext()) {
                String next = sc.next();
                double MatrixVal =  Double.parseDouble(next);
                theMatrix.add(MatrixVal);
                points++;
            }
            points = points/2;
            theMatrix = new Matrix(points, 2, theMatrix);
            PCAPlotter pcaPlotter = new PCAPlotter();
            pcaPlotter.plotData(theMatrix);

            Matrix meanRow = theMatrix.meanRow();
            Matrix theNewMatrix = theMatrix.subtractRow(meanRow);
            Matrix theNewMatrixTransposed = theNewMatrix.transpose();
            Matrix covMat = theNewMatrixTransposed.dot(theNewMatrix);
            double theDivider = 1d / points;
            covMat = covMat.multiply(theDivider);

            List<Matrix> eigenvectors = powerIteration(covMat, 2, 10e-5);
            pcaPlotter.plotEigenvectors(eigenvectors);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Computes some principal components from a dataset
	 * of face images.
	 */
	public static void pcaFaces() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("data/faces.txt"));
            Scanner sc = new Scanner(fileInputStream);
            Matrix theMatrix = new Matrix();
            int rows = 0;
            while (sc.hasNext()){
                theMatrix.add((double)sc.nextInt());
                rows++;
            }
            rows = rows / 1024;
            theMatrix = new Matrix(rows, 1024, theMatrix);
            Matrix meanRow = theMatrix.meanRow();
            Matrix theNewMatrix = theMatrix.subtractRow(meanRow);
            Matrix theNewMatrixTransposed = theNewMatrix.transpose();
            Matrix covMat = theNewMatrixTransposed.dot(theNewMatrix);
            double theDivider = 1d / rows;
            covMat = covMat.multiply(theDivider);

            List<Matrix> eigenvectors = powerIteration(covMat, 10, 10e-5);

            for (Matrix m : eigenvectors){
                ImageFrame imageFrame = new ImageFrame("SOMETHING", m, 32,32);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		//powerIterationTest();
		//pca();
		pcaFaces();
	}

}
