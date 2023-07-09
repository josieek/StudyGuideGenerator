import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Creates a string representations of all problems contained within a path
 */
public class AllMarkdownProblems {
  ArrayList<Path> markdowns;

  public AllMarkdownProblems() {
    this.markdowns = new ArrayList<Path>();
  }

  /**
   * @param root path which contains all markdown files to be visited
   * @return a string representation of all problems contained within all the markdown files
   * @throws IOException if the file cannot be walked
   */
  public String getProblems(Path root) throws IOException {
    AccMarkdowns accMarkdowns = new AccMarkdowns(this.markdowns);
    Files.walkFileTree(root, accMarkdowns);
    String problems = "";
    for (Path markdown : this.markdowns) {
      problems = problems.concat(new MarkdownToProblems().apply(markdown));
    }
    return problems.substring(0, problems.length() - 2);
  }
}
