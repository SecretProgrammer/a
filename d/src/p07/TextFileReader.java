package p07;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TextFileReader {

  final static String FILE_NAME = "a_belyaev.txt";
  final static Map<Character, Integer> MAP = new HashMap<>();
  final static int EOF = -1;

  public static void main(String[] par) throws Exception {

    FileInputStream fis = new FileInputStream(FILE_NAME);
    Reader r = new InputStreamReader(fis, StandardCharsets.UTF_16BE);
    while (true) {
      int i = r.read();
      char c = (char) i;
      if (i == EOF) {
        break;
      }
      Character cl = Character.toLowerCase(c); // на верхнем регистре кочерга и др. изменены
      Integer count = MAP.get(cl);
      if (count == null) { // можно заменить на лямбду
        MAP.put(cl, 1);
      } else {
        MAP.put(cl, count + 1);
      }
//      MAP.merge(cl, 1, Integer::sum);
    }
    List<CharCount> list = toList();
    list.sort(byName);
    print(list, "\n--- Sorted by character");
    list.sort(byCount);
    print(list, "\n--- Sorted by count");
//    printUnknown();
  }

  static List<CharCount> toList() {
    List<CharCount> result = new ArrayList<>();
    for (Character c : MAP.keySet()) {
      result.add(new CharCount(c, MAP.get(c)));
    }
    return result;
  }

  static void print(List<CharCount> list, String title) {
    System.out.println(title);
    for (CharCount cc : list) {
      char chr = cc.c;
      String s = "" + chr;
      String comment = "";
      if (chr == ' ') {
        s = "SP";
        comment = "space";
      } else if (chr == '\n') {
        s = "LF";
        comment = "line feed";
      } else if (chr == '\f') {
        s = "FF";
        comment = "form feed";
      } else if (chr == '\r') {
        s = "CR";
        comment = "carriage return";
      } else if (chr == '\u0009') {
        s = "HT";
        comment = "horizontal tabulation";
      } else if (chr == '\ufeff') {
        s = "BE";
        comment = "UTF-16BE";
      }
      String sf = String
          .format("%3s%6d%7s%6s %s", s, cc.count, "0x" + Integer.toHexString(chr), Integer.toString(chr),
              comment);
      System.out.println(sf);
    }
  }

  static void printUnknown() {
    System.out.println("\n--- Unknown \"latin\"");
    printChar('\u017f'); // кочерга
    printChar('\u0283'); // ʃ
    printChar('\u00e6'); // æ
    printChar('\u0153'); // œ
    printChar('\u03be'); // ξ
    printChar('\u025b'); // ɛ
  }

  static void printChar(char c) {
    System.out.println(c + " " + Character.toUpperCase(c));
  }

  static class CharCount {

    final char c;
    final int count;

    CharCount(char c, int count) {
      this.c = c;
      this.count = count;
    }
  }

  final static Comparator byName = new Comparator<CharCount>() { // можно заменить на лямбду
    @Override
    public int compare(CharCount o1, CharCount o2) {
      return o1.c - o2.c; // by name ascending (по возрастанию)
    }
  };

  final static Comparator byCount = new Comparator<CharCount>() { // можно заменить на лямбду
    @Override
    public int compare(CharCount o1, CharCount o2) {
      return o2.count - o1.count; // by count descending (по убыванию)
    }
  };
}


