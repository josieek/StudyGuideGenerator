import java.util.Objects;

/**
 * Represents a single Problem
 */
public class Problem {
  private String question;
  private String answer;
  private Difficulty difficulty;

  Problem(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  public String getQuestion() {
    return this.question;
  }

  public String getAnswer() {
    return this.answer;
  }

  public void changeDifficulty() {
    if (this.difficulty == Difficulty.HARD) {
      this.difficulty = Difficulty.EASY;
    } else {
      this.difficulty = Difficulty.HARD;
    }
  }

  @Override
  /**
   * true if all fields are equal
   */
  public boolean equals(Object that) {
    if (! (that instanceof Problem)) {
      return false;
    } else {
      Problem other = (Problem) that;
      return this.question.equals(other.question) && this.answer.equals(other.answer)
          && this.difficulty == other.difficulty;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.question) + Objects.hash(this.answer)
        + Objects.hash(this.difficulty);
  }

  /**
   * @return question, answer, and difficulty all seperated by a new line,
   *         difficulty is followed by two new lines
   */
  @Override
  public String toString() {
    return this.question + "\n" + this.answer + "\n" + this.difficulty + "\n\n";
  }



}
