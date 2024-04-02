//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		April 9th, 2022
// Description: Driver to unit test DGraphMatrix class
// Action:	    test constructors, getters, setters, modifiers, testers, read, print
//-------------------------------------

import java.util.*;

public class DGraphMatrixUnitTests{
    public static void main(String[] args) {
		DGraphMatrix graph01, graph02, graph03, graph04, graph05, graph06, graph07;
		LinkedList<Integer> list[];
		
		System.out.println("======= CONSTRUCTORS =======");
		
		System.out.println("==== 2. Empty graph, 5 vertices:");
		graph02 = new DGraphMatrix(5);
		graph02.printDGraph();

		System.out.println("======= CONVERT FROM EDGELIST =======");
		System.out.println("======= Random Edgelist graph: =======");
		DGraphEdgeList edgeListGraph = new DGraphEdgeList(5,7);
		edgeListGraph.printTgfDGraph();
		System.out.println("======= Converting to adjacency matrix: =======");
		DGraphMatrix matrixGraph = new DGraphMatrix(edgeListGraph);
		matrixGraph.printDGraph();
		System.out.println("======= Create weighted Edgelist graph: =======");
		DGraphEdgeListWeighted edgeListGraphW = new DGraphEdgeListWeighted(5);
		DEdgeWeighted edge1 = new DEdgeWeighted(0, 1, 10); DEdgeWeighted edge2 = new DEdgeWeighted(3, 4, 11); 
		DEdgeWeighted edge3 = new DEdgeWeighted(0, 2, 5); DEdgeWeighted edge4 = new DEdgeWeighted(2, 3, 2);
		DEdgeWeighted edge5 = new DEdgeWeighted(1, 3, 15);

		edgeListGraphW.addEdge(edge1); edgeListGraphW.addEdge(edge2); 
		edgeListGraphW.addEdge(edge3); edgeListGraphW.addEdge(edge4);
		edgeListGraphW.addEdge(edge5);

		edgeListGraphW.printTgfDGraph();

		System.out.println("# Edges in the graph: " + edgeListGraphW.getNrEdges());

		System.out.println("First edge in my Weighted edge list: " + edgeListGraphW.edges.get(0));
		System.out.println("Weight of first edge: " + edgeListGraphW.edges.get(0).getWeight());
		System.out.println("Vertex 2 of first edge: " + edgeListGraphW.edges.get(0).getVertex2());


		System.out.println("======= Converting to adjacency matrix: =======");
		DGraphMatrix matrixGraphW = new DGraphMatrix(edgeListGraphW);
		matrixGraphW.printDGraph();

		System.out.println("======= FLOYD WARSHALL =======");
		System.out.println("======= Run floyd warhshall alg on weighted graph: =======");
		matrixGraphW.floydWarshall();
		//System.out.println(Arrays.deepToString(matrixGraphW.floydWarshall()));

	
		
	}
}