import java.io.IOException;
import java.nio.file.Path;

/**
 * Class which driver creates when given three arguments
 */
public class CreateGuideProblems {

  /**
   * @param pathFrom , the file from which to extract a study guide and list of questions
   * @param flag , the way in which the study guide should be sorted
   * @param pathTo , the path where the study guide should be written to
   *
   * @throws IOException if a file cannot be written to
   */
  public void createGuideProblems(String pathFrom, String flag, String pathTo) throws IOException {
    Path toSummarize = Path.of(pathFrom);

    StudyGuide sg = new StudyGuide();
    sg.addFromFile(toSummarize);

    sg.sort(flag);

    if (pathTo.endsWith(".md")) {
      new WriteGuide().write(Path.of(pathTo), sg.toString());
      String questions = new AllMarkdownProblems().getProblems(Path.of(pathFrom));
      String pathString = pathTo.substring(0, pathTo.length() - 2) + "sr";
      Path tosr = Path.of(pathString);
      new WriteGuide().write(tosr, questions);
    } else {
      throw new IllegalArgumentException("output file must be a .md");
    }
  }
}
