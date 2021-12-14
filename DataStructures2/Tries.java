package DataStructures2;

public class Tries{
    
    public class Node{
        boolean end;
        Node[] next;
        
        public Node(){
            end = false;
            next = new Node[26];
        }
    }
    
    Node trie;
    
    public Tries(){
        trie = new Node();
    }
    
    public void insert(String word){
        int i = 0;
        Node temp = trie;
        while(i < word.length()){
            if(temp.next[word.charAt(i) - 'a'] == null)
                temp.next[word.charAt(i) - 'a'] = new Node();
            
            temp = temp.next[word.charAt(i) - 'a'];
            i++;
        }
        temp.end = true;
    }
    
    public boolean search(String word){
        int i = 0;
        Node temp = trie;
        while(i < word.length()){
            if(temp.next[word.charAt(i) - 'a'] == null)
                return false;
            
            temp = temp.next[word.charAt(i) - 'a'];
            i++;
        }
        return temp.end;
    }
    
    public static void main(String[] args){
        Tries t = new Tries();
        t.insert("abc");
        t.insert("abcd");
        t.insert("priya");
        
        System.out.println(t.search("abcd") + " | " + t.search("ab") + " | " + t.search("priya"));
    }
}