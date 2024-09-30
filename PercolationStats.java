import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] sites_to_percolate;
    // where the amount of open sites needed for the grid to percolate is saved for each trial
    private int trial_size; // the amount of total trials, is used for confidence
    private static Stopwatch time;

    // // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            // must be a certain size grid and amount of trials to work
            throw new IllegalArgumentException("Grid and Trials must be > 0 !!!!");
        }
        else {
            time = new Stopwatch();
            sites_to_percolate
                    = new double[trials]; // sets length of array where values will be saved
            trial_size = trials;
            for (int i = 0; i < trials; i++) {
                // amount of times average sites needed for percolation will be found
                Percolation p = new Percolation(n);
                while (!p.percolates()) {
                    // while percolation is not being reached
                    int row = StdRandom.uniformInt(0,
                                                   n); // chooses random value from 1 to n for row
                    int col = StdRandom.uniformInt(0,
                                                   n); // chooses random value from 1 to n for column
                    if (!p.isOpen(row, col)) {
                        // if the row and column is not full or not open, that coordinate will be opened
                        p.open(row, col);
                    }
                }
                sites_to_percolate[i] = (double) p.numberOfOpenSites() / (n
                        * n); // saves the amount of sites opened divided by the whole area
            }
        }
    }

    // // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(sites_to_percolate);
    }

    // // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(sites_to_percolate);
    }

    // // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trial_size));
    }

    // // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trial_size));
    }

    // // test client (see below)
    public static void main(String[] args) {
        // test trial for one by one grid with 50 trials
        System.out.println("Statistic for an 1 by 1 grid through 50 trials");
        PercolationStats one_size_fifty_trials = new PercolationStats(1, 50);
        System.out.println("Mean: " + one_size_fifty_trials.mean());
        System.out.println("Standard Deviation: " + one_size_fifty_trials.stddev());
        System.out.println("Confidence Low: " + one_size_fifty_trials.confidenceLow());
        System.out.println("Confidence High: " + one_size_fifty_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        // test trial for higher grid size and one trial
        System.out.println("Statistic for an 50 by 50 grid through 1 trials");
        PercolationStats fifty_size_one_trials = new PercolationStats(50, 1);
        System.out.println("Mean: " + fifty_size_one_trials.mean());
        System.out.println("Standard Deviation: " + fifty_size_one_trials.stddev());
        System.out.println("Confidence Low: " + fifty_size_one_trials.confidenceLow());
        System.out.println("Confidence High: " + fifty_size_one_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        // finding how large of a grid we can get by getting as close to one minute time with one hundred trials
        System.out.println("Statistic for an 100 by 100 grid through 100 trials");
        PercolationStats hun_size_hundred_trials = new PercolationStats(100, 100);
        System.out.println("Mean: " + hun_size_hundred_trials.mean());
        System.out.println("Standard Deviation: " + hun_size_hundred_trials.stddev());
        System.out.println("Confidence Low: " + hun_size_hundred_trials.confidenceLow());
        System.out.println("Confidence High: " + hun_size_hundred_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        System.out.println("Statistic for an 200 by 200 grid through 100 trials");
        PercolationStats thun_size_hundred_trials = new PercolationStats(200, 100);
        System.out.println("Mean: " + thun_size_hundred_trials.mean());
        System.out.println("Standard Deviation: " + thun_size_hundred_trials.stddev());
        System.out.println("Confidence Low: " + thun_size_hundred_trials.confidenceLow());
        System.out.println("Confidence High: " + thun_size_hundred_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        System.out.println("Statistic for an 400 by 400 grid through 100 trials");
        PercolationStats fhun_size_hundred_trials = new PercolationStats(400, 100);
        System.out.println("Mean: " + fhun_size_hundred_trials.mean());
        System.out.println("Standard Deviation: " + fhun_size_hundred_trials.stddev());
        System.out.println("Confidence Low: " + fhun_size_hundred_trials.confidenceLow());
        System.out.println("Confidence High: " + fhun_size_hundred_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        System.out.println("Statistic for an 550 by 550 grid through 100 trials");
        PercolationStats ffhun_size_hundred_trials = new PercolationStats(550, 100);
        System.out.println("Mean: " + ffhun_size_hundred_trials.mean());
        System.out.println("Standard Deviation: " + ffhun_size_hundred_trials.stddev());
        System.out.println("Confidence Low: " + ffhun_size_hundred_trials.confidenceLow());
        System.out.println("Confidence High: " + ffhun_size_hundred_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        System.out.println("Statistic for an 475 by 475 grid through 100 trials");
        PercolationStats fshun_size_hundred_trials = new PercolationStats(475, 100);
        System.out.println("Mean: " + fshun_size_hundred_trials.mean());
        System.out.println("Standard Deviation: " + fshun_size_hundred_trials.stddev());
        System.out.println("Confidence Low: " + fshun_size_hundred_trials.confidenceLow());
        System.out.println("Confidence High: " + fshun_size_hundred_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        System.out.println();

        // 1 trial by 1 by 1 grid
        System.out.println("Statistic for an 1 by 1 grid through 1 trials");
        PercolationStats one_size_one_trials = new PercolationStats(1, 1);
        System.out.println("Mean: " + one_size_one_trials.mean());
        System.out.println("Standard Deviation: " + one_size_one_trials.stddev());
        System.out.println("Confidence Low: " + one_size_one_trials.confidenceLow());
        System.out.println("Confidence High: " + one_size_one_trials.confidenceHigh());
        System.out.println("Time " + time.elapsedTime());

        // tests for errors
        // PercolationStats zero_size_eight_trials = new PercolationStats(0, 8);
        // PercolationStats zero_size_eight_trials = new PercolationStats(8, 0);
    }
}
