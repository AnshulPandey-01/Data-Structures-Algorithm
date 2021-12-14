package DataStructures2;

import java.util.*;

public class WeightedDirectedGraph {
    
    private static final int MAX_VALUE = 1_000_000;
    
    static class Pair{
        private final int node;
        private final int distance;

        Pair(int node, int distance){
            this.node = node;
            this.distance = distance;
        }

        public int getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }
    
    private final int size;
    private final LinkedList<Pair> adj[];
    
    public static enum TRAVERSAL{
        BFS,
        DFS
    }
    
    public WeightedDirectedGraph(int size){
        this.size = size;
        adj = new LinkedList[this.size];
        for(int i = 0; i<this.size; i++){
            adj[i] = new LinkedList<>();
        }
    }
    
    public void add(int source, int destination, int distance){
        adj[source].add(new Pair(destination, distance));
    }
    
    private void TopoSort(int node, boolean[] vis, Stack<Integer> s){
        vis[node] = true;
        
        for(Pair neighbor : adj[node])
            if(!vis[neighbor.getNode()])
                TopoSort(neighbor.getNode(), vis, s);
        
        
        s.push(node);
    }
    
    /**
     * Weighted DAG
     * 0 1 2
     * 1 2 3
     * 2 3 6
     * 0 4 1
     * 4 5 4
     * 5 3 1
     * 4 2 2
     */
    public void shortestPath(int source){
        Stack<Integer> st = new Stack<>();
        boolean[] vis = new boolean[size];
        
        for(int i = 0; i<size; i++)
            if(!vis[i])
                TopoSort(i, vis, st);
        
        int[] dist = new int[size];
        Arrays.fill(dist, MAX_VALUE);
        dist[source] = 0;
        
        while(!st.empty()){
            int node = st.pop();
            if(dist[node] != Integer.MAX_VALUE){
                for(Pair neighbor : adj[node]){
                    if(dist[node] + neighbor.getDistance() < dist[neighbor.getNode()]){
                        dist[neighbor.getNode()] = dist[node] + neighbor.getDistance();
                    }
                }
            }
        }
        
        System.out.print("Shortest paths from node " + source + ": ");
        for(int distance : dist){
            if(distance == MAX_VALUE)
                System.out.print("Unreachable  ");
            else
                System.out.print(distance + "  ");
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        
        WeightedDirectedGraph wd = new WeightedDirectedGraph(V);
        
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        
        System.out.println("Enter " + E + " edges and distances");
        for(int i = 0; i<E; i++){
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int distance = sc.nextInt();
            wd.add(source, destination, distance);
        }
        
        System.out.print("Enter source: ");
        wd.shortestPath(sc.nextInt());
    }
}
