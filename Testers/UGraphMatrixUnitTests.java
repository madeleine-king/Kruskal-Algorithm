//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		April 9th, 2022
// Description: Driver to unit test UGraphMatrix class
// Action:	    test constructors, getters, setters, modifiers, testers, read, print
//-------------------------------------


import java.util.*;

public class UGraphMatrixUnitTests{
    public static void main(String[] args) {
		UGraphMatrix graph01, graph02, graph03, graph04, graph05, graph06, graph07;
		LinkedList<Integer> list[];
		
		System.out.println("======= CONSTRUCTORS =======");
		
		System.out.println("==== 2. Empty graph, 5 vertices:");
		graph02 = new UGraphMatrix(5);
		graph02.printUGraph();

		System.out.println("======= CONVERT FROM EDGELIST =======");
		System.out.println("======= Random unweighted Edgelist graph: =======");
		UGraphEdgeList edgeListGraph = new UGraphEdgeList(5,7);
		edgeListGraph.printTgfUGraph();
		System.out.println("======= Converting to adjacency matrix: =======");
		UGraphMatrix matrixGraph = new UGraphMatrix(edgeListGraph);
		matrixGraph.printUGraph();

		System.out.println("======= Create weighted Edgelist graph: =======");
		UGraphEdgeListWeighted edgeListGraphW = new UGraphEdgeListWeighted(5);
		UEdgeWeighted edge1 = new UEdgeWeighted(0, 1, 10); UEdgeWeighted edge2 = new UEdgeWeighted(3, 4, 11); 
		UEdgeWeighted edge3 = new UEdgeWeighted(0, 2, 5); UEdgeWeighted edge4 = new UEdgeWeighted(2, 3, 2);
		UEdgeWeighted edge5 = new UEdgeWeighted(1, 3, 15);

		edgeListGraphW.addEdge(edge1); edgeListGraphW.addEdge(edge2); 
		edgeListGraphW.addEdge(edge3); edgeListGraphW.addEdge(edge4);
		edgeListGraphW.addEdge(edge5);

		edgeListGraphW.printTgfUGraph();

		System.out.println("# Edges in the graph: " + edgeListGraphW.getNrEdges());

		System.out.println("First edge in my Weighted edge list: " + edgeListGraphW.edges.get(0));
		System.out.println("Weight of first edge: " + edgeListGraphW.edges.get(0).getWeight());
		System.out.println("Vertex 2 of first edge: " + edgeListGraphW.edges.get(0).getVertex2());


		System.out.println("======= Converting to adjacency matrix: =======");
		UGraphMatrix matrixGraphW = new UGraphMatrix(edgeListGraphW);
		matrixGraphW.printUGraph();

		System.out.println("======= FLOYD WARSHALL =======");
		System.out.println("======= Run floyd warhshall alg on weighted graph: =======");
		matrixGraphW.floydWarshall();
		//System.out.println(Arrays.deepToString(matrixGraphW.floydWarshall()));

		
	}
}
