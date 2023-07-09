import static java.awt.event.KeyEvent.VK_ENTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionControllerTest {
  String inputString;
  Reader inputReader;
  String outputString;
  Appendable output;
  Path computerPath;
  String enter;

  private Problem hard1;
  private Problem hard2;
  private Problem easy1;
  private Problem easy2;
  private ArrayList<Problem> ungroupedQuestions;
  private ProblemSet computerSet;
  private ArrayList<Problem> groupedProblems;

  @BeforeEach
  public void init() throws IOException {
    enter = KeyEvent.getKeyText(VK_ENTER);
    this.inputString = "3" + enter + "1" + enter + "1" + enter + "1" + enter + "2" + enter;
    this.inputReader = new StringReader(inputString);
    this.output = new StringBuilder();
    this.outputString = "How many problems would you like to practice?\n"
        + "During which war was the differential analyzer invented?\n"
        + "1. Show Answer 2. Exit\n"
        + "WW1\n"
        + "1. Change difficulty 2. Exit\n"
        + "Who created the first automatic calculator?\n"
        + "1. Show Answer 2. Exit\n"
        + "Charles Babbage\n"
        + "1. Change difficulty 2. Exit\n"
        + "You studied 2 questions\n"
        + "1 question set to hard\n"
        + "1 question changed set to easy\n"
        + "Current counts in question bank:\n"
        + "1 hard questions\n"
        + "2 easy questions";

    hard1 = new Problem("Who created the first automatic calculator?",
        "Charles Babbage",
        Difficulty.HARD);
    hard2 = new Problem("During which war was the differential analyzer invented?",
        "WW1", Difficulty.HARD);
    easy1 = new Problem("How do you navigate to GitHub?", "Github.com",
        Difficulty.EASY);
    easy2 = new Problem("Who founded Microsoft", "Bill Gates",
        Difficulty.EASY);
    this.ungroupedQuestions = new ArrayList<Problem>();
    ungroupedQuestions.add(easy1);
    ungroupedQuestions.add(hard1);
    ungroupedQuestions.add(easy2);
    ungroupedQuestions.add(hard2);
    this.computerPath = Path.of("src/test/resources/OutputExamples/ComputerPath.sr");
    this.computerSet = new ProblemSet(this.computerPath, new Random(1));

    this.groupedProblems = new ArrayList<Problem>();
    this.groupedProblems.add(hard1);
    this.groupedProblems.add(hard2);
    this.groupedProblems.add(easy1);
    this.groupedProblems.add(easy2);
  }

  @Test
  public void testControlInput() throws IOException {
    String input = "src/test/resources/OutputExamples/ComputerPath.sr\n"
        + "4\n3\n1\n3\n";
    StringReader stringReader = new StringReader(input);
    SessionController computerController =
        new SessionController(stringReader, output, new Random(1));
    String expectedOutput = "Enter the path to an SR file question list\n"
        + "How many problems would you like to practice?\n"
        + "Question: Who created the first automatic calculator?\n"
        + "Difficulty: HARD\n"
        + "1. Show Answer 2. Exit\n"
        + "Enter either 1 or 2\n"
        + "Charles Babbage\n"
        + "1. Change difficulty 2. Next question 3. Exit\n"
        + "You answered 1 questions.\n"
        + "0 problems set to hard.\n"
        + "0 problems set to easy.\n"
        + "Current counts in question bank:\n"
        + "2 hard questions\n"
        + "2 easy questions";
    computerController.controlInput();
    assertEquals(expectedOutput, this.output.toString());

    String zeroInput = "src/test/resources/OutputExamples/ComputerPath.sr\n"
        + "0\n";
    StringReader stringReaderZero = new StringReader(zeroInput);
    StringBuilder zeroOutput = new StringBuilder();
    SessionController computerControllerZero =
        new SessionController(stringReaderZero, zeroOutput, new Random(1));
    String expectedOutputZero = "Enter the path to an SR file question list\n"
        + "How many problems would you like to practice?\n"
        + "You answered 0 questions.\n"
        + "0 problems set to hard.\n"
        + "0 problems set to easy.\n"
        + "Current counts in question bank:\n"
        + "2 hard questions\n"
        + "2 easy questions";
    computerControllerZero.controlInput();
    assertEquals(expectedOutputZero, zeroOutput.toString());

    String inputExit = "src/test/resources/OutputExamples/ComputerPath.sr\n"
        + "4\n2\n";
    StringReader stringReaderExit = new StringReader(inputExit);
    StringBuilder exitOutput = new StringBuilder();
    SessionController computerControllerExit =
        new SessionController(stringReaderExit, exitOutput, new Random(1));
    String expectedOutputExit = "Enter the path to an SR file question list\n"
        + "How many problems would you like to practice?\n"
        + "Question: Who created the first automatic calculator?\n"
        + "Difficulty: HARD\n"
        + "1. Show Answer 2. Exit\n"
        + "You answered 0 questions.\n"
        + "0 problems set to hard.\n"
        + "0 problems set to easy.\n"
        + "Current counts in question bank:\n"
        + "2 hard questions\n"
        + "2 easy questions";
    computerControllerExit.controlInput();
    assertEquals(expectedOutputExit, exitOutput.toString());

    String inputInvalid = "src/test/resources/OutputExamples/ComputerPath.md\n"
        + "src/test/resources/OutputExamples/ComputerPath.sr\n"
        + "4\n1\n4\n3\n";
    StringReader stringReaderInvalid = new StringReader(inputInvalid);
    StringBuilder outputInvalid = new StringBuilder();
    SessionController computerControllerInvalid =
        new SessionController(stringReaderInvalid, outputInvalid, new Random(1));
    String expectedOutputInvalid = "Enter the path to an SR file question list\n"
        + "Path must be to an SR file\n"
        + "How many problems would you like to practice?\n"
        + "Question: Who created the first automatic calculator?\n"
        + "Difficulty: HARD\n"
        + "1. Show Answer 2. Exit\n"
        + "Charles Babbage\n"
        + "1. Change difficulty 2. Next question 3. Exit\n"
        + "Enter 1, 2, or 3\n"
        + "You answered 1 questions.\n"
        + "0 problems set to hard.\n"
        + "0 problems set to easy.\n"
        + "Current counts in question bank:\n"
        + "2 hard questions\n"
        + "2 easy questions";
    computerControllerInvalid.controlInput();
    assertEquals(expectedOutputInvalid, outputInvalid.toString());


  }


}