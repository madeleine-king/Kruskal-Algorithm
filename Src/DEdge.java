//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 1, 2022
// Description:	Edge in an undirected graph, standardized representation
//-----------------------------------------------------

public class DEdge {
    private int u;
    private int v;
    
    //-------------------------------------
    // Constructor
    //-------------------------------------
    public DEdge(int u, int v){
            this.u = u;
            this.v = v;
    }


    //-------------------------------------
    // Getters
    //-------------------------------------
    public int getVertex1(){
        return u;
    }
    public int getVertex2(){
        return v;
    }
    
    public DEdge getReversedEdge(){
        DEdge nEdge = new DEdge(v, u);
        return nEdge;
    }

    //-------------------------------------
    // Testers
    //-------------------------------------
    public boolean equals(DEdge edge){
        if (u == edge.getVertex1() && v ==edge.getVertex2()){
            return true;
        }
        return false;
    }
    public boolean isLoop(){
        return u == v;
	}
	
	
	//Modifier 
	public DEdge reverseEdge(int u, int v){
	    int temp = u;
	    u = v;
	    v = temp;
	    DEdge revEdge = new DEdge(u,v);
	    return revEdge;
	 }
	
	
    //-------------------------------------
    // ToString
    //-------------------------------------
	public String toString(){
		String string = "" + u + " " + v;
		return string;
	}
	
	public void printEdge(){
	    System.out.println(toString());
	}
}
