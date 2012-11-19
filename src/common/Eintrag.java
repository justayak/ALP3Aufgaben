package common;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 14.11.12
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
public class Eintrag<S,W> {

    private S s;
    private W w;

    public Eintrag(S s, W w){
        this.s = s;
        this.w = w;
    }

    public S getS(){return this.s;}
    public W getW(){return this.w;}

    @Override
    public String toString(){
        return "{S:" + this.getS() + ",W:" + this.getW() + "}";
    }
}
