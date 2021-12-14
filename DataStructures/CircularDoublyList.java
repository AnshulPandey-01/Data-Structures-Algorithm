package DataStructures;

public class CircularDoublyList{
    DNode head;
    
    private void initialize(int data){
        head = new DNode(data);
        head.prev = head.next = head;
    }
    
    public void prepand(int data){
        if(head == null){
            initialize(data);
            return ;
        }
        
        DNode newNode = new DNode(data);
        newNode.next = head;
        newNode.prev = head.prev;
        head.prev.next = newNode;
        head.prev = newNode;
        head = newNode;
    }
    
    public void deleteAtBegin(){
        if(head==null){
            System.out.println("List empty");
            return ;
        }
        
        head.next.prev = head.prev;
        head = head.next;
        head.prev.next = head;
    }
    
    public void append(int data){
        if(head == null){
            initialize(data);
            return ;
        }
        
        DNode newNode = new DNode(data);
        newNode.next = head;
        newNode.prev = head.prev;
        head.prev.next = newNode;
        head.prev = newNode;
    }
    
    public void deleteAtEnd(){
        if(head==null){
            System.out.println("List empty");
            return ;
        }
        
        head.prev = head.prev.prev;
        head.prev.next = head;
    }
    
    public void deleteAtPosition(int pos){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        if(pos==0){
            deleteAtBegin();
            return;
        }
        
        DNode current = head;
        boolean flag = true;
        int i;
        for(i = 1; i<pos && current.next.next!=head; current = current.next, i++);
        
        if(i==pos){
            current.next = current.next.next;
            current.next.prev = current;
            flag = false;
        }
        
        if(flag){
            System.out.println("Position out of bound");
        }
    }
    
    public void deleteValues(int data){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        while(head.data==data){
            deleteAtBegin();
        }
        
        DNode current = head;
        boolean flag = true;
        while(current.next!=head){
            if(current.next.data==data){
                current.next = current.next.next;
                current.next.prev = current;
                flag = false;
            }
            else
                current = current.next;
        }
        
        if(flag){
            System.out.println("Value not present in the list");
        }
    }
    
    public void insertAtPosition(int data, int pos){
        if(pos==0){
            prepand(data);
            return;
        }
        
        DNode current = head;
        boolean flag = true;
        int i;
        for(i = 1; i<pos && current.next!=head; current = current.next, i++);
        
        if(i==pos){
            DNode newNode = new DNode(data);
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            flag = false;
        }
        
        if(flag){
            System.out.println("Position out of bound");
        }
    }
    
    public void search(int item){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        DNode current = head;
        boolean flag = true;
        int i = 0;
        
        while(current.next!=head){
            if(current.data==item){
                flag = false;
                System.out.println("Item fount at position: " + i);
            }
            i++;
            current = current.next;
        }
        
        if(current.data==item){
            flag = false;
            System.out.println("Item fount at position: " + i);
        }
        
        if(flag){
            System.out.println("Item not found");
        }
    }
    
    public void print(){
        if(head==null){
            System.out.println("List empty");
            return ;
        }
        
        DNode current = head;
        while(current.next!=head){
            System.out.println("Data: "+current.data+"\t prev: "+current.prev+"\t current: "+current+"\t next: "+current.next);
            current = current.next;
        }
        System.out.println("Data: "+current.data+"\t prev: "+current.prev+"\t current: "+current+"\t next: "+current.next);
    }
    
    public void reversePrint(){
        if(head==null){
            System.out.println("List empty");
            return ;
        }
        
        DNode current = head.prev;
        while(current.prev!=head.prev){
            System.out.println("Data: "+current.data+"\t prev: "+current.prev+"\t current: "+current+"\t next: "+current.next);
            current = current.prev;
        }
        System.out.println("Data: "+current.data+"\t prev: "+current.prev+"\t current: "+current+"\t next: "+current.next);
    }
    
    public static void main(String[] args){
        CircularDoublyList cd = new CircularDoublyList();
        cd.append(10);
        cd.prepand(20);
        cd.append(30);
        cd.prepand(40);
        cd.insertAtPosition(50, 2);
        cd.print();
    }
}