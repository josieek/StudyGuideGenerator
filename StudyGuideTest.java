package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudyGuideTest {
  Path sampleFiles;
  Path arrayPath;
  Path vectorPath;
  Path summaryFile;
  Path output;
  StudyGuide sg;
  private Path apath;
  private Path bpath;
  private Path cpath;
  private ArrayList<Path> pathsToSort;
  private StudyGuide unsorted;

  @BeforeEach
  void init() throws IOException, InterruptedException {
    sampleFiles = Files.createTempDirectory("SampleFiles");
    arrayPath = Files.createTempFile(sampleFiles,  "arrays", ".md");
    output = Files.createTempDirectory("output");
    summaryFile = Files.createTempFile(output,  "Summary", ".md");

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
    vectorPath = Files.createTempFile(sampleFiles,  "vectors", ".md");
    String vector = "# Vectors\n"
        + "- [[Vectors act like resizable arrays]].\n"
        + "\n"
        + "## Declaring a vector\n"
        + "- [[General Form: Vector<type> v = new Vector();]]\n"
        + "- Example: Vector<Integer> v = new Vector();\n"
        + "\n"
        + "- [[type needs to be a valid reference type]]\n"
        + "\n"
        + "#### Adding an element to a vector\n"
        + "- [[v.add(object of type);]]";
    byte[] vectorBytes = vector.getBytes();
    Files.write(vectorPath, vectorBytes);

    ArrayList<Path> paths = new ArrayList<Path>();
    paths.add(arrayPath);
    paths.add(vectorPath);
    sg = new StudyGuide(paths);

    this.sampleFiles = Files.createTempDirectory("SampleFiles");
    this.apath = Files.createTempFile(sampleFiles,  "aFile", ".md");
    Thread.sleep(1000);
    this.bpath = Files.createTempFile(sampleFiles,  "bFile", ".md");
    Thread.sleep(1000);
    this.cpath = Files.createTempFile(sampleFiles,  "cFile", ".md");
    pathsToSort = new ArrayList<Path>();
    pathsToSort.add(bpath);
    pathsToSort.add(apath);
    pathsToSort.add(cpath);
    this.unsorted = new StudyGuide(pathsToSort);

  }


  @Test
  public void testToString() {
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
    String vectorSummary = "\n# Vectors\n"
        + "- Vectors act like resizable arrays\n"
        + "\n## Declaring a vector\n"
        + "- General Form: Vector<type> v = new Vector();\n"
        + "- type needs to be a valid reference type\n"
        + "\n#### Adding an element to a vector\n"
        + "- v.add(object of type);";
    String summaries = "";
    summaries = summaries.concat(arraySummary);
    summaries = summaries.concat(vectorSummary);

    assertEquals(summaries, sg.toString());

  }

  @Test
  public void testSortThrows() {
    assertThrows(IllegalArgumentException.class, () -> sg.sort("invalid"));
  }

  @Test
  public void testSort() {
    assertEquals(this.pathsToSort, this.unsorted.getMarkdowns());
    this.unsorted.sort("created");
    ArrayList<Path> byCreated = new ArrayList<Path>();
    byCreated.add(this.apath);
    byCreated.add(this.bpath);
    byCreated.add(this.cpath);
    assertEquals(byCreated, this.unsorted.getMarkdowns());
    this.unsorted.sort("modified");
    ArrayList<Path> byModified = new ArrayList<Path>();
    byModified.add(this.cpath);
    byModified.add(this.bpath);
    byModified.add(this.apath);
    assertEquals(byModified, this.unsorted.getMarkdowns());
  }


}