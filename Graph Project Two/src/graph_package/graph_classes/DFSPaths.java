package graph_package.graph_classes;


/**
 * Created by Mateusz on 18.03.2017.
 */
public class DFSPaths {
    private int[] edgeTo;
    // tablica wierzchołków już odwiedzonych
    private boolean[] marked;
    // wierzchołek startowy
    private int startedVertex;
    // czy jest to cykl Hamiltona
    private boolean isHamilton;


    public DFSPaths(Graph graph, int startedVertex) {
        isHamilton = false;
        this.startedVertex = startedVertex;
        edgeTo = new int[graph.getNodeGraphLength()];
        marked = new boolean[graph.getNodeGraphLength()];
        graph.generateNodeArray();
        dfs_recursive(graph, startedVertex);
    }

    private void dfs_recursive(Graph graph, int vertex) {
        // oznaczamy wierzchołek, jako oznaczony
        System.out.println("vertex: " + vertex);
        marked[vertex] = true;
        //Pobieramy wierzchołek
        GraphNode node = graph.getGraphNode(vertex);

        // odwiedzamy każdy sąsiedni nieodwiedzony wierzchołek i zapisujemy trase
        for (GraphNode eachVertex : node.getConnectionList()) {
            // sprawdzamy czy sąsiadem naszego wierzchołka jest punkt startowy,
            // oraz czy wszystkie wierzchołki są już odwiedzone
            if (eachVertex.getId() == startedVertex && isAllMarked()){
                isHamilton = true;
                System.out.println(":: " + eachVertex.getId());
                return;
            }

            System.out.println("vertex: "  + vertex + ": m["+eachVertex.getId() + "] = " +marked[eachVertex.getId()]);

            //jeżeli nieodwiedzony
            if (!marked[eachVertex.getId()]) {
                edgeTo[eachVertex.getId()] = vertex;
                dfs_recursive(graph, eachVertex.getId());
            }
            System.out.println("::: "+eachVertex.getId());

//            marked[eachVertex.getId()] = false;

        }
    }

    // Sprawdza, czy wszystkie węzły zostały odwiedzone
    private boolean isAllMarked() {
        for (boolean check : marked) {
            if (!check)
                return false;
        }
        return true;
    }

    public boolean isHamiltonianGraph() {
        return isHamilton;
    }
}
