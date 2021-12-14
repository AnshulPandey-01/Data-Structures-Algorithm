package DataStructures2;

class DisjointSets {

    int[] rank, parent;
    int n;

    public DisjointSets(int n) {
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

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    void union(int x, int y) {
        int xRoot = find(x), yRoot = find(y);
        parent[yRoot] = xRoot;
    }
    
    public static void main(String[] args){
        DisjointSets d = new DisjointSets(10);
        d.union(4, 6);
        d.union(2, 8);
        d.union(4, 1);
        d.union(1, 5);
        System.out.println(d.find(5));
        System.out.println(d.find(1));
        System.out.println(d.find(4));
    }
}
