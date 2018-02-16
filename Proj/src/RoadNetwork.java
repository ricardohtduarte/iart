import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;

public class RoadNetwork {
    private Graph graph = new SingleGraph("RoadNetWork");
    private Distance distance = new Distance();

    public RoadNetwork() {
        LoadRoads roads = new LoadRoads();
        roads.read_nodes(this);
        roads.read_edges(this);
       // AStar astar = new AStar(graph, "3", "10");
        System.out.println("Node: " + graph.getNode("3").getId());
        graph.display();
    }

    public Graph get_graph(){return graph;}

    public void add_place(String id, double lat, double longt){
        Node a = graph.addNode(id);
        a.addAttribute("ui.label", id);
        a.addAttribute("Latitude", lat);
        a.addAttribute("Longitude", longt);
        a.addAttribute("G", 0.0);
        a.addAttribute("H", 0.0);
        a.addAttribute("F", 0.0);
    }

    public void add_road(String id, String sourceId, String destId){
        Edge edge = graph.addEdge(id, sourceId, destId);
        edge.addAttribute("Distance", distance.calc_distance(graph, sourceId, destId));
    }

    public void show_nodes() {
        for (Node node : graph) {
            System.out.println("Node: " + node.getId() + " Lat: " + node.getAttribute("Latitude") + " Long: " + node.getAttribute("Longitude"));

            for (Edge edge : node) {
                System.out.println("Edge: " + edge.getId() + " Cost: " + edge.getAttribute("Cost"));

            }
            System.out.println("");
        }
    }
}

