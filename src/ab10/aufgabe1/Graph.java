package ab10.aufgabe1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 10.01.13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public interface Graph<N extends Comparable<N>> extends Serializable {
    Node[] nodes();
    boolean adj(Node<N> v, Node<N> u)
            throws NodeNotFoundException;
    HashMap<Node<N>, ArrayList<Node<N>>> adjList(Node<N> v);
    N node(Node<N> v)
            throws NodeNotFoundException;

    /**
     * vereinfacht, da in diesem einfachen Beispiel der Node NUR durch seinen Value bestimmt wird.
     * @param value
     */
    Node<N> setNode(int value);
    void setEdge(Node<N> v, Node<N> e)
            throws NodeNotFoundException;
    void removeNode(Node<N> v);
    void removeEdge(Node<N> v, Node<N> w);

    /**
     * Aufgabe b
     */
    void print();

    /**
     * Aufgabe b
     * @param searchedValue
     * @return dfs-Graph
     */
    Graph<N> dfs(N searchedValue);
}
