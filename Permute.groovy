Object[] chars = [ 'a', 'b', 'c', 'd', 'e', 'f' ]

static long factorial(int length) {
    long ret = 1;
    for(int i = 1; i <= length; ++i) {
        ret *= i;
    }

    return ret;
}

static void _rotate(final Object[] ary, int start, int end) {
    Object last = ary[end - 1];
    for(int i = end - 2; i >= start; --i) {
        ary[i+1] = ary[i];
    }

    ary[start] = last;
}

static void _permute(final Object[] ary, int start, int end, Closure process) {
    if(end - start == 1) {
        process(ary);
        return;
    }

    for(int i = start; i < end; ++i) {
        _permute(ary, start + 1, end, process);
        _rotate(ary, start, end);
    }
}

static void permute(final Object[] ary, Closure process) {
    _permute(ary, 0, ary.length, process);
}

def set = new HashSet();
def addSet = { Object[] args -> set.add(args.join()); };
def counter = 0;
def printThem = { Object[] args -> println("${++counter}: ${args}"); }

permute(chars, addSet);
assert(set.size() == factorial(chars.length));
