public int maxProfit(int[] prices) {
    if(prices.length < 2) {
        return 0;
    }
    
    int buyAt = 0;
    int sellAt = 1;
    int profit = prices[sellAt] - prices[buyAt];

    for(int i = 1; i < prices.length - 1; ++i) {
        if(prices[i+1] - prices[buyAt] > profit && (i+1 > buyAt)) {
            sellAt = i + 1;
            profit = prices[sellAt] - prices[buyAt];
        }

        if(prices[sellAt] - prices[i] > profit && (i < sellAt)) {
            buyAt = i;
            profit = prices[sellAt] - prices[buyAt];
        }
    }

    return Math.max(profit, 0);
}

println(maxProfit([1,2,3] as int[]));
println(maxProfit([2,1,3] as int[]));
println(maxProfit([3,2,1] as int[]));
println(maxProfit([7, 1, 5, 3, 6, 4] as int[]));
println(maxProfit([7, 6, 4, 3, 1] as int[]));
println(maxProfit([100, 50, 200, 250, 120, 350] as int[]));
