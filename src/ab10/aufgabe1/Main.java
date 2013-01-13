package ab10.aufgabe1;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 10.01.13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[]args) throws NodeNotFoundException{

        ImplGraph graph = new ImplGraph();

        Node<Integer> node1 = graph.setNode(1);
        Node<Integer> node2 = graph.setNode(2);
        Node<Integer> node3 = graph.setNode(3);
        Node<Integer> node4 = graph.setNode(4);
        Node<Integer> node5 = graph.setNode(5);
        Node<Integer> node6 = graph.setNode(6);
        Node<Integer> node7 = graph.setNode(7);

        graph.setEdge(node1, node2);
        graph.setEdge(node1, node3);
        graph.setEdge(node2, node4);
        graph.setEdge(node2, node5);
        graph.setEdge(node3, node6);
        graph.setEdge(node3, node7);

        graph.print();

    }

}
