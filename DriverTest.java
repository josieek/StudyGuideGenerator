package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverTest {

  private String guideString;
  private String problemString;
  private String pathFrom;
  private String flag;
  private String pathTo;
  private String[] args;
  private Driver driver;
  private String[] argsError;
  private String[] twoArgs;
  private String friendBirthdays;
  private String familyBirthdays;
  private Path toFamily;
  private Path toFriends;



  @BeforeEach
  public void init() throws IOException, InterruptedException {
    guideString = "## Dad\n"
        + "- Chocolate angel food cake\n"
        + "- peace and quiet\n"
        + "\n"
        + "# Mom\n"
        + "- Carvel brand oreo ice cream cake\n\n"
        + "# Rae\n"
        + "- Does not like desserts";


    problemString = "When is Dad's birthday? \n April 6th\nHARD\n\n"
        + "When is Mom's birthday? \n October 7th\nHARD\n\n"
        + "When is Rae's birthday? \n June 21st\nHARD\n\n"
        + "When is Sam's birthday? \n January 25th\nHARD";

    this.familyBirthdays = "## Dad\n- [[When is Dad's birthday? ::: April 6th]]\n "
        + "- [[Chocolate angel food cake]] Start early - a lengthy recipe!\n"
        + "- All he ever asks for is [[peace and quiet]]\n\n"
        + "# Mom\n - [[When is Mom's birthday? ::: October 7th]]\n"
        + "-Buy her a [[Carvel brand oreo ice cream cake]]";

    this.friendBirthdays = "# Rae\n- [[Does not like desserts]]\n"
        + "- [[When is Rae's birthday? ::: June 21st]]\n"
        + " - [[When is Sam's birthday? ::: January 25th]]";

    Path pathRoot = Files.createTempDirectory("Birthdays");
    this.toFamily = Files.createTempFile(pathRoot, "Family", ".md");
    Thread.sleep(1000);
    new WriteGuide().write(this.toFamily, this.familyBirthdays);
    this.toFriends = Files.createTempFile(pathRoot, "Friends", ".md");
    new WriteGuide().write(this.toFriends, this.friendBirthdays);


    Path outputDir = Files.createTempDirectory("Output");
    Path outputmd = Files.createTempFile(outputDir, "BirthdaySummary", ".md");

    this.pathFrom = pathRoot.toString();
    this.pathTo = outputmd.toString();
    this.flag = "filename";
    this.args = new String[3];
    this.args[0] = this.pathFrom;
    this.args[1] = this.flag;
    this.args[2] = this.pathTo;
    this.driver = new Driver();

    this.argsError = new String[3];
    this.argsError[0] = this.pathFrom;
    this.argsError[1] = this.flag;
    this.argsError[2] = "src/SampleFiles/BirthdayOutputs/BirthdaySummary.sr";

    this.twoArgs = new String[2];
    this.twoArgs[0] = this.pathFrom;
    this.twoArgs[1] = this.flag;
  }

  @Test
  public void testMain() throws IOException {

    driver.main(args);

    try {
      assertEquals(guideString, Files.readString(Path.of(this.pathTo)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(problemString, Files.readString(Path.of(
          this.pathTo.substring(0, pathTo.length() - 2) + "sr")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertThrows(IllegalArgumentException.class, () -> driver.main(argsError));
    assertThrows(IllegalArgumentException.class, () -> driver.main(twoArgs));
  }

}