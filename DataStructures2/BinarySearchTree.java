package DataStructures2;

import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree{
    int data;
    BinarySearchTree left, right;

    public BinarySearchTree(int data){
        this.data = data;
    }
    
    private static boolean isBST(BinarySearchTree node, int min, int max) { 
        if (node == null)
            return true;
        
        /* false if this node violates the min/max constraints */
        if (node.data < min || node.data > max)
            return false;
        
        // otherwise check the subtrees recursively 
        // checking left side for value less than parent
        // checking right side for value greater than equal to parent
        return (isBST(node.left, min, node.data-1) && isBST(node.right, node.data, max));
    }
    
    public static boolean isBST(BinarySearchTree root){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
    }
    
    void insert(int value){
        if(value<data){
            if(left==null)
                left = new BinarySearchTree(value);
            else
                left.insert(value);
        }else{
            if(right==null)
                right = new BinarySearchTree(value);
            else
                right.insert(value);
        }
    }
    
    // replace the node which has to be deleted with its Inorder successor
    void delete(int value){
        deleteNode(this, value);
    }
    
    private BinarySearchTree deleteNode(BinarySearchTree node, int value){
        if(node==null) return node;
        
        if(value<node.data)
            node.left = deleteNode(node.left, value);
        else if(value>node.data)
            node.right = deleteNode(node.right, value);
        else{
            if(node.left==null)
                return node.right;
            else if(node.right==null)
                return node.left;
            
            // node with two children.
            // Get the inorder successor i.e. smallest in the right subtree 
            node.data = minValue(node.right);
            // Delete the inorder successor 
            node.right = deleteNode(node.right, node.data);
        }
        return node;
    }
    
    private int minValue(BinarySearchTree node){
        while(node.left!=null)
            node = node.left;
        
        return node.data;
    }
    
    boolean contains(int value){
        if(value==data)
            return true;
        else if(value<data){
            if(left==null)
                return false;
            else
                return left.contains(value);
        }else{
            if(right==null)
                return false;
            else
                return right.contains(value);
        }
    }
    
    int height(BinarySearchTree node){
        int lDepth = 0, rDepth = 0;
        if(node.left!=null)
            lDepth = node.height(node.left);
        if(node.right!=null)
            rDepth = node.height(node.right);
        
        if(lDepth > rDepth)
            return (lDepth + 1); 
        else
            return (rDepth + 1); 
    }
    
    // not a zero based indexing
    int height(){
        return height(this);
    }
    
    // not a zero based indexing
    int size(BinarySearchTree node){
        if(node==null) return 0;
        
        return node.size(node.left) + node.size(node.right) + 1;
    }
    
    private int kthSmallestElement(BinarySearchTree node, int k){
        int s = node.size(node.left);
        if(k<s+1)
            return kthSmallestElement(node.left, k);
        else if(k>s+1)
            return kthSmallestElement(node.right, k-s-1);
        else
            return node.data;
        
    }
    
    // not a zero based indexing
    int kthSmallestElement(int k){
        return kthSmallestElement(this, k);
    }
    
    // In Order Traversal gives data in sorted form
    void printInOrder(){
        if(left!=null)
            left.printInOrder();
        System.out.println(data);
        if(right!=null)
            right.printInOrder();
    }
    
    void printPreOrder(){
        System.out.println(data);
        if(left!=null)
            left.printPreOrder();
        if(right!=null)
            right.printPreOrder();
    }
    
    void printPostOrder(){
        if(left!=null)
            left.printPostOrder();
        if(right!=null)
            right.printPostOrder();
        System.out.println(data);
    }
    
    void breadthFirstTraversal(){
        Queue<BinarySearchTree> q = new LinkedList<>();
        q.add(this);
        while(q.size()>0){
            BinarySearchTree current = q.poll();
            System.out.println(current.data);
            if(current.left!=null)
                q.add(current.left);
            if(current.right!=null)
                q.add(current.right);
        }
    }
    
    public static void main(String[] agrs){
        BinarySearchTree a = new BinarySearchTree(10);
        a.insert(5);
        a.insert(12);
        a.insert(3);
        a.insert(2);
        a.insert(7);
        a.insert(11);
        System.out.println("Element: "+a.kthSmallestElement(2));
        a.printInOrder();
        a.delete(10);
        System.out.println("After deletion: ");
        a.breadthFirstTraversal();
        System.out.println(isBST(a));
    }
}