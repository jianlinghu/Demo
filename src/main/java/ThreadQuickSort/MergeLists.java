package ThreadQuickSort;

import java.util.ArrayList;
import java.util.List;

public class MergeLists {

  public List<String> mergeNSort(List<List<String>> lists) {
    List<String> result = lists.get(0);

    for (int i = 1; i < lists.size(); i++) {
      result = mergeTwoSort(result, lists.get(i));
    }

    for (String x : result) {
      System.out.print(x + " ");
    }
    return result;
  }

  private List<String> mergeTwoSort(List<String> list1, List<String> list2) {
    int p1 = 0, p2 = 0;
    List<String> list3 = new ArrayList<String>();

    while (p1 < list1.size() && p2 < list2.size()) {
      if (list1.get(p1).compareTo(list2.get(p2)) < 0) {
        list3.add(list1.get(p1++));
      } else {
        list3.add(list2.get(p2++));
      }
    }

    while (p1 < list1.size()) {
      list3.add(list1.get(p1++));
    }

    while (p2 < list2.size()) {
      list3.add(list2.get(p2++));
    }
    return list3;
  }
}
