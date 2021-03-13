import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private final double[] probabilities;
    private final int size;
    private final int trials;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n < 1) throw new IllegalArgumentException("n must be > 0");
        if (trials < 1) throw new IllegalArgumentException("trials must be > 0");
        Percolation percolation;
        probabilities = new double[trials];
        size = n;
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            runExperiment(percolation, i);
        }

    }

    private void runExperiment(Percolation p, int j) {
        int i = 0;

        while (!p.percolates()) {

            int index = StdRandom.uniform(1, size * size);
            int row = (index / size) + 1;
            int col = (index % size) + 1;

            // System.out.printf("Row %d, Col %d, percolates = %b%n", row, col, p.percolates());


            p.open(row, col);
            // p.displayGrid();

            i++;
        }

        // System.out.println(i);

        double probability = (double) i / (size * size);

        probabilities[j] = probability;
    }

    // sample mean of percolation threshold
    public double mean() {

        return StdStats.mean(probabilities);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(probabilities);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double s = stddev();

        double m = mean();

        return m - CONFIDENCE_95 * s / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double s = stddev();

        double m = mean();

        return m + CONFIDENCE_95 * s / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats pStats = new PercolationStats(6, 3);

        System.out.printf("mean                    = %f%n", pStats.mean());
        System.out.printf("stddev                  = %f%n", pStats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]%n", pStats.confidenceLo(), pStats.confidenceHi());

    }
}
