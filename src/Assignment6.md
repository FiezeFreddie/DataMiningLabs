### Assignment 6
#### 6.1 Exercise 6.1. Power Iteration
Question 0.1. What property defines the relation between the M , v, and Lambda? Write down this equation.

    Mv = Lambda * v
    
    Meaning that multiplying vector v with matrix M 
    has the same result as multiplying v with a single value.
    
Question 3.1. How many eigenvectors could there possibly be in a D x D matrix?

    In a D x D matrix there are D possible eigenvectors.
   
Question 3.2. Are the eigenvalues of these eigenvectors increasing or decreasing as you compute
more vectors? What do these eigenvalues say about the eigenvectors?

    Decreasing. Power iteration computes the largest eigenvalue each iteration.
    The eigenvalue is the numerical value by which the vector can be multiplied,
    to get the same result as multiplying the vector with a matrix.
    
Question 3.3. We have seen a very similar method before, what was it used for then? We did not
need to normalize the vector then, why is that?

#### Exercise 6.2. Principal Component Analysis

Question 1.1. By just looking at the plotted data, what can you say about the direction of the first
principal component? What about the second principal component?

    The first principal component should be a diagonal line that goes from the bottom left to the top right.
    The second component is a line orthogonal to the first one.
    
Question 2.1. Which of these eigenvectors captures the most variance? Was this the first principal
component or the second?

    The first component clearly has more variance
    
Question 4.1. What do these principal components mean, in terms of faces?

    the principal components is a 1024 dimensional vector where each value represents a grey value of a pixel.
    the resulting picture with its values has the most variance.