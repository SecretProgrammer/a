package p05;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class TextFileReader {

  static final int EOF = -1;

  public static void main(String[] par) throws Exception {

    String f = "data/f.txt";
    Class<TextFileReader> c = TextFileReader.class;
    InputStream inputStream = c.getResourceAsStream(f);
//    f = "f.txt";
//    inputStream = c.getClassLoader().getResourceAsStream(f);
    Reader r = new InputStreamReader(inputStream);
    while (true) {
      int i = r.read();
      print(i);
      if (i == EOF) {
        break;
      }
    }
  }

  static void print(int i) {
    char c = (char) i;
    String h = Integer.toHexString(i);
    String b = Integer.toBinaryString(i);
    System.out.println(c + "\t" + i + "\t" + h + "\t" + b);
  }
}
