package exp;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

class Experiment {

        final static String FILE_NAME_IN = "a_belyaev.txt";
    //  final static String FILE_NAME_IN = "src/Exp/f.txt";
//    final static String FILE_NAME_IN = "src/Exp/test";
    final static String FILE_NAME_OUT = "src/Exp/result.txt";
    final static Charset CHARSET = StandardCharsets.UTF_16;
//    final static Charset CHARSET = StandardCharsets.UTF_8;
    final static Map<String, Integer> MAP = new HashMap<>();

    public static void main(String[] args) throws Exception {

        List<Node> list = new ArrayList<>();
        FileInputStream f = new FileInputStream(FILE_NAME_IN);
        InputStreamReader r = new InputStreamReader(f, CHARSET);
        PrintWriter printWriter = new PrintWriter(FILE_NAME_OUT, CHARSET.name());

        String word = "";
        int wordCount = 0;
        int pageCount = 0;
        int charCount = 0;
        while (true) {
            Integer count;
            int i = r.read();
            if (i == -1) {
                if (!word.isEmpty()) {
//              if (!"".equals(word)) {
                    count = MAP.get(word);
                    if (count == null) {
                        MAP.put(word, 1);
                    } else {
                        MAP.put(word, ++count);
                    }
                    wordCount++;
                }
                break;
            }
            char c = Character.toLowerCase((char) i);
            charCount++;
            if (isWordSeparator(c)) {
                if (!word.isEmpty()) {
                    count = MAP.get(word);
                    if (count == null) {
                        MAP.put(word, 1);
                        wordCount++;
                    } else {
                        MAP.put(word, ++count);
                    }
                    word = "";
                }
                if (c == '\f') {
                    pageCount++;
                }
            } else {
                word += c;
            }
        }

        final Map<Integer, Integer> map = new HashMap<>();
        for (String s : MAP.keySet()) {
//            printWriter.println(s + " " + MAP.get(s));

//            Integer count = map.get(MAP.get(s));
//            if (count == null) {
//                map.put(MAP.get(s), 1);
//            } else {
//                map.put(MAP.get(s), ++count);
//            }

            list.add(new Node(s, MAP.get(s)));
        }
        printWriter.println();

//        int iii = 0;
//        int jjj = 0;
//        for (Integer i : map.keySet()){
//            printWriter.println(i+" "+map.get(i));
//            iii += i * map.get(i);
//            if (i<10){
//                jjj+= map.get(i);
//            }
//        }

//        printWriter.println();
//        printWriter.println(iii);
//        printWriter.println(jjj);

        printWriter.println("\n" + "words      " + wordCount);
        printWriter.println("characters " + charCount);
        printWriter.println("bytes      " + (charCount * 2 + 2));
        printWriter.println("pages      " + pageCount);
        printWriter.println();

        list.sort(byCount);
        int c = 0;
        int min = 0;
        int p = 0;
        printWriter.println("\n --- минимальный словарный запас --- \n");
        for (Node n : list) {
            printWriter.println(n.word + " " + n.count);
//            c += n.count;
//            min++;
//            if (c >= wordCount / 100 * 80) {
//                break;
//            }
        }
//        printWriter.println(c);
        printWriter.println("\n" + "составляет " + min + " слов(а)");

//        int addCuant = 0;
//        for (Node n : list){
//            if (n.count == 1){
//                addCuant++;
//            }
//        }
//        printWriter.println(addCuant);

        printWriter.close();
    }

    static boolean isWordSeparator(char c) {
        return c == '.' || c == ',' || c == ':' || c == ';' || c == '-' || c == '(' || c == ')'
                || c == '?' || c == '!' || c == '…' || c == '—' || c == '«' || c == '»' ||
                c == '*' || Character.isDigit(c) || Character.isWhitespace(c);
    }

    static Comparator<Node> byCount = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.count - o1.count;
        }
    };

    static class Node {
        String word;
        int count;

        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

}
class M {
    static <P> void m(P[] a, Predicate<P> i) {
        for (P e : a) if (i.test(e)) wO(e);
    }
    public static void main(String[] par) {
        Integer[] a = {1,2,3};
        Predicate<Integer> p;
        p = v->v%2==1;
        m(a, p);                // 13
    }
    static void wO(Object o) {System.out.print(o);}
}
