package ab11.aufgabe3;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 23.01.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public interface IGraph {

    /**
     * Adds a new node if the key doesnt exist or manipulate the given on
     * @param key
     * @param value
     */
    void node(int key, String value);
    void edge(int nodeA, int nodeB, double weight);
    double shortestPathAsWeight(int startNode, int endNode);
    int[] shortestPathAsNodes(int startNode, int endNode);
    void print();
}
