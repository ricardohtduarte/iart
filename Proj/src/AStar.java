import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.Collections;

public class AStar {
    private Graph graph;
    private String sourceNode;
    private String destNode;
    private Distance distance = new Distance();


    public AStar(Graph graph, String sourceNode, String destNode){
        this.graph = graph;
        this.sourceNode = sourceNode;
        this.destNode = destNode;

        ArrayList<Node> path = aStar(sourceNode, destNode);
        for(Node node: path){
            System.out.println(node.getId());
        }
    }

    public ArrayList<Node> aStar(String origin, String destination){
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closeList = new ArrayList<>();
        openList.add(graph.getNode(origin));

        while(!openList.isEmpty()){

            Node current_lowest = calc_lowest_score(openList);
            double current_lowest_g = current_lowest.getAttribute("G");
            openList.remove(current_lowest);
            closeList.add(current_lowest);

            if(current_lowest.getId() == destination){

                System.out.println("A* computation ended");
                ArrayList<Node> result = new ArrayList<>();
                Node current = current_lowest;

                while(current!=null){
                    Node parent = current.getAttribute("A*Parent");
                    result.add(parent);
                    current = parent;
                }
                Collections.reverse(result);
                return result;

            }
            else{
                ArrayList<Node> expanding_nodes = getAdjacentNodes(current_lowest);

                for(Node expand_node : expanding_nodes){

                    if(closeList.contains(expand_node)){
                        continue;
                    }

                    double expand_node_g = expand_node.getAttribute("G");
                    double new_expand_node_g = distance.calc_distance(graph, current_lowest.getId(), expand_node.getId()) + current_lowest_g;
                    double expand_node_h = distance.calc_distance(graph, expand_node.getId(), graph.getNode(destination).getId());
                    boolean isExpandNodeGbest = false;

                    if(!openList.contains(expand_node)){
                        isExpandNodeGbest = true;
                        expand_node.setAttribute("H", expand_node_h);
                        openList.add(expand_node);
                    }
                    else if(new_expand_node_g < expand_node_g){
                        isExpandNodeGbest = true;
                    }
                    if(isExpandNodeGbest){
                        expand_node.setAttribute("A*Parent", current_lowest);
                        computeF(expand_node, new_expand_node_g, expand_node_h);
                        System.out.println("Node: " + expand_node.getId() + " ExpandNodeG: " + new_expand_node_g + " ExpandNodeH: " + expand_node_h);
                    }
                }
            }
        }
        return null;
    }

    private void computeF(Node node, double G_attr, double H_attr){
        node.setAttribute("G", G_attr);
        node.setAttribute("H", H_attr);
        node.setAttribute("F", G_attr + H_attr);
    }

    private Node calc_lowest_score(ArrayList<Node> openlist){

        double lowest_score = 90000000;
        Node lowest = null;

        for(int i = 0; i < openlist.size(); i++){
            double current_score = openlist.get(i).getAttribute("F");
            if(current_score < lowest_score){
                lowest = openlist.get(i);
            }
            else continue;
        }
        return lowest;
    }

    private ArrayList<Node> getAdjacentNodes(Node node){
        ArrayList<Node> adjacents = new ArrayList<>();
        for(int i = 0; i < adjacents.size(); i++){
            adjacents.add(node);
        }
        return adjacents;
    }
}
