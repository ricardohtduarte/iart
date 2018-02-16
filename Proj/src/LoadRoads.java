import java.io.BufferedReader;
import java.io.FileReader;

public class LoadRoads {


    public LoadRoads(){}

    public void read_nodes(RoadNetwork roads){
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/nodes.txt"));
            while ((line = br.readLine()) != null) {
                roads.add_place(parse_line(line)[0], Double.parseDouble(parse_line(line)[1]), Double.parseDouble(parse_line(line)[2]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void read_edges(RoadNetwork roads){
        String line;
        int edgeId = 0;
        try{
            BufferedReader buff = new BufferedReader(new FileReader("src/edges.txt"));
            while ((line = buff.readLine()) != null) {
                roads.add_road(Integer.toString(edgeId), parse_line(line)[1], parse_line(line)[2]);
                edgeId++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String[] parse_line(String line){
        String[] split_line = line.split(";");
        return split_line;
    }
}
