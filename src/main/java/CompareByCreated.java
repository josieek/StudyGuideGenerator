import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;


/**
 *  Comparator class for two paths to compare by creation
 */
public class CompareByCreated implements Comparator<Path> {

  /**
   *
   * @param p1 the first path to be compared.
   * @param p2 the second path to be compared.
   * @return int - the result of comparing the comparison dates
   */
  public int compare(Path p1, Path p2) {
    try {
      return Files.readAttributes(p1, BasicFileAttributes.class).creationTime().compareTo(
          Files.readAttributes(p2, BasicFileAttributes.class).creationTime());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
