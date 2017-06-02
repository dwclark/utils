double myPow(double x, int n) {
    double ret = 1;
    for(int i = 0; i < n; ++i) {
        ret *= x;
    }

    return ret;
}

assert(myPow(10, 0) == 1);
assert(myPow(10, 2) == 100);
assert((myPow(17.7, 5) - 1737266.04657) < 0.001)
