import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int count = 1;

        String champion = "";

        while (!StdIn.isEmpty()) 
        {
            String s = StdIn.readString();
            if (StdRandom.bernoulli(1./count)) {
                champion = s;
            }
            count++;
        }

        StdOut.println(champion);
    }
}
