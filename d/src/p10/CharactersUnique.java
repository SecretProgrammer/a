/**
 * Список знаков-букв и их количества.
 */
package p10;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

class CharactersUnique {

  final static String FILE_NAME_IN = "a_belyaev.txt";
  final static String FILE_NAME_OUT = "src/p10/result.txt";
  final static Charset CHARSET = StandardCharsets.UTF_16;
  final static char A_RUS = 0x410;
  final static char TAB = '\t';

  static PrintWriter printWriter;

  public static void main(String[] par) throws Exception {

    final int[] counts = new int[0x10000];  // индекс массива - код знака
                                            // значение элемента массива - количество этого знака в тексте
    FileInputStream f = new FileInputStream(FILE_NAME_IN);
    InputStreamReader r = new InputStreamReader(f, CHARSET);
    printWriter = new PrintWriter(FILE_NAME_OUT, CHARSET.name());

    while (true) {
      int i = r.read();
      if (i == -1) {
        break;
      }
      counts[Character.toLowerCase(i)]++;
    }

    int totalCharacters = 0;
    int letters = 0;
    for (int i = 0; i <= 0xffff; i++) {
      int count = counts[i];
      if (count != 0) {
        totalCharacters += count;
        if (i >= 'A' && i < A_RUS) {
          letters += count; // только знаки-буквы (и цифры)
        }
        char c = (char) i;
        String s;
        switch(c) {
          case '\u0009': s = "HT"; break;
          case '\r': s = "CR"; break;
          case '\n': s = "LF"; break;
          case ' ': s = "SP"; break;
          default: s = "" + c;
        }
/*      То же, что switch, но более громоздко
        s = "" + c;
        if (c == '\u0009') {
          s = "HT";
        }
        if (i == '\r') {
          s = "CR";
        }
        if (i == '\n') {
          s = "LF";
        }
        if (i == ' ') {
          s = "SP";
        }
*/
        String h = "0x" + Integer.toHexString(i);
        print(s +  ' '  + h +  ' '  + count);
      }
    }

    print("Letters " + ' ' + letters);
    print("Characters " +  ' '  + totalCharacters);
    print("File length " +  ' '  + (totalCharacters * 2 + 2));
    print("Lines " +  ' '  + counts['\n']);
    print("Lines " +  ' '  + counts['\r']);
    printWriter.close();
    f.close();
  }

  private static void print (String s) {
    printWriter.println(s);
    System.out.println(s);
  }
}
