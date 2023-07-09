import java.nio.file.Path;
import java.util.Comparator;

/**
 * Comparator class that compares paths by file name
 */
public class CompareByName implements Comparator<Path> {
  /**
   *
   * @param p1 the first path to be compared.
   * @param p2 the second path to be compared.
   * @return int the result of comparing the two paths by file name
   */
  public int compare(Path p1, Path p2) {
    return p1.getFileName().toString().compareTo(p2.getFileName().toString());
  }

}
