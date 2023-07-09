import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class representing all Problems in the set
 */
public class ProblemSet {
  private ArrayList<Problem> allProblems;
  private Random random;
  private int numEasy;
  private int numHard;
  private Path tosr;

  ProblemSet(Path tosr, Random random) {
    this.allProblems = new SrToAlProblem().apply(tosr);
    this.random = random;
    int numHards = 0;
    for (int i = 0; i < this.allProblems.size(); i += 1) {
      if (this.allProblems.get(i).getDifficulty() == Difficulty.HARD) {
        numHards += 1;
      }
    }
    this.numHard = numHards;
    int numEasys = 0;
    for (int i = 0; i < this.allProblems.size(); i += 1) {
      if (this.allProblems.get(i).getDifficulty() == Difficulty.EASY) {
        numEasys += 1;
      }
    }
    this.numEasy = numEasys;
  }

  /**
   * @return the list of problems after being shuffled and then sorted by difficulty
   */
  public ArrayList<Problem> getAllProblems() {
    Collections.shuffle(this.allProblems, this.random);
    this.allProblems.sort(new CompareDifficulty());
    return this.allProblems;
  }


  /**
   * changes the current Problem's difficulty
   * and updates the total number of hard and easy questions accordingly
   *
   * @param index of the problem whose difficulty should be changed
   */
  public void changeToHard(int index) {
    this.numHard += 1;
    this.numEasy -= 1;
    this.allProblems.get(index).changeDifficulty();
  }

  /**
   * changes the current Problem's difficulty
   * and updates the total number of hard and easy questions accordingly
   *
   * @param index of the problem whose difficulty should be changed
   */
  public void changeToEasy(int index) {
    this.numHard -= 1;
    this.numEasy += 1;
    this.allProblems.get(index).changeDifficulty();
  }

  public int getHard() {
    return this.numHard;
  }

  public int getEasy() {
    return this.numEasy;
  }

  /**
   * @return all String representations of Problems in this Set, folded together
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Problem problem : this.allProblems) {
      sb.append(problem.toString());
    }
    String problemsString = sb.toString();
    return problemsString.substring(0, problemsString.length() - 1);
  }


  /**
   * writes this ProblemSet to the path
   *
   * @param tosr path to the sr file
   * @throws IOException if path not found
   */
  public void writeToPath(Path tosr) throws IOException {
    new WriteGuide().write(tosr, this.toString());
  }


}
