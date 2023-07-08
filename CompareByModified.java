package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;


/**
 * Comparator class for two paths. Compares by modified dtate
 */
public class CompareByModified implements Comparator<Path>  {
  /**
   *
   * @param p1 the first path to be compared.
   * @param p2 the second path to be compared.
   * @return int the result of comparing the paths by the date of last modified
   */
  public int compare(Path p1, Path p2) {
    try {
      return Files.getLastModifiedTime(p2).compareTo(Files.getLastModifiedTime(p1));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}