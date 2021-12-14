package DataStructures;

// If we keep the tail pointer also then insertion at the end i.e. append could be done in O(1)
class Node{
    Node next;
    int data;
    public Node(int data){
        this.data = data;
    }
}

public class LinkedList{
    int length;
    Node head;
    
    public LinkedList(){
        this.length = 0;
        this.head = null;
    }
    
    public int get(int index) {
        if(index < 0 || index >= this.length) {
            return -1;
        }else {
            int counter = 0;
            Node curr = head;
            while(counter != (index)) {
                curr = curr.next;
                counter++;
            }
            return curr.data;
        }
    }
    
    public void append(int data){
        if(head==null){
            head = new Node(data);
            this.length++;
            return;
        }
        
        Node current = head;
        while(current.next!=null){
            current = current.next;
        }
        current.next = new Node(data);
        this.length++;
    }
    
    public void prepand(int data){
        Node newHead = new Node(data);
        newHead.next = head;
        head = newHead;
        this.length++;
    }
    
    public void insertAtPosition(int data, int pos){
        if(0 <= pos && this.length >= pos){
            if(pos==0){
                prepand(data);
            }else if(this.length == pos){
                append(data);
            }else{
                Node curr = head;
                for(int i = 0; i<pos-1; i++){
                    if(curr.next != null) curr = curr.next;
                    else return ;
                }

                Node newNode = new Node(data);
                newNode.next = curr.next;
                curr.next = newNode;
                this.length++;
            }
        }
    }
    
    public void deleteAtPosition(int pos){
        if(pos < 0 || pos >= this.length) return;
        
        if(pos == 0) {
            head = head.next;
            this.length--;
        }else {
            Node curr = head;
            for(int i = 0; i<pos-1; i++){
                if(curr.next != null) curr = curr.next;
                else return ;
            }

            curr.next = curr.next.next;
            this.length--;
        }
    }
    
    public void deleteWithValue(int data){
        if(head==null) return;
        if(head.data==data){
            head = head.next;
            return;
        }
        Node current  = head;
        while(current.next!=null){
            if(current.next.data==data){
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
    
    void reverse(){
        Node prev = null;
        Node current  = head;
        Node next = null;

        while(current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    
    public Node getMiddle(Node node){
        if(node==null)
            return node;
        
        Node slow = node, fast = node;
        
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private Node sortedList(Node a, Node b){
        Node result = null;
        
        if(a==null)
            return b;
        if(b==null)
            return a;
        
        if(a.data<=b.data){
            result = a;
            result.next = sortedList(a.next, b);
        }else{
            result = b;
            result.next = sortedList(a, b.next);
        }
        
        return result;
    }
    
    private Node mergeSort(Node head){
        if(head==null || head.next==null)
            return head;
        
        Node middle = getMiddle(head);
        Node afterMiddle = middle.next;
        
        middle.next = null;
        
        Node left = mergeSort(head);
        Node right = mergeSort(afterMiddle);
        
        Node sortedList = sortedList(left, right);
        return sortedList;
    }
    
    public void sort(){
        this.head = mergeSort(this.head);
    }
    
    public void search(int item){
        if(head==null){
            System.out.println("Empty List");
            return ;
        }
        Node current = head;
        int pos = 0;
        boolean flag = true;
        while(current!=null){
            if(current.data==item){
                System.out.println("Item found at position " + pos);
                flag = false;
            }
            pos++;
            current = current.next;
        }
        if(flag){
            System.out.println("Item not found");
        }
    }
    
    public void show(){
        if(head==null) return;
        Node current = head;
        while(current!=null){
            System.out.println(current.data+"\t"+current.next);
            current = current.next;
        }
    }
    
    public static void main(String[] args){
        LinkedList a = new LinkedList();
        for(int i = 1; i<=5; i++)
            a.append(i*2);
        a.prepand(15);
        a.insertAtPosition(23, 2);
        a.deleteWithValue(8);
        a.deleteAtPosition(5);
        a.prepand(4);
        a.search(4);
        a.reverse();
        a.show();
        a.sort();
        System.out.println();
        a.show();
    }
}