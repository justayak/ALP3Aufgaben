package ab10.aufgabe1;

import java.io.Console;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 13.01.13
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */
public class ImplGraph implements Graph<Integer> {

    /**
     * Der Key betimmt jeden Knoten, die Liste in Values bestimmt alle Knoten, die mit dem "Key-Knoten" verbunden sind
     */
    private HashMap<Node<Integer>, ArrayList<Node<Integer>>> adjacencyList = new HashMap<Node<Integer>, ArrayList<Node<Integer>>>();

    @Override
    public Node[] nodes() {
        Set<Node<Integer>> set = this.adjacencyList.keySet();
        Node<Integer>[] result = set.toArray(new Node[0]);
        return result;
    }

    @Override
    public boolean adj(Node<Integer> v, Node<Integer> u)throws NodeNotFoundException {
        if(this.adjacencyList.containsKey(v) && this.adjacencyList.containsKey(u)){
            ArrayList<Node<Integer>> nodes = this.adjacencyList.get(v);
            for(int i = 0; i < nodes.size(); i++){
                if(nodes.get(i).value() == u.value()){
                    return true;
                }
            }
            return false;
        }else {
            throw new NodeNotFoundException();
        }


    }

    @Override
    public HashMap<Node<Integer>, ArrayList<Node<Integer>>> adjList(Node<Integer> v) {
        return this.adjacencyList;
    }

    @Override
    public Integer node(Node<Integer> v) throws NodeNotFoundException {
        if (this.adjacencyList.containsKey(v)){
            return v.value(); // seehr grundlegende Implementierung, darum so einfach
        }else {
            throw new NodeNotFoundException();
        }
    }

    @Override
    public Node<Integer> setNode(int value) {
        Node<Integer> node = new Node<Integer>(value);
        if(!this.adjacencyList.containsKey(node)){
            this.adjacencyList.put(node, new ArrayList<Node<Integer>>());
        }
        return node;
    }

    @Override
    public void setEdge(Node<Integer> v, Node<Integer> e) throws NodeNotFoundException {
        if(!this.adj(v,e)){
            ArrayList<Node<Integer>> one = this.adjacencyList.get(v);
            one.add(e);
            ArrayList<Node<Integer>> two = this.adjacencyList.get(e);
            one.add(v);
        }
    }

    @Override
    public void removeNode(Node<Integer> v) {
        this.adjacencyList.remove(v);
    }

    @Override
    public void removeEdge(Node<Integer> v, Node<Integer> w) {
        try {
            if(this.adj(v,w)){
                ArrayList<Node<Integer>> one = this.adjacencyList.get(v);
                one.remove(w);
                ArrayList<Node<Integer>> two = this.adjacencyList.get(w);
                one.remove(v);
            }
        } catch (NodeNotFoundException e) {
            // es geht einfach weiter...
        }
    }

    /**
     * Aufgabe b
     */
    @Override
    public void print() {
        String adjSymbol = "#";
        String notAdjSymbol = ".";
        System.out.println("=================================");
        System.out.println("Nodes are adjetant: " + adjSymbol);
        System.out.println("Nodes are not adjetant: " + notAdjSymbol);
        System.out.println("=================================");

        Node<Integer>[] keys = this.adjacencyList.keySet().toArray(new Node[0]);
        Arrays.sort(keys);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < keys.length; i++){
            Node<Integer> current  = keys[i];
            ArrayList<Node<Integer>> adj = this.adjacencyList.get(current);
            HashSet<Integer> adjPositions = this.getAdjPos(keys,adj);
            for(int j = 0; j < keys.length; j++){
                sb.append("\t");
                if(j==i){
                    sb.append(current.value());
                }else if (j > i){
                    if (adjPositions.contains(j)){
                        sb.append(adjSymbol);
                    }else {
                        sb.append(notAdjSymbol);
                    }
                }
            }
            sb.append("\r\n");
        }

        System.out.println(sb.toString());
    }

    private HashSet<Integer> getAdjPos(Node<Integer>[] sortedKeys, ArrayList<Node<Integer>> adj){
        HashSet<Integer> result = new HashSet<Integer>();
        for(Node<Integer> value : adj){
            result.add(Arrays.binarySearch(sortedKeys, value));
        }
        return result;
    }

    /**
     * Aufgabe b
     *
     * @param searchedValue
     * @return dfs-Graph
     */
    @Override
    public Graph<Integer> dfs(Integer searchedValue) {
        return null;
    }
}
