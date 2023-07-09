import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * A FileVisitor class which accumulates a list of all markdown files in the given path
 */
public class AccMarkdowns implements FileVisitor<Path> {
  private ArrayList<Path> markdowns;

  /**
   * initializes the arraylist of markdown paths.
   * initializes the boolean hasBeenVisited to false, because none of the methods in this
   *             class have been called yet
   */
  public AccMarkdowns(ArrayList<Path> markdowns) {
    this.markdowns = markdowns;
  }

  /**
   * called everytime the file system walker encounters a file
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return directive on how to process current item's siblings and children.
   *         In this case, we continue.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (file.toString().endsWith(".md")) {
      this.markdowns.add(file);
    }
    return CONTINUE;
  }

  /**
   * What to do after processing all items in a directory
   *
   * @param dir  a reference to the directory
   * @param exec {@code null} if the iteration of the directory completes without
   *             an error; otherwise the I/O exception that caused the iteration
   *             of the directory to complete prematurely
   * @return in all cases, continue processing the sibling and child items of current item
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    System.out.format("Finishing Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * What to do at the beginning of processing a directory
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return a directive to continue processing siblings and children of current item.
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) throws IOException {
    System.out.format("Starting Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * called when a file cannot be visited for some undetermined reason (perhaps
   * locked by another process or a access permissions issue)
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return continue
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println(exc);
    return CONTINUE;
  }
}