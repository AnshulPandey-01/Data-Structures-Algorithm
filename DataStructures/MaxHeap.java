package DataStructures;

// Priority Queue as a Max Binary Heap
// You can get k max elements in side an array is just O(n) time. By building an heap and then extracting max k times.

public class MaxHeap {

    int[] heap;
    int size;
    int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = new int[this.maxSize + 1];
        heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    public void insert(int element) {
        heap[++size] = element;
        int current = size;
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;
        
        if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {
            if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
        
    }

    public int extractMax() {
        int popped = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return popped;
    }
    
    public void print() {
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2 * i] + " RIGHT CHILD :" + heap[2 * i + 1]); 
            System.out.println();
        }
    }
    
    public static void main(String[] args){
        MaxHeap m = new MaxHeap(10);
        m.insert(5);
        m.insert(3);
        m.insert(8);
        System.out.println("Max: "+m.extractMax());
        m.print();
    }
}
