package p031;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TextFileReader {

    private static final int EOF = -1;

    private static class CharCount implements Comparable {

        private char c;
        private int count;

        private CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
        @Override
        public int compareTo(Object o) {
            CharCount that = (CharCount) o;
            return
                that.count - this.count;  // по убыванию
//              this.count - that.count;  // по возрастанию
        }
    }

    public static void main(String[] par) throws Exception {

        List<Character> list = new ArrayList<>(10000);
        String f = "src/p03/f.txt";
            f = "r_j.txt";

        FileReader r = new FileReader(f);
        while (true) {
            int i = r.read();
            char c = (char) i;
            if (i == EOF) {
                break;
            }
            list.add(c);
        }
        List<CharCount> cc = characterCount(list);
        Collections.sort(cc);
        print(cc);
    }

    private static List<CharCount> characterCount(List<Character> list) {
        List<CharCount> result = new ArrayList<>(100);
        for (Character c : list) {
            boolean found = false;
            for (CharCount cc : result) {
                if (c == cc.c) {
                    cc.count++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.add(new CharCount(c, 1));
            }
        }
        return result;
    }

    private static void print(List<CharCount> list) {
        for (CharCount cc : list) {
            System.out.println("[ " + cc.c + " ]" + " --- " + cc.count);
        }
    }

}
