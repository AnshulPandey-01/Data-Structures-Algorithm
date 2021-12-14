/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures2;

import java.util.*;

/**
 * @author anshu
 * 
 * Undirected Graph
 */
public class Graph {
    
    private LinkedList<Integer> adj[];
    
    public static enum TRAVERSAL{
        BFS,
        DFS
    }
    
    public Graph(int V){
        adj = new LinkedList[V];
        for(int i = 0; i<V; i++){
            adj[i] = new LinkedList<>();
        }
    }
    
    public void addEdge(int source, int destination){
        adj[source].add(destination);
        adj[destination].add(source);
    }
    
    /**
     * Graph
     * 1-2
     * | |\
     * 0-4-3 
     * Time: O(V)
     */
    public int bfs(int source, int destination){
        boolean[] vis = new boolean[adj.length];
        vis[source] = true;
        
        int[] parent = new int[adj.length];
        Arrays.fill(parent, -1);
        
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr == destination) break;
            
            for(int neighbor : adj[curr]){
                if(!vis[neighbor]){
                    q.add(neighbor);
                    vis[neighbor] = true;
                    parent[neighbor] = curr;
                }
            }
        }
        
        System.out.print("Path: ");
        return printPath(parent, destination, 0);
    }
    
    private int printPath(int[] parent, int curr, int distance){
        if(parent[curr] == -1){
            if(distance==0){
                System.out.print("Not possible");
                return -1;
            }
            System.out.print(curr);
            return distance;
        }else{
            distance = printPath(parent, parent[curr], distance+1);
            System.out.print(" -> " + curr);
            return distance;
        }
    }
    
    /**
     * Graph
     *     0 1-2
     *    /| |/
     *   5 4-3
     *  / \
     * 6---7
     * Time: O(V)
     */
    public boolean dfs(int source, int destination){
        boolean[] vis = new boolean[adj.length];
        vis[source] = true;
        
        return dfs(source, destination, vis);
    }
    
    private boolean dfs(int source, int destination, boolean[] vis){
        if(source == destination)
            return true;
        
        for(int neighbor : adj[source]){
            if(!vis[neighbor]){
                vis[neighbor] = true;
                boolean isConnected = dfs(neighbor, destination, vis);
                if(isConnected) return true;
            }
        }
        
        return false;
    }
    
    public boolean dfsStack(int source, int destination){
        boolean[] vis = new boolean[adj.length];
        vis[source] = true;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        
        while(!stack.isEmpty()){
            int curr = stack.pop();
            
            if(curr==destination) return true;
            
            for(int neighbor : adj[curr]){
                if(!vis[neighbor]){
                    stack.push(neighbor);
                    vis[neighbor] = true;
                }
            }
        }
        
        return false;
    }
    
    private boolean checkForCycle(int curr, boolean[] vis){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{curr, -1});
        
        vis[curr] = true;
        
        while(!q.isEmpty()){
            int node = q.peek()[0];
            int parent = q.poll()[1];
            
            for(int neighbor : adj[node]){
                if(!vis[neighbor]){
                    q.add(new int[]{neighbor, node});
                    vis[neighbor] = true;
                }else if(parent != neighbor){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Graph
     * 1 2  4-5
     * | |  |/
     * 0-3  6
     */
    public boolean isCyclic(TRAVERSAL type){
        boolean[] vis = new boolean[adj.length];
        
        if(type==TRAVERSAL.BFS){
            for(int i = 0; i<adj.length; i++){
                if(!vis[i] && checkForCycle(i, vis)) 
                    return true;
            }
        }else{
            for(int i = 0; i<adj.length; i++){
                if(!vis[i] && checkCycle(i, -1, vis)) 
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean checkCycle(int node, int parent, boolean[] vis){
        vis[node] = true;
        
        for(int neighbor : adj[node]){
            if(!vis[neighbor]){
                if(checkCycle(neighbor, node, vis))
                    return true;
            }else if(neighbor != parent){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean bfsCheck(int node, int[] color){
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        
        color[node] = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int temp : adj[curr]){
                if(color[temp]==-1){
                    color[temp] = 1 - color[curr];
                    q.add(temp);
                }else if(color[temp]==color[curr]){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**Graph
     *     3-5
     *    /   \
     * 1-2     7-0
     *    \   /
     *     4-6
     */
    public boolean isBipartite(TRAVERSAL type){
        int n = adj.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        
        if(type==TRAVERSAL.BFS){
            for(int i = 0; i<n; i++){
                if(color[i] == -1 && !bfsCheck(i, color)){
                    return false;
                }
            }
        }else{
            for(int i = 0; i<n; i++){
                if(color[i] == -1 && !dfsCheck(i, color)){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean dfsCheck(int node, int[] color){
        if(color[node] == -1) color[node] = 1;
        
        for(int curr : adj[node]){
            if(color[curr]==-1){
                color[curr] = 1 - color[node];
                if(!dfsCheck(curr, color))
                    return false;
            }else if(color[curr] == color[node]){
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Graph:
     *   1-2
     *  / | \
     * 0  |  \   7
     *  \ |   \ / \
     *   3-4-5-6---8
     */
    public void shortestPath(int source){
        int[] distance = new int[adj.length];
        Arrays.fill(distance, 1_000_000);
        distance[source] = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        
        while(!q.isEmpty()){
            int node = q.poll();
            
            for(int neighbor : adj[node]){
                if(distance[node] + 1 < distance[neighbor]){
                    distance[neighbor] = distance[node] + 1;
                    q.add(neighbor);
                }
            }
        }
        
        System.out.print("Shortest paths from node " + source + ": ");
        for(int dist : distance) System.out.print(dist + "  ");
        System.out.println();
    }
    
    private void printBridgeDFS(int node, int parent, boolean[] vis, int[] time, int[] low, int timer){
        vis[node] = true;
        time[node] = low[node] = timer++;
        
        for(int neighbor : adj[node]){
            if(neighbor==parent) continue;
            
            if(!vis[neighbor]){
                printBridgeDFS(neighbor, node, vis, time, low, timer);
                low[node] = Math.min(low[node], low[neighbor]);
                
                if(low[neighbor] > time[node]) System.out.println(node + " - " + neighbor);
            }else{
                low[node] = Math.min(low[node], time[neighbor]);
            }
        }
    }
    
    /**
     * Graph
     * 0   4
     * |\  |\
     * 1-2-3 5
     * Time: O(V+E)
     */
    public void printBridges(){
        int n = adj.length;
        boolean[] vis = new boolean[n];
        int[] time = new int[n];
        int[] low = new int[n];
        
        System.out.println("The bridges are: ");
        int timer = 0;
        for(int i = 0; i<n; i++){
            if(!vis[i])
                printBridgeDFS(i, -1, vis, time, low, timer);
        }
    }
    
    private void articulationPointDFS(int node, int parent, boolean[] vis, int[] time, int[] low,
            int timer, Set<Integer> articulationPoints){
        vis[node] = true;
        time[node] = low[node] = timer++;
        
        for(int neighbor : adj[node]){
            if(neighbor==parent) continue;
            
            if(!vis[neighbor]){
                articulationPointDFS(neighbor, node, vis, time, low, timer, articulationPoints);
                low[node] = Math.min(low[node], low[neighbor]);
                
                if(low[neighbor] > time[node]){
                    articulationPoints.add(node);
                    articulationPoints.add(neighbor);
                }
            }else{
                low[node] = Math.min(low[node], time[neighbor]);
            }
        }
    }
    
    /**
     * Graph
     * 0   4
     * |\  |\
     * 1-2-3-5
     * Time: O(V+E)
     */
    public Set<Integer> getArticulationPoints(){
        int n = adj.length;
        boolean[] vis = new boolean[n];
        int[] time = new int[n];
        int[] low = new int[n];
        
        Set<Integer> articulationPoints = new HashSet<>();
        int timer = 0;
        for(int i = 0; i<n; i++){
            if(!vis[i])
                articulationPointDFS(i, -1, vis, time, low, timer, articulationPoints);
        }
        
        return articulationPoints;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        
        Graph graph = new Graph(V);
        
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        
        System.out.println("Enter " + E + " edges");
        for(int i = 0; i<E; i++){
            int source = sc.nextInt();
            int destination = sc.nextInt();
            
            graph.addEdge(source, destination);
        }
        
//        System.out.println("Enter source and destination: ");
//        int source = sc.nextInt();
//        int destination = sc.nextInt();
//        
//        System.out.println("\n" + graph.bfs(source, destination));
//        System.out.println("Is Path Possible: " + graph.dfs(source, destination));
//        System.out.println("Is Path Possible: " + graph.dfsStack(source, destination));
        
//        System.out.println("Is cyclic graph: " + graph.isCyclic(TRAVERSAL.DFS));
//        System.out.println("Is Bipartite graph: " + graph.isBipartite(TRAVERSAL.DFS));
//        System.out.print("Enter source: ");        
//        graph.shortestPath(sc.nextInt());
//        graph.printBridges();
        System.out.println("The Articulation Points are: " + graph.getArticulationPoints());
    }
    
}
