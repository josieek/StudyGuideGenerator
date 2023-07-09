import java.util.Comparator;

/**
 * Class which implements the Comparator interface
 * Compares two Problems by difficulty
 */
public class CompareDifficulty implements Comparator<Problem> {
  /**
   * @param p1 the first Problem to be compared.
   * @param p2 the second Problem to be compared.
   * @return -1 if p1 is difficult and p2 is easy, 1 if p1 is easy and p2 is difficult,
   *          0 if p1 and p2 are the same difficulty
   */
  public int compare(Problem p1, Problem p2) {
    if (p1.getDifficulty() == Difficulty.HARD && p2.getDifficulty() == Difficulty.EASY) {
      return -1;
    } else if (p1.getDifficulty() == Difficulty.EASY && p2.getDifficulty() == Difficulty.HARD) {
      return 1;
    } else {
      return 0;
    }
  }
}
