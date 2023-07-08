package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemSetTest {
  private ArrayList<Problem> voughtQuestions;
  private Problem problem1;
  private Problem problem2;
  private Problem problem3;
  private String problemsString;
  private ProblemSet voughtSet;
  private Path voughtPath;

  private Problem hard1;
  private Problem hard2;
  private Problem easy1;
  private Problem easy2;
  private ArrayList<Problem> ungroupedQuestions;
  private ArrayList<Problem> groupedQuestions;
  private ProblemSet computerSet;
  private Path computerPath;
  private ProblemSet birthdaySet;
  private ArrayList<Problem> birthdayProblems;
  private ArrayList<Problem> birthdayProblemsSorted;
  private Problem momBirthday;
  private Problem dadBirthday;
  private Problem ceceBirthday;


  @BeforeEach
  public void init() {
    this.voughtQuestions = new ArrayList<Problem>();
    this.problem1 = new Problem("What is the name of the Vought superheros? ",
        "The Seven", Difficulty.EASY);
    this.problem2 = new Problem("Who is the face of the Seven? ",
        "Homelander", Difficulty.HARD);
    this.problem3 = new Problem("What is Homelander's real name? ",
        "John", Difficulty.HARD);
    this.voughtQuestions = new ArrayList<Problem>();
    this.voughtQuestions.add(problem1);
    this.voughtQuestions.add(problem3);
    this.voughtQuestions.add(problem2);

    problemsString =  "Who is the face of the Seven? \n"
        + "Homelander\n"
        + "HARD\n\n"
        + "What is Homelander's real name? \n"
        + "John\n"
        + "HARD\n\n"
        + "What is the name of the Vought superheros? \n"
        + "The Seven\n"
        + "HARD\n";
    this.voughtPath = Path.of("src/SampleFiles/OutputExamples/VoughtQuestionsTest.sr");
    this.voughtSet = new ProblemSet(this.voughtPath, new Random(1));

    hard1 = new Problem("Who created the first automatic calculator?",
        "Charles Babbage",
        Difficulty.HARD);
    hard2 = new Problem("During which war was the differential analyzer invented?",
        "WW1", Difficulty.HARD);
    easy1 = new Problem("How do you navigate to Github?", "GitHub.com",
        Difficulty.EASY);
    easy2 = new Problem("Who founded Microsoft", "Bill Gates",
        Difficulty.EASY);
    this.ungroupedQuestions = new ArrayList<Problem>();
    ungroupedQuestions.add(easy1);
    ungroupedQuestions.add(hard1);
    ungroupedQuestions.add(easy2);
    ungroupedQuestions.add(hard2);
    this.computerPath = Path.of("src/SampleFiles/OutputExamples/ComputerPath.sr");
    this.computerSet = new ProblemSet(this.computerPath, new Random(1));

    this.groupedQuestions = new ArrayList<Problem>();
    groupedQuestions.add(hard2);
    groupedQuestions.add(hard1);
    groupedQuestions.add(easy1);

    momBirthday = new Problem("When is Mom's birthday?",
        "October 7th",
        Difficulty.HARD);
    dadBirthday = new Problem("When is Dad's birthday?",
        "April 6th", Difficulty.EASY);
    ceceBirthday = new Problem("When is Cece's birthday?", "October 28th",
        Difficulty.HARD);
    this.birthdayProblems = new ArrayList<Problem>();
    birthdayProblems.add(momBirthday);
    birthdayProblems.add(dadBirthday);
    birthdayProblems.add(ceceBirthday);
    this.birthdaySet = new ProblemSet(
        Path.of("src/SampleFiles/BirthdayOutputs/BirthdaySummaryShort.sr"),
        new Random(1));
    this.birthdayProblemsSorted = new ArrayList<Problem>();
    birthdayProblemsSorted.add(momBirthday);
    birthdayProblemsSorted.add(ceceBirthday);
    birthdayProblemsSorted.add(dadBirthday);

  }

  @Test
  public void testToString() {
    assertEquals(problemsString, this.voughtSet.toString());
  }

  @Test
  public void testGetHard() {
    assertEquals(3, this.voughtSet.getHard());
  }

  @Test
  public void testGetEasy() {
    assertEquals(2,  this.computerSet.getEasy());
  }

  @Test
  public void testWrite() throws IOException {
    this.voughtSet.writeToPath(this.voughtPath);
    try {
      assertEquals(this.problemsString,
          Files.readString(this.voughtPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGetAllProblems() {
    assertEquals(this.birthdayProblemsSorted, this.birthdaySet.getAllProblems());
  }


  @Test
  public void testChangeToEasy() {
    assertEquals(0, this.voughtSet.getEasy());
    assertEquals(3, this.voughtSet.getHard());
    this.voughtSet.changeToEasy(0);
    assertEquals(1, this.voughtSet.getEasy());
    assertEquals(2, this.voughtSet.getHard());
  }

  @Test
  public void testChangeToHard() {
    assertEquals(2, this.computerSet.getEasy());
    assertEquals(2, this.computerSet.getHard());
    this.computerSet.changeToHard(1);
    assertEquals(1, this.computerSet.getEasy());
    assertEquals(3, this.computerSet.getHard());
  }



}