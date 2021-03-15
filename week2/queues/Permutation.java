import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;


public class Permutation {

    
   

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);

        int count = 0;

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            rq.enqueue(s);   
        }

        Iterator<String> it = rq.iterator();

        while (it.hasNext() && count  < size)
        {
            System.out.println(it.next());
            count++;
        }

    }
    
}
