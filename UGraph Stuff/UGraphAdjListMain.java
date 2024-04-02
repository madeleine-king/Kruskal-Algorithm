//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	driver to use/test UGraphAdjList class
// Action:	    read, print and then write to a tgf file
//
//-----------------------------------------------------
import java.util.*;

public class UGraphAdjListMain{

    public static void main(String[] args) {
		// get the input and output file paths from the command line
		String inputFile = args[0];
		String outputFile = args[1];
				
		System.out.println("==== Read a graph from a tgf file, print, convert to adjacency list, print then perform DFS:");
		UGraphEdgeList graphEdgeList = new UGraphEdgeList();
		// Import
		graphEdgeList.readFromTgfFile(inputFile);
		// Print
		graphEdgeList.printTgfUGraph();
		UGraphAdjList graphAdjList = new UGraphAdjList(graphEdgeList);
		graphAdjList.printUGraph();
		graphAdjList.dfs();
		
	
	}
}
