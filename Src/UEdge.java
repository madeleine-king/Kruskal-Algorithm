//-----------------------------------------------------
// Author: 		Madeleine King
// Date: 		Feb 1, 2022
// Description:	Edge in an undirected graph, standardized representation
//-----------------------------------------------------

public class UEdge {
    public int u;
    public int v;
    
    //-------------------------------------
    // Constructor
    //-------------------------------------
    public UEdge(int u, int v){
		// standardize edge s.t. u < v
        if (u < v){
            this.u = u;
            this.v = v;
        }
        else {
            this.u = v;
            this.v = u;
        }
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

    //-------------------------------------
    // Testers
    //-------------------------------------
    public boolean equals(UEdge edge){
        if (u == edge.getVertex1() && v ==edge.getVertex2()){
            return true;
        }
        return false;
    }
    public boolean isLoop(){
        return u == v;
	}
    //-------------------------------------
    // ToString
    //-------------------------------------
	public String toString(){
		String string = "" + u + " " + v;
		return string;
	}
}
