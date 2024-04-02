//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	driver to use/test DGraphAdjList class
// Action:	    read, print and then write to a tgf file
//
//-----------------------------------------------------
import java.util.*;

public class DGraphAdjListMainBFS{

    public static void main(String[] args) {
		// get the input and output file paths from the command line
		String inputFile = args[0];
		String outputFile = args[1];
				
		System.out.println("==== Read a graph from a tgf file, print, convert to adjacency list, print, perform BFS, convert back to edge list and write to file");
		System.out.println("==== Read a graph from tgf file = " + inputFile);
		DGraphEdgeList graphEdgeList = new DGraphEdgeList();
		// Import
		graphEdgeList.readFromTgfFile(inputFile);
		System.out.println("==== DONE\n");
		// Print
		System.out.println("==== Print the graph we just read");
		graphEdgeList.printTgfDGraph();
		System.out.println("==== DONE\n");
// 		System.out.println("==== Test if it is simple");
// 		boolean isSimple = graph.isSimple();
// 		System.out.println(" Is simple = "+  isSimple);
//		System.out.println("==== DONE\n");

		// Convert to Adj List representation
		System.out.println("==== CONVERT to adjLists\n");
		DGraphAdjList graphAdjList = new DGraphAdjList(graphEdgeList);
		
		System.out.println("==== PRINT adj lists graph\n");
		graphAdjList.printDGraph();
		System.out.println("==== DONE\n");
		
		System.out.println("==== Run BFS ====");
		graphAdjList.BFS(0);
		System.out.println("==== DONE\n");
		
		// Convert AdjLists to EdgeList
		System.out.println("==== CONVERT adj lists graph to edge list graph\n");
		DGraphEdgeList newEdgeList = graphAdjList.convertToEdgeList();
		System.out.println("==== DONE\n");
		System.out.println("==== Write converted graph to output file = "+outputFile);
		newEdgeList.writeToTgfFile(outputFile);
		System.out.println("Converted to Edge List and wrote to "+outputFile);
		
	}
}
