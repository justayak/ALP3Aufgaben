package ab10.aufgabe1;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 10.01.13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public class Node<V extends Comparable<V>> implements Comparable<Node<V>> {

    public Node(V value){
        this.value = value;
    }

    private V value;

    public void value(V v){
        this.value = v;
    }

    public V value(){
        return this.value;
    }

    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o==this) return true;
        if(o.getClass() == this.getClass()){
            Node<V> other = (Node<V>)o;
            if (other.value() == this.value())return true;
        }
        return false;
    }

    @Override
    public int compareTo(Node<V> o) {
        return this.value.compareTo(o.value());
    }
}
