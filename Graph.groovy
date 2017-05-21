import groovy.transform.TupleConstructor;

class Graph<T> {
    
    private static class Edge<V> {
        final V vertex;
        final int weight;
        
        Edge(final V vertex, final int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        Edge(final V vertex) {
            this(vertex, 0);
        }

        public boolean equals(final Object o) {
            Edge e = (Edge<V>) o;
            return this.vertex == e.vertex;
        }

        public int hashCode() {
            return vertex.hashCode();
        }
    }

    final Map<T,Set<Edge<T>>> nodes;

    Graph(final List<T> all) {
        Map<T,Set<Edge<T>>> tmp = new LinkedHashMap<>(all.size());
        all.each { T n -> tmp[n] = new HashSet<>(); };
        nodes = tmp.asImmutable();
    }

    public void addEdge(final T u, final T v, final int weight) {
        Edge<T> edge = new Edge<>(v, weight);
        Set<Edge<T>> set = nodes[u];
        if(!set.contains(edge)) {
            set.add(edge);
        }
    }

    public void addEdge(final T u, final T v) {
        addEdge(u, v, 0);
        addEdge(v, u, 0);
    }

    private boolean _removeEdge(final T u, final T v) {
        Edge<T> edge = new Edge<>(v);
        Set<Edge<T>> set = nodes[u];
        set.remove(new Edge<>(v));
        return false;
    }
    
    public boolean removeEdge(final T u, final T v) {
        boolean ret = _removeEdge(u, v);
        _removeEdge(v, u);
        return ret;
    }

    public int edgeWeight(final T u, final T v) {
        Edge<T> found = nodes[u]?.find { Edge<T> e -> e.vertex == v; };
        return found ? found.weight : 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        nodes.each { T n, Set<Edge<T>> edges ->
            sb.append(n).append(' - (');
            sb.append(edges.collect { Edge<T> e -> e.vertex; }.join(', '));
            sb.append(')\n'); };
        return sb.toString();
    }

    enum VertexColor { GREY, BLACK };
    enum EdgeType { CROSS, TREE, BACKWARD, FORWARD };

    @TupleConstructor
    static class EdgeLabel<V> {
        final V u;
        final V v;
        final EdgeType type;
    }
    
    static class DfsStep<V> {
        V vertex;
        V predecessor;
        int discovered = -1;
        int finished = -1;
    }
    
    public static class DfsWalk<V> {
        int counter = 0;
        
        Map<V,VertexColor> colors = [:];
        Map<V,DfsStep<V>> steps = new LinkedHashMap<>();
        List<EdgeLabel<V>> labels = [];

        V getRoot() {
            return steps.keySet().iterator().next();
        }

        boolean connectedTo(final V vertex) {
            return steps.keySet().contains(vertex);
        }
        
        DfsStep startVisit(final V current, final V predecessor) {
            colors[current] = VertexColor.GREY;
            DfsStep<V> newStep = new DfsStep(vertex: current, predecessor: predecessor, discovered: ++counter);
            steps[current] = newStep;
            return newStep;
        }

        void finish(final DfsStep<V> step) {
            step.finished = ++counter;
            colors[step.vertex] = VertexColor.BLACK;
        }

        boolean hasColor(final V n) {
            return colors.containsKey(n);
        }
        
        void addEdgeLabel(final V u, final V v) {
            EdgeType type = EdgeType.CROSS;
            if(!colors.containsKey(v)) {
                type = EdgeType.TREE;
            }
            else if(colors[v] == VertexColor.GREY) {
                type = EdgeType.BACKWARD;
            }
            else if(steps.containsKey(v) && (steps[u].discovered < steps[v].discovered)) {
                type = EdgeType.FORWARD;
            }
            
            labels += new EdgeLabel(u: u, v: v, type: type);
        }

