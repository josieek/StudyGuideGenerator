package cs3500.pa01;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A Function class which converts the given markdown path to a string
 */
public class MarkdownToGuide implements Function<Path, String> {
  String summary;

  MarkdownToGuide() {
    this.summary = "";
  }

  /**
   * @param path to markdown file
   * @return String summary of important info contained within the markdown file
   */
  public String apply(Path path) {
    Scanner filereader;
    try {
      filereader = new Scanner(path.toFile());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    String context = "";
    if (filereader.hasNext()) {
      String firstLine = filereader.nextLine();
      if (this.isHeader(firstLine)) {
        this.summary = this.summary.concat(firstLine + "\n");
      }
    }
    while (filereader.hasNext()) {
      String line = filereader.nextLine();
      if (this.isHeader(line)) {
        this.summary = this.summary.concat((this.extractInfo(context)));
        context = "";
        this.summary = this.summary.concat("\n" + line + "\n");
      } else {
        context = context.concat(line);
      }
    }
    this.summary = this.summary.concat((this.extractInfo(context)));
    return this.summary;
  }


  /**
   * @param str the String between headings (or after last heading)
   * @return A string representing the important info contained within the given string,
   *         formatted correctly with "-" and newline characters
   */
  private String extractInfo(String str) {
    String extracted = "";
    while (str.contains("[[")) {
      if (!str.substring(str.indexOf("[[") + 2, str.indexOf("]]")).contains(":::")) {
        extracted =
            extracted.concat("- " + str.substring(str.indexOf("[[") + 2,
                str.indexOf("]]")) + "\n");
      }
      str = str.substring(str.indexOf("]]") + 1);
    }
    return extracted;
  }

  /**
   * @param str the line which may or may not be a header
   * @return boolean if the line is a header
   */
  public boolean isHeader(String str) {
    return str.startsWith("# ") || str.startsWith("## ") || str.startsWith("### ")
        || str.startsWith("#### ");
  }



}
