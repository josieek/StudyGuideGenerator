package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompareByCreatedTest {

  private Path apath;
  private Path bpath;
  private Path sampleFiles;

  @Test
  public void testCompare() {
    assertEquals(-1, new CompareByCreated().compare(apath, bpath));
    assertEquals(1, new CompareByCreated().compare(bpath, apath));
    assertThrows(RuntimeException.class, () -> new CompareByCreated().compare(
        Path.of("invalid"), bpath));
  }

  @BeforeEach
  void init() throws IOException, InterruptedException {
    sampleFiles = Files.createTempDirectory("SampleFiles");
    apath = Files.createTempFile(sampleFiles,  "aFile", ".md");
    Thread.sleep(1000);
    bpath = Files.createTempFile(sampleFiles,  "bFile", ".md");
  }
}