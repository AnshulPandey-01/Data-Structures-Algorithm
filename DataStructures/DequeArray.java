package DataStructures;

class DequeArray{
    static final int MAX = 100;
    int arr[], front, rear, size;
    
    DequeArray(int size){
        arr = new int[MAX];
        front = -1;
        rear = 0;
        this.size = size;
    }
    
    boolean isFull(){
        return ((front==0 && rear==size-1) || front==rear+1);
    }
    
    boolean isEmpty(){
        return (front==-1);
    }
    
    void push_front(int data){
        if(isFull()){
            System.out.println("Overflow");
            return ;
        }
        if(front==-1){
            front = 0;
            rear = 0;
        }
        else if(front==0)
            front = size - 1;
        else
            front--;
        arr[front] = data;
    }
    
    void push_rear(int data){
        if(isFull()){
            System.out.println("Overflow");
            return ;
        }
        if(front==-1){
            front = 0;
            rear = 0;
        }
        else if(rear == size-1)
            rear = 0;
        else
            rear++;
        arr[rear] = data;
    }
    
    void deleteFront(){
        if(isEmpty()){
            System.out.println("Deque empty");
            return ;
        }
        if(front==rear){
            front = -1;
            rear = -1;
        }
        else{
            if(front==size-1)
                front = 0;
            else
                front = front + 1;
        }
    }
    
    void deleteRear(){
        if(isEmpty()){
            System.out.println("Deque empty");
            return ;
        }
        if(front==rear){
            front=-1;
            rear=-1;
        }
        else if(rear==0)
            rear = size-1;
        else
            rear = rear - 1;
    }
    
    int getFront(){
        if(isEmpty()){
            System.out.println("Deque empty");
            return -1;
        }
        return arr[front];
    }
    
    int getRear(){
        if(isEmpty()){
            System.out.println("Deque empty");
            return -1;
        }
        return arr[rear];
    }
    
    void print(){
        if(isEmpty()){
            System.out.println("Deque empty");
            return ;
        }
        int i = front;
        while(i!=rear){
            System.out.println(arr[i]);
            i = (i+1)%size;
        }
        System.out.println(arr[i]);
    }
    
    public static void main(String[] agrs){
        DequeArray a = new DequeArray(5);
        a.push_front(10);
        a.push_rear(20);
        a.push_front(30);
        a.push_rear(40);
        a.print();
    }
}