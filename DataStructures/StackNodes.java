package DataStructures;

// In case of implementing Stack using linkedlist we push elements from bottom side.
class StackNodes{
    Node root;
    
    boolean isEmpty(){
        return (root == null);
    }
    
    void push(int x){
        if(this.isEmpty()){
            root = new Node(x);
        }else{
            Node newRoot = new Node(x);
            newRoot.next = root;
            root = newRoot;
        }
        System.out.println(x + " pushed to stack"); 
    }
    
    int pop(){
        if(this.isEmpty()){
            System.out.println("Stack is Empty");
            return -1;
        }else{
            int pop = root.data;
            root = root.next;
            return pop;
        }
    }
    
    int peek(){
        if(this.isEmpty()){
            System.out.println("Stack is Empty");
            return -1;
        }
        else
            return root.data;
    }
    
    public static void main(String args[]){
        StackNodes s = new StackNodes();
        System.out.println("Stack is Empty: " + s.isEmpty());
        s.push(10);
        System.out.println("Stack is Empty: " + s.isEmpty());
        s.push(20);
        s.push(30);
        System.out.println("Top element is " + s.peek());
        System.out.println(s.pop() + " poped out of stack");
        System.out.println("Top element" + s.peek());
    }
}