static void _cartesian(List<List> lists, final int listsIndex, final int[] subIndexes, final Closure closure) {

    List list = lists[listsIndex];
    
    if(listsIndex + 1 == lists.size()) {
        for(int i = 0; i < list.size(); ++i) {
            subIndexes[listsIndex] = i;
            closure.call(lists, subIndexes);
        }
    }
    else {
        for(int i = 0; i < list.size(); ++i) {
            subIndexes[listsIndex] = i;
            _cartesian(lists, listsIndex + 1, subIndexes, closure);
        }
    }
}

static void cartesian(List<List> lists, final Closure closure) {
    _cartesian(lists, 0, new int[lists.size()], closure);
}

static int expectedSize(List<List> lists) {
    int ret = 1;
    lists.each { List list -> ret *= list.size(); };
    return ret;
}

int counter = 0;
def printIt = { List<List> lists, int[] indexes ->
    List list = [];
    for(int i = 0; i < indexes.length; ++i) {
        list.add(lists[i][indexes[i]]);
    }

    println("${++counter}: ${list}");
}

List<List> listOfLists = [ [ 1, 2, 3 ],
                           [ 4, 5, 6, 7 ],
                           [ 8, 9, 10 ] ];

cartesian(listOfLists, printIt);
assert(expectedSize(listOfLists) == counter);
