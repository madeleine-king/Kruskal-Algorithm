//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		March 11, 2022
// Description:	DGraph represented via an Adjacency List
//
//-----------------------------------------------------
import java.util.*;
import java.io.*;  


@SuppressWarnings("unchecked")
public class DGraphAdjList {
    private LinkedList<Integer>[] list; 
    //-------------------------------------
    // Constructors
    //-------------------------------------
    public DGraphAdjList(){
       list = new LinkedList[0];  
    }
    public DGraphAdjList(int nrVerts){
        list = new LinkedList[nrVerts];
        for (int i = 0; i < nrVerts; i++){
            list[i] = new LinkedList<Integer>();
        } 
    }
    public DGraphAdjList(int nrVerts, int nrEdges){
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
    
    public DGraphAdjList(DGraphEdgeList graph){ 
        list = new LinkedList[graph.getNrVerts()];
        for (int i = 0; i < graph.getNrVerts(); i++){
            list[i] = new LinkedList<Integer>();
        }
        
        for (DEdge edge : graph.getEdgeList()){ //right now: for each edge in the edge list, 
            //go to index of first number, put second
            list[edge.getVertex1()].add(edge.getVertex2());
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
                 //   if (vertexEdges.get(j) > i){ 
                        edgeCount++;
                  //  }
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
//         for (int i = 0; i < nrVerts; i++){
//             list[i] = new LinkedList<>();
//         }
        list = new LinkedList[nrVerts];
        for (int i = 0; i < nrVerts; i++){
            list[i] = new LinkedList<Integer>();
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
    
//     public void isDAG(){
//         //if there is a back edge, not a dag
//         //if there is an edge from a vertex to its ancestor, not a dag
//         
//     }
    
    public boolean isStronglyConnected(){
        //strongly connected true if you can run all without backedge = true
        //for each vert run backedge
        for (int i = 0; i < list.length; i++){
            if (dfsDeadend(i) == true){ return false; }
        }
        return true;
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

    //-------------------------------------
    // I/O
    //-------------------------------------
	public void printDGraph(){
		System.out.println(toString(list));
	}
	
    public void readFromTgfFile(String filepath){
        DGraphEdgeList graph = new DGraphEdgeList();
        graph.readFromTgfFile(filepath);
    }

    //-------------------------------------
    // DFS
    //-------------------------------------
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
        return visited; //return to the call that invoked me
    }
    
    public void dfs(){
        boolean[] visited = new boolean[list.length];
        //init visited with false everywhere
        for (int i = 0; i < list.length; i++){
            visited[i] = false;
        }   
        for (int i = 0; i < list.length; i++){
            if (visited[i] == false){
                dfsR(i, visited);  
            }
        } 
    }
    
        public ArrayList<Integer> dfsVertReturn(){
        ArrayList<Integer> visitedVerts = new ArrayList<Integer>();
            for (int i = 0; i < list.length; i++){
                if (visitedVerts.contains(i) == false){
                    dfsVertReturnR(i, visitedVerts);  
                }
            }
            return visitedVerts; 
        }
    
        public ArrayList<Integer> dfsVertReturnR(int v, ArrayList<Integer> visitedVerts){    
        visitedVerts.add(v);
        //for all neighbors of v
        for (int i = 0; i < list[v].size(); i++){
            int vert = list[v].get(i);
            if (visitedVerts.contains(vert) == false){
                dfsVertReturnR(vert, visitedVerts);
            }
        }
        return visitedVerts; //return to the call that invoked me
    }
    
        public ArrayList<Integer> dfsFinishReturn(){
        int counter = 0; 
        int finishVerts[] = new int[list.length];
        ArrayList<Integer> visitedVerts = new ArrayList<Integer>();
            for (int i = 0; i < list.length; i++){
                if (visitedVerts.contains(i) == false){
                    dfsFinishReturnR(i, visitedVerts, finishVerts, counter);  
                }
            }
            ArrayList<Integer> finishVertsList = new ArrayList<Integer>(list.length);
            for (int x : finishVerts){
                finishVertsList.add(x);
            }
            return finishVertsList; 
        }
    
        public ArrayList<Integer> dfsFinishReturnR(int v, ArrayList<Integer> visitedVerts, int[] finishVerts, int counter){    
        visitedVerts.add(v);
        //for all neighbors of v
        for (int i = 0; i < list[v].size(); i++){
            int vert = list[v].get(i);
            if (visitedVerts.contains(vert) == false){
                counter++;
                dfsVertReturnR(vert, visitedVerts);
            }
            finishVerts[counter] = vert;
        }
        return visitedVerts; //return to the call that invoked me
    }
    
    
        public DGraphEdgeList dfsEdgesR(int v, boolean[] visited, DGraphEdgeList dfsTree){    
        //mark current vertext as visited
        System.out.print("visited " + v + "\n");
        visited[v] = true;
        //for all neighbors of v
        for (int i = 0; i < list[v].size(); i++){
            int vert = list[v].get(i);
            if (visited[vert] == false){
                System.out.println("Visiting edge " + v + ", " + vert);
                dfsTree.addEdge(v, vert);
                dfsEdgesR(vert, visited, dfsTree);
            }
        }
        return dfsTree; //return to the call that invoked me
    }
    
    public DGraphEdgeList dfsEdges(int v){
        boolean[] visited = new boolean[list.length];
        DGraphEdgeList dfsTree = new DGraphEdgeList(list.length);
        //init visited with false everywhere
        for (int i = 0; i < list.length; i++){
            visited[i] = false;
        }  
        dfsEdgesR(v, visited, dfsTree);
        for (int i = 0; i < list.length; i++){
            if (visited[i] == false){
                System.out.println("Starting at " + i);
                dfsEdgesR(i, visited, dfsTree);  
            }
        } 
        return dfsTree;
    }

    public boolean[] dfsDeadendR(int v, boolean[] visited){    
        //mark current vertext as visited
        visited[v] = true;
        //for all neighbors of v
        for (int i = 0; i < list[v].size(); i++){
            int vert = list[v].get(i);
            if (visited[vert] == false){
                dfsDeadendR(vert, visited);
            }
        }
        return visited; //return to the call that invoked me
    }
    
    public boolean dfsDeadend(int start){
        boolean[] visited = new boolean[list.length];
        //init visited with false everywhere
        for (int i = 0; i < list.length; i++){
            visited[i] = false;
        }
        dfsDeadendR(start, visited);
        for (int i = 0; i < list.length; i++){
            if (visited[i] == false){
                dfsDeadendR(i, visited);  
                System.out.println("When starting from " + start + " hit dead end");
                return true;
        } 
    }
    return false;
    }

    //-------------------------------------
    // Conversion to Edge List
    //-------------------------------------     
    public DGraphEdgeList convertToEdgeList(){ 
    DGraphEdgeList graphEdgeList = new DGraphEdgeList(list.length);
        for (int i = 0; i < list.length; i++){
		    for (int j = 0; j < list[i].size(); j++){
				    graphEdgeList.addEdge(i, list[i].get(j));
		    }
        }
        return graphEdgeList;
    }
    
    //-------------------------------------
    // Reverse
    //-------------------------------------    
    
    public DGraphAdjList reverse(){
        DGraphEdgeList graphEdgeList = this.convertToEdgeList();
        graphEdgeList = graphEdgeList.reverse();
        DGraphAdjList reversed = new DGraphAdjList(graphEdgeList);
        return reversed;
    }
    
    //finding out if graph is strongly connected: run a DFS starting at every variable
    //strongly connected if, for every vertex, you can run DFS and reach every variable without hitting a dead end
    
    //-------------------------------------
    // BFS
    //-------------------------------------   
    
    public void BFS(int s){
        ArrayList<String> color = new ArrayList<String>(list.length);
        ArrayList<Integer> parent = new ArrayList<Integer>(list.length);
        ArrayList<Integer> distance = new ArrayList<Integer>(list.length);
        ArrayList<Integer> queue = new ArrayList<Integer>();
        for(int i = 0; i < list.length; i++){ //set distance to infinity, color to white, parent to nil
            color.add("white");
            parent.add(-1);
            distance.add(-1);
        }
        color.set(s, "grey");
        System.out.println("Setting starting vert " + s + " to grey");
        parent.set(s, -1); //DO WE NEED THIS?
        distance.set(s, 0);
        queue.add(s);
        while(!queue.isEmpty()){
            int u = queue.get(0);
            queue.remove(0);
            for (int i = 0; i < list[u].size(); i++){
                int child = list[u].get(i);
                if(color.get(child) == "white"){
                    color.set(child, "grey");
                    System.out.println("Setting vert " + child + " to grey");
                    parent.set(child, u);
                    distance.set(child, distance.get(u) + 1);
                    queue.add(child);
                }
            }
            color.set(u, "black");
            System.out.println("Setting vert " + u + " to black");
        }
        
        for (int j = 0; j < list.length; j++){
            if(color.get(j) == "white"){
                        color.set(j, "grey");
                        System.out.println("Setting starting vert " + j + " to grey");
                        parent.set(j, -1); //DO WE NEED THIS?
                        //distance.set(j, 0);
                        queue.add(j);
                        while(!queue.isEmpty()){
                            int u = queue.get(0);
                            queue.remove(0);
                            for (int i = 0; i < list[u].size(); i++){
                                int child = list[u].get(i);
                                if(color.get(child) == "white"){
                                    color.set(child, "grey");
                                    System.out.println("Setting vert " + child + " to grey");
                                    parent.set(child, u);
                                    //distance.set(child, distance.get(u) + 1);
                                    queue.add(child);
                                }
                            }
                            color.set(u, "black");
                            System.out.println("Setting vert " + u + " to black");
                        }
            }
        }
        
        //print all info 
        for  (int j = 0; j < list.length; j++){
            System.out.println("==== Result of BFS: ====");
            System.out.println("Vertex " + j+ " has a distance of " + distance.get(j) + " its parent is " +parent.get(j));
        }
        
    }
   
//     public void BFSloop(int s){
//         color.set(s, "grey");
//         System.out.println("Setting starting vert " + s + " to grey");
//         parent.set(s, -1); //DO WE NEED THIS?
//         distance.set(s, 0);
//         queue.add(s);
//         while(!queue.isEmpty()){
//             int u = queue.get(0);
//             queue.remove(0);
//             for (int i = 0; i < list[u].size(); i++){
//                 int child = list[u].get(i);
//                 if(color.get(child) == "white"){
//                     color.set(child, "grey");
//                     System.out.println("Setting vert " + child + " to grey");
//                     parent.set(child, u);
//                     distance.set(child, distance.get(u) + 1);
//                     queue.add(child);
//                 }
//             }
//             color.set(u, "black");
//             System.out.println("Setting vert " + u + " to black");
//             System.out.println("Color: " + color);
//         }
//     }
    
    public void dijkstra(int s){
    }
        
}
