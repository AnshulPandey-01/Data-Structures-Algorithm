package DataStructures;

class CircularLinkedList{
    Node head;
    
    void append(int data){
        if(head==null){
            head = new Node(data);
            head.next = head;
            return;
        }
        
        Node current = head;
        while(current.next!=head)
            current = current.next;
        
        Node newNode = new Node(data);
        current.next = newNode;
        newNode.next = head;
    }
    
    void deleteAtEnd(){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        Node current = head;
        while(current.next.next!=head)
            current = current.next;
        
        current.next = head;
    }
     
    void prepand(int data){
        if(head==null){
            head = new Node(data);
            head.next = head;
            return;
        }
        
        Node current = head;
        while(current.next!=head)
            current = current.next;
        
        Node newNode = new Node(data);
        newNode.next = head;
        current.next = newNode;
        head = newNode;
    }
    
    void deleteAtBegin(){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        Node current = head;
        while(current.next!=head)
            current = current.next;
        
        current.next = head.next;
        head = head.next;
    }
    
    void insertAtPosition(int data, int pos){
        if(pos==0){
            prepand(data);
            return;
        }
        
        Node current = head;
        boolean flag = true;
        int i;
        for(i = 1; i<pos && current.next!=head; current = current.next, i++);
        
        if(i==pos){
            Node newNode = new Node(data);
            newNode.next = current.next;
            current.next = newNode;
            flag = false;
        }
        
        if(flag){
            System.out.println("Position out of bound");
        }
    }
    
    void deleteAtPosition(int pos){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        if(pos==0){
            deleteAtBegin();
            return;
        }
        
        Node current = head;
        boolean flag = true;
        int i;
        for(i = 1; i<pos && current.next.next!=head; current = current.next, i++);
        
        if(i==pos){
            current.next = current.next.next;
            flag = false;
        }
        
        if(flag){
            System.out.println("Position out of bound");
        }
    }
    
    void deleteValues(int data){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        while(head.data==data){
            deleteAtBegin();
        }
        
        Node current = head;
        boolean flag = true;
        while(current.next!=head){
            if(current.next.data==data){
                current.next = current.next.next;
                flag = false;
            }
            else
                current = current.next;
        }
        
        if(flag){
            System.out.println("Value not present in the list");
        }
    }
    
    void search(int item){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        Node current = head;
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
    
    void show(){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        
        Node current = head;
        while(current.next!=head){
            System.out.println("Data: "+current.data+"\t Current Node: "+current+"\t Next Node: "+current.next);
            current = current.next;
        }
        System.out.println("Data: "+current.data+"\t Current Node: "+current+"\t Next Node: "+current.next);
    }
    
    void recursiveShow(Node current){
        if(head==null){
            System.out.println("List Empty");
            return ;
        }
        if(current.next==head){
            System.out.println("Data: "+current.data+"\t Current Node: "+current+"\t Next Node: "+current.next);
            return;
        }
        System.out.println("Data: "+current.data+"\t Current Node: "+current+"\t Next Node: "+current.next);
        recursiveShow(current.next);
    }
    
    public static void main(String[] agrs){
        CircularLinkedList c = new CircularLinkedList();
        c.append(10);
        c.prepand(20);
        c.append(30);
        c.prepand(40);
        c.recursiveShow(c.head);
    }
}