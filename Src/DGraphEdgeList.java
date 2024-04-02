//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	Undirected (multi) DGraph represented via an edge list
//-----------------------------------------------------
import java.util.*;
import java.io.*;  

public class DGraphEdgeList {
    public int nrVerts;
    public ArrayList<DEdge> edges;

    //-------------------------------------
    // Constructors
    //-------------------------------------
    public DGraphEdgeList(){
        this.nrVerts = 0;
        this.edges = new ArrayList<DEdge>();
    }
    public DGraphEdgeList(int nrVerts){
        this.nrVerts = nrVerts;
        this.edges = new ArrayList<DEdge>();
    }
    public DGraphEdgeList(int nrVerts, int nrEdges){
        this.nrVerts = nrVerts;
        this.edges = new ArrayList<DEdge>();
		for (int i = 0; i < nrEdges; i++){
				Random rand = new Random();
				int u = rand.nextInt(nrVerts);
				int v = rand.nextInt(nrVerts);
				addEdge(u, v);
            }
        }

    //-------------------------------------
    // Getters
    //-------------------------------------
    public int getNrVerts(){
        return nrVerts;
    }
    public int getNrEdges(){
        return edges.size();
    }
    public ArrayList<DEdge> getEdgeList(){
        return edges;
    }
	
    //-------------------------------------
    // Utilities
    //-------------------------------------
    private int getMaxVertex(ArrayList<DEdge> edges){
		if (edges.isEmpty()){
		     return -1; 
		}

		int max = 0;            
         for (int i = 0; i < getNrEdges(); i++){
		     if (edges.get(i).getVertex1() > max) {
		            max = edges.get(i).getVertex1();
		    }
		    if (edges.get(i).getVertex2()>max){
		            max = edges.get(i).getVertex2();
		    }  
        }
		return max;
	}

	
    //-------------------------------------
    // Setters
    //-------------------------------------
    public void setNrVerts(int nrVerts){
        this.nrVerts = nrVerts;
        edges = new ArrayList<DEdge>();
    }
    public void setEdgeList(ArrayList<DEdge> edges){
        this.edges = edges;
		int max = getMaxVertex(edges);
		if (nrVerts < max){nrVerts = max + 1;}
		
	}

    //-------------------------------------
    // Modifiers
    //-------------------------------------
    public void addVerts(int nr){
        nrVerts += nr;
    }
    public void addEdge(DEdge edge){
		edges.add(edge);
		//update number of vertices
		if (edge.getVertex1() > (nrVerts - 1)){
		    setNrVerts(edge.getVertex1() + 1);
		}
		else if(edge.getVertex2() > (nrVerts - 1)){
		    setNrVerts(edge.getVertex2() + 1);
		} 
    }
    public void addEdge(int u, int v){
        DEdge edge = new DEdge(u, v);
        addEdge(edge);
    }
	
	public DGraphEdgeList reverse(){
	    //make empty dgraph 
	    DGraphEdgeList newgraph = new DGraphEdgeList(nrVerts);
	    for (DEdge edge : getEdgeList()){
	        DEdge newEdge = edge.reverseEdge(edge.getVertex1(), edge.getVertex2());
	        newgraph.addEdge(newEdge); //overwriting each edge
	     }
	    return newgraph;
	   }
	
    //-------------------------------------
    // Testers
    //-------------------------------------
    public boolean hasEdge(DEdge newEdge){
		for (int i = 0; i < getNrEdges(); i++){
		    if (edges.get(i).equals(newEdge)){
		        return true;
		    }
	    }
	    return false;
    }
    public boolean isEmpty(){
        if (edges.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
	public boolean hasLoops(){
        for (int i = 0; i < getNrEdges(); i++){
		   if (edges.get(i).isLoop()){
		       return true;
		   }
	    }
	    return false;
	}
	public boolean hasMultiEdges(){
        for (int i = 0; i < getNrEdges()-1; i++){
             for (int j = i+1; j < getNrEdges(); j++){
             	DEdge edge1 = edges.get(i);
                DEdge edge2 = edges.get(j);         	
		        if (edge1.equals(edge2)){
		            return true;
		    	}
	        }
	    }
        return false;
	}
	public boolean isSimple(){
         if (hasMultiEdges()){
            return false;
        }
        if (hasLoops()){
            return false;
        }
        return true;
	}

    //-------------------------------------
    // ToString
    //-------------------------------------
	public String edgesToTgfString(ArrayList<DEdge> edges){
		String tgfString = "";
		for (DEdge edge : edges){
			tgfString += edge.toString() + "\n";
		}
		return tgfString;
	}
	public String toTgfString(){
		String tgfString = "";
		// create the string for the lines giving the vertices in the tgf file
		for (int k = 0; k < nrVerts;  k++) {
			tgfString = tgfString + k + "\n";
		}
		//write #
		tgfString += "#\n";
		// followed by the string representing the lines for edges
        tgfString += edgesToTgfString(edges);
		return tgfString;
	}
	
    //-------------------------------------
    // I/O
    //-------------------------------------
	public void printTgfDGraph(){
		System.out.println( toTgfString());
	}
    public void writeToTgfFile(String filename){
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(toTgfString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("writeToTgfFile::error writing to file: " + filename);
            e.printStackTrace();
          }
    }
    public void readFromTgfFile(String filepath){
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
				addEdge(u, v);	
				nrEdges++;
			}
            fileScanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("readFromTgfFile::error reading file: " + filepath);
            e.printStackTrace();
          }
    }

}
