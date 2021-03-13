import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[][] isOpen;
    private final int size;
    private int numberOfOpenSites = 0;
    private final WeightedQuickUnionUF unionFind;

    private final int top;
    private final int bottom;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("n must be positive");
        isOpen = new boolean[n][n];
        size = n;
        unionFind = new WeightedQuickUnionUF(size * size + 2);

        top = 0;
        bottom = size * size + 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                isOpen[i][j] = false;
            }
        }


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // System.out.printf("Open Row %d, Col %d%n", row, col);
        if (row < 1 || row > size) throw new IllegalArgumentException(" Row out of range");
        if (col < 1 || col > size) throw new IllegalArgumentException(" Column out of range");


        if (!isOpen(row, col)) {
            isOpen[row - 1][col - 1] = true;
            numberOfOpenSites++;
        } else return;

        if (row == 1) {
            unionFind.union(getIndex(row, col), top);
        }

        if (row == size) {
            unionFind.union(bottom, getIndex(row, col));
        }


        if (row < size && isOpen(row + 1, col)) unionFind.union(getIndex(row, col), getIndex(row + 1, col));
        if (col < size && isOpen(row, col + 1)) unionFind.union(getIndex(row, col), getIndex(row, col + 1));
        if (col > 1 && isOpen(row, col - 1)) unionFind.union(getIndex(row, col), getIndex(row, col - 1));
        if (row > 1 && isOpen(row - 1, col)) unionFind.union(getIndex(row, col), getIndex(row - 1, col));
        if (size == 1) {              // for 1x1 case
            unionFind.union(bottom, 1);
            unionFind.union(top, 1);
        }

    }

    private int getIndex(int row, int col) {
        return (row - 1) * size + col;
    }

    // public void displayGrid() {

    //     System.out.printf("%5s", " ");
    //     for (int i = 1; i < size + 1; i++) {
    //         System.out.printf("%5d", i);
    //     }
    //     System.out.println();
    //     for (int i = 0; i < size; i++) {
    //         System.out.println();
    //         System.out.printf("%-10d", i + 1);
    //         for (int j = 0; j < size; j++) {

    //             System.out.printf("%6b", isOpen[i][j]);
    //         }

    //     }
    //     System.out.println();

    // }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // System.out.printf("Is Open Row %d, Col %d%n", row, col);
        if (row < 1 || row > size) throw new IllegalArgumentException(" Row out of range");
        if (col < 1 || col > size) throw new IllegalArgumentException(" Column out of range");
        return isOpen[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // System.out.printf("Is Full Row %d, Col %d%n", row, col);
        // System.out.printf("Find item %d %n", unionFind.find(getIndex(row, col)));
        // System.out.printf("Find item top = %d is %d %n", top, unionFind.find(top));
        if (row < 1 || row > size) throw new IllegalArgumentException(" Row out of range");
        if (col < 1 || col > size) throw new IllegalArgumentException(" Column out of range");
        return unionFind.find(getIndex(row, col)) == unionFind.find(top);
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;

    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.find(top) == unionFind.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
//        p.open(2, 1);
//        p.open(1, 1);
//        p.open(4, 4);
//        p.open(3, 4);
//        p.open(2, 4);
//        p.open(2, 3);
//        p.open(2, 2);
//        p.open(2, 1);
//        p.open(3, 1);
//        p.open(4, 1);
//        p.open(5, 1);
//        p.open(5, 2);
//        p.open(6, 2);
//        p.open(5, 4);
        System.out.println(p.isFull(3, 1));
        // p.displayGrid();
        // System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());

    }
}
