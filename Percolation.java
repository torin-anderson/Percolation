import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private boolean[][] grid;
    private int size;
    private WeightedQuickUnionUF non_backwash_uf;
    private int counter_open;
    private int top_site; // represents top virtual site

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Must be value over 0!!");
        }
        grid = new boolean[n][n];
        non_backwash_uf = new WeightedQuickUnionUF(
                n * n + 1); // does not take into account the bottom virtual site
        size = n;
        counter_open = 0;
        top_site = n * n; // set top site
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            // makes sure row and column are valid
            throw new IllegalArgumentException("Must be within grid");
        }
        else {
            if (!isOpen(row, col)) {
                // if coordinate is not open, makes it so and ups counter
                grid[row][col] = true;
                ++counter_open;
            }
            if (row == 0) {
                // if inputted row is 1, we can automatically connect the column to top site
                non_backwash_uf.union(top_site, col);
            }
            if (row > 0 && isOpen(row - 1, col)) {
                // if the spot above the coordinate is open then we connect to that
                non_backwash_uf.union((row - 1) * size + (col), (row) * size + (col));
            }
            if (row < size - 1 && isOpen(row + 1, col)) {
                // if the spot below the coordinate is open then we connect to that
                non_backwash_uf.union((row + 1) * size + (col), (row) * size + (col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                // if the spot to the left of the coordinate is open then we connect to that
                non_backwash_uf.union((row) * size + (col - 1), (row) * size + (col));
            }
            if (col < size - 1 && isOpen(row, col + 1)) {
                // if spot to the right of the coordinate is open then we connect to that
                non_backwash_uf.union((row) * size + (col + 1), (row) * size + (col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            // makes sure row and column are valid
            throw new IllegalArgumentException("Must be within grid");
        }
        else {
            // checks if there's already a value at coordinate
            return grid[row][col];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            // makes sure row and column are valid
            throw new IllegalArgumentException("Must be within grid");
        }
        else {
            // checks to see if the top site is connected to the coordinate and if not then it is not full
            // uses non backwash incase the coordinate is only connected to bottom site and if so then this will show fullness properly
            return non_backwash_uf.connected((row) * size + (col), top_site);
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return counter_open;
    }

    // does the system percolate?
    public boolean percolates() {
        // checks if top site and any spots on the last row are connected
        for (int i = 0; i < size; i++) {
            // iterates through every spot in the bottom row
            if (non_backwash_uf.connected(top_site, (size - 1) * size + (i))) {
                return true;
            }
        }
        // if none are connected then it does not
        return false;
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Test for 2 by 2 grid");
        // test for 2 by 2 grid
        Percolation two_by_two = new Percolation(2);
        System.out.println("Checks if open at 0,0: " + two_by_two.isOpen(0, 0));
        two_by_two.open(0, 0);
        System.out.println("Checks if open at 0,0: " + two_by_two.isOpen(0, 0));
        System.out.println("Does it percolate: " + two_by_two.percolates());
        System.out.println("Number of open sites: " + two_by_two.numberOfOpenSites());
        System.out.println("Checks if 1,0 is full: " + two_by_two.isFull(1, 0));
        two_by_two.open(1, 0);
        System.out.println("Does it percolate: " + two_by_two.percolates());

        System.out.println();

        // test for 1 by 1 grid
        System.out.println("Test for 1 by 1 grid");
        Percolation one_by_one = new Percolation(1);
        System.out.println("Does it percolate: " + one_by_one.percolates());
        System.out.println("Checks if open at 0,0: " + one_by_one.isOpen(0, 0));
        one_by_one.open(0, 0);
        System.out.println("Checks if open at 0,0: " + one_by_one.isOpen(0, 0));
        System.out.println("Checks if full at 0,0: " + one_by_one.isFull(0, 0));
        System.out.println("Does it percolate: " + one_by_one.percolates());
        System.out.println("Number of open sites: " + one_by_one.numberOfOpenSites());

        System.out.println();

        System.out.println("Checks to make sure the program fails if it's a 0 by 0 grid");
        // Tests to make sure n=0 fails
        Percolation fail = new Percolation(0);
    }
}
