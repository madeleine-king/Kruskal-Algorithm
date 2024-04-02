//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		April 8, 2022
// Description:	Undirected (multi) Graph represented via an adj matrix (WEIGHTED AND UNWEIGHTED)
//-----------------------------------------------------
import java.util.*;
import java.io.*;  

public class UGraphMatrix{
	private int matrix[][];
	private int nrVerts;

	public UGraphMatrix(int nrVerts){
		this.nrVerts = nrVerts;
		matrix = new int[nrVerts][nrVerts];

	}

	public UGraphMatrix(UGraphEdgeList graph){ //unweighted edge list 
		this.nrVerts = graph.getNrVerts();
		matrix = new int[nrVerts][nrVerts];
		for (int i = 0; i < graph.getNrEdges(); i++) { //for each edge in the graph
      		//add the edge to the matrix
			UEdge edge = graph.edges.get(i);
      		matrix[edge.getVertex1()][edge.getVertex2()] = 1; //unweighted
      		matrix[edge.getVertex2()][edge.getVertex1()] = 1; 
      	}
	  	for(int i=0; i< nrVerts; i++) {
            for(int j=0; j< nrVerts; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][j] = 9999;
                }
            }
        }

	}

	public UGraphMatrix(UGraphEdgeListWeighted graph){ //weighted edge list
		this.nrVerts = graph.getNrVerts();
		matrix = new int[nrVerts][nrVerts];
		for (int i = 0; i < graph.getNrEdges(); i++) { //for each edge in the graph
	  		//add the edge to the matrix
			UEdgeWeighted edge = graph.edges.get(i);
	  		matrix[edge.getVertex1()][edge.getVertex2()] = edge.getWeight(); //weighted
	  		matrix[edge.getVertex2()][edge.getVertex1()] = edge.getWeight(); 
	  	}
	  	for(int i=0; i< nrVerts; i++) {
            for(int j=0; j< nrVerts; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][j] = 9999;
                }
            }
        }

	}

    //-------------------------------------
    // I/O
    //-------------------------------------
	public void printUGraph(){
		System.out.println(Arrays.deepToString(matrix));
	}

	public int[][] floydWarshall(){

		int dist[][] = new int[nrVerts][nrVerts];
		//int distReturn [][] = new int[nrVerts]

		for (int i = 0; i < nrVerts; i++){
        	for (int j = 0; j < nrVerts; j++){
                dist[i][j] = matrix[i][j];
        	}
		}

		//distMatrix starts as same as matrix then updated w the value of the shortest path
		//run for each k value up to nrVerts
		//for each vertex u, find the length of the shortest path to each vert including u
		//after each k run, update distMatrix 
		for (int k = 0; k < nrVerts; k++){
            // Pick all vertices as source one by one
            for (int i = 0; i < nrVerts; i++){
                // Pick all vertices as destination for the
                // above picked source
                for (int j = 0; j < nrVerts; j++){
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        //System.out.println("In the if. new dist should be: " + (dist[i][k] + dist[k][j]));
                    }
                    //System.out.println(Arrays.deepToString(dist));
                    }
                }
            }
            System.out.println(Arrays.deepToString(dist));
            return dist;
        }
	}





