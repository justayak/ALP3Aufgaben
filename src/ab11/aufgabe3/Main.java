package ab11.aufgabe3;

import common.Edge;
import common.Node;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 23.01.13
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args){
        Node[] nodes = new Node[12];
        Node a = new Node(0,"a"); addNode(nodes,a);
        Node b = new Node(1, "b"); addNode(nodes,b);
        Node c = new Node(2, "c"); addNode(nodes,c);
        Node d = new Node(3, "d"); addNode(nodes,d);
        Node e = new Node(4, "e"); addNode(nodes,e);
        Node f = new Node(5, "f"); addNode(nodes,f);
        Node g = new Node(6, "g"); addNode(nodes,g);
        Node h = new Node(7, "h"); addNode(nodes,h);
        Node i = new Node(8, "i"); addNode(nodes,i);
        Node j = new Node(9, "j"); addNode(nodes,j);
        Node k = new Node(10, "k"); addNode(nodes,k);
        Node l = new Node(11, "l"); addNode(nodes,l);

        Edge[] edges = new Edge[]{
                new Edge(a,b,1),
                new Edge(a,c,5),
                new Edge(b,e,3),
                new Edge(c,f,1),
                new Edge(e,f,1),
                new Edge(b,d,5),
                new Edge(d,l,2),
                new Edge(l,h,3),
                new Edge(l,i,5),
                new Edge(h,i,1),
                new Edge(f,g,3),
                new Edge(g,j,1),
                new Edge(g,k,2),
                new Edge(j,k,6),
                new Edge(i,j,1),
        };

        FloydWarshall alg = new FloydWarshall(nodes,edges);
        List<Node> shortestPath = alg.getShortestPath(a,h);
        for(Node node : shortestPath){
            System.out.println(" ==> " + node.content());
        }

        /*
        ====================================
        Gegebener Baum:
        ====================================

                    a
                1/     \5
                b       c
           5/  3|       |1
           d    e---1---f
           2|          3|
            l           g
         3/  5\       1/ 2\
         h--1--i---1--j-6--k

        =====================================
        ERGEBNIS von (a) nach (h):
        =====================================
         ==> a
         ==> b
         ==> e
         ==> f
         ==> g
         ==> j
         ==> i
         ==> h

         */

    }

    private static void addNode(Node[] list, Node node){
        list[node.key] = node;
    }

}
