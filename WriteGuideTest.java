package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WriteGuideTest {

  private Path summaryPath;
  private Path outputPath;

  @Test
  public void testWrite() throws IOException {
    WriteGuide wg = new WriteGuide();
    String arraySummary = "# Java Arrays\n"
        + "- An **array** is a collection of variables of the same type\n"
        + "\n## Declaring an Array\n"
        + "- An **array** is a collection of variables of the same type\n"
        + "\n## Declaring an Array\n"
        + "- General Form: type[] arrayName;\n"
        + "only creates a reference\n"
        + "- no array has actually been created yet\n"
        + "\n## Creating an Array (Instantiation)\n"
        + "- General form:  arrayName = new type[numberOfElements];\n"
        + "- only creates a reference\n"
        + "- numberOfElements must be a positive Integer.\n"
        + "- Gotcha: Array size is not \n";
    String vectorSummary = "# Vectors\n"
        + "- Vectors act like resizable arrays\n"
        + "\n## Declaring a vector\n"
        + "- General Form: Vector<type> v = new Vector();\n"
        + "- type needs to be a valid reference type\n"
        + "\n#### Adding an element to a vector\n"
        + "- v.add(object of type);\n";
    String summaries = "";
    summaries = summaries.concat(arraySummary);
    summaries = summaries.concat(vectorSummary);
    wg.write(summaryPath, summaries);
    try {
      assertEquals(summaries, Files.readString(summaryPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String problems = "Who is the face of the Seven? \n"
        + "Homelander\n"
        + "HARD\n\n"
        + "What is Homelander's real name? \n"
        + "John\n"
        + "HARD\n\n"
        + "What is the name of the Vought superheros? \n"
        + "The Seven\n"
        + "HARD\n\n";

    wg.write(outputPath, problems);
    try {
      assertEquals(problems, Files.readString(outputPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }

  @BeforeEach
  public void init() {
    summaryPath = Path.of("src/SampleFiles/Summary1.md");
    outputPath = Path.of("src/SampleFiles/OutputExamples/VoughtQuestionsTest.sr");
  }


}