package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The controller which handles user input and the current statistics of this study session
 */
public class SessionController {

  private Path toGuide;
  private ArrayList<Problem> allProblems;
  int curIndex;
  int changedToEasy;
  int changedToHard;
  private ProblemSet problemSet;
  private int maxProblems;
  private final Appendable appender;
  private final Scanner sc;

  boolean isPracticing;
  Random random;


  /**
   * @param input which the program reads from
   * @param appender which the program append output to
   */

  SessionController(Readable input, Appendable appender) {
    this.toGuide = toGuide;
    this.curIndex = 0;
    this.changedToEasy = 0;
    this.changedToHard = 0;
    this.appender = appender;
    this.sc = new Scanner(input);
    this.allProblems = new ArrayList<Problem>();
    this.isPracticing = true;
    this.random = new Random();
  }

  SessionController(Readable input, Appendable appender, Random random) {
    this.toGuide = toGuide;
    this.curIndex = 0;
    this.changedToEasy = 0;
    this.changedToHard = 0;
    this.appender = appender;
    this.sc = new Scanner(input);
    this.allProblems = new ArrayList<Problem>();
    this.isPracticing = true;
    this.random = random;
  }

  /**
   * Generates a list of Problems and creates a ProblemSet from the input file path
   *
   * @throws IOException if file is not found
   */
  private void startSession() throws IOException {
    this.appender.append("Enter the path to an SR file question list\n");
    String input = sc.nextLine();
    while (!input.endsWith(".sr")) {
      this.appender.append("Path must be to an SR file\n");
      input = sc.nextLine();
    }
    this.toGuide = Path.of(input);
    this.allProblems = new SrToAlProblem().apply(toGuide);
    this.problemSet = new ProblemSet(this.toGuide, this.random);
    this.allProblems = this.problemSet.getAllProblems();
  }

  /**
   * Sets the max amount of problems the user can practice, based on what they input
   *
   * @throws IOException if reader can't read
   */
  private void setMaxProblems() throws IOException {
    this.appender.append("How many problems would you like to practice?\n");
    int inputProblems = sc.nextInt();
    if (inputProblems < this.allProblems.size()) {
      this.maxProblems = inputProblems;
    } else {
      this.maxProblems = this.allProblems.size();
    }

  }

  /**
   * Deals with processing user input during this study session
   * Gets the amount of problems they want to study
   * Deals with what they want to do for each problem
   * Ends session when over
   *
   * @throws IOException when writing the updated file to the path
   */
  public void controlInput() throws IOException {
    this.startSession();
    this.setMaxProblems();
    this.cycleProblems();
    this.endGame();
  }

  /**
   * Asks the user if they want to see an answer or exit
   * Then asks if they want to change difficulty, see next question, or exit
   * Runs the program accordingly
   *
   * @throws IOException if reader can't read input
   */
  private void cycleProblems() throws IOException {
    while (this.curIndex < this.maxProblems && this.isPracticing) {
      this.showAnswerOrExit();
      if (this.isPracticing) {
        this.appender.append("1. Change difficulty 2. Next question 3. Exit\n");
        int input2 = sc.nextInt();
        while (!(input2 == 1 || input2 == 2 || input2 == 3)) {
          this.appender.append("Enter 1, 2, or 3\n");
          input2 = sc.nextInt();
        }
        if (input2 == 1) {
          this.changeDifficulty();
        } else if (input2 == 3) {
          isPracticing = false;
        }
        this.curIndex += 1;
      }
    }
  }


  /**
   *
   * @throws IOException if the reader can't read
   */
  private void showAnswerOrExit() throws IOException {
    this.appender.append("Question: " + this.allProblems.get(this.curIndex).getQuestion()
        + "\nDifficulty: "
        + this.allProblems.get(this.curIndex).getDifficulty()
        + "\n");
    this.appender.append("1. Show Answer 2. Exit\n");
    int input = sc.nextInt();
    while (!(input == 1 || input == 2)) {
      this.appender.append("Enter either 1 or 2\n");
      input = sc.nextInt();
    }
    if (input == 1) {
      this.appender.append(this.allProblems.get(this.curIndex).getAnswer() + "\n");
    } else  {
      this.isPracticing = false;
    }
  }


  /**
   * Displays the final stats of the study session and writes the updated problem list to the path
   *
   * @throws IOException when writing to the path
   */
  private void endGame() throws IOException {
    appender.append("You answered " + this.curIndex + " questions.\n");
    appender.append(this.changedToHard + " problems set to hard.\n");
    appender.append(this.changedToEasy + " problems set to easy.\n");
    appender.append("Current counts in question bank:\n");
    appender.append(this.problemSet.getHard()
        + " hard questions\n");
    appender.append(this.problemSet.getEasy()
        + " easy questions");
    this.problemSet.writeToPath(this.toGuide);
  }

  /**
   * Changes the difficulty of the Problem at the current index
   * Also changes the count for problems changed to easy or problems changed to hard
   */
  private void changeDifficulty() {
    if (this.allProblems.get(curIndex).getDifficulty() == Difficulty.HARD) {
      this.changedToEasy += 1;
      this.problemSet.changeToEasy(this.curIndex);
    } else {
      this.changedToHard += 1;
      this.problemSet.changeToHard(this.curIndex);
    }
  }

}
