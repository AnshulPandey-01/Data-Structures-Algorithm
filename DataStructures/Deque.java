package DataStructures;

class Deque{
    DNode front, back;
    int size;
    
    Deque(){
        size = 0;
    }
    
    boolean isEmpty(){
        return (front==null);
    }
    
    int size(){
        return size;
    }
    
    void push_front(int data){
        if(isEmpty())
            front = back = new DNode(data);
        else{
            DNode newNode = new DNode(data);
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }
    
    void delete_front(){
        if(isEmpty())
            System.out.println("Empty Deque");
        else{
            front = front.next;
            front.prev = null;
            if(front==null)
                back = null;
            size--;
        }
    }
    
    void push_back(int data){
        if(isEmpty())
            back = front = new DNode(data);
        else{
            DNode newNode = new DNode(data);
            newNode.prev = back;
            back.next = newNode;
            back = newNode;
        }
        size++;
    }
    
    void delete_back(){
        if(isEmpty())
            System.out.println("Empty Deque");
        else{
            back = back.prev;
            back.next = null;
            if(back==null)
                front = null;
            size--;
        }
    }
    
    int getFront(){
        if(isEmpty())
            return -1;
        return front.data;
    }
    
    int getBack(){
        if(isEmpty())
            return -1;
        return back.data;
    }
    
    void print(){
        if(isEmpty())
            System.out.println("Empty Deque");
        else{
            DNode current = front;
            while(current!=null){
                System.out.println(current.data+"\t"+current.next);
                current = current.next;
            }
        }
    }
    
    public static void main(String[] agrs){
        Deque a = new Deque();
        a.push_front(5);
        a.push_back(10);
        a.push_back(20);
        a.push_front(30);
        a.push_back(40);
        a.print();
    }
}