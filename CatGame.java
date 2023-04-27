public class CatGame {
    public CatGraph graph;
    public Boolean[] marked;
    public int catPos;
    public int n;
    
    public CatGame(int n) {
        this.n = n;
        graph = new CatGraph(n*n);
        marked = new boolean[n*n];
        catPos = index(n/2, n/2);
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int v = index(row, col);
                graph.addEdge(new CatEdge(v, v-n-1)); //top left
                graph.addEdge(new CatEdge(v, v-n)); //top right
                graph.addEdge(new CatEdge(v, v-1)); //left
                graph.addEdge(new CatEdge(v, v+1)); //right
                graph.addEdge(new CatEdge(v, v+n-1)); //bottom left
                graph.addEdge(new CatEdge(v, v+n)); //bottom right
            }
        }
        
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
        catPos = STP(graph, v);
    }
    
    boolean marked(int row, int col) {
        v = index(row, col);
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