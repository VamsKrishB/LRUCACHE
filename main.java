import java.util.*;
import java.io.*;

class CDLLNode {
    int key, val;
    CDLLNode prev, next;
    
    public CDLLNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class CDLL { // Circular DLL class
    CDLLNode head;
    
    public CDLL() {
        head = null;
    }
    
    CDLLNode insertAtBegin(int key, int val) {
        CDLLNode nn = new CDLLNode(key, val);
        nn.next = nn;
        nn.prev = nn;
        if (head == null) {
            head = nn;
            return head;
        }
        CDLLNode last = head.prev;
        nn.next = head;
        head.prev = nn;
        nn.prev = last;
        last.next = nn;
        head = nn;
        return head;
    }
    
    void printLL() {
        if (head == null) return; // Empty list, nothing to print
        CDLLNode temp = head;
        do {
            System.out.println(temp.key + " " + temp.val);
            temp = temp.next;
        } while (temp != head);
    }
    
    int deleteLast() {
        if (head == null) return -1;
        CDLLNode last = head.prev;
        int k = last.key;
        if (head == head.next) {
            head = null;
            return k;
        }
        last.prev.next = head;
        head.prev = last.prev;
        return k;
    }
    
    void moveAtFront(CDLLNode nodeToMove) {
        if (nodeToMove == head) return; // Already at front
        
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

class LRUCache {
    CDLL lst;
    int capacity, size;
    Map<Integer, CDLLNode> mp;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lst = new CDLL();
        this.mp = new HashMap<>();
    }
    
    int get(int key) {
        if (!mp.containsKey(key)) {
            return -1;
        }
        CDLLNode node = mp.get(key);
        int ret = node.val;
        lst.moveAtFront(node);
        return ret;
    }
    
    void put(int k, int v) {
        if (mp.containsKey(k)) { // Update value for existing key
            CDLLNode node = mp.get(k);
            node.val = v;
            lst.moveAtFront(node);
        } else { // Insert new entry
            if (size < capacity) {
                CDLLNode node = lst.insertAtBegin(k, v);
                mp.put(k, node);
                size++;
            } else {
                int del = lst.deleteLast();
                mp.remove(del);
                CDLLNode node = lst.insertAtBegin(k, v);
                mp.put(k, node);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        LRUCache ch = new LRUCache(3);
        ch.put(1, 1);
        System.out.println(ch.get(1));
        
        ch.put(3, 100);
        System.out.println(ch.get(3));
        
        ch.put(4, 400);
        System.out.println(ch.get(4));
        
        ch.put(5, 500); // This should remove the least recently used key (1)
        System.out.println(ch.get(1)); // Expected output: -1 (not found)
        
        ch.put(6, 600);
        System.out.println(ch.get(3)); // Expected output: 100
        
        ch.put(7, 700);
        System.out.println(ch.get(4)); // Expected output: -1 (not found as 4 was removed)
    }
}
