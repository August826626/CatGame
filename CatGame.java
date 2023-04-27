import edu.princeton.cs.algs4.Edge;

public class CatGame {
    public CatGraph graph;
    public boolean[] marked;
    public int catPos;
    public int n;
    public ShortestPathTree sp;
    
    public CatGame(int n) {
        this.n = n;
        graph = new CatGraph(n*n);
        marked = new boolean[n*n];
        catPos = index(n/2, n/2);
        
        //inner hexagons
        for (int row = 1; row < n-1; row++) {
            for (int col = 1; col < n-1; col++) {
                int v = index(row, col);
                
                //add top left and right
                if (row != 0) { //not on top row
                    if (col != 0) { //if not on left side
                        graph.addEdge(new CatEdge(v, v-n-1)); //top left
                    }
                    if (col != n) { //if not on right side
                        graph.addEdge(new CatEdge(v, v-n)); //top right
                    }
                }
                
                //add bottom left and right
                if (row != n) { //not on bottom row
                    if (col != 0) { //if not on left side
                        graph.addEdge(new CatEdge(v, v+n-1)); //bottom left
                    }
                    if (col != n) { //if not on right side
                        graph.addEdge(new CatEdge(v, v+n)); //bottom right
                    }
                }
                
                //add left
                if (col != 0) {
                    graph.addEdge(new CatEdge(v, v-1)); //left
                }
                
                //add right
                if (col != n) {
                    graph.addEdge(new CatEdge(v, v+1)); //right
                }
                
                //if on border
                if (col == 0 || col == n) {
                    graph.addEdge(new CatEdge(v, n*n)); //freedom hexagon
                }
                if (row == 0 || row == n) {
                    graph.addEdge(new CatEdge(v, n*n)); //freedom hexagon
                }
            }
        }
        
        
        //make separate for loop for outside tiles (one for each side?)
        //make them connect to freedom hexagon
        
        for (int i = 0; i < marked.length; i ++) {
            marked[i] = false;
        }
    }
    
    void markTile(int row, int col) {
        int v = index(row, col);
        
        //update marked
        marked[v] = true;
        
        //make adjacent edge weights infinity
        for(Edge e : graph.adj(v)) {
            CatEdge i = (CatEdge) e;
            i.changeWeight();
        }
        
        //dijkstra to move cat
        sp = new ShortestPathTree(graph, catPos);
    }
    
    boolean marked(int row, int col) {
        int v = index(row, col);
        return marked[v];
    }
    
    //int[] getCatTile() {}
    
    //boolean catHasEscaped() {}
    
    //boolean catIsTrapped() {}
    
    public int index(int row, int col) {
        return row*n + col;
    }
    
    public int getCol(int v) {
        return v % n; //remainder
    }
    
    public int getRow(int v) {
        return v / n; //division
    }
    
}