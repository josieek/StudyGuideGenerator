package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * File writer class
 */
public class WriteGuide {

  /**
   * @param info the string which will be written to the file
   */
  public void write(Path path, String info) throws IOException {

    // Convert String to data for writing ("raw" byte data)
    byte[] data = info.getBytes();

    // The path may not exist, or we may not have permissions to write to it,
    // in which case we need to handle that error (hence try-catch)
    // Built-in convenience method for writing data to a file.
    // Markdown is really just plain text with some
    // special syntax, so you can add `.md` to the file-path to write a Markdown file.
    Files.write(path, data);
  }
}
