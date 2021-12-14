package DataStructures;

import java.util.Stack;

class QueueSingleStack{
    Stack<Integer> s;
    
    QueueSingleStack(){
        s = new Stack();
    }
    
    boolean isEmpty(){
        return s.empty();
    }
    
    void enqueue(int value){
        s.push(value);
    }
    
    int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }else if(s.size() == 1){
            return s.pop();
        }else{
            int x = s.pop();
            int res = dequeue();
            enqueue(x);
            return res;
        }
    }
    
    int front(){
        if(!isEmpty())
            return s.peek();
        else{
            System.out.println("Queue is Empty");
            return -1;
        }
    }
    
    int rear(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }else if(s.size() == 1){
            return s.peek();
        }else{
            int x = s.pop();
            int res = rear();
            enqueue(x);
            return res;
        }
    }
    
    public static void main(String[] args){
        QueueSingleStack q = new QueueSingleStack();
        System.out.println("Queue Empty: " + q.isEmpty());
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Queue Empty: " + q.isEmpty());
        System.out.println("front: " + q.front());
        System.out.println("rear: " + q.rear());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println("Queue Empty: " + q.isEmpty());
    }
}