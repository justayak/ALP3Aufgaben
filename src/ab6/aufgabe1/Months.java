package ab6.aufgabe1;

/**
 * Created with IntelliJ IDEA.
 * ALP III - Julian
 * <p/>
 * User: Baka
 * Date: 29.11.12
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public enum Months {
    Januar(5),
    Februar(4),
    Maerz(8),
    April(1),
    Mai(9),
    Juni(7),
    Juli(6),
    August(2),
    September(12),
    Oktober(11),
    November(10),
    Dezember(3);

    private final int rank;

    Months(int rank){
        this.rank = rank;
    }

    public int rank(){
        return this.rank;
    }
}
