import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccMarkdownsTest {
  @Test
  public void testVisitFileFailed() throws IOException {
    ArrayList<Path> empty = new ArrayList<Path>();
    AccMarkdowns accMarkdowns = new AccMarkdowns(empty);
    Files.walkFileTree(Path.of("src/test/resources/SampleFiles"), accMarkdowns);
    Assertions.assertDoesNotThrow(() -> accMarkdowns.visitFileFailed(Path.of("src/SampleFiles"),
        new IOException()));
    ArrayList<Path> unfiltered = new ArrayList<Path>();
    unfiltered.add(Path.of("src/test/resources/UnfilteredExamples/Vought.md"));
    ArrayList<Path> empty2 = new ArrayList<Path>();
    AccMarkdowns pa2Test = new AccMarkdowns(empty2);
    Files.walkFileTree(Path.of("src/test/resources/UnfilteredExamples/"), pa2Test);
    assertEquals(unfiltered, empty2);
  }

}