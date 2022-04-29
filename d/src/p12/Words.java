/**
 *  Список всех слов в порядке их появления (all).
 *  Список разных слов по алфавиту (без количества употребления каждого слова).
 */

package p12;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Words {

//  final static String FILE_NAME_IN = "a_belyaev.txt";
  final static String FILE_NAME_IN = "src/p12/f.txt";
  final static String FILE_NAME_OUT = "src/p12/result.txt";
  final static Charset CHARSET = StandardCharsets.UTF_16;
  final static boolean TEST = true;

  public static void main(String[] par) throws Exception {

    boolean all = true;
//    all = false;

    List<Word> words = new ArrayList<>();
    FileInputStream f = new FileInputStream(FILE_NAME_IN);
    InputStreamReader r = new InputStreamReader(f, CHARSET);
    PrintWriter printWriter = new PrintWriter(FILE_NAME_OUT, CHARSET.name());

    Word word = new Word("",1);
    int wordCount = 0;
    int pageCount = 0;
    int charCount = 0;
    while (true) {
      int i = r.read();
      if (i == -1) {
        if (!word.word.isEmpty()) {
//      if (!"".equals(word)) {
          words.add(word);
          wordCount++;
        }
        break;
      }
      char c = Character.toLowerCase((char) i);
      charCount++;
      if (isWordSeparator(c)) {
        if (TEST){
          System.out.println("-1-");
        }
        if (!word.word.isEmpty()) {
          if (TEST){
            System.out.println("-2-");
          }
          if (words.contains(word)){
            if (TEST){
              System.out.println("-3-");
            }
            word.count++;
          } else {
            if (TEST){
              System.out.println("adding");
            }
            words.add(word);
            word.word = "";
            wordCount++;
          }
        }
        if (c == '\f') {
          pageCount++;
        }
      } else {
        if (TEST) {
          System.out.print(" + ");  //  many "+"
        }
        word.word += c;
      }
    }
    if (!all) {
      TreeSet<Word> sortedWords = new TreeSet<>(words);
      for (Word w : sortedWords) {
        printWriter.println(w.word);
      }
    }
    if (all) {
      for (Word w : words) {
        printWriter.println(w.word);
      }
    }
    printWriter.println("words      " + wordCount);
    printWriter.println("characters " + charCount);
    printWriter.println("bytes      " + (charCount * 2 + 2));
    printWriter.println("pages      " + pageCount);
    printWriter.close();
  }

  static boolean isWordSeparator(char c) {
    return c == '.' || c == ',' || c == ':' || c == ';' || c == '-' || c == '(' || c == ')'
        || c == '?' || c == '!' || Character.isWhitespace(c);
  }

  static class Word {

    String word;
    int count;

    Word(String word, int count) {
      this.word = word;
      this.count = count;
    }
  }
}