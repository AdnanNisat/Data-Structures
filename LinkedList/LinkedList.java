import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListI<E>{
    class Node<E>{  //Node Class
        E data;
        Node<E> next;
        public Node(E obj){  //Node Class Constructor
            data = obj;
            next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    public LinkedList(){ // LinkedList constructor
        head = null;
        tail = null;
        currentSize = 0;
    }

    public void addFirst(E obj){  //Add first to the list method
        Node<E> node = new Node<E>(obj);

        if(head == null){
            node.next = head;
            head = tail = node;
            currentSize++;
            return;
        }
        node.next = head;
        head = node;
        currentSize++;
    }

    public void addLast(E obj){  //Add Last to the list method
        Node<E> node = new Node<E>(obj);

        if(head == null){
            head = tail = node;
            currentSize++;
            return;
        }

        tail.next = node;
        tail = node;
        currentSize++;
        return;
    }

    public E removeFirst(){  //Removes The First Item And Returns It
        if(head == null)  //If there is no item
            return null;

        E tmp = head.data;

        if(head == tail)  //If there is only one item
            head = tail = null;
        else              //For more than one item
            head = head.next;

        currentSize--;
        return tmp;
    }

    public E removeLast(){  //Removes the last item and returns it
        if(head == null)  //if there is no item
            return null;
        if(head == tail)  //if there is only one item
            return removeFirst();
        Node<E> previous = null, current = head; //for more than one item
        while(current != tail){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        currentSize--;
        return current.data;
    }

    public E remove(E obj){ //Removes the obj from the list
        Node<E> current = head, previous = null;
        while(current != null){
            if(((Comparable<E>)current.data).compareTo(obj) == 0){
                if(current == head)
                    return removeFirst();
                if(current == tail)
                    return removeLast();
                currentSize--;
                previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(E obj){  //Checks in obj is in the list
        Node<E> current = head;
        while(current != null){
            if(((Comparable<E>)current.data).compareTo(obj) == 0)
                return true;
            current = current.next;
        }
        return false;
    }

    public E peekFirst(){  //Returns the first list item
        if(head == null)
            return null;
        return head.data;
    }

    public E peekLast(){  //Returns the last list item
        if(tail == null)
            return null;
        return tail.data;
    }

    class IteratorHelper implements Iterator<E>{
        Node<E> index;

        public IteratorHelper(){
            index = head;
        }
        
        public boolean hasNext() {
            return (index != null);
        }
        
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E val = index.data;
            index = index.next;
            return val;
        }
    }

    public Iterator<E> iterator(){
       return new IteratorHelper();
    }
}
