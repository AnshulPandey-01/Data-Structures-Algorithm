package DataStructures;

// It is an naive implementation of PriorityQueue where enqueue takes O(n) and dequeue takes O(1).
// Priority Queue should implemented using Heap in which case enqueue and dequeue both takes O(log n).

class PriorityNode{
    int key;
    int data;
    PriorityNode next;
    
    public PriorityNode(int data, int key){
        this.data = data;
        this.key = key;
    }
}

class PriorityQueue{
    PriorityNode front;
    
    void enque(int data, int key){
        if(front==null || key>front.key){
            PriorityNode newNode  = new PriorityNode(data, key);
            newNode.next = front;
            front = newNode;
            return ;
        }
        PriorityNode current = front;
        while(current.next!=null && current.next.key>=key)
            current = current.next;
        PriorityNode newNode  = new PriorityNode(data, key);
        newNode.next = current.next;
        current.next = newNode;
    }
    
    int deque(){
        if(front==null){
            System.out.println("Queue empty");
            return -1;
        }
        int x = front.data;
        front = front.next;
        return x;
    }
    
    void show(){
        if(front==null){
            System.out.println("Queue empty");
        }
        PriorityNode current = front;
        while(current!=null){
            System.out.println("Data: "+current.data+"\t next: "+current.next);
            current = current.next;
        }
    }
    
    public static void main(String[] args){
        PriorityQueue q = new PriorityQueue();
        q.enque(10, 5);
        q.enque(20, 4);
        q.enque(30, 6);
        System.out.println(q.deque());
        q.show();
    }
}