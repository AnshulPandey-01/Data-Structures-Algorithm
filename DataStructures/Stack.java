package DataStructures;

// Stack followa LIFO i.e. Last In First Out approch
class Stack{
    static final int MAX = 10;
    int a[] = new int[MAX];
    int top = -1;
    
    boolean isEmpty(){
        return (top<0);
    }
    
    boolean isFull(){
        return (top>=MAX-1);
    }
    
    boolean push(int x){
        if(top >= MAX-1){
            System.out.println("Stack Overflow");
            return false;
        }else{
            a[++top] = x;
            System.out.println(x + " pushed into stack");
            return true;
        }
    }
    
    int pop(){
        if(top < 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        else{
            int x = a[top--];
            System.out.println(x + " poped out of stack");
            return x;
        }
    }
    
    int peek(){
        if(top < 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        else
            return a[top];
    }
    
    void search(int x){
        if(this.isEmpty())
            System.out.println("Stack is Empty");
        else{
            for(int i = 0; i<=this.top; i++){
                if(this.a[i]==x){
                    System.out.println("Element " + x + " is found at position " + (i + 1));
                    break;
                }
            }
        }
    }
    
    public static void main(String args[]){
        Stack s = new Stack();
        System.out.println("Stack is Empty: " + s.isEmpty());
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println("Stack is Full: " + s.isFull());
        s.search(15);
        s.search(30);
        s.pop();
        s.peek();
        System.out.println("Stack is Empty: " + s.isEmpty());
    }
}