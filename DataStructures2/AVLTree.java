package DataStructures2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class AVLTree{
    
    class Node{
        int data, height;
        Node left, right;
        
        Node(int value){
            data = value;
            height = 1;
        }
    }
    
    Node root;
    
    private int getHeight(Node N){
        if(N == null)
            return 0;
        
        return N.height;
    }
    
    private Node insertNode(Node node, int value){
        // Normal BST insertion
        if (node == null) 
            return (new Node(value)); 
        
        if(value<node.data){
            node.left = insertNode(node.left, value);
        }else{
            node.right = insertNode(node.right, value);
        }
        
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        
        // check whether this node became unbalanced
        int balance = getHeight(node.left) - getHeight(node.right);
        
        // If this node becomes unbalanced, then there 4 cases
        // Left Left Case
        if (balance > 1 && value < node.left.data)
            return rightRotate(node);
        
        // Right Right Case 
        if (balance < -1 && value > node.right.data)
            return leftRotate(node);
        
        // Left Right Case 
        if (balance > 1 && value > node.left.data){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case 
        if (balance < -1 && value < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        // return the unchanged node
        return node;
    }
    
    void insert(int value){
        root = insertNode(root, value);
    }
    
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T2 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T2;
        
        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        
        // Return new root
        return x;
    }
    
    private Node leftRotate(Node x){
        Node y = x.right; 
        Node T2 = y.left;
        
        // Perform rotation
        y.left = x; 
        x.right = T2;
        
        // Update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        
        // Return new root
        return y; 
    }
    
    private Node minValueNode(Node node){
        Node current = node;
        while(node.left!=null)
            node = node.left;
        
        return current;
    }
    
    private int getBalance(Node N){  
        if (N == null)
            return 0;
        return getHeight(N.left) - getHeight(N.right);
    }
    
    private Node deleteNode(Node node, int value){
        if(node==null)
            return node;
        
        if(value<node.data)
            node.left = deleteNode(node.left, value);
        else if(value>node.data)
            node.right = deleteNode(node.right, value);
        else{
            if(node.left==null || node.right==null){
                Node temp = null;  
                if (temp == node.left)  
                    temp = node.right;  
                else
                    temp = node.left;  
  
                // No child case
                if (temp == null)
                    node = null;
                else
                    node = temp;
            }else{
                // node with two children.
                // Get the inorder successor i.e. smallest in the right subtree 
                Node temp = minValueNode(node.right);
  
                // Copy the inorder successor's data to this node  
                node.data = temp.data;
  
                // Delete the inorder successor  
                node.right = deleteNode(node.right, temp.data);  
            }
        }
        
        // If the tree had only one node then return
        if (node == null)
            return node;
        
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE to check whether this node became unbalanced
        int balance = getBalance(node);
        
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        
        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        
        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    void delete(int value){
        root = deleteNode(root, value);
    }
    
    boolean contains(int value){
        Node current = root;
        
        while(current!=null){
            if(current.data==value){
                return true;
            }else if(value<current.data){
                current = current.left;
            }else if(value>current.data){
                current = current.right;
            }
        }
        
        return false;
    }
    
    void printInOrder(){
        if (root == null)
            return;
        
        Stack<Node> s = new Stack<>();
        Node current = root;
        
        while (current != null || s.size() > 0) {
            /* Reach the left most Node of the curr Node */
            while(current !=  null){
                s.push(current);
                current = current.left;
            }
            
            /* Current must be NULL at this point */
            current = s.pop();
            
            System.out.println(current.data);
            
            // right subtree of current node
            current = current.right;
        }
    }
    
    void printPostOrder(){
        if (root == null) 
            return;
        
        Stack<Node> s1 = new Stack<>(); 
        Stack<Node> s2 = new Stack<>();
  
        // push root to first stack 
        s1.push(root); 
  
        // Run while first stack is not empty 
        while (!s1.isEmpty()) { 
            // Pop an item from s1 and push it to s2 
            Node temp = s1.pop(); 
            s2.push(temp); 
  
            // Push left and right children of 
            // removed item to s1 
            if (temp.left != null) 
                s1.push(temp.left); 
            if (temp.right != null) 
                s1.push(temp.right); 
        } 
  
        // Print all elements of second stack 
        while (!s2.isEmpty()){
            Node temp = s2.pop();
            System.out.println(temp.data);
        } 
    }
    
    void printPreOrder(){ 
        if (root == null) 
            return;
        
        Stack<Node> s = new Stack<>(); 
        s.push(root);
        
        while (!s.empty()) {
            // Pop the top item from stack and print it 
            Node current = s.pop();
            System.out.println(current.data);
            
            // Push right and left children of the popped node to stack 
            if (current.right != null)
                s.push(current.right);
            if (current.left != null)
                s.push(current.left);
        }
    } 
    
    void breadthFirstTraversal(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            Node current = q.poll();
            System.out.println(current.data);
            if(current.left!=null)
                q.add(current.left);
            if(current.right!=null)
                q.add(current.right);
        }
    }
    
    public static void main(String[] args){
        AVLTree a = new AVLTree(); 
        a.insert(10);
        a.insert(20);
        a.insert(30);
        a.insert(40);
        a.insert(50);
        a.insert(25);
        a.printInOrder();
        System.out.println(a.contains(25));
        System.out.println("After deleteion: ");
        a.delete(20);
        a.printInOrder();
    }
}