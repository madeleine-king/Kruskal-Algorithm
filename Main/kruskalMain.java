//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		April 22nd, 2022
// Description:	
// Action:	    
//
//
//-----------------------------------------------------
import java.util.*;

public class kruskalMain{

    public static void main(String[] args) {
		// get the input and output file paths from the command line
		String inputFile = args[0];
		String outputFile = args[1];

		System.out.println("==== KRUSKAL ALG ====");

		System.out.println("==== Constructing a weighted edge list using addEdge: ====");
		//print weighted graph edge list graph

		UGraphEdgeListWeighted graphUW = new UGraphEdgeListWeighted();

		graphUW.addEdge(1, 2, 2); graphUW.addEdge(2, 3, 3);
		graphUW.addEdge(1, 3, 1); graphUW.addEdge(0, 3, 1);
		graphUW.addEdge(0, 1, 2); graphUW.addEdge(0, 4, 5);
		graphUW.addEdge(1, 4, 3);

		graphUW.printTgfUGraph();

		System.out.println("==== Constructing a weighted edge list from TGF file: ====");
		UGraphEdgeListWeighted graphReadIn = new UGraphEdgeListWeighted();
		graphReadIn.readFromTgfFileWeighted(inputFile);
		graphReadIn.printTgfUGraph();
		System.out.println("edges: " + graphReadIn.getEdgeListWeighted());

		System.out.println("==== Run Kruskal's alorithm for minimum spanning tree on graph ====");
		UGraphEdgeListWeighted minSpanningTree = graphReadIn.kruskal();

		System.out.println("==== Minimum spanning tree: ====");
		minSpanningTree.printTgfUGraph();

		System.out.println("==== Writing minimum spanning tree to file ====");

		minSpanningTree.writeToTgfFile(outputFile);

		System.out.println("==== Wrote to file ====");

	}
}
