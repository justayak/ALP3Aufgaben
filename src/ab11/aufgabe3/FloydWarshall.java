package ab11.aufgabe3;

import common.Edge;
import common.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 24.01.13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class FloydWarshall {

    private  static float[][] D;
    private  static Node[][] P;

    public FloydWarshall(Node[] nodes, Edge[] edges){
        this.init(nodes,edges);
    }

    public List<Node> shortestPath(Node source, Node target){
        if(D[source.key][target.key] == Float.POSITIVE_INFINITY){
            return new ArrayList<Node>();
        }
        List<Node> path = getIntermediatePath(source, target);
        path.add(0, source);
        path.add(target);
        return path;
    }

    private void init(Node[] nodes, Edge[] edges) {
        D = initializeWeight(nodes, edges);
        P = new Node[nodes.length][nodes.length];

        for(int k=0; k<nodes.length; k++){
            for(int i=0; i<nodes.length; i++){
                for(int j=0; j<nodes.length; j++){
                    if(D[i][k] != Float.POSITIVE_INFINITY && D[k][j] != Float.POSITIVE_INFINITY && D[i][k]+D[k][j] < D[i][j]){
                        D[i][j] = D[i][k]+D[k][j];
                        P[i][j] = nodes[k];
                    }
                }
            }
        }
    }

    private List<Node> getIntermediatePath(Node source, Node target){
        if(P[source.key][target.key] == null){
            return new ArrayList<Node>();
        }
        List<Node> path = new ArrayList<Node>();
        path.addAll(getIntermediatePath(source, P[source.key][target.key]));
        path.add(P[source.key][target.key]);
        path.addAll(getIntermediatePath(P[source.key][target.key], target));
        return path;
    }

    private float[][] initializeWeight(Node[] nodes, Edge[] edges){
        float[][] Weight = new float[nodes.length][nodes.length];
        for(int i=0; i<nodes.length; i++){
            Arrays.fill(Weight[i], Float.POSITIVE_INFINITY);
        }
        for(Edge e : edges){
            Weight[e.from.key][e.to.key] = e.weight();
            Weight[e.to.key][e.from.key] = e.weight();
        }
        return Weight;
    }
}
