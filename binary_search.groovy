static int binarySearch(final Object[] ary, final Object lookFor) {
    int start = 0;
    int end = ary.length;
    int index = 0;
    
    while(start < end) {
        index = start + ((end - start) >>> 1);
        int cmp = lookFor <=> ary[index];
        if(cmp < 0) {
            end = index;
        }
        else if(cmp > 0) {
            start = index + 1;
        }
        else {
            return index;
        }
    }

    return -(index + 1);
}

(0..6).each { idx ->
    println("binarySearch ${idx}: " + binarySearch([0] as Object[], idx));
}

(0..6).each { idx ->
    println("binarySearch ${idx}: " + binarySearch([0,1] as Object[], idx));
}

(0..6).each { idx ->
    println("binarySearch ${idx}: " + binarySearch([0,1,2] as Object[], idx));
}

(0..17).each { idx ->
    println("binarySearch ${idx}: " + binarySearch((0..17) as Object[], idx));
}

(0..98).each { idx ->
    println("binarySearch ${idx}: " + binarySearch((0..95) as Object[], idx));
}




