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


class CompareByModifiedTest {

  private Path apath;
  private Path bpath;
  private Path sampleFiles;


  @Test
  void testCompare() throws IOException {
    this.init();
    assertEquals(1, new CompareByModified().compare(apath,
        bpath));
    assertEquals(-1, new CompareByModified().compare(bpath, apath));
    assertThrows(RuntimeException.class,
        () -> new CompareByModified().compare(Path.of("invalid"), bpath));
  }

  @BeforeEach
  void init() throws IOException {
    sampleFiles = Files.createTempDirectory("SampleFiles");
    apath = Files.createTempFile(sampleFiles,  "a", ".md");
    bpath = Files.createTempFile(sampleFiles,  "b", ".md");


    BasicFileAttributes arrayAttributes = Files.readAttributes(apath,
        BasicFileAttributes.class);
    BasicFileAttributes vectorAttributes = Files.readAttributes(bpath,
        BasicFileAttributes.class);

    long arrayModifiedTime = arrayAttributes.lastModifiedTime().toMillis();
    long vectorModifiedTime = vectorAttributes.lastModifiedTime().toMillis();
    FileTime newVectorTime = FileTime.fromMillis(arrayModifiedTime + 1000);
    Files.setLastModifiedTime(bpath, newVectorTime);

  }
}