package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

/**
 * Date: 9/21/18 Time: 6:58 PM
 */
public class RecursiveBacktrackerTest extends AbstractGraphical2DMazeWithMaskingTest {

  private RecursiveBacktracker backtracker = new RecursiveBacktracker();

  @Override
  protected String getFileName() {
    return "recursivebacktracker";
  }

  @Override
  protected Generator2DMaze getGenerator() {
    return backtracker;
  }

}
