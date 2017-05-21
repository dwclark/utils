import groovy.transform.Immutable;

def nodes = [ 0, 1, 2, 3, 4 ];


def graph = new Graph(nodes);
graph.addEdge(nodes[0], nodes[1]);
graph.addEdge(nodes[0], nodes[2]);
//graph.addEdge(nodes[1], nodes[2]);
graph.addEdge(nodes[1], nodes[3]);
graph.addEdge(nodes[3], nodes[4]);
println(graph);

println("Depth First Search")
def walk = graph.depthFirstSearch(nodes[0]);
println(walk);
println("Root: ${walk.root}")
println("Is Connected: ${graph.connected}");

println("Breadth First Search")
walk = graph.breadthFirstSearch(nodes[0]);
println(walk);
println("Path from 0 -> 4: ${walk.pathTo(nodes[4])}");
println("Path from 0 -> 2: ${walk.pathTo(nodes[2])}");

def letters = [ 'A', 'B', 'C', 'D', 'E' ];
def disconnected = new Graph(letters);
disconnected.addEdge(letters[0], letters[1])
disconnected.addEdge(letters[1], letters[2]);
disconnected.addEdge(letters[3], letters[4]);
println("Is Connected: ${disconnected.connected}");
disconnected.addEdge(letters[2], letters[3]);
println("Is Connected: ${disconnected.connected}");

