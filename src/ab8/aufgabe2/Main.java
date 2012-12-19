package ab8.aufgabe2;

import common.Helper;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 16.12.12
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Text gewichten..
        String text = Helper.loadText("EinWinterMaerchen");

        // Aufgabe 2(a)
        String result = huffmanify(text);
        System.out.println(result);
        System.out.println(result.length() + " > Huffman-Bitcount");
        int asciiSize = text.length() * 8;
        System.out.println(asciiSize + " > 8Bit-Ascii-Bitcount");

        // Aufgabe 2(b)
        // Kompressionsfaktor:
        float single = asciiSize / 100.0f;
        int komressionsfaktor = Math.round(result.length() / single);
        System.out.println("Kompressionsfaktor: " + komressionsfaktor + "%");

        // Ergebnis:
//        450409 > Huffman-Bitcount
//        743272 > 8Bit-Ascii-Bitcount
//        Kompressionsfaktor: 61%

        // Aufgabe 2(c)
//        776104 > Huffman-Bitcount
//        743272 > 8Bit-Ascii-Bitcount
//        Kompressionsfaktor: 104%
        /*

        Wenn man Paare aufeinanderfolgender Zeichen mit dem Huffman-Code codiert, dann verschlechtert sich die
        Kompression im Vergleich zu ASCII-8-Bit sogar!

         */


    }

    private static int CHARCOUNT_HUFFMAN = 2;

    /**
     *
     * @param text
     * @return
     */
    public static String huffmanify(String text){
        Tokens tokens = new Tokens();
        for(int i = 0; i < text.length(); i++){
            String token = "";
            if (i+CHARCOUNT_HUFFMAN >= text.length()){
                token = text.substring(i, i+(text.length()-i));
            }else {
                token = text.substring(i,i+CHARCOUNT_HUFFMAN);
            }
            tokens.addToken(token);
        }

        // Huffman-Tree bauen..
        PriorityQueue<Node> leafs = tokens.getLeafs();
        Node[] savedLeafs = leafs.toArray(new Node[0]);
        while (leafs.size()>1){
            Node left = leafs.poll();
            Node right = leafs.poll();
            Node parent = new Node(left,right);
            leafs.add(parent);
        };

        HashMap<String, String> dict = generateDictionary(savedLeafs);

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            String binary;
            if (i+CHARCOUNT_HUFFMAN >= text.length()){
                binary = dict.get(text.substring(i,i+(text.length()-i)));
            }else {
                binary = dict.get(text.substring(i, i+CHARCOUNT_HUFFMAN));
            }
            builder.append(binary);
        }

        return builder.toString();
    }

    /**
     * @param leafs
     * @return Das Wörterbuch mit allen Tokens und ihrer "binären" Schreibweise
     */
    private static HashMap<String, String> generateDictionary(Node[] leafs){
        HashMap<String, String> result = new HashMap<String, String>();
        for(Node leaf : leafs){
            String binary = "";
            Node current = leaf;
            while (!current.isRoot()){
                binary += current.Binary;
                current = current.Parent;
            }
            result.put(leaf.Token,binary);
        }
        return result;
    }

    /**
     *
     */
    private static class Node implements Comparable<Node> {
        /**
         * ctor for Leafs
         */
        public Node(String token, int weight){
            this.Token = token;
            this.Weight = weight;
            this.IsLeaf = true;
        }

        /**
         * ctor for inner node (or root)
         */
        public Node(Node left, Node right){
            this.Left = left;
            this.Left.Binary = 0;
            this.Right = right;
            this.Right.Binary = 1;
            left.Parent = this;
            right.Parent = this;
            this.Weight = left.Weight + right.Weight;
            this.IsLeaf = false;
        }

        public int Binary = -1;
        public int Weight;
        public String Token;
        public Node Left;
        public Node Right;
        public Node Parent;
        public boolean IsLeaf;
        @Override
        public int compareTo(Node o) {
            return ((Integer)this.Weight).compareTo(o.Weight);
        }
        public boolean isRoot(){
            return this.Parent == null;
        }
    }

    /**
     * Ist für das Gewichten der Characters verantwortlich
     */
    private static class Tokens {
        private HashMap<String, Integer> data = new HashMap<String, Integer>();
        public void addToken(String t){
            int value = 0;
            if(this.data.containsKey(t)){
                value = this.data.get(t);
            }
            this.data.put(t, value + 1);
        }
        public PriorityQueue<Node> getLeafs(){
            List<Node> leafsList = new ArrayList<Node>();
            PriorityQueue<Node> result = new PriorityQueue<Node>();
            Iterator it = this.data.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String, Integer> pair = (Map.Entry)it.next();
                result.add(new Node(pair.getKey(),pair.getValue()));

            }
            return result;
        }
    }

}
