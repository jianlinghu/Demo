package ThreadQuickSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取和写出
 */
public class FileOperation {

  /**
   * 文件读取方法
   */
  public List<String> readFile() {
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

  /**
   * 文件写出方法
   */
  public void writeFile(List<String> arr) {
    File file = new File("out.txt");
    FileOutputStream fos;
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      if (arr.size() != 0) {
        fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        for (String st : arr) {
          bw.write(st + "\n");
        }
        bw.flush();
        bw.close();
        fos.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

