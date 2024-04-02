//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 15, 2022
// Description:	Undirected (multi) UGraph represented via an Adjacency List
//
//-----------------------------------------------------
import java.util.*;
import java.io.*;  

@SuppressWarnings("unchecked")
public class UGraphAdjList {
    private LinkedList<Integer>[] list; 
    //-------------------------------------
    // Constructors
    //-------------------------------------
    public UGraphAdjList(){
       // this.list = new LinkedList<Integer>[0];  
       list = new LinkedList[0];  
    }
    public UGraphAdjList(int nrVerts){
        list = new LinkedList[nrVerts];
        for (int i = 0; i < nrVerts; i++){
            list[i] = new LinkedList<Integer>();
        } 
    }
    public UGraphAdjList(int nrVerts, int nrEdges){
        list = new LinkedList[nrVerts];
        for (int i = 0; i < nrVerts; i++){
            list[i] = new LinkedList<Integer>();
        } 
		for (int j = 0; j < nrEdges; j++){
				Random rand = new Random();
				int u = rand.nextInt(nrVerts);
				int v = rand.nextInt(nrVerts);
				addEdge(u, v);
        }
    }
    
    public UGraphAdjList(UGraphEdgeList graph){
       // ArrayList<Boolean>visited;
        
        list = new LinkedList[graph.getNrVerts()];
        for (int i = 0; i < graph.getNrVerts(); i++){
            list[i] = new LinkedList<Integer>();
        }
        
        for (UEdge edge : graph.getEdgeList()){
            //go to index of first number, put second
            list[edge.getVertex1()].add(edge.getVertex2());
            //go to the index of second number, put first
            list[edge.getVertex2()].add(edge.getVertex1());
        }
    }

    //-------------------------------------
    // Getters
    //-------------------------------------
    public int getNrVerts(){
        return list.length;
    }
    public int getNrEdges(){
        int edgeCount = 0;
        for (int i = 0; i < list.length; i++){
            LinkedList<Integer> vertexEdges = list[i];
            for(int j = 0; j < vertexEdges.size(); j++){
                    if (vertexEdges.get(j) > i){
                        edgeCount++;
                    }
            }
        }
        return edgeCount;
    }
    
    
    //-------------------------------------
    // Utilities
    //-------------------------------------
    private int getMaxVertex(){
        return list.length;
	}

    //-------------------------------------
    // Setters
    //-------------------------------------
    public void setNrVerts(int nrVerts){
        for (int i = 0; i < nrVerts; i++){
            list[i] = new LinkedList<>();
        }
    }

    //-------------------------------------
    // Modifiers
    //-------------------------------------
    public void addVerts(int nrVerts){
        for (int i = 0; i < nrVerts; i++){
            list[i] = new LinkedList<Integer>();
        } 
    }
    public void addEdge(int u, int v){
        list[u].add(v);
        list[v].add(u);
    }
    
    //-------------------------------------
    // Testers
    //-------------------------------------
    public boolean hasEdge(int u, int v){
        LinkedList<Integer> vertexEdges = list[u];
        for (int i = 0; i < vertexEdges.size(); i++){
            if (vertexEdges.get(i) == v){
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty(){
        if (list == null) {return true;}
        return false;
    }

    //-------------------------------------
    // ToString
    //-------------------------------------
	public String toString(LinkedList<Integer> list[]){
		String tgfString = "";
		int counter = 0;
		for (LinkedList<Integer> vertex : list){
		    tgfString += counter + ": ";
			tgfString += vertex + "\n";
			counter++;
		}
		return tgfString;
	}

//     //-------------------------------------
//     // I/O
//     //-------------------------------------
	public void printUGraph(){
		System.out.println(toString(list));
	}
	
    public void readFromTgfFile(String filepath){
        UGraphEdgeList graph = new UGraphEdgeList();
        graph.readFromTgfFile(filepath);
    }
    
    //depth-first search
    //initialize boolean value for each vert- all marked as unvisited

    public boolean[] dfsR(int v, boolean[] visited){    
        //mark current vertext as visited
        System.out.print("visited " + v + "\n");
        visited[v] = true;
        //for all neighbors of v
        for (int i = 0; i < list[v].size(); i++){
            int vert = list[v].get(i);
            if (visited[vert] == false){
                dfsR(vert, visited);
            }
        }
        //if neighbor u is not visited, run DFS on u DFS(u)
        return visited; //return to the call that invoked me
    }
    
//     public void dfs(int start){
//         //ArrayList<Boolean>visited = new ArrayList<Boolean>();
//         boolean[] visited = new boolean[list.length];
//         //init visited with false everywhere
//         for (int i = 0; i < list.length; i++){
//             visited[i] = false;
//         }   
//         dfsR(start, visited);      
//     }    
    
        public void dfs(){
        //ArrayList<Boolean>visited = new ArrayList<Boolean>();
        boolean[] visited = new boolean[list.length];
        //init visited with false everywhere
        for (int i = 0; i < list.length; i++){
            visited[i] = false;
        }   
        
        for (int i = 0; i < list.length; i++){
            if (visited[i] = false){
            dfsR(i, visited);  
            }
        } 
    }    
    
    //finding out if graph is strongly connected: run a DFS starting at every variable
    //strongly connected if, for every vertex, you can run DFS and reach every variable without hitting a dead end
    
}