        @Override
        String toString() {
            StringBuilder sb = new StringBuilder();
            steps.each { V v, DfsStep<V> step ->
                sb.append(v).append(':');
                sb.append(' p: ').append(step.predecessor);
                sb.append(' d: ').append(step.discovered);
                sb.append(' f: ').append(step.finished);
                sb.append('\n'); };
            return sb.toString();
        }
    }

    private void dfsVisit(final DfsWalk<T> walk, final DfsStep<T> step) {
        T u = step.vertex;
        nodes[u].each { Edge<T> e ->
            T v = e.vertex;
            walk.addEdgeLabel(u, v);
            
            if(!walk.hasColor(v)) {
                dfsVisit(walk, walk.startVisit(v, u));
            }
        };

        walk.finish(step);
    }

    public DfsWalk<T> depthFirstSearch(final T s) {
        DfsWalk<T> walk = new DfsWalk<>();
        dfsVisit(walk, walk.startVisit(s, null));
        return walk;
    }

    public List<DfsWalk<T>> depthFirstSearches() {
        List<DfsWalk<T>> walks = [];
        nodes.each { T node, Set<Edge<T>> edges ->
            DfsWalk<T> walk = walks.find { DfsWalk w -> w.hasColor(node); };
            if(!walk) {
                walks += depthFirstSearch(node);
            } };

        return walks;
    }

    public boolean isConnected() {
        return depthFirstSearches().size() == 1;
    }

    public static class BfsStep<V> {
        V vertex;
        V predecessor;
        int distance;

        static final BfsStep<V> FIRST = new BfsStep<>(vertex: null, predecessor: null, distance: -1);
    }

    public static class BfsWalk<V> {
        Queue<BfsStep<V>> queue = new LinkedList<>();
        Map<V,VertexColor> colors = [:];
        Map<V,BfsStep<V>> steps = new LinkedHashMap<>();
        int distance = 0;

        void enqueue(final V s, final BfsStep<V> previous) {
            colors[s] = VertexColor.GREY;
            BfsStep<V> step = new BfsStep(vertex: s, predecessor: previous.vertex, distance: previous.distance + 1);
            steps[s] = step;
            queue.add(step);
        }

        void dequeue() {
            V v = queue.poll();
            colors[v] = VertexColor.BLACK;
        }

        BfsStep<V> head() {
            queue.peek();
        }

        boolean isEmpty() {
            return queue.empty;
        }

        boolean hasColor(final V v) {
            return colors.containsKey(v);
        }

        V getRoot() {
            return steps.keySet().iterator().next();
        }

        List<V> pathTo(V destination) {
            BfsStep<V> step = steps[destination];
            if(!step) {
                return Collections.emptyList();
            }

            V theRoot = root;;
            List<V> path = [ destination ];
            while(step.vertex != theRoot) {
                step = steps[step.predecessor];
                path << step.vertex;
            }

            return path.reverse();
        }

        @Override
        String toString() {
            StringBuilder sb = new StringBuilder();
            steps.each { V v, BfsStep<V> step ->
                sb.append(v).append(':');
                sb.append(' p: ').append(step.predecessor);
                sb.append(' d: ').append(step.distance).append('\n'); };

            return sb.toString();
        }
    }

    public BfsWalk<T> breadthFirstSearch(final T n) {
        BfsWalk<T> walk = new BfsWalk<>();
        walk.enqueue(n, BfsStep.FIRST);
        while(!walk.empty) {
            BfsStep<T> step = walk.head();
            nodes[step.vertex]?.each { Edge<T> neighbor ->
                if(!walk.hasColor(neighbor.vertex)) {
                    walk.enqueue(neighbor.vertex, step);
                } };
            walk.dequeue();
        }

        return walk;
    }

    public List<BfsWalk<T>> breadthFirstSearches() {
        List<BfsWalk<T>> walks = [];
        nodes.each { T node, Set<Edge<T>> edges ->
            BfsWalk<T> walk = walks.find { BfsWalk w -> w.hasColor(node); };
            if(!walk) {
                walks += breadthFirstSearch(node);
            } };

        return walks;
    }
}

