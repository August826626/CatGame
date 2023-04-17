import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.DijkstraSP;

public class CatGame {
    EdgeWeightedGraph graph;
    
    public CatGame(int n) {
        graph = new EdgeWeightedGraph(n*n);
        
    }
    
    void markTile(int row, int col) {
        graph
    }
    
    //boolean marked(int row, int col) {}
    
    //int[] getCatTile() {}
    
    //boolean catHasEscaped() {}
    
    //boolean catIsTrapped() {}
    
    int convert(int row, int col) {
        return n * (row - 1) + col;
    }
    
}