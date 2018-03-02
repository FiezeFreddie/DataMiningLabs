# DataMiningLabs

# 1
Question 1.1. What happens when you try to add shingles that are already in the ShingleSet? In
the above example, the shingle “ab” occurs twice in the string, how many times does it occur in the
ShingleSet?

```
It will only be in the list once.

    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element {@code e} to this set if
     * the set contains no element {@code e2} such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns {@code false}.
     *
     * @param e element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     *         element
     * @throws ClassCastException if the specified object cannot be compared
     *         with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     *         and this set uses natural ordering, or its comparator
     *         does not permit null elements
     */
    public boolean add(E e) {
        return m.put(e, PRESENT)==null;
    }
```

Question 3.1. Are these sentences very similar? Should the Jaccard distance between these two sets
therefore now be large or small?

```
We have a large Jaccard distance cause almost none of the 5 character Shingles match.

For k = 5
0.9655172413793104
```

Question 3.2. We had previously set our k to 5, what would happen if we reduce our k to 1? Would
that increase or decrease the distance between our two sets? Why is that? What happens in the case
where we increase our k to 15? Theorize what a feasible value for k would be, given the sentences
above.

```
For a K of 1 it will decrease the Jaccard distance between the two sets. It will match any character that is in both sentences.

For a K of 15 it will increase the Jaccard distance to a point won't even have one Shingle in common.

For k = 1
0.33333333333333337
For k = 15
1.0


The value of k should be such that the probability that any shingle occurs in any given document is low. In this case somewhere between 1 and 5.
```

Question 5.1. Did the Jaccard distance between the two sets now increase or decrease? Why is that?

```
It increased for a K of 1, because it wasn't able to match the whitespace. For a K of 5 it actually decreased the distance. More Shingles could be matched.

Without whitspaces:
For k = 1
0.35
For k = 5
0.9375

With whitespaces:
For k = 1
0.33333333333333337
For k = 5
0.9655172413793104
```

### 2
Question 2.1. To gain some insight in computing minhash signature matrices, compute the MinHash
signature by hand of the given ShingleSets above using the the hash functions h1 and h2. Do
this computation by hand! Refer to the slides or study material if you forgot how to do this.

```
		Sets
		0	1	2	3

S	0	1	0	0	1
H	1	0	0	1	0
I	2	0	1	0	1
N	3	1	0	1	1
G	4	0	0	1	0
L
E
S
	h1	h2		Signature
0	1	1		0	2	1	0
1	2	4		1	3	0	1
2	3	2		0	2	0	0
3	4	0
4	0	3
```

Question 4.1. Compute the minhash signature matrix using your program. Verify that the result of
your implementation is correct by comparing the answers of your program to the answers that you
got in Question 2.1.

```
1, 3, 0, 1, 
0, 2, 0, 0
```

### 3
Question 2.1. An important implementation issue is that you should keep seperate lists of buckets
for each band. This means that this algorithm will work suboptimal if you index the buckets only as:
buckets[hash(s)] instead of buckets[hash(s),band]. Why is this the case?

```
When a seperate array of buckets for each band is not used the amount of false positives will increase.
This is because some sets might be put into the same bucket when they are from different bands. Now documents that are not similar
are now considered by the algorithm to be similar.
```

Question 3.1. When you run your code multiple times, you will notice that sometimes you get other
candidates. How is this possible?
```
The random hash functions that are added to the minHash object causes the signature matrix to be different each run
therefore during the computation of candidates some sets are put in the same/different bucket each time.
```

Question 3.2. Run your code 10 times. Take notes which candidates get suggested and how many
times each candidate gets suggested. How does this relate to the Jaccard distance between the two
sets of the candidate pair (not in terms of formulas, just an indication)? To check this, compute
the Jaccard distance between all possible combinations of all ShingleSets and compare this to the
frequencies (how many times a pair gets suggested as a candidate) to verify your idea.

```
10 times:
[[3, 0], [2, 0], [3, 1]]
[[3, 0]]
[[3, 0], [3, 1]]
[[3, 0]]
[[3, 0]]
[[3, 0]]
[[3, 0], [3, 1]]
[[3, 0], [3, 1]]
[[3, 0]]
[[3, 0], [3, 1]]

Manual 5 times
sets	freq
4 3     1
1 4     5
3 1     1
4 2     3

Jaccard distance

s1 s2 = 1 - 0 = 1
s1 s3 = 1 - 0.25 = 0.75
s1 s4 = 1 - 2/3 = 1/3 = 0.33
s2 s3 = 1 - 0 = 1
s2 s4 = 1 - 1/3 = 2/3 = 0.66
s3 s4 = 1 - 1/5 = 4/5 = 0.8
```

Question 3.3. Why (or when) would you use this algorithm?

```
Mainly for efficiency reasons. Matrix operations can become expensive for large datasets.
Using minhash + LSH will reduce this size greatly.
```


Question 3.4. What happens if the number of buckets is too small? For example what would happen
if we only use 10 buckets?
```
When the number of buckets is too small the amount of false positives increase.
with only a few amount of buckets to choose from sets that are not really similar can be put into the same bucket.
This results in more false positives.
```

Question 3.5. What is the effect of the number of rows per band? If we set the number of rows
per band to 1, what will happen? And if you set the number of rows per band to the length of the
signature?

```
Setting the number of rows to 1 will cause collisions.
Setting the number of rows to the length of the signature will result in splits, causing possible candidates to end up in different buckets.
```