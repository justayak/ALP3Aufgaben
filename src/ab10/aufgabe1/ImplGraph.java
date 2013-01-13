package ab10.aufgabe1;

import com.sun.javafx.scene.layout.region.Margins;
import common.Helper;

import java.io.Console;
import java.io.IOException;
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
            if (e.value() != v.value()){ // Sonst würde bei einem "Selbstverweis" der Eintrag doppelt gesetzt werden!
                ArrayList<Node<Integer>> two = this.adjacencyList.get(e);
                two.add(v);
            }
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
                    if (adjPositions.contains(j)){ // Bei "Selbstverweis"
                        sb.append(adjSymbol);
                    }
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

    /**
     * Aufgabe b
     */
    @Override
    public void save(String fileName) {
        StringBuilder sb = new StringBuilder();
        if(this.adjacencyList.size() > 0){
            Node<Integer>[] keys = this.adjacencyList.keySet().toArray(new Node[0]);
            Arrays.sort(keys);
            for(int i = 0; i < keys.length; i++){
                Node<Integer> current  = keys[i];
                ArrayList<Node<Integer>> adj = this.adjacencyList.get(current);
                for(Node<Integer> adjElement : adj){
                    if (adjElement.value() >= current.value()){
                        sb.append(current.value());
                        sb.append("-");
                        sb.append(adjElement.value());
                        sb.append("\r\n");
                    }
                }
            }
        }
        Helper.writeText(fileName,sb.toString());
    }

    /**
     * Aufgabe b
     */
    @Override
    public void load(String fileName) {
        try {
            String content = Helper.loadText(fileName);
            String[] lines = content.split("\r\n");
            ImplGraph newGraph = new ImplGraph();
            for(String line : lines){
                String[] edge = line.split("-");
                Node<Integer> a = newGraph.setNode(Integer.parseInt(edge[0]));
                Node<Integer> b = newGraph.setNode(Integer.parseInt(edge[1]));
                newGraph.setEdge(a,b);
            }
            this.adjacencyList = newGraph.adjacencyList;
        } catch (IOException e) {
            System.out.println("Ladefehler... fuck!");
        } catch (Exception e) {
            System.out.println("AnyFehler... fuck!");
        }
    }

    /**
     * Aufgabe b
     */
    @Override
    public Graph<Integer> dfs(Node<Integer> start, Node<Integer> goal) {
        if (start.value() == goal.value()){

        }



        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void dfsRec(Node<Integer> start, Node<Integer> goal, Graph<Integer> accumulator){

    }

    private HashSet<Integer> getAdjPos(Node<Integer>[] sortedKeys, ArrayList<Node<Integer>> adj){
        HashSet<Integer> result = new HashSet<Integer>();
        for(Node<Integer> value : adj){
            result.add(Arrays.binarySearch(sortedKeys, value));
        }
        return result;
    }
}
