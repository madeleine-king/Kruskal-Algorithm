//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 15, 2022
// Description: Driver to unit test DGraphEdgeList class
// Action:	    test constructors, getters, setters, modifiers, testers, read, print and write to a tgf file
//-------------------------------------

import java.util.*;

public class DGraphEdgeListUnitTests{
    public static void main(String[] args) {
		DGraphEdgeList graph01, graph02, graph03, graph04, graph05, graph06, graph07;
		ArrayList<DEdge> edges;
		
		System.out.println("======= CONSTRUCTORS =======");
		
		System.out.println("==== 1. Empty graph, 0 vertices:");
		graph01 = new DGraphEdgeList();
		graph01.printTgfDGraph();
		
		System.out.println("==== 2. Empty graph, 5 vertices:");
		graph02 = new DGraphEdgeList(5);
		graph02.printTgfDGraph();
		
		System.out.println("==== 3. Random graph with 5 verts and 7 edges:");
		//create graph03: a random graph as suggested, then print it
		graph03 = new DGraphEdgeList(5, 7);
		graph03.printTgfDGraph();
		
		System.out.println("======= GETTERS =======");
		System.out.println("==== 4. From previous random graph:");
		//get the nr verts, nr edges and the edge list from graph03, print them
		System.out.println("nrVerts: " + graph03.getNrVerts() + " nrEdges: " + graph03.getNrEdges() + " edge list: " + graph03.getEdgeList());
		
		System.out.println("======= SETTERS =======");
		System.out.println("==== 5. Set nrVerts to 5 in first empty graph:");
		// as described, on graph01
		graph01.setNrVerts(5);
		graph01.printTgfDGraph();
		System.out.println("==== 6. Then set edge list to the edge list in the previous random graph");
		// get the edges of graph03, set the edges of graph01 to be these edges, then print the updated graph01
		graph01.setEdgeList(graph03.getEdgeList());
		graph01.printTgfDGraph();
		
		System.out.println("======= MODIFIERS =======");
		System.out.println("==== 7. Make complete graph on 3 verts by adding one edge at a time:");
		graph04 = new DGraphEdgeList(3);
		DEdge edge1 = new DEdge(1, 2);
		DEdge edge2 = new DEdge(2, 3);
		DEdge edge3 = new DEdge(3, 1);
		graph04.addEdge(edge1);
		graph04.addEdge(edge2);
		graph04.addEdge(edge3);
		graph04.printTgfDGraph();
		
		System.out.println("==== 8. Make a graph on 5 verts:");
		graph05 = new DGraphEdgeList(5, 3);
		graph05.printTgfDGraph();
		
		System.out.println("==== TESTERS ====");
		System.out.println("==== 9. Tests on graph05 (previous graph)");
		// to do: print graph05, then run all the testers in the class to verify they run correctly
		System.out.println("Printing graph05\n");
		graph05.printTgfDGraph();
		DEdge edgeNew = new DEdge(0, 1);
		
		System.out.println("Has edge (0, 1)? " + graph05.hasEdge(edge1));
		System.out.println("Is empty? " + graph05.isEmpty());
		System.out.println("Has loops? " + graph05.hasLoops());
		System.out.println("Has multiedges? " + graph05.hasMultiEdges());
		System.out.println("Is simple? " + graph05.isSimple());
		
		System.out.println("==== I/O ====");
		System.out.println("==== 10. Write graph05:");
		// write graph05 to a tfg file in the output folder
		graph05.writeToTgfFile("graph05.tgf");
		System.out.println("Wrote to graph05.tgf");
		
		System.out.println("==== 11. Read graph06 from graph05.tgf file, print, add a new edge, write to file graph06:");
		graph06 = new DGraphEdgeList();
		graph06.readFromTgfFile("graph05.tgf");
		graph06.printTgfDGraph();
		graph06.addEdge(1, 2);
		System.out.println("Added edge (1, 2) to graph06");
		graph06.printTgfDGraph();
		graph06.writeToTgfFile("graph06.tgf");
		System.out.println("Wrote to graph06.tgf");
		
		
		
	}
}
