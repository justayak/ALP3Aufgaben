package common;


/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 24.01.13
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class Edge implements Comparable<Edge>{

    public final Node from;
    public final Node to;
    private float weight;

    public Edge(final Node from, final Node to, final float weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public float weight(){
        return this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight == o.weight){
            if(this.from.equals(o.from)){
                if(this.to.equals(o.to)){
                    return 0;
                }else {
                    return this.to.compareTo(o.to);
                }
            }else {
                return this.from.compareTo(o.from);
            }
        }else {
            return  (int)this.weight - (int)o.weight;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() == o.getClass()){
            final Edge other = (Edge) o;
            return this.compareTo(other) == 0;
        }
        return false;
    }
}
