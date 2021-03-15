import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;

    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = n;
    }


    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        if (n == q.length) resize(2 * q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        n++;

    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        n--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;


    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int index = StdRandom.uniform(n-1);

        return q[first + index];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new DequeIterator();


    }

    private class DequeIterator implements Iterator<Item> { // Support LIFO iteration.

        private final int[] elements = StdRandom.permutation(n);
        private int position = 0;

        public boolean hasNext() {
            return position != n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();


            return q[first + elements[position++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);

        // rq.enqueue(4);
        // rq.enqueue(5);
        // rq.enqueue(6);
        // rq.enqueue(7);
        // rq.enqueue(8);

        // System.out.println(rq.sample());
        // System.out.println(rq.size());

        // System.out.println(rq.sample());

        Iterator<Integer> it = rq.iterator();

        int count = 0;

        while (it.hasNext() && count < 10) {
            // System.out.println(("Count:"));
            // System.out.println(count);
            // System.out.print("next: ");
            System.out.println(it.next());
            count++;
        }

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(489);
        queue.sample();
        queue.sample();
        queue.enqueue(134);
        queue.enqueue(194);
        queue.enqueue(376);
        queue.enqueue(194);
        queue.enqueue(145);
        queue.enqueue(10);
        queue.enqueue(304);
        queue.sample();


    }

}
