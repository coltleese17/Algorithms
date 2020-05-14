import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static WeightedQuickUnionUF wuf;
    private int size;
    private boolean[] stateArray;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N should be greater than 0");
        }
        this.size = n;
        wuf = new WeightedQuickUnionUF(n * n + 2);
        stateArray = new boolean[n * n + 2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isIndicesOutOfBounds(row, col)) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int index = xyto1d(row, col);

        if (!stateArray[index])
            stateArray[index] = true;

        if (index == 1) {
            wuf.union(index, 0);
        }

        if (index == this.size) {
            wuf.union(index, (this.size * this.size) + 1);
        }

        if (row > 1 && isOpen(row - 1, col)) {
            wuf.union(index, xyto1d(row - 1, col));
        }

        if (row < this.size && isOpen(row + 1, col)) {
            wuf.union(index, xyto1d(row + 1, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            wuf.union(index, xyto1d(row, col - 1));
        }

        if (col < this.size && isOpen(row, col + 1)) {
            wuf.union(index, xyto1d(row, col + 1));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int x, int y) {
        if (isIndicesOutOfBounds(x, y)) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int index = xyto1d(x, y);
        return stateArray[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int x, int y) {
        if (isIndicesOutOfBounds(x, y)) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int index = xyto1d(x, y);
        return wuf.connected(index, 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < stateArray.length; i++) {
            if (stateArray[i]) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return wuf.connected((this.size * this.size) + 1, 0);
    }

    // converts x+y coords to unique 1d
    private int xyto1d(int x, int y) {
        if (isIndicesOutOfBounds(x, y)) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return (x - 1) * this.size + y;
    }

    private boolean isIndicesOutOfBounds(int x, int y) {
        return (x < 1 || x > this.size || y < 1 || y > this.size);
    }

    public static void main(String[] args) {

        Percolation tired = new Percolation(3);

        tired.open(1, 1);
        tired.open(1, 2);
        tired.open(1, 3);

        System.out.println(tired.percolates());
    }
}
