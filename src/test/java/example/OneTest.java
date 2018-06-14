package example;

import static org.junit.Assert.*;

import ThreadQuickSort.CutList;
import ThreadQuickSort.FileOperation;
import ThreadQuickSort.MergeLists;
import ThreadQuickSort.MyCallable;
import ThreadQuickSort.QuickSort;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;


public class OneTest {

  @Before
  public void beforeFoo() {
    File file = new File("out.txt");
    if (file.exists()) {
      file.delete();
    }


  }

  private List<String> readInFile() {
    File file = new File("in.txt");
    FileInputStream is;
    List<String> list = new ArrayList<String>();
    try {
      if (file.length() != 0) {
        is = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        while ((line = reader.readLine()) != null) {
          list.add(line);
        }
        reader.close();
        is.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  @Test
  public void testFoo() {
    One one = new One();
    one.foo();
    List<String> list = readInFile();
    assertEquals("10", list.get(0));
  }

  /**
   * 测试文件的读取和写出
   */
  @Test
  public void testwrite() {
    FileOperation fo = new FileOperation();
    List<String> list = fo.readFile();
    System.out.println(list);
    String[] array = list.toArray(new String[list.size()]);
    System.out.println("将集合转换成数组并输出");
    for (String s : array) {
      System.out.println(s);
    }
    QuickSort qsort = new QuickSort();
    System.out.println("快速排序数组并输出");
    qsort.sort(array, 0, list.size() - 1);
    for (String s : array) {
      System.out.println(s);
    }
    System.out.println("将排序后的数组转换成集合并输出");
    List<String> list2 = Arrays.asList(array);
    System.out.println(list2);
    fo.writeFile(list2);
  }


  /**
   * 将一个list均分成n个list
   */
  @Test
  public void test2() {
    CutList cutList = new CutList();
    FileOperation fo = new FileOperation();
    List<String> list = fo.readFile();
    List<List<String>> lists = cutList.averageAssign(list, 3);
    for (List<String> list1 : lists) {
      System.out.println(list1);
    }
  }

  /**
   * 调用callable多线程排序返回有序数组
   */
  @Test
  public void test3() {
    CutList tsort = new CutList();
    FileOperation fo = new FileOperation();
    List<String> list = fo.readFile();
    List<List<String>> lists = tsort.averageAssign(list, 3);
    Map<Integer, String[]> map = new HashMap();
    MyCallable mycall;
    for (int i = 0; i < lists.size(); i++) {
      String[] strings = lists.get(i).toArray(new String[lists.get(i).size()]);
      mycall = new MyCallable(strings);
      try {
        String[] call = mycall.call();
      } catch (Exception e) {
        e.printStackTrace();
      }
      map.put(i, strings);
    }
    for (Map.Entry<Integer, String[]> entry : map.entrySet()) {
      String[] strings = entry.getValue();
      for (String s : strings) {
        System.out.println(s);
      }
      System.out.println("================");
    }
  }

  /**
   * 测试归并
   */
  @Test
  public void test4() {
    CutList tsort = new CutList();
    FileOperation fo = new FileOperation();
    List<String> list = fo.readFile();
    List<List<String>> lists = tsort.averageAssign(list, 3);
    Map<Integer, String[]> map = new HashMap();
    List<List<String>> lists2 = new ArrayList<List<String>>();
    List<String> strings1 = new ArrayList<String>();
    MergeLists ml = new MergeLists();
    MyCallable mycall;
    for (int i = 0; i < lists.size(); i++) {
      String[] strings = lists.get(i).toArray(new String[lists.get(i).size()]);
      mycall = new MyCallable(strings);
      try {
        String[] call = mycall.call();
        strings1 = Arrays.asList(call);
      } catch (Exception e) {
        e.printStackTrace();
      }
      lists2.add(strings1);
    }
    List<String> strings = ml.mergeNSort(lists2);
    System.out.println(strings.toArray());

  }
}