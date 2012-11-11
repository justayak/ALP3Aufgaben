package ab3.aufgabe1;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 11.11.12
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main (String[] args){

        Woerterbuch wb = new Woerterbuch(10);
        wb.insert(5,"hallo");
        wb.insert(2,"welt");
        wb.insert(3,"hello");
        wb.insert(1,"world");
        wb.insert(8,"12345");
        wb.insert(99995,"67890");

        System.out.println(wb.count());


    }
}
