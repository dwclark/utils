private int subSum(final int[] array, final int lower, final int upper) {
    int total = 0;
    for(int i = lower; i <= upper; ++i) {
        total += array[i];
    }

    return total;
}

public int maxSubArray(int[] array) {
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < array.length; ++i) {
        for(int j = i; j < array.length; ++j) {
            final int sum = subSum(array, i, j);
            if(sum > max) {
                max = sum;
            }
        }
    }

    return max;
}

println(maxSubArray([1,2,-1] as int[]));
println(maxSubArray([-2,1,-3,4,-1,2,1,-5,4] as int[]));
