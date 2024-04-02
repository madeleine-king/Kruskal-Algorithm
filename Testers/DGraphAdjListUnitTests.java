//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		March 11, 2022
// Description: Driver to unit test DGraphAdjList class
// Action:	    test constructors, getters, setters, modifiers, testers, read, print, run levels of DFS
//-------------------------------------

import java.util.*;

public class DGraphAdjListUnitTests{
    public static void main(String[] args) {
		DGraphAdjList graph01, graph02, graph03, graph04, graph05, graph06, graph07;
		LinkedList<Integer> list[];
		
		System.out.println("======= CONSTRUCTORS =======");
		
		System.out.println("==== 1. Empty graph, 0 vertices:");
		graph01 = new DGraphAdjList();
		graph01.printDGraph();
		
		System.out.println("==== 2. Empty graph, 5 vertices:");
		graph02 = new DGraphAdjList(5);
		graph02.printDGraph();
		
		System.out.println("==== 3. Random graph, 5 vertices 7 edges:");
		graph03 = new DGraphAdjList(5, 7);
		graph03.printDGraph();
	
		
		System.out.println("======= GETTERS =======");
		System.out.println("==== 4. From previous random graph:");
		System.out.println("nrVerts: " + graph03.getNrVerts() + " nrEdges: " + graph03.getNrEdges());
		
		
		System.out.println("======= SETTERS =======");
		System.out.println("==== 5. Set nrVerts to 5 in first empty graph:");
		graph01.setNrVerts(5);
		graph01.printDGraph();

		System.out.println("======= MODIFIERS =======");
		System.out.println("==== 7. Make complete graph on 3 verts by adding one edge at a time:");
		graph04 = new DGraphAdjList(3);
		graph04.addEdge(1, 2);
		graph04.addEdge(2, 0);
		graph04.addEdge(0, 1);
		System.out.println("Added edges 1,2 2,0 and 0,1");
		graph04.printDGraph();
 		System.out.println("==== TESTERS ====");
 		System.out.println("==== 9. Tests on graph04 (previous graph)");
		System.out.println("Printing graph04\n");
		graph04.printDGraph();
 		
		System.out.println("Has edge (0, 1)? " + graph04.hasEdge(0, 1));
		System.out.println("Is empty? " + graph04.isEmpty());

		System.out.println("==== 11. Read graph06 from graph05.tgf file as DEdgeList, convert to DAdjacencyList, print");
		DGraphEdgeList graph09 = new DGraphEdgeList();
		graph09.readFromTgfFile("Data/DGraphs/mydgraph0.tgf");
		graph05 = new DGraphAdjList(graph09);
		graph05.printDGraph();
		
		System.out.println("====Convert to Edge List====:");
		DGraphEdgeList graph08 = graph03.convertToEdgeList();
		graph08.printTgfDGraph();
		
		System.out.println("====Reverse Graph Level 1====:");
		graph04.printDGraph();
		graph04 = graph04.reverse();
		graph04.printDGraph();
		
		System.out.println("==== Run DFS that prints out verts as it visits them Level 2 and 3====:");
		System.out.println("Running DFS on this graph: ");
		graph03.printDGraph();
		graph03.dfs();
		System.out.println("Performed DFS");
		
		System.out.println("==== Run DFS that returns list of verts in order of discovery time Level 4====:");
		System.out.println("Running DFS on this graph: ");
		graph03.printDGraph();
		System.out.println("Vertices visited in order of discovery time: " + graph03.dfsVertReturn());
		
		
		System.out.println("==== Run DFS that returns list of verts in order of finishing time Level 5 NOTE: BUGGY AND INCOMPLETE====:");
		System.out.println("Running DFS on this graph: ");
		graph03.printDGraph();
		System.out.println("Vertices visited in order of finishing time: " + graph03.dfsFinishReturn());
		
		System.out.println("==== Run DFS that returns edge tree Level 6====:");
		System.out.println("Running DFS on this graph starting at vertex 2: ");
		DGraphEdgeList graph10;
		graph03.printDGraph();
		graph10 = graph03.dfsEdges(2);
		System.out.println("\nPrinting DFS tree: ");
		graph10.printTgfDGraph();
		System.out.println("\nSaving DFS tree as tfg file: ");
		// write graph10 to a tfg file in the output folder
		graph10.writeToTgfFile("graph10.tgf");
		System.out.println("Wrote to graph10.tgf");
		
		System.out.println("==== Check if graph is strongly connected Level 9====:");
		System.out.println("Is graph03 strongly connected? " + graph03.isStronglyConnected());
		
		System.out.println("==== Run BFS !!! :-) ====:");
		graph03.BFS(0);
		System.out.println("ran BFS");
		
		
	}
}
