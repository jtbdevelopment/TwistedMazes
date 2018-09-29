package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

/**
 * Date: 8/28/18 Time: 7:03 PM
 */
public class SidewinderTest extends AbstractGraphical2DMazeTest {

  private Sidewinder sidewinder = new Sidewinder();

  @Override
  protected String getFileName() {
    return "sidewinder";
  }

  @Override
  protected Generator2DMaze getGenerator() {
    return sidewinder;
  }

}
