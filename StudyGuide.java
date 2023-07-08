package cs3500.pa01;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * A Class representing the desired StudyGuide from a path
 */
public class StudyGuide {

  ArrayList<Path> markdowns;

  /**
   * Initializes the arraylist of paths to markdown files
   */
  StudyGuide() {
    this.markdowns = new ArrayList<Path>();
  }

  /**
   * Default constructor used for testing
   *
   * @param markdowns the list of markdowns to sort and convert into a string
   */
  StudyGuide(ArrayList<Path> markdowns) {
    this.markdowns = markdowns;
  }

  /**
   * @param root path to the root directory file, which all markdown files must be extracted from
   */
  public void addFromFile(Path root) throws IOException {
    AccMarkdowns accMarkdowns = new AccMarkdowns(this.markdowns);
    Files.walkFileTree(root, accMarkdowns);
  }

  /**
   * @param sortBy comparator to sort the list of markdown paths by
   */
  public void sort(String sortBy) {
    if (sortBy.equals("filename")) {
      this.markdowns.sort(new CompareByName());
    } else if (sortBy.equals("created")) {
      this.markdowns.sort(new CompareByCreated());
    } else if (sortBy.equals("modified")) {
      this.markdowns.sort(new CompareByModified());
    } else {
      throw new IllegalArgumentException("not a valid flag");
    }
  }

  /**
   * @return a string representation of the important data contained within
   *          all of the markdown paths
   */
  public String toString() {
    String summaries = "";
    for (Path p : this.markdowns) {
      summaries = summaries.concat((new MarkdownToGuide().apply(p) + "\n"));
    }
    return summaries.substring(0, summaries.length() - 2);
  }

  public ArrayList<Path> getMarkdowns() {
    return this.markdowns;
  }




}
