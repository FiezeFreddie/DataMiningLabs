### Assignment 3

Question 1.1 Draw the directed graph based on this data.
![Directed Graph](https://github.com/FiezeFreddie/DataMiningLabs/raw/assignment3/graph.png)

Question 1.2 Write out the transition matrix for this network. Verify that all columns sum up to 1.
```
  A   B   C   D
A 0.0 0.5 1.0 0.0 
B 0.0 0.0 0.0 0.5 
C 0.5 0.0 0.0 0.5 
D 0.5 0.5 0.0 0.0
```

Question 1.3 If we would initialize a random surfer at a random location (ie. the chance to be
spawned at a certain vertex is equal for all vertices), what are the chances for this random surfer to
be at a certain location after one iteration? Manually calculate these probabilities.
```
A -> C 0.5
  -> D 0.5

B -> A 0.5
  -> D 0.5

C -> A 1.0

D -> B 0.5
  -> C 0.5
  
A = 1.5 -> .375 %
B = 0.5 -> .125 %
C = 1   -> .25  %
D = 1   -> .25  %

```

Question 8.1. Explain the results you now get from the PageRank algorithm.
```
You'll almost always end up in C, with 98.8% chance. If you even reach C, it will keep increasing the weight towards itself, hence the edge from C to C.
```

Question 9.1. Check the results using this adapted version of PageRank, are the results better?
```
PageRank: {A=0.0029296875, B=0.00390625, C=0.988037109375, D=0.005126953125}
TaxationPageRank: {A=0.09038671359999997, B=0.10075706879999999, C=0.6822519039999999, D=0.12660431359999996}

The results look better. It's still centered around C. Though you can definitaly see it averaging out a cross all vectors.
```

Question 9.2. What happens if we set beta to 0? What happens if we set beta to 1?
```
0: {A=0.25, B=0.25, C=0.25, D=0.25}

1: {A=0.0029296875, B=0.00390625, C=0.988037109375, D=0.005126953125}

Zero: it returns the plain randomsurfer matrix. One: It's the same as without the taxation algorithm.
```

Question 10.1. What is the most important airport according to the results? (You can use the
sortByValues in main.java to sort by PageRank value)
```
Atlanta, GA=0.06562884225126445
```