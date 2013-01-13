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
        Node<Integer> node8 = graph.setNode(8);
        Node<Integer> node9 = graph.setNode(9);
        Node<Integer> node10 = graph.setNode(10);
        Node<Integer> node11 = graph.setNode(11);
        Node<Integer> node12 = graph.setNode(12);
        Node<Integer> node13 = graph.setNode(13);
        Node<Integer> node14 = graph.setNode(14);


        graph.setEdge(node1, node2);
        graph.setEdge(node2, node3);
        graph.setEdge(node2, node4);
        graph.setEdge(node3, node5);
        graph.setEdge(node3, node6);
        graph.setEdge(node4, node7);
        graph.setEdge(node4, node8);
        graph.setEdge(node5, node9);
        graph.setEdge(node6, node10);
        graph.setEdge(node6, node11);
        graph.setEdge(node11, node12);
        graph.setEdge(node11, node13);
        graph.setEdge(node13, node14);

        graph.setEdge(node1,node14);  // RING

        graph.print();

        graph.save("aufgabe10");
        graph.load("aufgabe10");

        graph.print();

        Graph<Integer> dfs = graph.dfs(node1,node14);
        System.out.println("Searching for node1 -> node14");
        dfs.print();
        dfs = graph.dfs(node1,node5);
        System.out.println("Searching for node1 -> node5");
        dfs.print();
        dfs = graph.dfs(node1,node11);
        System.out.println("Searching for node1 -> node11");
        dfs.print();
        dfs = graph.dfs(node1,node14);
        System.out.println("Searching for node1 -> node14");
        dfs.print();

/*  R E S U L T

=================================
Nodes are adjetant: #
Nodes are not adjetant: .
=================================
	1	#	.	.	.	.	.	.	.	.	.	.	.	#
		2	#	#	.	.	.	.	.	.	.	.	.	.
			3	.	#	#	.	.	.	.	.	.	.	.
				4	.	.	#	#	.	.	.	.	.	.
					5	.	.	.	#	.	.	.	.	.
						6	.	.	.	#	#	.	.	.
							7	.	.	.	.	.	.	.
								8	.	.	.	.	.	.
									9	.	.	.	.	.
										10	.	.	.	.
											11	#	#	.
												12	.	.
													13	#
														14

=================================
Nodes are adjetant: #
Nodes are not adjetant: .
=================================
	1	#	.	.	.	.	.	.	.	.	.	.	.	#
		2	#	#	.	.	.	.	.	.	.	.	.	.
			3	.	#	#	.	.	.	.	.	.	.	.
				4	.	.	#	#	.	.	.	.	.	.
					5	.	.	.	#	.	.	.	.	.
						6	.	.	.	#	#	.	.	.
							7	.	.	.	.	.	.	.
								8	.	.	.	.	.	.
									9	.	.	.	.	.
										10	.	.	.	.
											11	#	#	.
												12	.	.
													13	#
														14

Searching for node1 -> node14
=================================
Nodes are adjetant: #
Nodes are not adjetant: .
=================================
	1	#	.	.	.	.	.	.	.	.	.
		2	#	.	.	.	.	.	.	.	.
			3	#	#	.	.	.	.	.	.
				5	.	#	.	.	.	.	.
					6	.	#	#	.	.	.
						9	.	.	.	.	.
							10	.	.	.	.
								11	#	#	.
									12	.	.
										13	#
											14

Searching for node1 -> node5
=================================
Nodes are adjetant: #
Nodes are not adjetant: .
=================================
	1	#	.	.
		2	#	.
			3	#
				5

Searching for node1 -> node11
=================================
Nodes are adjetant: #
Nodes are not adjetant: .
=================================
	1	#	.	.	.	.	.	.
		2	#	.	.	.	.	.
			3	#	#	.	.	.
				5	.	#	.	.
					6	.	#	#
						9	.	.
							10	.
								11

Searching for node1 -> node14
=================================
Nodes are adjetant: #
Nodes are not adjetant: .
=================================
	1	#	.	.	.	.	.	.	.	.	.
		2	#	.	.	.	.	.	.	.	.
			3	#	#	.	.	.	.	.	.
				5	.	#	.	.	.	.	.
					6	.	#	#	.	.	.
						9	.	.	.	.	.
							10	.	.	.	.
								11	#	#	.
									12	.	.
										13	#
											14

 */
    }

}
