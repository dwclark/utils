
public int maxProfit(int[] prices) {
    int profit = 0;
    for(int i = 0; i < prices.length - 1; ++i) {
        final int possible = prices[i+1] - prices[i];
        if(possible > 0) {
            profit += possible;
        }
    }

    return profit;
}

assert(maxProfit([1,2,3] as int[]) == 2);
assert(maxProfit([2,1,3] as int[]) == 2);
assert(maxProfit([2,1,3,2,4,1,7] as int[]) == (2+2+6));
