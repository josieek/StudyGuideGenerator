package cs3500.pa01;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Function class which takes a Path and outputs a string representing all problems in the file
 */
public class MarkdownToProblems implements Function<Path, String> {
  private String problems;

  /**
   * Constructor which initializes the string of problems to an empty string
   * Also initializes a new string builder
   */
  MarkdownToProblems() {
    this.problems = "";
  }

  /**
   *
   * @param path the path of the markdown file
   * @return String representation of all problems in the file
   */
  public String apply(Path path) {
    Scanner filereader;
    try {
      filereader = new Scanner(path.toFile());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    while (filereader.hasNext()) {
      String line = filereader.nextLine();
      if (line.contains("[[") && line.contains(":::")) {
        this.problems = this.problems.concat(this.formatQuestion(line));
      }
    }
    return this.problems;
  }

  /**
   * @param string the current line
   *               appends all questions contained in the line to the string builder
   */
  private String formatQuestion(String string) {
    String restOfLine = string;
    String problems = "";
    while (restOfLine.contains("[[")) {
      problems = problems.concat(restOfLine.substring(restOfLine.indexOf("[[") + 2,
          restOfLine.indexOf(":::")) + "\n");
      restOfLine = restOfLine.substring(restOfLine.indexOf(":::"));

      problems = problems.concat(restOfLine.substring(restOfLine.indexOf(":::") + 3,
          restOfLine.indexOf("]]")) + "\n");
      restOfLine = restOfLine.substring(restOfLine.indexOf("]]") + 2);
      problems = problems.concat("HARD\n" + "\n");
    }
    return problems;
  }
}