//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	Undirected (multi) DGraph represented via an edge list
//-----------------------------------------------------
import java.util.*;
import java.io.*;  

public class DGraphEdgeListWeighted extends DGraphEdgeList {
	public ArrayList<DEdgeWeighted> edges;
	
	public DGraphEdgeListWeighted(){
       this.edges = new ArrayList<DEdgeWeighted>();
    }
    public DGraphEdgeListWeighted(int nrVerts){
        super(nrVerts);
        this.edges = new ArrayList<DEdgeWeighted>();
    }
    public DGraphEdgeListWeighted(int nrVerts, int nrEdges){
        super(nrVerts);
        this.edges = new ArrayList<DEdgeWeighted>();
		for (int i = 0; i < nrEdges; i++){
				Random rand = new Random();
				int u = rand.nextInt(nrVerts);
				int v = rand.nextInt(nrVerts);
				addEdge(u, v);
            }
        }
       
    public void addEdge(DEdgeWeighted edge){
        edges.add(edge);
        //update number of vertices
        if(edge.getVertex2() > (nrVerts - 1)){
            setNrVerts(edge.getVertex2() + 1);
        } 
    }

    public int getNrEdges(){
        return edges.size();
    }

    public void printTgfDGraph(){
        System.out.println( toString());

    }

    public String edgesToString(ArrayList<DEdgeWeighted> edges){
        String tgfString = "";
        for (DEdgeWeighted edge : edges){
            tgfString += edge.toString() + "\n";
        }
        return tgfString;
    }
    public String toString(){
        String tgfString = "";
        // create the string for the lines giving the vertices in the tgf file
        for (int k = 0; k < nrVerts;  k++) {
            tgfString = tgfString + k + "\n";
        }
        //write #
        tgfString += "#\n";
        // followed by the string representing the lines for edges
        tgfString += edgesToString(edges);
        return tgfString;
    }       


}
