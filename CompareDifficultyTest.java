package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompareDifficultyTest {
  private Problem hard;
  private Problem easy;

  @Test
  public void testCompare() {
    assertEquals(- 1, new CompareDifficulty().compare(hard, easy));
    assertEquals(1, new CompareDifficulty().compare(easy, hard));
    assertEquals(0, new CompareDifficulty().compare(hard, hard));
    assertEquals(0, new CompareDifficulty().compare(easy, easy));
  }

  @BeforeEach
  public void init() {
    this.hard = new Problem("Who is the weakest character in the Seven?",
        "The Deep", Difficulty.HARD);
    this.easy = new Problem("What color is the sky?", "blue",
        Difficulty.EASY);
  }
}