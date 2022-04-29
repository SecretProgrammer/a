package p09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParamMap {

  public static void main(String[] par) {

//    m1();
    m2();
  }

  static void m1() {

    String LF = "\n";
    String key1 = "A";
    String key2 = "B";
    String key3 = key2;
    final Map<String, Integer> map = new HashMap();

    Integer value = map.get(key1);
    print("1 value=" + value);
    print("size=" + map.size() + LF);

    Integer previousValue = map.put(key1, value);
    print("2 key1 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    value = 1;
    previousValue = map.put(key1, value);
    print("3 key1 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    previousValue = map.put(key1, value);
    print("4 key1 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    previousValue = map.put(key2, 2);
    print("5 key2 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    value = map.get(key3);
    previousValue = map.put(key3, 3);
    print("6 key3 value=" + value + LF);

    print("7 key3 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    value = map.get(key2);
    print("8 key2 value=" + value + LF);

    previousValue = map.remove(key2);
    print("9 key2 previousValue=" + previousValue);
    print("size=" + map.size() + LF);
  }

  static void m2() {

    String o1 = "A";
    String o2 = "B";
    List<String> list = new ArrayList();
    list.add(o1);
    list.add(o2);
    list.add(o2);
//  list = Arrays.asList(01, 02, 02);
    print(list.size()); // 3
    final Map<String, Integer> map = new HashMap();
    for (String key : list) {
      Integer value = map.get(key);
      if (value == null) {
        map.put(key, 1);
      } else {
        int newValue = value + 1;
        map.put(key, newValue);
      }
    }
    for (String key : map.keySet()) {
      print(key + " = " + map.get(key));
    }
    print(map);
  }

  static void print(Object o) {
    System.out.println(o);
  }
}