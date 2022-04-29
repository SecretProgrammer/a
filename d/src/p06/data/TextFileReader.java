package p06.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class TextFileReader {

  public static void main(String[] par) throws Exception {

    String f = "data/f.txt";
    Class<TextFileReader> c = TextFileReader.class;
    InputStream inputStream = c.getResourceAsStream(f);
    BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      System.out.println(line);
    }
  }
}
