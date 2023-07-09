import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MarkdownToGuideTest {

  private Path arrayPath;
  private Path sampleData;
  private String birthdays;
  private Path toBirthdays;

  @BeforeEach
  public void init() throws IOException {
    sampleData = Files.createTempDirectory("SampleData");
    arrayPath = Files.createTempFile(sampleData,  "arrays", ".md");

    String array = "# Java Arrays\n"
        + "- [[An **array** is a collection of variables of the same type]], referred to\n"
        + "  by a common name.\n"
        + "- In Java, arrays are objects, and must be created dynamically (at runtime).\n"
        + "\n"
        + "## Declaring an Array\n"
        + "- [[General Form: type[] arrayName;]]\n"
        + "- ex: int[] myData;\n"
        + "\n"
        + "- The above [[only creates a reference]] to an array object, but "
        + "[[no array has actually been created yet]].\n"
        + "\n"
        + "## Creating an Array (Instantiation)\n"
        + "- [[General form:  arrayName = new type[numberOfElements];]]\n"
        + "- ex: myData = new int[100];\n"
        + "\n"
        + "- Data types of the reference and array need to match.\n"
        + "- [[numberOfElements must be a positive Integer.]]\n"
        + "- [[Gotcha: Array size is not modifiable once instantiated.]]";
    byte[] arrayBytes = array.getBytes();
    Files.write(arrayPath, arrayBytes);

    this.birthdays = "## Dad\n- Chocolate angel food cake\n- peace and quiet\n\n"
        + "# Mom\n- Carvel brand oreo ice cream cake\n\n"
        + "### Cece\n- Youtube history\n";

    this.toBirthdays = Path.of("src/test/resources/Birthdays/Family.md");

  }

  @Test
  public void testApply() {
    String arraySummary = "# Java Arrays\n"
        + "- An **array** is a collection of variables of the same type\n"
        + "\n## Declaring an Array\n"
        + "- General Form: type[] arrayName;\n"
        + "- only creates a reference\n"
        + "- no array has actually been created yet\n"
        + "\n## Creating an Array (Instantiation)\n"
        + "- General form:  arrayName = new type[numberOfElements];\n"
        + "- numberOfElements must be a positive Integer.\n"
        + "- Gotcha: Array size is not modifiable once instantiated.\n";

    assertEquals(arraySummary,
        new MarkdownToGuide().apply(arrayPath));

    assertEquals(birthdays, new MarkdownToGuide().apply(toBirthdays));

    assertEquals("", new MarkdownToGuide().apply(
        Path.of("src/test/resources/EmptyFile.md")));

    assertEquals("\n# Rae\n- Does not like desserts\n", new MarkdownToGuide().apply(
        Path.of("src/test/resources/Birthdays/Fundies.md")));

    assertThrows(RuntimeException.class,
        () -> new MarkdownToGuide().apply(Path.of("invalid")));
  }

  @Test
  public void testIsHeader() {
    MarkdownToGuide mtg = new MarkdownToGuide();
    assertEquals(true, mtg.isHeader("# Header 1"));
    assertEquals(true, mtg.isHeader("## Header 2"));
    assertEquals(true, mtg.isHeader("### Header 3"));
    assertEquals(true, mtg.isHeader("#### Header 4"));
    assertEquals(false, mtg.isHeader("Header 4"));
  }




}