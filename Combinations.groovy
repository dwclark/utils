List chars = [ 'a', 'b', 'c', 'd' ]

static void _combine(List list, Closure closure, Set alreadySeen) {
    if(!alreadySeen.contains(list)) {
        closure.call(list);
        alreadySeen.add(list);
    }
    else {
        return;
    }

    for(int i = 0; i < list.size(); ++i) {
        List myList = new ArrayList(list.size() - 1);
        for(int copyIndex = 0; copyIndex < list.size(); ++copyIndex) {
            if(copyIndex != i) {
                myList << list[copyIndex]
            }
        }

        _combine(myList, closure, alreadySeen);
    }
}

static void combine(List list, Closure closure) {
    _combine(list, closure, new HashSet());
}

static void powerSet(List list, Closure closure) {
    int bits = list.size();
    int top = 2 ** bits;
    List vals = [];
    
    for(int outer = 0; outer < top; ++outer) {
        for(int inner = 0; inner < bits; ++inner) {
            if(((1 << inner) & outer) != 0) {
                vals << list[inner];
            }
        }
        
        closure.call(vals);
        vals.clear();
    }
}

def counter = 0;
def printIt = { List list -> println("${++counter}: ${list}"); }

println("*** Using recursion ***");
combine(chars, printIt);

println("*** Using bitmask iteration ***");
counter = 0;
powerSet(chars, printIt);
