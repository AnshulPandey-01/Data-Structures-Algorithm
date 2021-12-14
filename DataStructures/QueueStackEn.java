package DataStructures;

import java.util.Stack;

// Here enqueue operation has absolute time complexity of O(2n) and dequeue operation is in constant time.
// Less efficient than QueueStackDe

class QueueStackEn{
    Stack<Integer> s1, s2;
    
    QueueStackEn(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    boolean isEmpty(){
        return s1.empty();
    }
    
    void enqueue(int x){
        while(!s1.empty()){
            s2.push(s1.pop());
        }
        
        s1.push(x);
        
        while(!s2.empty()){
            s1.push(s2.pop());
        }
    }
    
    int dequeue(){
        if(s1.empty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        
        return s1.pop();
    }
    
    int front(){
        if(!isEmpty())
            return s1.peek();
        else{
            System.out.println("Queue is Empty");
            return -1;
        }
    }
    
    int rear(){
        if(!isEmpty()){
            while(!s1.empty()){
                s2.push(s1.pop());
            }

            int x = s2.peek();

            while(!s2.empty()){
                s1.push(s2.pop());
            }

            return x;
        }
        else{
            System.out.println("Queue is Empty");
            return -1;
        }
    }
    
    public static void main(String[] args){
        QueueStackEn q = new QueueStackEn();
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