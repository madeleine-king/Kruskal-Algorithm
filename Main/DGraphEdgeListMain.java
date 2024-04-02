//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	driver to use/test GraphEdgeList class
// Action:	    read, print and then write to a tgf file
//
//
//-----------------------------------------------------
import java.util.*;

public class DGraphEdgeListMain{

    public static void main(String[] args) {
		// get the input and output file paths from the command line
		String inputFile = args[0];
		String outputFile = args[1];
				
		System.out.println("==== Read a graph from a tgf file, print, test if simple, write to file:");
		DGraphEdgeList graph = new DGraphEdgeList(5);
		// Import
		graph.readFromTgfFile(inputFile);
		// Print
		graph.printTgfDGraph();
		// Run a function: test if simple
		System.out.println("==== Is simple = "+  graph.isSimple());
		// Export
		graph.writeToTgfFile(outputFile);
	}
}
