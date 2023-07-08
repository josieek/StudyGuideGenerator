package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateGuideProblemsTest {
  private String guideString;
  private String problemString;
  private String pathFrom;
  private String flag;
  private String pathTo;
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
  }

  @Test
  public void testCreateGuideProblems() throws IOException {
    CreateGuideProblems cgp = new CreateGuideProblems();

    assertThrows(IllegalArgumentException.class, () -> cgp.createGuideProblems(this.pathFrom,
        this.flag,
        "src/SampleFiles/BirthdayOutputs/BirthdaySummary.sr"));

  }
}