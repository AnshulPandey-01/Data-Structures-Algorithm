package DataStructures;

// Queue followa FIFO i.e. First In First Out approch
// Implementing Queue using Array from Floating Front Approach ---> Also Known as Circular Queue

class Queue{
    int arr[], front, rear, size, capacity;
    
    Queue(int c){
        capacity = c;
        front = size = 0;
        rear = capacity - 1;
        arr = new int[capacity];
    }
    
    boolean isEmpty(){
        return (size == 0);
    }
    
    boolean isFull(){
        return (size == capacity);
    }
    
    void enqueue(int item){
        if(isFull()) return ;
        rear = (rear + 1)%capacity;
        arr[rear] = item;
        size = size + 1;
        System.out.println(item + " enqueued to queue");
    }
    
    int dequeue(){
        if(isEmpty()) return -1;
        int item = arr[front];
        front = (front+1)%capacity;
        size = size - 1;
        return item;
    }
    
    int front(){
        if(isEmpty())
            return -1;
        
        return arr[front];
    }
    
    int rear(){
        if(isEmpty())
            return -1;
        
        return arr[rear];
    }
    
    public static void main(String[] args){
        Queue q = new Queue(3);
        System.out.println("Queue is Empty: " + q.isEmpty());
        System.out.println("Queue is Full: " + q.isFull());
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("rear: " + q.rear());
        System.out.println("front: " + q.front());
        System.out.println("Queue is Empty: " + q.isEmpty());
        System.out.println("Queue is Full: " + q.isFull());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println("Queue is Empty: " + q.isEmpty());
        System.out.println("Queue is Full: " + q.isFull());
    }
}