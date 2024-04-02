//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	Undirected (multi) DGraph represented via an edge list
//-----------------------------------------------------
import java.util.*;
import java.io.*;  

public class UGraphEdgeListWeighted extends UGraphEdgeList {
	public ArrayList<UEdgeWeighted> edges;
	
	public UGraphEdgeListWeighted(){
       this.edges = new ArrayList<UEdgeWeighted>();
    }
    public UGraphEdgeListWeighted(int nrVerts){
        super(nrVerts);
        this.edges = new ArrayList<UEdgeWeighted>();
    }
    public UGraphEdgeListWeighted(int nrVerts, int nrEdges){
        super(nrVerts);
        this.edges = new ArrayList<UEdgeWeighted>();
		for (int i = 0; i < nrEdges; i++){
				Random rand = new Random();
				int u = rand.nextInt(nrVerts);
				int v = rand.nextInt(nrVerts);
                int w = rand.nextInt(10)+1;
				addEdge(u, v, w);
            }
        }

    public void addEdge(UEdgeWeighted edge){
        edges.add(edge);
        //update number of vertices
        if(edge.getVertex2() > (nrVerts - 1)){
            setNrVerts(edge.getVertex2() + 1);
        } 
    }

    public void addEdge(int u, int v, int w){
        UEdgeWeighted edge = new UEdgeWeighted(u, v, w);
        edges.add(edge);
        //update number of vertices
        if(edge.getVertex2() > (nrVerts - 1)){
            setNrVerts(edge.getVertex2() + 1);
        } 
    }

    public int getNrEdges(){
        return edges.size();
    }

    public ArrayList<UEdgeWeighted> getEdgeListWeighted(){
        return edges;
    }

    public void printTgfUGraph(){
        System.out.println( toString());

    }

    public String edgesToString(ArrayList<UEdgeWeighted> edges){ //add weights
        String tgfString = "";
        for (UEdgeWeighted edge : edges){
            tgfString += edge.toString() + "\n";
        }
        return tgfString;
    }
    public String toString(){ // add weights
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

    public void readFromTgfFileWeighted(String filepath){
        try {
            File graphFile = new File(filepath);
            Scanner fileScanner = new Scanner(graphFile);
            String lineString = fileScanner.nextLine();
            
            nrVerts=0;
            while (fileScanner.hasNextLine()){
                String data = fileScanner.nextLine();
                if (data.equals("#")){
                    nrVerts++;
                    break; }
                else{
                    nrVerts++; 
                }
            }
            int nrEdges=0;
            List<String> edgeslist = new ArrayList<String>();
            while (fileScanner.hasNextLine()){
                String edgeData[] = fileScanner.nextLine().split(" ");
                int u = Integer.parseInt(edgeData[0]);
                int v = Integer.parseInt(edgeData[1]);
                int w = Integer.parseInt(edgeData[2]);
                addEdge(u, v, w);  
                nrEdges++;
            }
            fileScanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("readFromTgfFile::error reading file: " + filepath);
            e.printStackTrace();
          }
    }

    public void writeToTgfFile(String filepath){
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(toString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("writeToTgfFile::error writing to file: " + filepath);
            e.printStackTrace();
          }
    }

        //-------Kruskal---------
        
    public UGraphEdgeListWeighted kruskal(){
        //initialize edgelist graph
        UGraphEdgeListWeighted minSpanningTree = new UGraphEdgeListWeighted();

        //order edges by weight
        sortEdges();

        System.out.println("Sorted edges: " + getEdgeListWeighted());

        //for each edge: add to graph
        for (UEdgeWeighted edge : edges){
        //make an adjacency list of the current graph
            UGraphAdjList testGraph = new UGraphAdjList(minSpanningTree);
            System.out.println("Current state of min spanning tree as adjacency list:");
            testGraph.printUGraph();
            testGraph.addEdge(edge.getVertex1(), edge.getVertex2());
            //check if graph has a cycle
            if (testGraph.hasCycle() == false){ 
                minSpanningTree.addEdge(edge); //if not cycle, add 
            }
        }
        return minSpanningTree;
    }

    public void sortEdges(){
        int n = edges.size();
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (edges.get(j).getWeight() > edges.get(j + 1).getWeight()){
                    UEdgeWeighted temp = edges.get(j);
                    edges.set(j, edges.get(j+1));
                    edges.set(j+1, temp);
                }
            }
        }
    }


}
