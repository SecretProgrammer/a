/**
 * Количество всех слов.
 */

package p11;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

class WordCount {

    final static String FILE_NAME_IN = "a_belyaev.txt";
//  final static String FILE_NAME_IN = "src/p11/f.txt";
  final static Charset CHARSET = StandardCharsets.UTF_16;

  public static void main(String[] par) throws Exception {

    FileInputStream f = new FileInputStream(FILE_NAME_IN);
    InputStreamReader r = new InputStreamReader(f, CHARSET);

    boolean inWord = false;
    int wordCount = 0;
    while (true) {
      int i = r.read();
      if (i == -1) {
        if (inWord) {
          wordCount++;
        }
        break;
      }
      char c = Character.toLowerCase((char) i);
      if (isWordSeparator(c)) {
        if (inWord) {
          wordCount++;
        }
        inWord = false;
      } else {
        inWord = true;
      }
    }
    System.out.println("words " + wordCount);
  }

  static boolean isWordSeparator(char c) {
    return c == '.' || c == ',' || c == ':' || c == ';' || c == '-' || c == '(' || c == ')'
        || c == '?' || c == '!' || Character.isWhitespace(c);
  }
}