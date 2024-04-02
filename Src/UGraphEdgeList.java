//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 10, 2022
// Description:	Undirected (multi) UGraph represented via an edge list
//-----------------------------------------------------
import java.util.*;
import java.io.*;  

public class UGraphEdgeList {
    public int nrVerts;
    public ArrayList<UEdge> edges;

    //-------------------------------------
    // Constructors
    //-------------------------------------
    public UGraphEdgeList(){
        this.nrVerts = 0;
        this.edges = new ArrayList<UEdge>();
    }
    public UGraphEdgeList(int nrVerts){
        this.nrVerts = nrVerts;
        this.edges = new ArrayList<UEdge>();
    }
    public UGraphEdgeList(int nrVerts, int nrEdges){
        this.nrVerts = nrVerts;
        this.edges = new ArrayList<UEdge>();
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
    public ArrayList<UEdge> getEdgeList(){
        return edges;
    }
	
    //-------------------------------------
    // Utilities
    //-------------------------------------
    private int getMaxVertex(){
		if (edges.isEmpty()){
		     return -1; 
		}
		int max = 0;            
         for (int i = 0; i < getNrEdges(); i++){
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
        edges = new ArrayList<UEdge>();
    }
    public void setEdgeList(ArrayList<UEdge> edges){
        this.edges = edges;
		int max = getMaxVertex();
		if (nrVerts < max){nrVerts = max + 1;}
		
	}

    //-------------------------------------
    // Modifiers
    //-------------------------------------
    public void addVerts(int nr){
        nrVerts += nr;
    }
    public void addEdge(UEdge edge){
		edges.add(edge);
		//update number of vertices
		if(edge.getVertex2() > (nrVerts - 1)){
		    setNrVerts(edge.getVertex2() + 1);
		} 
    }
    public void addEdge(int u, int v){
        UEdge edge = new UEdge(u, v);
        addEdge(edge);
    }
    
    

    //-------------------------------------
    // Testers
    //-------------------------------------
    public boolean hasEdge(UEdge newEdge){
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
             	UEdge edge1 = edges.get(i);
                UEdge edge2 = edges.get(j);         	
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
	public String edgesToTgfString(ArrayList<UEdge> edges){
		String tgfString = "";
		for (UEdge edge : edges){
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
	public void printTgfUGraph(){
		System.out.println( toTgfString());
	}
    public void writeToTgfFile(String filepath){
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(toTgfString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("writeToTgfFile::error writing to file: " + filepath);
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

    //-------------------------------------
    // Converters
    //-------------------------------------
	// to do next week
    // public UGraphAdjMatrix convertToAdjMatrix(){
    //    
    // }
    // public UGraphAdjList convertToAdjList(){
    	
    	
    	
    	
    // }
}
