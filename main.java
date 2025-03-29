import java.util.*;
import java.io.*;

class CDLLNode
    {
        int key, val;
        CDLLNode prev, next;
        public CDLLNode(int key, int val)
        {
            this.key =key;
            this.val = val;
        }
    }
class CDLL // circular DLL class
    {
        CDLLNode head;
        public CDLL()
        {
            head = null;
        }
        CDLLNode insertAtBegin(int key, int val)
        {
            CDLLNode nn = new CDLLNode(key, val);
            nn.next=nn;
            nn.prev=nn;
            if(head == null)
            {
               head = nn;
                return head;
            }
            CDLLNode last = head.prev;
            nn.next = head; head.prev = nn;  // 
            nn.prev = last;
            last.next = nn;
            head = nn;  
            return head;
        }
        void printLL()
        {
            if(head == null) return; // empty list, noting to print
            CDLLNode temp = head;
            while(temp.next!=head)
                {
                    System.out.println(temp.key+" "+temp.val);
                    temp = temp.next;
                }
            System.out.println(temp.key+" "+temp.val);
            
        }
        int deleteLast()
        {
            if(head == null)return -1;
            CDLLNode last = head.prev.prev;
            int k = last.next.key;
            if(head== head.next)
            {  
                head = null;
                return k;
            }
            
            last.next = head;
            head.prev = last;
            return k;
        }
        void moveAtFront(CDLLNode nodeToMove)
        {
            if(nodeToMove == head)
            return; //already at front 
    
            CDLLNode p = nodeToMove.prev;
            CDLLNode n = nodeToMove.next;


            n.prev = p;
            p.next = n;

            nodeToMove.next = head;
            nodeToMove.prev = head.prev;
            head.prev.next = nodeToMove;
            head.prev = nodeToMove;
            head = nodeToMove;
            
        }
    }
class LRUCache{
    CDLL lst;
    int capacity, size;
    Map<Integer , CDLLNode> mp;

    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        this.size =0;
        this.lst = new CDLL();
        this.mp = new HashMap<>();
    }
    int get(int key)
    {
        if(!mp.containsKey(key))
        {
            return -1;
        }
        CDLLNode node = mp.get(key);
        int ret = node.val;
        lst.moveAtFront(node);
        return ret;
    }
    void put(int k, int v)
    {
        if(mp.containsKey(k)) // update value for existing key
        {
            CDLLNode node = mp.get(k);
            node.val =v;
            lst.moveAtFront(node);
        }
        else  // insert new entry
        {
            if(size < capacity)
            {
                CDLLNode node = lst.insertAtBegin(k,v);
                mp.put(k, node);
                size++;
            }
            else 
            {
               
                int del = lst.deleteLast();
                mp.remove(del);
                CDLLNode node = lst.insertAtBegin(k,v);
                mp.put(k, node);
                
            }
        }
    }
}
class Main {
    public static void main(String[] args) {
        LRUCache ch = new LRUCache(3);
        ch.put(1,1);
        System.out.println(ch.get(1));

        ch.put(3,100);
        System.out.println(ch.get(3));
        
        ch.put(4,400);
        System.out.println(ch.get(4));
        
        ch.put(5,500);
        System.out.println(ch.get(1));
        
    }
}
