package DataStructures;

// In Doubly LinkedList we keep track of both next and prev element.
class DNode{
    DNode prev;
    DNode next;
    int data;
    public DNode(int data){
        this.data = data;
    }
}

/* 
It is not compulsory to use tail pointer, we can implement it using only head pointer also.
By keeping track of tail pointer in doubly linked list we can insert and delete at end in O(1) time.
*/
public class DoublyLinkedList{
    DNode head;
    DNode tail;
    int length;
    
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }
    
    private void initialize(int data){
        head = tail = new DNode(data);
        this.length++;
    }
    
    private void reset(){
        head = tail = null;
        this.length = 0;
    }
    
    public void append(int data){
        if(head==null){
            initialize(data);
            return;
        }
        
        DNode newTail = new DNode(data);
        newTail.prev = tail;
        tail.next = newTail;
        tail = newTail;
        this.length++;
    }
    
    public void prepand(int data){
        if(head==null){
            initialize(data);
            return;
        }
        
        DNode newHead = new DNode(data);
        newHead.next = head;
        head.prev = newHead;
        head = newHead;
        this.length++;
    }
    
    public void insertAtPosition(int data, int pos){
        if (0 <= pos && this.length >= pos) {
            if (pos==0) {
                prepand(data);
            } else if(this.length == pos) {
                append(data);
            } else {
                DNode curr = head;
                for (int i = 0; i<pos-1; i++) {
                    if(curr.next != null) curr = curr.next;
                    else return ;
                }

                DNode newNode = new DNode(data);
                newNode.next = curr.next;
                newNode.prev = curr;
                curr.next.prev = newNode;
                curr.next = newNode;
                this.length++;
            }
        }
    }
    
    public void deleteAtStart(){
        if(head==null){
            System.out.println("list is empty");
            return ;
        }
        
        if (this.length==1) {
            reset();
        } else {
            head = head.next;
            head.prev = null;
            this.length--;
        }
    }
    
    public void deleteAtEnd(){
        if(head==null){
            System.out.println("list is empty");
            return;
        }
        
        if (this.length==1) {
            reset();
        } else {
            tail = tail.prev;
            tail.next = null;
            this.length--;
        }
    }
    
    public void deleteAtPosition(int pos){
        if (pos < 0 || pos >= this.length) return;
        
        if (this.length == 1) {
            reset();
        } else if (pos == 0) {
            deleteAtStart();
        } else if(pos == this.length-1) {
            deleteAtEnd();
        } else {
            DNode curr = head;
            for (int i = 0; i<pos-1; i++) {
                if (curr.next != null) curr = curr.next;
                else return ;
            }
            
            curr.next = curr.next.next;
            curr.next.prev = curr;
            this.length--;
        }
    }
    
    public void deleteWithValue(int data){
        if(head==null) return;
        if(head.data==data){
            head = head.next;
            return;
        }
        DNode current  = head;
        while(current.next!=null){
            if(current.next.data==data){
                if(current.next==tail){
                    tail = tail.prev;
                    current.next = null;
                    return;
                }
                current.next = current.next.next;
                current.next.prev = current;
                return;
            }
            current = current.next;
        }
        System.out.println("Value not present in the list");
    }
    
    public void revrse(){
        DNode current = head, next = null, prev = null;
        while(current!=null){
            next = current.next;
            prev = current.prev;
            current.next = prev;
            current.prev = next;
            current = next;
        }
        if(prev!=null)
            head = prev.prev;
    }
    
    public void showFromStart(){
        if(head==null) return;
        DNode current = head;
        while(current!=null){
            System.out.println(current.data+"\t prev: "+current.prev+"\t Node: "+current+"\t next: "+current.next);
            current = current.next;
        }
    }
    
     public void showFromEnd(){
        if(tail==null) return;
        DNode current = tail;
        while(current!=null){
            System.out.println(current.data+"\t prev: "+current.prev+"\t Node: "+current+"\t next: "+current.next);
            current = current.prev;
        }
    }
    
    public static void main(String[] args){
        DoublyLinkedList a = new DoublyLinkedList();
        a.append(10);
        a.prepand(15);
        a.prepand(11);
        a.append(20);
        a.insertAtPosition(21, 3);
        a.deleteAtStart();
        a.deleteAtEnd();
        a.showFromStart();
        System.out.println("\n");
        a.revrse();
        a.showFromStart();
    }
    
}
