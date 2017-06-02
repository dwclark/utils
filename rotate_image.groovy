static void rotate(int[][] matrix) {
    final int len = matrix.length;
    int[][] copy = new int[len][];
    for(int i = 0; i < len; ++i) {
        copy[i] = new int[len];
    }

    int col = len - 1;
    for(int i = 0; i < len; ++i) {
        int[] row = matrix[i];
        for(int j = 0; j < len; ++j) {
            copy[j][col] = row[j];
        }

        --col;
    }

    for(int i = 0; i < len; ++i) {
        System.arraycopy(copy, 0, matrix, 0, len);
    }
}


int[][] m = [ [ 1, 2, 3 ],
              [ 4, 5, 6 ],
              [ 7, 8, 9 ] ] as int[][];

rotate(m);
println(m);

int[][] m2 = [ [ 1, 2, 3, 4 ],
               [ 5, 6, 7, 8 ],
               [ 9, 10, 11, 12 ],
               [ 13, 14, 15, 16 ] ] as int[][];

rotate(m2);
println(m2)

//can you do this in place
//yes, but not in a short amount of time
