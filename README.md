### Assignment 2


Question 0.1. Using **w'** and some object with feature values **x'**, what is now the formula that needs
to be evaluated to determine if the object belongs to the positive class?
```
We should evaluate the equation w' transposed times x' is larger or equal to zero.

x = [1 2]T
w = [3 4]T

threshold = 10

wT * x = 11

11 is larger or equal to 10

---

x = [1 2 -1]T
w = [3 4 10]T

wT * x = 1

1 is larger or equal to 0
```

Question 0.2. How can you determine on which side of the decision boundary, that is represented
by the weights **w'**, a certain feature vector is?
```
The decision boundry is given by the above equation: w' transposed times x' is larger or equal to zero. If tue, it's on one side, if false, it's on the other side of the decision boundary.
```

Question 2.1. What could happen if the learning rate is too low? What could happen if the learning
rate is too high?
```
The choice of η affects the convergence of the perceptron. If η is too small, 
then convergence is slow; if it is too big, then the decision boundary will
"dance around" and again will converge slowly, if at all.
```

Question 3.1. The yellow line indicates the vector represented by w. The black line represents the
decision boundary. How are the two related?
```

```

Question 3.2. What equation belongs to the decision boundary? Write it down.
```
w*x = threshold
```

Question 4.1. What is contained in the dataset? How many images of each class are there?
```
Grayscale images, size 8x8. One hunderd of them. The first half represents zeros and has a label of -1,
 the second half represent ones and has a label of 1.  
```

Question 6.1. Explain the shape of the image representation of the weights. How does this distinguish
the different classes from one another? Why are some parts dark and other parts light?
```
It highlights weighted features of the trained model. 
The label of the zeros is -1, thus the inverted values are added to the weighted set.
Therefore you can see a bright white space in the midle, where the ones are originally white, and the zeros black.
The outersides are a near perfect grey (in between black/white) where the ones are originally black, and the zeros black.
```

Question 7.1. Why do we need a separate dataset to test the classifier? What would happen if we
tested our classifier on our training set?
```
If we would have tested on the training dataset we wouldn't find any misclassification. Thus, making it useless. Example:
Error rate: 0.0% -> actual errors: 0.0 -> testset size: 400
```

Question 8.1. Why do you think these were misclassified?
```
They are too similiar to a one, as well as a zero. Therefore it couldn't make a precise decision.
```

Question 1.1. Imagine we have one point of class a at the origin and two points of class b near
infinity. What would happen if we want to predict a point near the origin if we set k to 3? How could
we fix this?
```

```

Question 3.1. What are the advantages/disadvantages of the nearest neighbour classifier?
```
Advantage: The nearest neighbour classifier is more accurate. One misclassification vs two misclassifications.

Disadvantage: It is slower, for each prediction it does a distance calculation for the entire dataset.

Error rate: 0.005% -> actual errors: 2.0 -> testset size: 400
Total execution time: 84ms
Error rate: 0.0025% -> actual errors: 1.0 -> testset size: 400
Total execution time: 175ms
```