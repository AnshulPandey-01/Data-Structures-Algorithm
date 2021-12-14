package DataStructures2;

import java.util.*;

public class WeightedUndirectedGraph {
    
    private static final int MAX_VALUE = 1_000_000;
    
    private final int size;
    private final LinkedList<Pair> adj[];
    
    // for kruskal's Algorithm
    private List<Node> newAdj;
    
    static class Pair implements Comparator<Pair>{
        private final int node;
        private final int distance;

        public Pair() {
            this.node = 0;
            this.distance = 0;
        }
        
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
        
        public int compare(Pair node1, Pair node2){
            if(node1.distance < node2.distance)
                return -1;
            if(node1.distance > node2.distance)
                return 1;
            return 0;
        }
    }
    
    static class Node implements Comparator<Node>{
        private final int u;
        private final int v;
        private final int weight;

        public Node() {
            this.u = 0;
            this.v = 0;
            this.weight = 0;
        }
        
        Node(int u, int v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        public int getWeight() {
            return weight;
        }
        
        public int compare(Node node1, Node node2){
            if(node1.weight < node2.weight)
                return -1;
            if(node1.weight > node2.weight)
                return 1;
            return 0;
        }
    }
    
    public static enum TRAVERSAL{
        BFS,
        DFS
    }
    
    public WeightedUndirectedGraph(int size){
        this.size = size;
        adj = new LinkedList[this.size];
        for(int i = 0; i<this.size; i++){
            adj[i] = new LinkedList<>();
        }
        newAdj = new ArrayList<>(this.size);
    }
    
    public void add(int source, int destination, int distance){
        adj[source].add(new Pair(destination, distance));
        adj[destination].add(new Pair(source, distance));
        newAdj.add(new Node(source, destination, distance));
    }
    
    /**
     * Weighted Undirected Acyclic Graph
     * 0 1 2
     * 0 3 1
     * 1 4 5
     * 1 2 4
     * 2 3 3
     * 2 4 1
     */
    public void dijkstraAlgorithm(int source){
        int dist[] = new int[size];
        Arrays.fill(dist, MAX_VALUE);
        dist[source] = 0;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(size, new Pair());
        pq.add(new Pair(source, 0));
        
        while(pq.size() > 0){
            Pair node = pq.poll();
            for(Pair neighbor : adj[node.getNode()]){
                if(dist[node.getNode()] + neighbor.getDistance() < dist[neighbor.getNode()]){
                    dist[neighbor.getNode()] = dist[node.getNode()] + neighbor.getDistance();
                    pq.add(new Pair(neighbor.getNode(), dist[neighbor.getNode()]));
                }
            }
        }
        
        System.out.print("Shortest paths from node " + source + ": ");
        for(int distance : dist){
            System.out.print(distance + "  ");
        }
    }
    
    /**  
     * Weighted Undirected Acyclic Graph:
     * 0-1-2
     * |/|/
     * 3 4
     * With weights:
     * 0 1 2
     * 0 3 6
     * 1 2 3
     * 1 4 5
     * 2 4 7
     * 1 3 8
     * Time complexity: O((N+M)log(N)) N➡Vertex, M➡Edge
     */
    public void primsAlgorithm(){
        int[] key = new int[size];
        Arrays.fill(key, MAX_VALUE);
        key[0] = 0;
        
        int[] parent = new int[size];
        Arrays.fill(parent, -1);
        
        boolean[] mst = new boolean[size];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(size, new Pair());
        pq.add(new Pair(0, key[0]));
        
        for(int i = 0; i<size-1; i++){
            int u = pq.poll().getNode();            
            mst[u] = true;
            for(Pair neighbor : adj[u]){
                if(!mst[neighbor.getNode()] && neighbor.getDistance() < key[neighbor.getNode()]){
                    parent[neighbor.getNode()] = u;
                    key[neighbor.getNode()] = neighbor.getDistance();
                    pq.add(new Pair(neighbor.getNode(), key[neighbor.getNode()]));
                }
            }
        }
        
        System.out.println("Minimum spanning tree");
        for(int i = 1; i<size; i++){
            System.out.println(parent[i] + " - " + i);
        }
    }
    
    /**  
     * Weighted Undirected Acyclic Graph:
     * 0-1-2
     * |/|/
     * 3 4
     * With weights:
     * 0 1 2
     * 0 3 6
     * 1 2 3
     * 1 4 5
     * 2 4 7
     * 1 3 8
     * Time complexity: O((M*log(M)) M➡Edge
     */
    public void kruskalAlgorithm(){
        Collections.sort(newAdj, new Node());
        DisjointSetsAdv ds = new DisjointSetsAdv(size);
        
        int cost = 0;
        List<Node> mst = new ArrayList<>();
        for(Node node : newAdj){
            if(ds.find(node.getU()) != ds.find(node.getV())){
                cost += node.getWeight();
                mst.add(node);
                ds.union(node.getU(), node.getV());
            }
        }
        
        System.out.println("Minimum spanning tree");
        mst.forEach((node) -> {
            System.out.println(node.getU() + " - " + node.getV());
        });
        System.out.println("Cost: " + cost);
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        
        WeightedUndirectedGraph wug = new WeightedUndirectedGraph(V);
        
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        
        System.out.println("Enter " + E + " edges and distances");
        for(int i = 0; i<E; i++){
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int distance = sc.nextInt();
            wug.add(source, destination, distance);
        }
        
//        System.out.print("Enter source: ");
//        wug.dijkstraAlgorithm(sc.nextInt());
        System.out.println("Prim's Algorithm");
        wug.primsAlgorithm();
        System.out.println("Kruskal Algorithm");
        wug.kruskalAlgorithm();
    }
    
}
