4/23/22

Final project for Smith College course CSC 253: Applied Algorithms in 2022. 

In this project, I implement Kruskal's algorithm to find the minimum spanning tree of a graph.

In the course, we built classes for directed and undirected graphs from scratch in Java, which we then used to implement various algorithms. 

To compile: javac -d Classes/ Src/*.java Main/*.java Testers/*.java
To run on two different graph examples: 
java -cp Classes kruskalMain Data/UGraphs/myWeightedGraph1.tgf Output/minimumSpanningTree1.tgf
java -cp Classes kruskalMain Data/UGraphs/myWeightedGraph2.tgf Output/minimumSpanningTree2.tgf

To view the graphs we are running the algorithm on, and the outputted minimum spanning trees, open the mathematica notebook External/weightedGraph.nb
