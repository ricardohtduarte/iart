import org.graphstream.graph.Graph;

public class Distance {

    public Distance(){}

    public double calc_distance(Graph graph, String node1, String node2){

        double lat1 = graph.getNode(node1).getAttribute("Latitude");
        double long1 = graph.getNode(node1).getAttribute("Longitude");

        double lat2 = graph.getNode(node1).getAttribute("Latitude");
        double long2 = graph.getNode(node2).getAttribute("Longitude");

        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);

        int r = 6371000;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(long2-long1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(radLat1) * Math.cos(radLat2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = r*c;
        return distance;

    }
}
