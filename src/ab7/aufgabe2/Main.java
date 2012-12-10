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



    /**
     * start
     * @param args
     */
    public static void main(String[] args){
        String pattern = "and";
        try {
            String text = loadText("To_Paris_And_Prison");
            long start = System.nanoTime();
            Pattern[] patterns = stringMatching(text, pattern);
            long end = System.nanoTime();
            long elapsedNanos = end - start;
            System.out.println("-- Pattern: " + pattern + "\toccurred: " + patterns.length + "X");
            System.out.println("-- Pattern Size: " + pattern.length() + " Characters\tText Size: " +
                    text.length() + " Characters\tnanos: " + elapsedNanos + "\tmillis: " + elapsedNanos/1000000 );
//            System.out.println("-- positions: ");
//            for(Pattern p : patterns){
//                System.out.println("Pos: " + p.Position + "\t Surrounded by:" + p.Neighbors);
//            }
        } catch (IOException e) {
            System.out.println("fuck!" + e.getMessage());
        }

    }

    /**
     * Führt bf-string matching durch
     * @param text
     * @param pattern
     * @return
     */
    public static Pattern[] stringMatching(String text, String pattern){
        List<Pattern> patterns = new ArrayList<Pattern>();
        int patternPointer = 0;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == pattern.charAt(patternPointer)){
                patternPointer++;
                if (patternPointer == pattern.length()){
                    // match!
                    int position = i - pattern.length() +1;
                    String surrounding = getSurroundingText(text,pattern,position);
                    patterns.add(new Pattern(position,surrounding));
                    patternPointer = 0;
                }
            }else {
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
