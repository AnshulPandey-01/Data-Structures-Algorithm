/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures2;

import java.util.*;

/**
 *
 * @author anshu
 */
public class DirectedGraph {
    
    private int size;
    private LinkedList<Integer> adj[];
    
    public static enum TRAVERSAL{
        BFS,
        DFS
    }
    
    public DirectedGraph(int size){
        this.size = size;
        adj = new LinkedList[this.size];
        for(int i = 0; i<this.size; i++){
            adj[i] = new LinkedList<>();
        }
    }
    
    public void add(int source, int destination){
        adj[source].add(destination);
    }
    
    /**
     * Graph: 
     * Non Cyclic  
     * 0➡1
     * ⬇  ⬇
     * 2➡3
     * Cyclic:
     * 0➡1
     * ⬆  ⬇
     * 3⬅2
     */
    public boolean isCyclicDFS(){
        boolean[] vis = new boolean[this.size];
        boolean[] dfsVis = new boolean[this.size];
        
        for(int i = 0; i<this.size; i++){
            if(!vis[i] && checkCycle(i, vis, dfsVis)){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean checkCycle(int node, boolean[] vis, boolean[] dfsVis){
        vis[node] = true;
        dfsVis[node] = true;
        
        for(int neighbor : adj[node]){
            if(!vis[neighbor] && checkCycle(neighbor, vis, dfsVis)) return true;
            else if(dfsVis[neighbor]) return true;
        }
        
        dfsVis[node] = false;
        return false;
    }
    
    private void findTopoSortDFS(int node, boolean[] vis, Stack<Integer> s){
        vis[node] = true;
        
        for(int neighbor : adj[node]){
            if(!vis[neighbor])
                findTopoSortDFS(neighbor, vis, s);
        }
        
        s.push(node);
    }
    
    /**
     * Directed Acyclic Graph
     * 0➡1
     * ⬇  ⬇
     * 2➡3
     */
    public Stack<Integer> topologicalSortDFS(){
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[size];
        
        for(int i = 0; i<size; i++){
            if(!vis[i])
                findTopoSortDFS(i, vis, s);
        }
        
        return s;
    }
    
    /**
     * Directed Acyclic Graph
     * 0➡1
     * ⬇  ⬇
     * 2➡3
     */
    public List<Integer> topologicalSortBFS(){
        int[] inDegree = new int[size];
        for(int i = 0; i<size; i++){
            for(int neighbor : adj[i]){
                inDegree[neighbor]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<size; i++){
            if(inDegree[i]==0)
                q.add(i);
        }
        
        List<Integer> list = new ArrayList<>(size);
        while(!q.isEmpty()){
            int node = q.poll();
            list.add(node);
            for(int neighbor : adj[node]){
                inDegree[neighbor]--;
                if(inDegree[neighbor]==0)
                    q.add(neighbor);
            }
        }
        
        return list;
    }
    
    /** 
     * Cyclic:
     *      3
     *    ↙ ⬆
     * 0➡1➡2
     */
    public boolean isCyclicBFS(){
        int[] inDegree = new int[size];
        for(int i = 0; i<size; i++){
            for(int neighbor : adj[i]){
                inDegree[neighbor]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<size; i++){
            if(inDegree[i]==0)
                q.add(i);
        }
        
        int count = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            count++;
            for(int neighbor : adj[node]){
                inDegree[neighbor]--;
                if(inDegree[neighbor]==0)
                    q.add(neighbor);
            }
        }
        
        return count!=size;
    }
    
    private void revDFS(int node, List<List<Integer>> transpose, boolean[] vis){
        vis[node] = true;
        System.out.print(node + "  ");
        for(int neighbor : transpose.get(node)){
            if(!vis[neighbor])
                revDFS(neighbor, transpose, vis);
        }
    }
    
    /**
     * Graph
     * 0 ➡ 1 ➡ 3
     *  ↖ ↙     ⬇
     *   2      4
     */
    public void kosaRaju(){
        boolean vis[] = new boolean[size];
        Stack<Integer> st = topologicalSortDFS();
        
        List<List<Integer>> transpose = new ArrayList<>();
        for(int i = 0; i<size; i++)
            transpose.add(new ArrayList<>());
        
        for(int i = 0; i<size; i++){
            for(int node : adj[i]){
                transpose.get(node).add(i);
            }
        }
        
        System.out.println("SCC");
        while(!st.isEmpty()){
            int node = st.pop();
            if(!vis[node]){
                revDFS(node, transpose, vis);
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        
        DirectedGraph dg = new DirectedGraph(V);
        
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        
        System.out.println("Enter " + E + " edges");
        for(int i = 0; i<E; i++){
            int source = sc.nextInt();
            int destination = sc.nextInt();
            
            dg.add(source, destination);
        }
        
//        System.out.println("Is Graph Cyclic: " + dg.isCyclicDFS());
//        System.out.println(dg.topologicalSortBFS());
//        System.out.println("Is Graph Cyclic: " + dg.isCyclicBFS());
        dg.kosaRaju();
    }
}
