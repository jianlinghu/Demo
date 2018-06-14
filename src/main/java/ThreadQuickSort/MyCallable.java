package ThreadQuickSort;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {

  public  String[] arr;
  QuickSort quickSort = new QuickSort();

  public MyCallable(String[] arr) {
    this.arr = arr;
  }

  public String[] call() throws Exception {
    String[] sort = quickSort.sort(arr, 0, arr.length-1);
    return sort;
  }
}
