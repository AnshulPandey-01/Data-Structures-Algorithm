package DataStructures2;

class DisjointSetsAdv {

    int[] rank, parent;
    int n;

    public DisjointSetsAdv(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    void makeSet() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // find by Path Compression
    int find(int x) {

        if (parent[x] == x) {
            return x;
        }

        int result = find(parent[x]);
        parent[x] = result;
        return result;
    }

    // Union by Rank
    void union(int x, int y) {

        int xRoot = find(x), yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }
        
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
    }
    
    public static void main(String[] args){
        DisjointSets d = new DisjointSets(10);
        d.union(4, 6);
        d.union(2, 8);
        d.union(4, 1);
        d.union(1, 5);
        System.out.println(d.find(5));
        System.out.println(d.find(1));
        System.out.println(d.find(8));
    }
}
