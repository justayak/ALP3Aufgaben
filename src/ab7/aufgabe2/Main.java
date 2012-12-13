package ab7.aufgabe2;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 10.12.12
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    static int NBR_OF_COMPARES = 0;

    /*
    (b)
    Analyse:

-- Pattern: Pr	occurred: 180X	Number of Compares: 1227247	NoC - |T| : 638.0
-- Pattern Size: 2 Characters	Text Size: 1226609 Characters	nanos: 14401127	millis: 14
Average comparisons per found text: 3.5444446

-- Pattern: Pro	occurred: 113X	Number of Compares: 1227380	NoC - |T| : 771.0
-- Pattern Size: 3 Characters	Text Size: 1226609 Characters	nanos: 13643718	millis: 13
Average comparisons per found text: 6.823009

-- Pattern: Prof	occurred: 2X	Number of Compares: 1227712	NoC - |T| : 1103.0
-- Pattern Size: 4 Characters	Text Size: 1226609 Characters	nanos: 11524136	millis: 11
Average comparisons per found text: 551.5

-- Pattern: Profe	occurred: 2X	Number of Compares: 1227711	NoC - |T| : 1102.0
-- Pattern Size: 5 Characters	Text Size: 1226609 Characters	nanos: 11659642	millis: 11
Average comparisons per found text: 551.0

-- Pattern: Profes	occurred: 2X	Number of Compares: 1227710	NoC - |T| : 1101.0
-- Pattern Size: 6 Characters	Text Size: 1226609 Characters	nanos: 11723029	millis: 11
Average comparisons per found text: 550.5

-- Pattern: Profess	occurred: 2X	Number of Compares: 1227709	NoC - |T| : 1100.0
-- Pattern Size: 7 Characters	Text Size: 1226609 Characters	nanos: 11779947	millis: 11
Average comparisons per found text: 550.0

-- Pattern: Professo	occurred: 2X	Number of Compares: 1227708	NoC - |T| : 1099.0
-- Pattern Size: 8 Characters	Text Size: 1226609 Characters	nanos: 14225196	millis: 14
Average comparisons per found text: 549.5

-- Pattern: Professor	occurred: 2X	Number of Compares: 1227707	NoC - |T| : 1098.0
-- Pattern Size: 9 Characters	Text Size: 1226609 Characters	nanos: 12118227	millis: 12
Average comparisons per found text: 549.0

Was kann festgestellt werden?
* Da die Zeichen nicht Gleichverteilt sind, kommt es zu Beginn der Suche zu starken Schwankungen da u.u
* sehr viele Treffer ("Pr" -> 180 Treffer, "Pro" -> 113 Treffer) erzielt werden.
* Nachdem P jedoch hinreichend weit eingeschränkt ist, sodass immer nur die selben Stellen getroffen
* werden (ab "Prof") kommt es zu sehr vielen Fehltreffern (was die Hohe Zahl an Versuchen erklärt, bis
* endlich ein Match gefunden wird).
* Ab diesem Zeitpunkt ist auffällig, dass die Anzahl der Vergleiche wieder abnimmt, je größer das
* Pattern wird!
*
* Fazit:
* Aus (a) war nicht zu erwarten, dass die Anzahl der Vergleiche ab einem Gewissen Punkt wieder abnimmt,
* obwohl das Pattern P größer wird.
*

Weiteres Testergebnis:

-- Pattern: General	occurred: 7X	Number of Compares: 1227064	NoC - |T| : 455.0
-- Pattern Size: 7 Characters	Text Size: 1226609 Characters	nanos: 23685052	millis: 23
Average comparisons per found text: 65.0

-- Pattern: Genera	occurred: 7X	Number of Compares: 1227065	NoC - |T| : 456.0
-- Pattern Size: 6 Characters	Text Size: 1226609 Characters	nanos: 17297200	millis: 17
Average comparisons per found text: 65.14286

-- Pattern: Gener	occurred: 7X	Number of Compares: 1227066	NoC - |T| : 457.0
-- Pattern Size: 5 Characters	Text Size: 1226609 Characters	nanos: 17805266	millis: 17
Average comparisons per found text: 65.28571

-- Pattern: Gene	occurred: 16X	Number of Compares: 1227031	NoC - |T| : 422.0
-- Pattern Size: 4 Characters	Text Size: 1226609 Characters	nanos: 19622789	millis: 19
Average comparisons per found text: 26.375

-- Pattern: Gen	occurred: 20X	Number of Compares: 1227020	NoC - |T| : 411.0
-- Pattern Size: 3 Characters	Text Size: 1226609 Characters	nanos: 17727002	millis: 17
Average comparisons per found text: 20.55

-- Pattern: Ge	occurred: 42X	Number of Compares: 1226977	NoC - |T| : 368.0
-- Pattern Size: 2 Characters	Text Size: 1226609 Characters	nanos: 17706305	millis: 17
Average comparisons per found text: 8.761905
     */


    /**
     * start
     * @param args
     */
    public static void main(String[] args){
        String pattern = "Ge";
        try {
            String text = loadText("To_Paris_And_Prison");
            long start = System.nanoTime();
            Pattern[] patterns = stringMatching(text, pattern);
            long end = System.nanoTime();
            long elapsedNanos = end - start;
            float noc_dT =(NBR_OF_COMPARES - text.length());
            System.out.println("-- Pattern: " + pattern + "\toccurred: " + patterns.length + "X\tNumber of Compares: " + NBR_OF_COMPARES +
            "\tNoC - |T| : " + noc_dT);
            System.out.println("-- Pattern Size: " + pattern.length() + " Characters\tText Size: " +
                    text.length() + " Characters\tnanos: " + elapsedNanos + "\tmillis: " + elapsedNanos/1000000 );
            System.out.println("Average comparisons per found text: " + (noc_dT /patterns.length) );
//            System.out.println("-- positions: ");
//            for(Pattern p : patterns){
//                System.out.println("Pos: " + p.Position + "\t Surrounded by:" + p.Neighbors);
//            }
        } catch (IOException e) {
            System.out.println("fuck!" + e.getMessage());
        }

    }

    static boolean jumpedBack = false;

    /**
     * Führt bf-string matching durch
     * @param text
     * @param pattern
     * @return
     */
    public static Pattern[] stringMatching(String text, String pattern){
        List<Pattern> patterns = new ArrayList<Pattern>();
        int patternPointer = 0;
        int i = 0;
        while (i < text.length()){
            NBR_OF_COMPARES++;
            if (text.charAt(i) == pattern.charAt(patternPointer)){
                patternPointer++;
                i++;
                if (patternPointer == pattern.length()){
                    // match!
                    int position = i - pattern.length() + 1;
                    String surrounding = getSurroundingText(text,pattern,position);
                    patterns.add(new Pattern(position,surrounding));
                    patternPointer = 0;
                }
            }else {
                i = i - patternPointer + 1;
                patternPointer = 0;
                // wenn die länge nicht mehr ausreicht, breche hier ab:
                if (text.length() - i < pattern.length()){
                    break;
                }
            }
        }
        return patterns.toArray(new Pattern[0]);
    }


    /************************************************************************************
     *
     ************************************************************************************
     *
     ************************************************************************************/

    private static String getSurroundingText(String fullText, String pattern, int position){
        final int EXTEND = 7;
        int begin = position - EXTEND >= 0 ? position - EXTEND : 0;
        int end = position + pattern.length() + EXTEND >= fullText.length() ? fullText.length() -1 : position + pattern.length() + EXTEND;
        String result = "..." + fullText.substring(begin,end) + "...";
        return result;
    }

    private static class Pattern{
        public int Position;
        public String Neighbors;
        public Pattern(int position, String neighbors){
            this.Position = position;
            this.Neighbors = neighbors;
        }
    }

    /**
     * loads the title text file
     * @param title
     * @return
     * @throws IOException
     */
    private static String loadText(String title) throws IOException {
        String path = System.getProperty("user.dir") + "/res/" + title;
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally {
            stream.close();
        }
    }



}
