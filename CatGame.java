public class CatGame {
    public CatGraph graph;
    public boolean[] marked;
    public int catPos;
    public int n;
    public ShortestPathTree sp;
    public final int FREEDOM = n*n;
    
    public CatGame(int n) {
        this.n = n;
        graph = new CatGraph(n*n);
        marked = new boolean[n*n];
        catPos = index(n/2, n/2);
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int v = index(row, col);
                
                
                
                /////////
                if (row == 0) { //if on top row
                    graph.addEdge(new CatEdge(v, FREEDOM));
                } else {
                    if (col != 0) { //if not on left side
                        graph.addEdge(new CatEdge(v, v-n-1)); //top left
                    }
                    if (col != n) { //if not on right side
                        graph.addEdge(new CatEdge(v, v-n)); //top right
                    }
                }
                
                if (row == n) { //if on bottom row
                    graph.addEdge(new CatEdge(v, FREEDOM));
                } else {
                    if (col != 0) { //if not on left side
                        graph.addEdge(new CatEdge(v, v+n-1)); //bottom left
                    }
                    if (col != n) { //if not on right side
                        graph.addEdge(new CatEdge(v, v+n)); //bottom right
                    }
                }
                
                if (col == 0) {
                    graph.addEdge(new CatEdge(v, FREEDOM));
                } else {
                    graph.addEdge(new CatEdge(v, v-1); //left
                }
                
                if (col == n) {
                    graph.addEdge(new CatEdge(v, FREEDOM));
                } else {
                    graph.addEdge(new CatEdge(v, v+1); //right
                }
                
                /////////
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
        for(CatEdge e : graph.adj(v)) {
            e.changeWeight();
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