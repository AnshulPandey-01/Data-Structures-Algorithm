package DataStructures;

class QueueNode{
    class QNode{
        int value; 
        QNode next; 

        QNode(int value){ 
            this.value = value; 
            this.next = null; 
        }
    }
    
    // Inserting element from tail side i.e. rear side
    // deleting element from head side i.e. front side
    QNode front, rear;
    QueueNode(){ 
        front = rear = null; 
    }
    
    boolean isEmpty(){
        return (rear == null);
    }
    
    void enqueue(int item){
        QNode temp = new QNode(item); 
        if(isEmpty()){
            rear = front = temp;
        }
        
        rear.next = temp;
        rear = temp;
        
        System.out.println(item + " enqueued to queue");
    }
    
    int dequeue(){
        if(front==null){
            return -1;
        }
        
        int item = front.value;
        front = front.next;
        
        if(front == null){
            rear = null;
        }
        
        return item;
    }
    
    int front(){
        if(!isEmpty())
            return front.value;
        else{
            System.out.println("Queue is Empty");
            return -1;
        }
    }
    
    int rear(){
        if(!isEmpty())
            return rear.value;
        else{
            System.out.println("Queue is Empty");
            return -1;
        }
    }
    
    public static void main(String[] args){
        QueueNode q = new QueueNode();
        System.out.println("Queue Empty: " + q.isEmpty());
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("front: " + q.front());
        System.out.println("rear: " + q.rear());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println("Queue Empty: " + q.isEmpty());
    }
}