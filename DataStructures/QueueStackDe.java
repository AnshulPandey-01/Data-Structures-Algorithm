package DataStructures;

import java.util.Stack;

// Here enqueue operation is in constant time and dequeue operation has absolute time complexity of O(n).
// More efficient than QueueStackEn.

class QueueStackDe{
    Stack<Integer> s1, s2;
    
    QueueStackDe(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    boolean isEmpty(){
        return (s1.empty() && s2.empty());
    }
    
    void enqueue(int x){
        s1.push(x);
    }
    
    int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.pop());
            }
        }
        
        return s2.pop();
    }
    
    int rear(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        else{
            if(!s1.empty()){
                return s1.peek();
            }

            while(!s2.empty()){
                    s1.push(s2.pop());
            }
            return s1.peek();
        }
    }
    
    int front(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.pop());
            }
        }
        
        return s2.peek();
    }
    
    public static void main(String[] args){
        QueueStackDe q = new QueueStackDe();
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