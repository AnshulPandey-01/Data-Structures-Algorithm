package DataStructures;

// Priority Queue as a Max Binary Heap
// You can get k max elements in side an array is just O(n) time. By building an heap and then extracting max k times.

class Max_Heap {
    int[] heap;
    int size;
    int capacity;

    public Max_Heap(int c) {
        this.capacity = c;
        this.size = 0;
        heap = new int[this.capacity];
        heap[0] = Integer.MAX_VALUE;
    }
    
    private int parent(int i){
        return i/2;
    }
    
    private int leftChild(int i){
        return 2*i;
    }
    
    private int rightChild(int i){
        return (2*i)+1;
    }
    
    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }
    
    private void shiftUp(int i){
        while(i > 0 && heap[parent(i)] < heap[i]){
            swap(parent(i), i);
            i = parent(i);
        }
    }
    
    private void shiftDown(int i){
        int maxIndex = i;
        
        int l = leftChild(i);
        if(l <= size && heap[l] > heap[maxIndex])
            maxIndex = l;
        
        int r = rightChild(i);
        if(r <= size && heap[r] > heap[maxIndex])
            maxIndex = r;
        
        if(i!=maxIndex){
            swap(i, maxIndex);
            shiftDown(maxIndex);
        }
    }
    
    public void add(int element){
        if(size==capacity){
            System.out.println("Heap full can't insert.");
            return ;
        }
        heap[++size] = element;
        shiftUp(size);
    }
    
    public int getMax(){
        return heap[1];
    }
    
    public int extractMax(){
        int result = heap[1];
        heap[1] = heap[size--];
        shiftDown(1);
        return result;
    }
    
    public void removeElement(int i){
        heap[i] = Integer.MAX_VALUE;
        shiftUp(i);
        extractMax();
    }
    
    public void changePriority(int i, int value){
        int oldValue = heap[i];
        heap[i] = value;
        if(value > oldValue)
            shiftUp(i);
        else
            shiftDown(i);
    }
    
    public void print() {
        for (int i = 1; i <= size/2; i++) { 
            System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2 * i] + " RIGHT CHILD :" + heap[2 * i + 1]); 
            System.out.println();
        }
    }
    
    public static void main(String[] args){
        Max_Heap m = new Max_Heap(10);
        m.add(10);
        m.add(5);
        m.add(20);
        System.out.println(m.extractMax());
        m.print();
    }
}
