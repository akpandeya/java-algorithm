import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    // construct an empty deque
    public Deque()
    {
        
        first = null;
        last = null;

    }

    private class Node
    { // nested class to define nodes
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return first == null || last  == null;

    }

    // return the number of items on the deque
    public int size()
    {
        return size;

    }

    // add the item to the front
    public void addFirst(Item item)
    {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        oldFirst.prev = first;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty())
        {
            last = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        oldLast.next = last;
        last.prev = oldLast;
        if (isEmpty()) first = last;
        size++;

    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        Item item = first.item;
        first = first.next;
        first.prev = null;
        if (isEmpty()) last = first;
        size--;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast()
    {
        Item item = last.item;
        last = last.prev;
        last.next = null;
        if (isEmpty()) first = last;
        size--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {

    }

    // unit testing (required)
    public static void main(String[] args)
    {

    }

}
