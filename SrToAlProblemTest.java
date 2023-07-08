package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the SrToAlProblem
 */
public class SrToAlProblemTest {
  Problem problem1;
  Problem problem2;
  Problem problem3;
  ArrayList<Problem> problems;
  Path voughtQuestions;


  @Test
  public void testApply() {
    ArrayList<Problem> actualProblems = new SrToAlProblem().apply(this.voughtQuestions);
    assertEquals(this.problems, new SrToAlProblem().apply(this.voughtQuestions));
    assertThrows(RuntimeException.class,
        () -> new SrToAlProblem().apply(Path.of("fake path")));
  }



  /**
   * Initiliazes example Problems and ArrayLists of Problems
   */


  @BeforeEach
  public void init() {
    this.problem1 = new Problem("What is the name of the Vought superheros?",
        "The Seven", Difficulty.HARD);
    this.problem2 = new Problem("Who is the face of the Seven?",
        "Homelander", Difficulty.HARD);
    this.problem3 = new Problem("What is Homelander's real name?",
        "John", Difficulty.HARD);
    this.problems = new ArrayList<Problem>();
    this.problems.add(problem2);
    this.problems.add(problem3);
    this.problems.add(problem1);
    this.voughtQuestions =
        Path.of("src/SampleFiles/OutputExamples/VoughtQuestionsSRToALTest.sr");
  }
}