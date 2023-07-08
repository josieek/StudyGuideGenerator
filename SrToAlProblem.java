package cs3500.pa01;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * SR path to ArrayList of Problems
 * Takes a path to an SR file and creates instances for all Problems contained in the file,
 * outputting a list of all those problems
 */
public class SrToAlProblem implements Function<Path, ArrayList<Problem>> {

  private ArrayList<Problem> problems;

  SrToAlProblem() {
    this.problems = new ArrayList<Problem>();
  }

  /**
   *
   * @param sr the path to an sr file
   * @return ArrayList of Problems, a list of all Problems in the file
   */
  public ArrayList<Problem> apply(Path sr) {
    Scanner filereader;
    try {
      filereader = new Scanner(sr.toFile());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    while (filereader.hasNext()) {
      String line = filereader.nextLine();
      if (line.length() > 0) {
        String question = line;
        String answer = filereader.nextLine();
        String difficulty = filereader.nextLine();
        Problem problem = new Problem(question, answer, Difficulty.valueOf(difficulty));
        this.problems.add(problem);
      }
    }
    return this.problems;
  }
}
