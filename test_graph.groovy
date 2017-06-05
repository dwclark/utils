import groovy.transform.Immutable;

// def nodes = [ 0, 1, 2, 3, 4 ];


// def graph = new Graph(nodes);
// graph.addEdge(nodes[0], nodes[1]);
// graph.addEdge(nodes[0], nodes[2]);
// //graph.addEdge(nodes[1], nodes[2]);
// graph.addEdge(nodes[1], nodes[3]);
// graph.addEdge(nodes[3], nodes[4]);
// println(graph);

// println("Depth First Search")
// def walk = graph.depthFirstSearch(nodes[0]);
// println(walk);
// println("Root: ${walk.root}")
// println("Is Connected: ${graph.connected}");

// println("Breadth First Search")
// walk = graph.breadthFirstSearch(nodes[0]);
// println(walk);
// println("Path from 0 -> 4: ${walk.pathTo(nodes[4])}");
// println("Path from 0 -> 2: ${walk.pathTo(nodes[2])}");

// def letters = [ 'A', 'B', 'C', 'D', 'E' ];
// def disconnected = new Graph(letters);
// disconnected.addEdge(letters[0], letters[1])
// disconnected.addEdge(letters[1], letters[2]);
// disconnected.addEdge(letters[3], letters[4]);
// println("Is Connected: ${disconnected.connected}");
// disconnected.addEdge(letters[2], letters[3]);
// println("Is Connected: ${disconnected.connected}");

def weightedNodes = [ 0, 1, 2, 3 ];
def weightedGraph = new Graph(weightedNodes);
weightedGraph.addEdge(weightedNodes[0], weightedNodes[1], 1)
weightedGraph.addEdge(weightedNodes[0], weightedNodes[2], 10);
weightedGraph.addEdge(weightedNodes[1], weightedNodes[3], 1);
weightedGraph.addEdge(weightedNodes[2], weightedNodes[3], 1);

def paths = weightedGraph.dijkstra(weightedNodes[0]);
// println(paths.distances);
// println(paths.predecessors);
// println(paths.distanceTo(weightedNodes[3]));
// println(paths.pathTo(weightedNodes[3]));

def wnodes = [ 0, 1, 2, 3, 4, 5, 6 ];
def biggerWeighted = new Graph(wnodes);
biggerWeighted.addEdge(wnodes[0], wnodes[1], 4)
biggerWeighted.addEdge(wnodes[0], wnodes[2], 7)
biggerWeighted.addEdge(wnodes[0], wnodes[3], 2)
biggerWeighted.addEdge(wnodes[1], wnodes[6], 4)
biggerWeighted.addEdge(wnodes[2], wnodes[4], 1)
biggerWeighted.addEdge(wnodes[2], wnodes[5], 1)
biggerWeighted.addEdge(wnodes[3], wnodes[4], 3)
biggerWeighted.addEdge(wnodes[4], wnodes[6], 4)
biggerWeighted.addEdge(wnodes[5], wnodes[6], 3)

def biggerPaths = biggerWeighted.dijkstra(0);
println("Path to 6: ${biggerPaths.pathTo(6)}; distance: ${biggerPaths.distanceTo(6)}");
println("Path to 4: ${biggerPaths.pathTo(4)}; distance: ${biggerPaths.distanceTo(4)}");
