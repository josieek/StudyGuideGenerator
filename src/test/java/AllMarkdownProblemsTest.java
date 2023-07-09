import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllMarkdownProblemsTest {
  private String birthdays;
  private Path toBirthdays;
  private String familyBirthdays;
  private String friendBirthdays;
  private Path toFamily;
  private Path toFriends;

  @Test
  public void testGetList() throws IOException {
    Assertions.assertEquals(this.birthdays, new AllMarkdownProblems().getProblems(this.toBirthdays));
  }

  @BeforeEach
  public void init() throws IOException, InterruptedException {

    this.familyBirthdays = "[[When is Dad's birthday? ::: April 6th]]\n"
        + "[[When is Mom's birthday? ::: October 7th]]";
    this.friendBirthdays = "[[When is Rae's birthday? ::: June 21st]]\n"
        + "[[When is Sam's birthday? ::: January 25th]]";


    this.birthdays = "When is Dad's birthday? \n April 6th\nHARD\n\n"
        + "When is Mom's birthday? \n October 7th\nHARD\n\n"
        + "When is Rae's birthday? \n June 21st\nHARD\n\n"
        + "When is Sam's birthday? \n January 25th\nHARD";
    this.toBirthdays = Files.createTempDirectory("Birthdays");
    this.toFamily = Files.createTempFile(this.toBirthdays, "Family", ".md");
    Thread.sleep(1000);
    new WriteGuide().write(this.toFamily, this.familyBirthdays);
    this.toFriends = Files.createTempFile(this.toBirthdays, "Friends", ".md");
    new WriteGuide().write(this.toFriends, this.friendBirthdays);
  }
}