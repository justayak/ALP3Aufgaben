package ab10.aufgabe1;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 13.01.13
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class NodeNotFoundException extends InvalidArgumentException {
    public NodeNotFoundException(String[] strings) {
        super(strings);
    }
    public NodeNotFoundException(){
        super(new String[0]);
    };
}
