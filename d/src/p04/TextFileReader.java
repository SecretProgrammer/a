package p04;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TextFileReader {

  private static class CharacterCounter {

    private static final int EOF = -1;
    private static final boolean TEST = false;

    private static class CharCount implements Comparable{

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
//        this.count - that.count;  // по возрастанию
      }
    }

    private final List<Character> charList = new ArrayList<>();
    private final List<CharCount> charCountList = new ArrayList<>();

    private void count() throws Exception {

      String f = "src/p04/f.txt";
//    f = "r_j.txt";
      FileReader r = new FileReader(f);
      while (true) {
        int i = r.read();
        char c = (char) i;
        if (i == EOF) {
          break;
        }
        charList.add(c);
//        charList.add(Character.toUpperCase(c));
      }
      characterCount();
      Collections.sort(charCountList);
      print();
    }

    private void characterCount() {
      for (char c : charList) {
        if (TEST) {
          System.out.println("Loop for character " + c);
        }
        boolean found = false;
        for (CharCount cc : charCountList) {
          if (c == cc.c) {
            cc.count++;
            found = true;
            break;
          }
        }
        if (TEST) {
          System.out.println("found " + found);
        }
        if (!found) {
          charCountList.add(new CharCount(c, 1));
        }
        if (TEST) {
          for (CharCount cc : charCountList) {
            System.out.println("test " + cc.c + " " + cc.count);
          }
          System.out.println();
        }
      }
    }

    private void print() {
      for (CharCount cc : charCountList) {
        System.out.println(cc.c + " " + cc.count);
      }
    }
  }

  public static void main(String[] par) throws Exception {

    CharacterCounter characterCounter = new CharacterCounter();
    characterCounter.count();
  }
}
