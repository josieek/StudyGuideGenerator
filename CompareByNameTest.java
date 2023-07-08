package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @Test
 */
class CompareByNameTest {

  private Path apath;
  private Path bpath;
  private Path sampleFiles;

  @Test
  public void testCompare() {
    assertEquals(new CompareByName().compare(apath, bpath), -1);
    assertEquals(new CompareByName().compare(bpath, apath), 1);
  }

  @BeforeEach
  void init() throws IOException {
    sampleFiles = Files.createTempDirectory("SampleFiles");
    apath = Files.createTempFile(sampleFiles,  "aFile", ".md");
    bpath = Files.createTempFile(sampleFiles,  "bFile", ".md");
  }

}