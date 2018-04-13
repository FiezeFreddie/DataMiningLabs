import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Matrix extends ArrayList<Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The number of rows in this matrix.
	 */
	private int rows;

	/**
	 * The number of columns in this matrix.
	 */
	private int cols;

	/**
	 * Empty Matrix constructor.
	 */
	public Matrix() {
		rows = 0;
		cols = 0;
	}
	
	/**
	 * Constructor.
	 * @param r Number of rows.
	 * @param c Number of columns.
	 */
	public Matrix(int r, int c) {
		this(r, c, 0.0);
	}
	
	/**
	 * Constructor with initialization value.
	 * @param r Number of rows.
	 * @param c Number of columns.
	 * @param val The value to initialize with.
	 */
	public Matrix(int r, int c, double val) {
		super(r*c);
		rows = r;
		cols = c;

		for (int i = 0; i < rows*cols; i++)
			add(i, val);
	}

	/**
	 * Constructor for creating a submatrix.
	 * @param r
	 * @param c
	 * @param subList
	 */
	public Matrix(int r, int c, List<Double> subList) {
		super(subList);
		rows = r;
		cols = c;
	}

	/**
	 * Sets the value in the signature matrix.
	 * @param r The row of the value to be set.
	 * @param c The column of the value to be set.
	 * @param value The value to set at (r, c).
	 */
	public void set(int r, int c, double value) {
		set((cols*r) + c, value);
	}

	/**
	 * Returns the value of the signature matrix.
	 * @param r The row of the value.
	 * @param c The column of the value.
	 * @return The value at (r, c).
	 */
	public double get(int r, int c) {
		return get((cols*r) + c);
	}
	
	/**
	 * Appends a row to the matrix.
	 * @param row The row to append.
	 */
	public void appendRow(Matrix row) {
		assert(row.rows() == 1 && (row.cols() == cols()) || cols() == 0);
		
		if (cols() == 0)
			cols = row.cols();
		
		addAll(row);
		rows++;
	}

	/**
	 * @return Returns the number of rows in the signature matrix.
	 */
	public int rows() {
		return rows;
	}

	/**
	 * @return Returns the number of columns in the signature matrix.
	 */
	public int cols() {
		return cols;
	}
	
	/**
	 * Reads in a Matrix from fileName.
	 * @param fileName The file name of the matrix.
	 * @return The parsed matrix in fileName.
	 */
	public static Matrix readData(String fileName) {
		// create the result
		Matrix result = new Matrix();
		
		try {
			// open the file
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			// parse the content
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				
				Matrix row = new Matrix(1, split.length);
				for (int i = 0; i < split.length; i++)
					row.set(0, i, Double.parseDouble(split[i]));
				result.appendRow(row);
			}

			// close the file
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Calculates the dot product between this matrix and the other matrix.
	 * In other words, it calculates "this * other"
	 * @param other The matrix that is to be multiplied with.
	 * @return The product of the two matrices.
	 */
	public Matrix dot(Matrix other) {
		assert(this.cols() == other.rows());

		Matrix result = new Matrix(this.rows(), other.cols());

		// add code here
		for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                double temp = 0d;
                for (int p = 0; p < this.cols(); p++) {
                        temp += get(i, p) * other.get(p, j);
                }
                result.set(i, j, temp);
            }
        }
		return result;
	}

	/**
	 * Multiplies the matrix with a scalar, scaling each element in the matrix.
	 * @param scalar The scalar with which to multiply each element.
	 * @return A new matrix with scaled elements.
	 */
	public Matrix multiply(double scalar) {
		Matrix result = (Matrix) this.clone();

		// add code here
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                result.set(i, j, scalar * get(i, j));
            }
        }
		return result;
	}

	/**
	 * Adds one matrix to another. They need to be of the same size.
	 * @param other The other matrix.
	 * @return A new matrix where all elements of the two matrices have been added.
	 */
	public Matrix add(Matrix other) {
		assert(cols() == other.cols() && rows() == other.rows());

		Matrix result = new Matrix(rows(), cols());
		
		// add code here
        for (int i = 0; i < result.rows(); i++){
            for (int j = 0; j < result.cols(); j++){
                result.set(i, j,this.get(i,j) + other.get(i,j));
            }
        }
		return result;
	}
	
    /**
     * Transposes the matrix.
     * @return The transposed matrix.
     */
    public Matrix transpose() {
		Matrix result = new Matrix(cols(), rows());
     
		// add code here
        for (int i = 0; i < result.rows(); i++){
            for (int j = 0; j < result.cols(); j++){
                result.set(i, j, this.get(j,i));
            }
        }

        return result;
    }
    
    /**
     * Calculates the (L2) norm.
     * @return The (L2) norm.
     */
    public double norm() {
    	double result = 0.0;
    	
    	// add code here
    	for (int i = 0; i < this.rows(); i++){
    	    result += this.get(i, 0) * this.get(i, 0);
        }
    	return Math.sqrt(result);
    }
    
    /**
     * Retrieves row r from this matrix.
     * @param r The index of the row to get.
     * @return The row at index r.
     */
    public Matrix row(int r) {
    	return new Matrix(1, cols(), subList(r*cols(), (r+1)*cols()));
    }
    
    /**
     * Calculates the mean of this matrix to one row.
     * In other words, each element in the resulting row
     * is the mean of a column in this matrix.
     * @return The mean row of this matrix.
     */
    public Matrix meanRow() {
    	Matrix mean = new Matrix(1, cols());
    	
    	// add code here
        for (int i = 0; i < this.cols(); i++){
            double rowMean = 0d;
            for (int j = 0; j < this.rows(); j++){
                rowMean += this.get(j, i);
            }
            double theRows = (double) this.rows();
            mean.set(0, i, rowMean/theRows);
        }
    			
    	return mean;
    }
    
    /**
     * Subtracts a row from this matrix. r.cols() must
     * be equal to cols().
     * @param r The row to subtract.
     * @return This matrix with r subtracted from it.
     */
    public Matrix subtractRow(Matrix r) {
    	assert(r.cols() == cols());
    	
    	Matrix result = new Matrix(rows(), cols());
    	
    	// add code here
		for (int i = 0; i < result.rows(); i++){
		    for (int j = 0; j < result.cols(); j++){
		        result.set(i, j, this.get(i, j) - r.get(0, j));
            }
        }
    	
    	return result;
    }

	/**
	 * Puts the matrix in a String format.
	 */
	@Override
	public String toString() {
		String result = "[";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				result += get(r, c) + ", ";
			}
			result += "\n";
		}
		return result.substring(0, result.length() - 3) + "]\n";
	}

}
