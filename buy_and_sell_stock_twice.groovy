class Sale implements Comparable<Sale> {
    final int lower;
    final int upper;
    final int profit;

    Sale(final int lower, final int upper, final int profit) {
        this.lower = lower;
        this.upper = upper;
        this.profit = profit;
    }
    
    static boolean profitable(final int[] prices, final int lower, final int upper) {
        return prices[upper] - prices[lower] > 0;
    }

    static Sale sale(final int[] prices, final int lower, final int upper) {
        if(profitable(prices, lower, upper)) {
            return new Sale(lower, upper, prices[upper] - prices[lower]);
        }
        else {
            return null;
        }
    }

    boolean overlaps(final Sale rhs) {
        return ((rhs.lower >= lower && rhs.lower <= upper) ||
                (lower >= rhs.lower && lower <= rhs.upper));
    }

    public int compareTo(final Sale rhs) {
        return profit - rhs.profit;
    }

    public String toString() {
        return String.format("(%d,%d): %d", lower, upper, profit);
    }
}

public int maxProfit(int[] prices) {
    //find all possible sales
    List<Sale> sales = new ArrayList<>();
    for(int i = 0; i < prices.length - 1; ++i) {
        for(int j = 1; j < prices.length; ++j) {
            Sale s = Sale.sale(prices, i, j);
            if(s != null) {
                sales.add(s);
            }
        }
    }

    if(sales.size() == 0) {
        //no profit
        return 0;
    }
    
    Collections.sort(sales);
    Collections.reverse(sales);
    int largest = sales.get(0).profit;

    for(Sale s1 : sales) {
        for(Sale s2 : sales) {
            if(!s1.overlaps(s2) && (s1.profit + s2.profit > largest)) {
                largest = s1.profit + s2.profit;
            }
        }
    }

    return largest;
}

assert(maxProfit([2,1,3,2,4,1,7] as int[]) == 9);
assert(maxProfit([1,2,3] as int[]) == 2);
