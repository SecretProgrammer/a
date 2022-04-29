package p08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RawMap {

  public static void main(String[] par) {

    m1();
//    m2();
  }

  static void m1() {

    String LF = "\n";
    Object key1 = new Object();
    Object key2 = new Object();
    Object key3 = key2;
    final Map map = new HashMap();

    Object value = map.get(key1);
    print("1 value=" + value);
    print("size=" + map.size() + LF);

    Object previousValue = map.put(key1, value);
    print("2 key1 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    value = new Object();
    previousValue = map.put(key1, value);
    print("3 key1 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    previousValue = map.put(key1, value);
    print("4 key1 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    previousValue = map.put(key2, new Object());
    print("5 key2 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    value = map.get(key3);
    print("6 key3 value=" + value + LF);

    previousValue = map.put(key3, new Object());
    print("7 key3 previousValue=" + previousValue);
    print("size=" + map.size() + LF);

    value = map.get(key2);
    print("8 key2 value=" + value + LF);

    previousValue = map.remove(key2);
    print("9 key2 previousValue=" + previousValue);
    print("size=" + map.size() + LF);
  }

  static void m2() {

    Object o1 = new Object();
    Object o2 = new Object();
//    o1 = "A";
//    o2 = "B";
    List list = new ArrayList();
    list.add(o1);
    list.add(o2);
    list.add(o2);
//  list = Arrays.asList(01, 02, 02);
    print(list.size()); // 3
    final Map map = new HashMap();
    for (Object key : list) {
      Object value = map.get(key);
      if (value == null) {
        map.put(key, 1);
      } else {
        int newValue = (Integer) value + 1;
        map.put(key, newValue);
      }
    }
    for (Object key : map.keySet()) {
      print(key + "=" + map.get(key));
    }
    print(map);
  }

  static void print(Object o) {
    System.out.println(o);
  }
}