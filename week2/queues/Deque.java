import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // 8 bytes
    private Node last; // 8 bytes
    private int n; // 4 bytes

    // construct an empty deque
    public Deque() {

        first = null;
        last = null;

    }

    private class Node // 8 bytes
    { // nested class to define nodes
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;

    }

    // return the number of items on the deque
    public int size() {
        return n;

    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (oldFirst != null) oldFirst.prev = first;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast != null) oldLast.next = last;
        last.prev = oldLast;
        if (isEmpty()) first = last;
        n++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0) throw new NoSuchElementException("No element in the deque");
        Item item = first.item;
        first = first.next;
        if (first != null) first.prev = null;
        if (isEmpty()) last = first;
        n--;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n == 0) throw new NoSuchElementException("No element in the deque");
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        if (isEmpty()) first = last;
        n--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();

    }

    private class DequeIterator implements Iterator<Item> { // Support LIFO iteration.
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;


        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(4);
        deque.addFirst(3);
        deque.addLast(1);
        System.out.println(deque.size());

        Iterator<Integer> it = deque.iterator();
        int count = 0;
        while (it.hasNext() && count < 10) {
            Integer i = it.next();

            System.out.println(i);
            count++;
        }


    }

}
