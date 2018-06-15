package ThreadQuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostImportant {

  public void ThreadQsort() {
    //读取文件
    FileOperation fo = new FileOperation();
    List<String> list = fo.readFile();
    //将写入的集合拆分成n分
    CutList tsort = new CutList();
    List<List<String>> lists = tsort.averageAssign(list, 3);

    MyCallable mycall;
    List<String> sortString = new ArrayList<String>();
    List<List<String>> sortStrings = new ArrayList<List<String>>();
    //遍历，多线程处理每个集合
    for (int i = 0; i < lists.size(); i++) {
      //获取单个数组
      String[] strings = lists.get(i).toArray(new String[lists.get(i).size()]);
      mycall = new MyCallable(strings);
      try {
        String[] call = mycall.call();
        sortString = Arrays.asList(call);
      } catch (Exception e) {
        e.printStackTrace();
      }
      sortStrings.add(sortString);
    }
    //将多个数组归并排序，输出有序集合
    MergeLists ml = new MergeLists();
    List<String> sortedStrings = ml.mergeNSort(sortStrings);

    //将有序集合输出到文件
    fo.writeFile(sortedStrings);
  }
}
