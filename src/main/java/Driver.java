import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Driver class, which receives user input
 */
public class Driver {


  /**
   * @param args user input representing
   *             the path from which the summary guide should be extracted
   *             the order in which data should be formatted
   *             the path where the study guide should be stored
   *             if empty, begins the study session
   */

  public static void main(String[] args) throws IOException {

    if (args.length == 3) {
      new CreateGuideProblems().createGuideProblems(args[0], args[1], args[2]);
    } else if (args.length == 0) {
      Reader reader = new InputStreamReader(System.in);
      Appendable output = System.out;
      SessionController sessionController = new SessionController(reader,
          output);
      sessionController.controlInput();
    } else {
      throw new IllegalArgumentException("Enter either three arguments to create a study guide, "
          + "or no arguments to begin a study session");
    }
  }
}