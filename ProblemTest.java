package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemTest {
  private Problem hard;
  private Problem hardDupe;
  private Problem easy;
  ArrayList<Problem> problems;
  private String hardString;
  private String easyString;

  @BeforeEach
  public void init() {
    this.hard = new Problem("Who is the weakest character in the Seven?",
        "The Deep", Difficulty.HARD);
    this.hardDupe = new Problem("Who is the weakest character in the Seven?",
        "The Deep", Difficulty.HARD);
    this.easy = new Problem("What color is the sky?", "blue",
        Difficulty.EASY);
    this.problems = new ArrayList<Problem>();
    this.problems.add(hard);
    this.problems.add(easy);
    this.hardString = "Who is the weakest character in the Seven?\n"
        + "The Deep\n"
        + "HARD\n\n";
    this.easyString = "What color is the sky?\n" + "blue\n" + "EASY\n\n";
  }

  @Test
  public void testChangeDifficulty() {
    this.hard.changeDifficulty();
    assertEquals(Difficulty.EASY, this.hard.getDifficulty());
    this.easy.changeDifficulty();
    assertEquals(Difficulty.HARD, this.easy.getDifficulty());
  }

  @Test
  public void testEquals() {
    assertFalse(this.hard.equals(this.problems));
    assertTrue(this.hard.equals(this.hard));
    assertTrue(this.hard.equals(this.hardDupe));
    assertFalse(this.hard.equals(this.easy));
    Problem hard2 = new Problem("Who is the weakest character in the Seven?",
        "Black Noir", Difficulty.HARD);
    assertFalse(this.hard.equals(hard2));
    Problem hard3 = new Problem("Who is the most recently cancelled member of the Seven?",
        "The Deep", Difficulty.HARD);
    assertFalse(this.hard.equals(hard3));
    Problem hard4 = new Problem("Who is the weakest character in the Seven?",
        "The Deep", Difficulty.EASY);
    assertFalse(this.hard.equals(hard4));
  }

  @Test
  public void testHashCode() {
    assertFalse(this.hard.hashCode() == this.problems.hashCode());
    assertTrue(this.hard.hashCode() == this.hard.hashCode());
    assertTrue(this.hard.hashCode() == this.hardDupe.hashCode());
    assertFalse(this.hard.hashCode() == this.easy.hashCode());
  }

  @Test
  public void testtoString() {
    assertEquals(hardString, this.hard.toString());
    assertEquals(easyString, this.easy.toString());
  }
}