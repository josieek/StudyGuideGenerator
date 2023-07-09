import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MarkdownToProblemsTest {

  @Test
  public void testApply() {
    Path example = Path.of("src/test/resources/UnfilteredExamples/Vought.md");
    String problems = "What is the name of the Vought superheros? \n"
        + " The Seven\n"
        + "HARD\n\n"
        + "Who is the face of the Seven? \n"
        + " Homelander\n"
        + "HARD\n\n"
        + "What is Homelander's real name? \n"
        + " John\n"
        + "HARD\n\n";
    Assertions.assertEquals(problems, new MarkdownToProblems().apply(example));
    assertThrows(RuntimeException.class,
        () -> new MarkdownToProblems().apply(Path.of("fake path")));
  }

}