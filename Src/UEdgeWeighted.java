


public class UEdgeWeighted extends UEdge {
	private int weight;
//-------------------------------------
// Constructor
//-------------------------------------

    public UEdgeWeighted(int u, int v){
            super(u, v);
            this.weight = 1;
    }
	
    public UEdgeWeighted(int u, int v, int weight){
            super(u, v);
            this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }
    
    public String toString(){
        String string = "" + this.u + " " + this.v + " " + this.weight;
        return string;
    }

}