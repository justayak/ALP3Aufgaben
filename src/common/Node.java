package common;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 24.01.13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class Node implements Comparable<Node> {

    public final int key;
    private String content;

    public Node(final int key, String content){
        this.key = key;
        this.content = content;
    }

    public void content(String content){
        this.content = content;
    }

    public String content(){
        return this.content;
    }

    @Override
    public int compareTo(final Node o) {
        return this.key - o.key;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() == o.getClass()){
            final Node other = (Node) o;
            return this.compareTo(other) == 0;
        }
        return false;
    }
}
