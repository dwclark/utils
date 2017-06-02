def list = [ 2, 7, 11, 15 ]
def target = 22;

def findSolution = { 
    for(int outer = 0; outer < list.size(); ++outer) {
        for(int inner = outer + 1; inner < list.size(); ++inner) {
            if(list[outer] + list[inner] == target) {
                return [ outer, inner ];
            }
        }
    }
}

println(findSolution());
